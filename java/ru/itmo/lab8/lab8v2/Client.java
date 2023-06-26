package ru.itmo.lab8.lab8v2;



import ru.itmo.lab8.lab8v2.commands.Command;
import ru.itmo.lab8.lab8v2.readers.OrganizationReader;
import ru.itmo.lab8.lab8v2.readers.WorkerReader;
import ru.itmo.lab8.lab8v2.worker.Worker;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;


public class Client {

    private static final int BUF_SIZE = 16384;


    private OrganizationReader organizationReader;
    private WorkerReader workerReader;

    private static DatagramSocket socket;

    private HashSet<String> executedScripts = new HashSet<>();

    public void connect(String hostname, int port) throws Exception {
        // Создаем сокет для отправки датаграмм на сервер

        socket = new DatagramSocket();

        InetAddress address = InetAddress.getByName(hostname);

        Scanner scanner = new Scanner(System.in);
        socket.setSoTimeout(3000);
        System.out.println("Добро пожаловать на кровавую арену смерти!\nАвторизируйтесь(login) или зарегестрируйтесь(register), чтобы воспользоваться программой.");
    }




    public static String sendCommandToServer(Command command) throws IOException {
        InetAddress address =  InetAddress.getByName("localhost");
        int port = 1236;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        oos.flush();

        byte[] serializedCommand = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(serializedCommand,
                serializedCommand.length,
                address,
                port);
        socket.send(packet);

        // получаем ответ от сервера
        try {
            byte[] buffer = new byte[BUF_SIZE];

            packet = new DatagramPacket(buffer, BUF_SIZE);
            socket.receive(packet);
            String response = new String(packet.getData(), 0, packet.getLength());
            return (response);
        } catch (SocketTimeoutException e) {
            return ("Не удалось подключится к серверу. Повторите попытку позже.");
        }

    }

    public static OrganizationReader receiveOrgReaderFromServer() throws IOException, ClassNotFoundException {
        String lol = "lol";
        InetAddress address =  InetAddress.getByName("localhost");
        int port = 1236;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(lol);
        oos.flush();

        byte[] serializedCommand = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(serializedCommand,
                serializedCommand.length,
                address,
                port);
        socket.send(packet);

        byte[] buffer = new byte[BUF_SIZE];
        packet = new DatagramPacket(buffer, BUF_SIZE);
        socket.receive(packet);
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        OrganizationReader response = (OrganizationReader) ois.readObject();
        ois.close();
        bais.close();

        return response;

    }

    private boolean checkServerIsAlive(String lol, InetAddress address, int port) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(lol);
        oos.flush();

        byte[] serializedMessage = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(serializedMessage,
                serializedMessage.length,
                address,
                port);
        socket.send(packet);

        // получаем ответ от сервера
        byte[] buffer = new byte[BUF_SIZE];
        packet = new DatagramPacket(buffer, BUF_SIZE);
        socket.receive(packet);
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        boolean isAlive = (boolean) ois.readObject();
        ois.close();
        bais.close();

        return isAlive;

    }
    public static String sendCommandWithUserToServer(Command command) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        int port = 1236;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        oos.flush();

        byte[] serializedCommand = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(serializedCommand,
                serializedCommand.length,
                address,
                port);
        socket.send(packet);

        // получаем ответ от сервера
        try {
            byte[] buffer = new byte[BUF_SIZE];

            packet = new DatagramPacket(buffer, BUF_SIZE);
            socket.receive(packet);
            String response = new String(packet.getData(), 0, packet.getLength());
            return response;
        } catch (SocketTimeoutException e) {
            return("Не удалось подключится к серверу. Повторите попытку позже.");
        }
    }

    public static TreeSet<Worker> getWorkersFromServer() throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        int port = 1236;
        Integer command = 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        oos.flush();

        byte[] serializedCommand = baos.toByteArray();

        DatagramPacket packet = new DatagramPacket(serializedCommand,
                serializedCommand.length,
                address,
                port);
        socket.send(packet);

        // получаем ответ от сервера
        try {
            byte[] buffer = new byte[BUF_SIZE];

            packet = new DatagramPacket(buffer, BUF_SIZE);
            socket.receive(packet);
            byte[] data = packet.getData();
            ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
            ObjectInputStream objectStream = new ObjectInputStream(new BufferedInputStream(byteStream));
            TreeSet<Worker> workers = (TreeSet<Worker>) objectStream.readObject();
            return workers;
        } catch (SocketTimeoutException e) {
            System.out.println("Не удалось подключится к серверу");
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.connect("localhost", 1236);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
