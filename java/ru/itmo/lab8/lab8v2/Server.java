package ru.itmo.lab8.lab8v2;




import ru.itmo.lab8.lab8v2.commands.Command;
import ru.itmo.lab8.lab8v2.commands.SaveCommand;
import ru.itmo.lab8.lab8v2.readers.OrganizationReader;
import ru.itmo.lab8.lab8v2.utils.DataProvider;
import ru.itmo.lab8.lab8v2.worker.Worker;
import ru.itmo.lab8.lab8v2.commands.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;
import ru.itmo.lab8.lab8v2.commands.LoginCommand;

//withlogback
public class Server {

    private int port = 1236;
    private String host = "localhost";

    private static final int BUF_SIZE = 16384;
    private static Logger logger = Logger.getLogger(Server.class.getName());

    private CollectionManager collectionManager = new CollectionManager();
    OrganizationReader organizationReader = new OrganizationReader();
    DataProvider dataProvider;

    public void start() throws Exception {
//        final String EnvironmentalVariable = "MY_FILE";
//        String fileName = System.getenv(EnvironmentalVariable);
//        String fileName = "LolKekMambet.xml";
//        if (fileName == null) {
//            logger.warning("Переменная окружения не найдена. Установите переменную окружения и повторите попытку");
//            System.exit(0);
//        } else {
//            try {
//                File file = new File(fileName);
//                if (!file.canRead()) {
//                    logger.warning("Ваш файл закрыт для чтения. Измените права доступа или выберите другой файл");
//                } else if (!file.canWrite()) {
//                    logger.warning("Ваш файл закрыт для записи. Измените права доступа или выберите другой файл");
//                } else {
//                    if (file.length() == 0) {
//                        TreeSet<Worker> workers = new TreeSet<>();
//                        collectionManager = new CollectionManager(file, workers);
//                    } else {
//                        TreeSet<Worker> workers = parseXML(file, organizationReader);
//                        collectionManager = new CollectionManager(file, workers);
//                    }
//                }
//
//            } catch (FileNotFoundException e) {
//                logger.warning("Файл заданный переменной окружения не найден. Добавьте файл или проверьте переменную окружения и повторите попытку");
//            } catch (ParserConfigurationException | SAXException e) {
//                logger.warning("Файл не валидный. Необходимо выбрать другой файл.");
//            } catch (IOException e) {
//                logger.warning(e.getMessage());
//            }
//        }


//        final String userNameVariable = "username";
//        final String passwordVariable = "password";
//        String username = System.getenv(userNameVariable);
//        String password = System.getenv(passwordVariable);
//        if (username == null && password == null) {
//            logger.warning("Установленны не все перемемнные окружения.\n Установите переменные окружения \"username\" и \"password\" и повторите попытку");
//            System.exit(0);
//        }
        dataProvider = new DataProvider("s368791", "7qc3bUPfUtx4bI0P");
        if (dataProvider.getWorkers() == null) {
            TreeSet<Worker> workers = new TreeSet<>();
            logger.info("Коллекция сформирована");
            collectionManager = new CollectionManager(workers, dataProvider);
        } else {
            TreeSet<Worker> workers = dataProvider.getWorkers();
            logger.info("Коллекция сформирована");
            collectionManager = new CollectionManager(workers, dataProvider);
            organizationReader.setOrganizationsFullNames(dataProvider.organizationsFullNames());
        }


        DatagramSocket serverSocket = new DatagramSocket(port);
        logger.info("Server started on port" + port);

        byte[] buf = new byte[BUF_SIZE];

        Runnable consoleReader = () -> {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            while (true) {
                try {
                    String input = scanner.nextLine();
                    switch (input.trim().toLowerCase()) {
                        case "save" -> {
                            SaveCommand saveCommand = new SaveCommand();
                            saveCommand.setCollectionManager(collectionManager);
                            logger.info(saveCommand.execute());
                        }
                        default -> logger.warning("Такой команды нет на сервере");
                    }
                } catch (IOException e) {
                    logger.warning("Ошибка при чтении с консоли сервера: " + e.getMessage());
                } catch (NoSuchElementException e) {
                    logger.warning("Отключение сервера: cntl+D");
                    System.exit(0);
                }
            }
        };

        new Thread(consoleReader).start();

        ForkJoinPool requestPool = new ForkJoinPool();

// Создаем ThreadPool для обработки запросов
        ExecutorService processingPool = Executors.newCachedThreadPool();

// Создаем ForkJoinPool для отправки ответов
        ForkJoinPool responsePool = new ForkJoinPool();

        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, BUF_SIZE);
            serverSocket.receive(packet);

            // Чтение запроса в отдельном потоке
            CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Object object = ois.readObject();
                    ois.close();
                    bais.close();
                    return object;
                } catch (IOException | ClassNotFoundException e) {
                    logger.warning("Ошибка в работе сервера");
                    System.out.println(e.getMessage());
                    return null;
                }
            }, requestPool);

            // Обработка запроса в отдельном потоке
            future.thenAcceptAsync(object -> {
                try {
                    if (object instanceof Command) {
                        Command receivedCommand = (Command) object;
                        logger.info("Received command: " + receivedCommand.getName());
                        receivedCommand.setCollectionManager(collectionManager);
                        String response = collectionManager.executeCommand(receivedCommand);
                        InetAddress address = packet.getAddress();
                        int clientPort = packet.getPort();
                        byte[] responseData = response.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, clientPort);
                        responsePacket.setData(responseData, 0, responseData.length);
                        serverSocket.setSendBufferSize(responseData.length);

                        // Отправка ответа в отдельном потоке
                        CompletableFuture.runAsync(() -> {
                            try {
                                serverSocket.send(responsePacket);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }, responsePool);
                    } else if (object instanceof Integer) {
                        TreeSet<Worker> workers = collectionManager.getWorkers();
                        InetAddress address = packet.getAddress();
                        int clientPort = packet.getPort();
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                        objectStream.writeObject(workers);
                        objectStream.flush();

                        byte[] data = byteStream.toByteArray();
                        DatagramPacket responsePacket = new DatagramPacket(data, data.length, address, clientPort);
                        serverSocket.setSendBufferSize(data.length);
                        CompletableFuture.runAsync(() -> {
                            try {
                                serverSocket.send(responsePacket);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }, responsePool);

                    } else {
                        String receivedShit = (String) object;
                        OrganizationReader response = this.organizationReader;
                        InetAddress address = packet.getAddress();
                        int clientPort = packet.getPort();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        oos.writeObject(response);
                        oos.flush();
                        byte[] responseData = baos.toByteArray();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, clientPort);
                        responsePacket.setData(responseData, 0, responseData.length);
                        serverSocket.setSendBufferSize(responseData.length);

                        // Отправка ответа в отдельном потоке
                        CompletableFuture.runAsync(() -> {
                            try {
                                serverSocket.send(responsePacket);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }, responsePool);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, processingPool);
        }
    }


    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (Exception e) {
            logger.warning(e.getMessage());

        }
    }
}
