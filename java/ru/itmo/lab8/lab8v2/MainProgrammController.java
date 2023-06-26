package ru.itmo.lab8.lab8v2;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.*;
import org.controlsfx.control.table.TableFilter;
import ru.itmo.lab8.lab8v2.commands.*;
import ru.itmo.lab8.lab8v2.exceptions.WrongScriptDataException;
import ru.itmo.lab8.lab8v2.readers.OrganizationReader;
import ru.itmo.lab8.lab8v2.utils.User;
import ru.itmo.lab8.lab8v2.worker.Organization;
import ru.itmo.lab8.lab8v2.worker.Worker;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;
import static ru.itmo.lab8.lab8v2.Client.*;
import static ru.itmo.lab8.lab8v2.HelloApplication.*;
import static ru.itmo.lab8.lab8v2.Test.mainStage;

public class MainProgrammController {
    @FXML
    private ResourceBundle resources;
    private final int CANVAS_WIDTH = 1118;
    private final int CANVAS_HEIGHT = 587;
    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private Label welcomeText;

    @FXML
    private Label userName;
    @FXML
    private Label currentUserLabel;
    @FXML
    private Button logOutButton;
    @FXML
    private ImageView imageView;
    private ObservableList<TableWorker> workersData = FXCollections.observableArrayList();

    @FXML
    private TableView<TableWorker> workerTable;
    @FXML
    private TableColumn<TableWorker, Integer> idColumn;
    @FXML
    private TableColumn<TableWorker, String> ownerColumn;

    @FXML
    private TableColumn<TableWorker, String> nameColumn;
    @FXML
    private TableColumn<TableWorker, Double> coordinatesXColumn;
    @FXML
    private TableColumn<TableWorker, Double> coordinatesYColumn;
    @FXML
    private TableColumn<TableWorker, LocalDate> creationDateColumn;
    @FXML
    private TableColumn<TableWorker, Integer> salaryColumn;
    @FXML
    private TableColumn<TableWorker, LocalDate> startDateColumn;
    @FXML
    private TableColumn<TableWorker, LocalDateTime> endDateColumn;
    @FXML
    private TableColumn<TableWorker, String> positionColumn;
    @FXML
    private TableColumn<TableWorker, String> organizationFullNameColumn;
    @FXML
    private TableColumn<TableWorker, Float> organizationAnnualTurnoverColumn;
    @FXML
    private TableColumn<TableWorker, Long> organizationEmployeesCountColumn;
    @FXML
    private TableColumn<TableWorker, String> organizationAddressStreetColumn;
    @FXML
    private TableColumn<TableWorker, String> organizationAddressZipCodeColumn;
    @FXML
    private TableColumn<TableWorker, String> organizationAddressTownColumn;
    @FXML
    private TableColumn<TableWorker, Integer> organziationAddressLocationXColumn;
    @FXML
    private TableColumn<TableWorker, Long> organziationAddressLocationYColumn;
    @FXML
    private TableColumn<TableWorker, Integer> organziationAddressLocationZColumn;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameFilter;
    private FilteredList<TableWorker> filteredData;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button historyButton; //30
    @FXML
    private Button infoButton;

    @FXML
    private Button minByNameButton;
    @FXML
    private Button removeLowerButton;
    @FXML
    private Button sortDescendingButton;
    @FXML
    private Label serverResponse;
    String collectionType;
    String collectionSize;
    String collectionDate;
    String infoHeader;
    String minByNameHeader;
    String editHeader;
    String editText;
    TreeSet<Worker> workers;
    @FXML
    private AnchorPane canvasPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToggleButton visualButton;
    @FXML
    private ImageView dota;
    String historyHeader;
    String removeLowerResponse;
    @FXML
    private Button helpButton;
    String help;
    String helpText;
    @FXML
    private Button executeScriptButton;
    @FXML
    private HashSet<String> executedScripts = new HashSet<>();
    @FXML
    private Button addIfMinButton;


    @FXML
    void initialize() {
        try {
            setUpTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String userLabelText = HelloApplication.getCurrentUser().getName();
        userName.setText(userLabelText);
        setupLanguageChoiceBox(languageChoiceBox, this::updateText, "ru.itmo.lab8.lab8v2.bundles.main.Main", imageView);
        //16 poley
        TableFilter.forTableView(workerTable).apply();
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvasPane.getChildren().add(canvas);
        canvasPane.setVisible(false);
        Rectangle clipRect = new Rectangle(canvasPane.getPrefWidth(), canvasPane.getPrefHeight());
        dota.setClip(clipRect);
        dota.setManaged(false);
        addCanvasMouseListener();
    }

    void updateText(ResourceBundle bundle) {
        try {
            TreeSet<Worker> workers = getWorkersFromServer();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentUserLabel.setText(bundle.getString("currentUser"));
        String userLabelText = HelloApplication.getCurrentUser().getName();
        userName.setText(userLabelText);
        logOutButton.setText(bundle.getString("logOutButton"));
        idColumn.setText(bundle.getString("idColumn"));
        nameColumn.setText(bundle.getString("nameColumn"));
        ownerColumn.setText(bundle.getString("ownerColumn"));
        coordinatesXColumn.setText(bundle.getString("coordinateXColumn"));
        coordinatesYColumn.setText(bundle.getString("coordinateYColumn"));
        creationDateColumn.setText(bundle.getString("creationDateColumn"));
        salaryColumn.setText(bundle.getString("salaryColumn"));
        startDateColumn.setText(bundle.getString("startDateColumn"));
        endDateColumn.setText(bundle.getString("endDateColumn"));
        positionColumn.setText(bundle.getString("positionColumn"));
        organizationFullNameColumn.setText(bundle.getString("organizationFullNameColumn"));
        organizationAnnualTurnoverColumn.setText(bundle.getString("organizationAnnualTurnoverColumn"));
        organizationEmployeesCountColumn.setText(bundle.getString("organizationEmployeesCountColumn"));
        organizationAddressStreetColumn.setText(bundle.getString("organizationAddressStreetColumn"));
        organizationAddressZipCodeColumn.setText(bundle.getString("organizationAddressZipCodeColumn"));
        organizationAddressTownColumn.setText(bundle.getString("organizationAddressTownColumn"));
        organziationAddressLocationXColumn.setText(bundle.getString("organziationAddressLocationXColumn"));
        organziationAddressLocationYColumn.setText(bundle.getString("organziationAddressLocationYColumn"));
        organziationAddressLocationZColumn.setText(bundle.getString("organziationAddressLocationZColumn"));

        collectionType = bundle.getString("collectionType");
        collectionSize = bundle.getString("collectionSize");
        collectionDate = bundle.getString("collectionDate");
        infoHeader = bundle.getString("infoHeader");
        minByNameHeader = bundle.getString("minByName");
        clearHeader = bundle.getString("clearHeader");
        editHeader = bundle.getString("editHeader");
        editText = bundle.getString("editText");
        historyHeader = bundle.getString("historyHeader");
        help = bundle.getString("help");
        helpText = bundle.getString("helpText");
    }

    @FXML
    protected void logOutButtonOnClick() {
        setCurrentUser(null);
        try {
            changeScene("hello-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpTable() throws IOException {
        ObservableList<TableWorker> tableData = setupTableData(getWorkersFromServer());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        coordinatesXColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatesX"));
        coordinatesYColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatesY"));
        creationDateColumn.setCellValueFactory(cellData -> {
            ObjectProperty<LocalDate> property = new SimpleObjectProperty<>(cellData.getValue().getCreationDate());
            return property;
        });
        creationDateColumn.setCellFactory(column -> new TableCell<TableWorker, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String formattedDate = item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    setText(formattedDate);
                }
            }
        });
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        startDateColumn.setCellValueFactory(cellData -> {
            ObjectProperty<LocalDate> property = new SimpleObjectProperty<>(cellData.getValue().getStartDate());
            return property;
        });
        startDateColumn.setCellFactory(column -> new TableCell<TableWorker, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String formattedDate = item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    setText(formattedDate);
                }
            }
        });
        endDateColumn.setCellValueFactory(cellData -> {
            ObjectProperty<LocalDateTime> property = new SimpleObjectProperty<>(cellData.getValue().getEndDate());
            return Bindings.createObjectBinding(() -> property.get(), property);
        });

        endDateColumn.setCellFactory(column -> new TableCell<TableWorker, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String formattedDate = item.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    setText(formattedDate);
                }
            }
        });

        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        organizationFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("organizationFullName"));
        organizationAnnualTurnoverColumn.setCellValueFactory(new PropertyValueFactory<>("organizationAnnualTurnover"));
        organizationEmployeesCountColumn.setCellValueFactory(new PropertyValueFactory<>("organizationEmployeesCount"));
        organizationAddressStreetColumn.setCellValueFactory(new PropertyValueFactory<>("organizationAddressStreet"));
        organizationAddressZipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("organizationAddressZipCode"));
        organizationAddressTownColumn.setCellValueFactory(new PropertyValueFactory<>("organizationAddressTown"));
        organziationAddressLocationXColumn.setCellValueFactory(new PropertyValueFactory<>("organziationAddressLocationX"));
        organziationAddressLocationYColumn.setCellValueFactory(new PropertyValueFactory<>("organziationAddressLocationY"));
        organziationAddressLocationZColumn.setCellValueFactory(new PropertyValueFactory<>("organziationAddressLocationZ"));

        workersData = tableData;
        filteredData = new FilteredList<>(workersData, p -> true);

        nameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tableWorker -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (tableWorker.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        workerTable.setItems(filteredData);
    }

    private ObservableList<TableWorker> setupTableData(TreeSet<Worker> workers) {
        ObservableList<TableWorker> tableData = FXCollections.observableArrayList();
        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker worker = iterator.next();
            TableWorker tableWorker = new TableWorker(worker.getId(), worker.getUser().getName(),
                    worker.getName(), worker.getCoordinates().getX(), worker.getCoordinates().getY(),
                    worker.getCreationDate(), worker.getSalary(), worker.getStartDate(), worker.getEndDate(),
                    worker.getPosition().toString(), worker.getOrganization().getFullName(),
                    worker.getOrganization().getAnnualTurnover(), worker.getOrganization().getEmployeesCount(),
                    worker.getOrganization().getPostalAddress().getStreet(), worker.getOrganization().getPostalAddress().getZipCode(),
                    worker.getOrganization().getPostalAddress().getTown().getName(), worker.getOrganization().getPostalAddress().getTown().getX(),
                    worker.getOrganization().getPostalAddress().getTown().getY(), worker.getOrganization().getPostalAddress().getTown().getZ());
            tableData.add(tableWorker);
        }
        return tableData;
    }

    @FXML
    private void addButtonOnClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WorkerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait(); // ждем, пока пользователь закроет форму
        updateTable(); // обновляем таблицу
        if (visualButton.isSelected()) refreshButtonClick();
    }

    @FXML
    private void setEditButtonOnClick() {
        TableWorker selectedObject = workerTable.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            if (currentUser.getName().equals(selectedObject.getOwner())) {
                try {
                    EditFormController editFormController = new EditFormController(selectedObject);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editForm.fxml"));
                    fxmlLoader.setController(editFormController);
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Edit Object");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    updateTable();
                    if (visualButton.isSelected()) refreshButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edit");
                alert.setHeaderText(editHeader);
                alert.setContentText(editText);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onRemoveButtonOnClick() {
        TableWorker selectedObject = workerTable.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            RemoveByIdCommand remove = new RemoveByIdCommand(selectedObject.getId(), currentUser);
            try {
                serverResponse.setText(sendCommandToServer(remove));
                updateTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onHistoryButtonOnClick() {
        HistoryCommand historyCommand = new HistoryCommand();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(sendCommandToServer(historyCommand));
            alert.setTitle("History");
            alert.setHeaderText(historyHeader);
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String clearHeader;

    @FXML
    private void onClearButtonOnClick() {
        ClearCommand clearCommand = new ClearCommand(currentUser);
        try {
            String response = sendCommandToServer(clearCommand);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
            alert.setTitle("Clear");
            alert.setHeaderText(clearHeader);
            alert.showAndWait();
            updateTable();
            if (visualButton.isSelected()) refreshButtonClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onInfoButtonOnClick() {
        InfoCommand infoCommand = new InfoCommand();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(infoHeader);
            String responseParts = sendCommandToServer(infoCommand);
            String[] subString = responseParts.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss zzzz", currentLocale);
            String fullResponse = collectionType + subString[0] + collectionSize + subString[1] + collectionDate + ZonedDateTime.parse(subString[2]).format(formatter);
            alert.setContentText(fullResponse);
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMinByNameButtonOnClick() {
        MinByNameCommand minByNameCommand = new MinByNameCommand();
        try {
            String response = (sendCommandToServer(minByNameCommand));
            // сортируем элементы таблицы по колонке name в обратном орядке

            // получаем первый элемент таблицы
            Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
            alert.setTitle("Min by name");
            alert.setHeaderText(minByNameHeader);
            alert.showAndWait();
            TableView.TableViewSelectionModel<TableWorker> selectionModel = workerTable.getSelectionModel();
            selectionModel.selectFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSortDescendingButtonOnClick() {
        // получаем список элементов таблицы
        ObservableList<TableWorker> items = workerTable.getItems();

// создаем новый список элементов и копируем в него элементы из списка items
        ObservableList<TableWorker> sortedItems = FXCollections.observableArrayList(items);

// создаем компаратор для сравнения элементов по колонке name
        Comparator<TableWorker> comparator = Comparator.comparing(TableWorker::getSalary);

// сортируем список элементов с помощью компаратора
        sortedItems.sort(comparator.reversed());

// устанавливаем отсортированный список в качестве источника данных для таблицы
        workerTable.setItems(sortedItems);

    }

    private void updateTable() {
        try {
            TreeSet<Worker> workers = getWorkersFromServer();
            ObservableList<TableWorker> tableData = setupTableData(workers);

            workersData = tableData;
            filteredData = new FilteredList<>(workersData, p -> true);

            nameFilter.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(tableWorker -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (tableWorker.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false;
                });
            });


            workerTable.setItems(filteredData);
            workerTable.refresh(); // перерисовываем таблицу
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void refreshButtonClick() throws IOException {
        workers = getWorkersFromServer();
        draw();
    }

    private void draw() throws IOException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        ObservableList<TableWorker> tableWorkers = setupTableData(getWorkersFromServer());
        // нарисовать задний фон
        // нарисовать объекты на карте
        Iterator<TableWorker> iterator = tableWorkers.iterator();
        while (iterator.hasNext()) {
            TableWorker tableWorker = iterator.next();

            double salary = tableWorker.getSalary();
            double x = abs(tableWorker.getCoordinatesX());
            double y = abs(tableWorker.getCoordinatesY());
            Color color = getColorForUser(tableWorker.getOwner());

            int size = (int) (salary / 500);
            gc.setFill(color);
//            gc.fillOval(x - size / 2, y - size / 2, size, size);
            gc.fillOval(x, y, size, size);
        }
    }

    private void addCanvasMouseListener() {
        canvasPane.setOnMouseClicked(event -> {
            double clickX = event.getX();
            double clickY = event.getY();

            // Проверяем, было ли нажато на какой-то объект на Canvas
            ObservableList<TableWorker> tableWorkers = null;
            try {
                tableWorkers = setupTableData(getWorkersFromServer());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Iterator<TableWorker> iterator = tableWorkers.iterator();
            while (iterator.hasNext()) {
                TableWorker tableWorker = iterator.next();

                double salary = tableWorker.getSalary();
                double x = abs(tableWorker.getCoordinatesX());
                double y = abs(tableWorker.getCoordinatesY());
                int size = (int) (salary / 500);

                if (isClickedOnObject(clickX, clickY, x, y, size)) {
                    // Вызываем метод edit() с соответствующим объектом TableWorker
                    setEditToCanvasWorker(tableWorker);
                    break;
                }
            }
        });
    }

    private void setEditToCanvasWorker(TableWorker tableWorker) {
        TableWorker selectedObject = tableWorker;
        if (selectedObject != null) {
            if (currentUser.getName().equals(selectedObject.getOwner())) {
                try {
                    EditFormController editFormController = new EditFormController(selectedObject);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editForm.fxml"));
                    fxmlLoader.setController(editFormController);
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Edit Object");
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                    updateTable();
                    if (visualButton.isSelected()) refreshButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Edit");
                alert.setHeaderText(editHeader);
                alert.setContentText(editText);
                alert.showAndWait();
            }
        }
    }

    private boolean isClickedOnObject(double clickX, double clickY, double x, double y, double size) {
        // проверяем, находится ли точка (clickX, clickY) внутри круга с центром (x, y) и радиусом size
        double dx = clickX - x;
        double dy = clickY - y;
        return dx * dx + dy * dy <= size * size;
    }

    private Color getColorForUser(String userName) {
        int hashCode = userName.hashCode();
        return Color.rgb(hashCode % 256, (hashCode / 256) % 256, (hashCode / 65536) % 256);
    }

    @FXML
    void visualizationButtonOnClick() {
        if (visualButton.isSelected()) {
            scrollPane.setVisible(false);
            canvasPane.setVisible(true);
            try {
                refreshButtonClick();
            } catch (IOException e) {
                System.out.println("Взлом жопы");
                e.printStackTrace();
            }
        } else {
            canvasPane.setVisible(false);
            scrollPane.setVisible(true);
        }
    }

    @FXML
    private void RemoveLowerButtonOnClick() throws IOException {
        TableWorker selectedObject = workerTable.getSelectionModel().getSelectedItem();
        TreeSet<Worker> workers2 = getWorkersFromServer();
        removeLowerResponse = "";
        if (selectedObject != null) {
            Iterator<Worker> iterator = workers2.iterator();
            while (iterator.hasNext()) {
                Worker worker = iterator.next();
                if (worker.getId() == selectedObject.getId()) {
                    RemoveLowerCommand removeLowerCommand = new RemoveLowerCommand(worker, currentUser);
                    removeLowerResponse = sendCommandToServer(removeLowerCommand);
                    break;
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Remove lower");
        alert.setContentText(removeLowerResponse);
        alert.showAndWait();
        updateTable();
    }

    @FXML
    private void helpButtonOnClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(help);
        alert.setContentText(helpText);
        alert.showAndWait();
    }
    @FXML
    private void setExecuteScriptButtonOnClick(){
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(mainStage);
        if (file!=null) executedScripts.add(file.getName());
        try {
            executeScript(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        executedScripts.remove(file.getName());
        updateTable();
    }
    private void executeScript(File scriptFileName) throws IOException, ClassNotFoundException {
        User user = currentUser;
        OrganizationReader organizationReader = receiveOrgReaderFromServer();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File("user.home"));
//
//        File file = fileChooser.showOpenDialog(mainStage);
//        executedScripts.add(file.getName());
        File file = scriptFileName;
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                switch (line) {
                    case "add" -> {
                        try {
                            Worker scriptWorker = executeScriptCommand.readWorkerFromFile(reader, organizationReader, user);
                            AddCommand scriptAdd = new AddCommand(scriptWorker, user);
                            sendCommandToServer(scriptAdd);
                        } catch (WrongScriptDataException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case "help" -> {
                        HelpCommand scriptHelp = new HelpCommand();
                        sendCommandToServer(scriptHelp);
                    }
                    case "info" -> {
                        InfoCommand scriptInfo = new InfoCommand();
                        sendCommandToServer(scriptInfo);
                    }
                    case "show" -> {
                        ShowCommand scriptShow = new ShowCommand();
                        sendCommandToServer(scriptShow);
                    }
                    case "clear" -> {
                        ClearCommand scriptClear = new ClearCommand(user);
                        sendCommandToServer(scriptClear);
                    }
                    case "history" -> {
                        HistoryCommand scriptHistory = new HistoryCommand();
                        sendCommandToServer(scriptHistory);
                    }
                    case "min_by_name" -> {
                        MinByNameCommand scriptMinByName = new MinByNameCommand();
                        sendCommandToServer(scriptMinByName);
                    }
                    case "print_descending" -> {
                        PrintDescendingCommand scriptDesc = new PrintDescendingCommand();
                        sendCommandToServer(scriptDesc);
                    }
                    case "add_if_min" -> {
                        Worker scriptWorker = null;
                        try {
                            scriptWorker = executeScriptCommand.readWorkerFromFile(reader, organizationReader, user);
                        } catch (WrongScriptDataException e) {
                            throw new RuntimeException(e);
                        }
                        AddIfMinCommand scriptAddIfMin = new AddIfMinCommand(scriptWorker, user);
                        sendCommandToServer(scriptAddIfMin );
                    }
                    case "remove_lower" -> {
                        Worker scriptWorker = null;
                        try {
                            scriptWorker = executeScriptCommand.readWorkerFromFile(reader, organizationReader, user);
                        } catch (WrongScriptDataException e) {
                            throw new RuntimeException(e);
                        }
                        RemoveLowerCommand scriptRemoveLower = new RemoveLowerCommand(scriptWorker, user);
                    }
                    default -> {
                        if (line.matches("remove_by_id \\d+")) {
                            Pattern pattern = Pattern.compile("\\d+");
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                int id = Integer.parseInt(matcher.group());
                                RemoveByIdCommand scriptRemoveByID = new RemoveByIdCommand(id, user);
                                sendCommandToServer(scriptRemoveByID );
                            } else {
                                System.out.println("Неверный формат команды remove_by_id {id} в скрипте");
                            }
                        } else if (line.matches("update_by_id \\d+")) {
                            Pattern pattern = Pattern.compile("\\d+");
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                int id = Integer.parseInt(matcher.group());
                                try {
                                    Worker sciptWorker = executeScriptCommand.readWorkerFromFile(reader, organizationReader, user);
                                    UpdateIdCommand sciptUpdateById = new UpdateIdCommand(id, sciptWorker);
                                    sendCommandToServer(sciptUpdateById);
                                } catch (WrongScriptDataException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Неверный формат команды update_by_id id в скрипте");
                            }
                        } else if (line.matches("execute_script \\S*")) {
                            String[] tokens = line.split(" ");
                            if (tokens.length == 2) {
                                String inScriptFileName = tokens[1];
                                if (!executedScripts.contains(inScriptFileName)) {
                                    executedScripts.add(inScriptFileName);
                                    executeScript(new File(inScriptFileName));
                                    executedScripts.remove(inScriptFileName);
                                } else {
                                    System.out.println("Скрипт " + inScriptFileName + " уже был выполнен. Пропускаем...");
                                }
                            }
                        }

                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл со скриптом не найден");
        }
    }

    @FXML
    protected void addIfMinButtonOnClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddIfMinForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait(); // ждем, пока пользователь закроет форму
        updateTable(); // обновляем таблицу
        if (visualButton.isSelected()) refreshButtonClick();
    }
}

