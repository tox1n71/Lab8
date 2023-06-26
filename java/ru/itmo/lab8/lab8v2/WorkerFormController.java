package ru.itmo.lab8.lab8v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.itmo.lab8.lab8v2.commands.AddCommand;
import ru.itmo.lab8.lab8v2.exceptions.UniqueException;
import ru.itmo.lab8.lab8v2.readers.OrganizationReader;
import ru.itmo.lab8.lab8v2.worker.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static ru.itmo.lab8.lab8v2.Client.receiveOrgReaderFromServer;
import static ru.itmo.lab8.lab8v2.Client.sendCommandToServer;
import static ru.itmo.lab8.lab8v2.HelloApplication.currentLocale;
import static ru.itmo.lab8.lab8v2.HelloApplication.getCurrentUser;

public class WorkerFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField coordinatesXField;

    @FXML
    private TextField coordinatesYField;



    @FXML
    private TextField salaryField;

    @FXML
    private DatePicker startDateField;

    @FXML
    private DatePicker endDateField;

    @FXML
    private ChoiceBox<String> positionBox;

    @FXML
    private TextField organizationFullNameField;

    @FXML
    private TextField organizationAnnualTurnoverField;

    @FXML
    private TextField organizationEmployeesCountField;

    @FXML
    private TextField organizationAddressStreetField;

    @FXML
    private TextField organizationAddressZipCodeField;

    @FXML
    private TextField organizationAddressTownField;

    @FXML
    private TextField organizationAddressLocationXField;

    @FXML
    private TextField organizationAddressLocationYField;

    @FXML
    private TextField organizationAddressLocationZField;

    @FXML
    private Button submitButton;

    @FXML
    private Label responseLabel;
    private OrganizationReader organizationReader;
    @FXML
    private Label nameLabel;
    @FXML
    private Label coordinatesXLabel;
    @FXML
    private Label coordinatesYLabel;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label organizationFullNameLabel;
    @FXML
    private Label organizationAnnualTurnoverLabel;
    @FXML
    private Label organizationEmployeesCountLabel;
    @FXML
    private Label organizationAddressStreetLabel;
    @FXML
    private Label organizationAddressZipCodeLabel;
    @FXML
    private Label organizationAddressTownLabel;
    @FXML
    private Label organizationAddressLocationXLabel;
    @FXML
    private Label organizationAddressLocationYLabel;
    @FXML
    private Label organizationAddressLocationZLabel;
    @FXML
    private Label addingLabel;
    String successAdding;
    String notUnique;





    public WorkerFormController() {

    }

    @FXML
    private void initialize() {
        // Заполнение choice box с возможными значениями должности
        positionBox.getItems().addAll("LABORER", "ENGINEER", "HEAD_OF_DEPARTMENT", "LEAD_DEVELOPER", "BAKER");
        updateText(ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.bundles.add.Add", currentLocale));
    }
    void updateText(ResourceBundle bundle){
        addingLabel.setText(bundle.getString("addingLabel"));
        submitButton.setText(bundle.getString("submitButton"));
        nameLabel.setText(bundle.getString("nameLabel"));
        coordinatesXLabel.setText(bundle.getString("coordinatesXLabel"));
        coordinatesYLabel.setText(bundle.getString("coordinatesYLabel"));
        salaryLabel.setText(bundle.getString("salaryLabel"));
        startDateLabel.setText(bundle.getString("startDateLabel"));
        endDateLabel.setText(bundle.getString("endDateLabel"));
        positionLabel.setText(bundle.getString("positionLabel"));
        organizationFullNameLabel.setText(bundle.getString("organizationFullNameLabel"));
        organizationAnnualTurnoverLabel.setText(bundle.getString("organizationAnnualTurnoverLabel"));
        organizationEmployeesCountLabel.setText(bundle.getString("organizationEmployeesCountLabel"));
        organizationAddressStreetLabel.setText(bundle.getString("organizationAddressStreetLabel"));
        organizationAddressZipCodeLabel.setText(bundle.getString("organizationAddressZipCodeLabel"));
        organizationAddressTownLabel.setText(bundle.getString("organizationAddressTownLabel"));
        organizationAddressLocationXLabel.setText(bundle.getString("organizationAddressLocationXLabel"));
        organizationAddressLocationYLabel.setText(bundle.getString("organizationAddressLocationYLabel"));
        organizationAddressLocationZLabel.setText(bundle.getString("organizationAddressLocationZLabel"));
        successAdding = bundle.getString("successAdding");
        notUnique = bundle.getString("notUnique");
    }
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            organizationReader = receiveOrgReaderFromServer();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            // Получение значений из полей ввода
            String name = nameField.getText();
            Double coordinatesX = Double.parseDouble(coordinatesXField.getText());
            Double coordinatesY = Double.parseDouble(coordinatesYField.getText());
            Integer salary = Integer.parseInt(salaryField.getText());
            LocalDate startDate = startDateField.getValue();
            LocalDateTime endDate = endDateField.getValue() != null ? endDateField.getValue().atStartOfDay() : null;
            String position = positionBox.getValue();
            String organizationFullName = organizationFullNameField.getText();
            Float organizationAnnualTurnover = Float.parseFloat(organizationAnnualTurnoverField.getText());
            Long organizationEmployeesCount = Long.parseLong(organizationEmployeesCountField.getText());
            String organizationAddressStreet = organizationAddressStreetField.getText();
            String organizationAddressZipCode = organizationAddressZipCodeField.getText();
            String organizationAddressTown = organizationAddressTownField.getText();
            Integer organizationAddressLocationX = Integer.parseInt(organizationAddressLocationXField.getText());
            Long organizationAddressLocationY = Long.parseLong(organizationAddressLocationYField.getText());
            Integer organizationAddressLocationZ = Integer.parseInt(organizationAddressLocationZField.getText());

            // Проверка на пустые поля
            if (name.isEmpty() || coordinatesXField.getText().isEmpty() || coordinatesYField.getText().isEmpty()
                     || salaryField.getText().isEmpty()
                    || startDateField.getValue() == null || positionBox.getValue() == null
                    || organizationFullNameField.getText().isEmpty() || organizationAnnualTurnoverField.getText().isEmpty()
                    || organizationEmployeesCountField.getText().isEmpty() || organizationAddressStreetField.getText().isEmpty()
                    || organizationAddressZipCodeField.getText().isEmpty() || organizationAddressTownField.getText().isEmpty()
                    || organizationAddressLocationXField.getText().isEmpty() || organizationAddressLocationYField.getText().isEmpty()
                    || organizationAddressLocationZField.getText().isEmpty()) {
                throw new IllegalArgumentException("Все поля должны быть заполнены");
            }
            organizationReader.checkOrgFullName(organizationFullName);

            // Проверка длины ZipCode
            if (organizationAddressZipCode.length() < 4) {
                throw new IllegalArgumentException("Длина ZipCode должна быть не менее 4 символов");
            }
            if (coordinatesX > 1000){
                throw new IllegalArgumentException("Координата X должна быть < 1000");
            }
            if (coordinatesY > 600){
                throw new IllegalArgumentException("Координата Y должна быть < 600");
            }

            // Создание объекта Worker на основе полученных значений
            Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);
            Position pos = Position.valueOf(position);
            Address organizationAddress = new Address(organizationAddressStreet, organizationAddressZipCode,
                    new Location(organizationAddressLocationX, organizationAddressLocationY, organizationAddressLocationZ,organizationAddressTown));
            Organization organization = new Organization(organizationFullName, organizationAnnualTurnover, organizationEmployeesCount, organizationAddress);
            Worker worker = new Worker(name, coordinates, salary, startDate, endDate, pos, organization);
            System.out.println(worker.getName());
            System.out.println(worker.getStartDate());
            // Отправка команды на сервер
//            client.sendCommandWithWorkerToServer(new AddCommand(worker));
            AddCommand addCommand = new AddCommand(worker, getCurrentUser());

            responseLabel.setText("Объект успешно добавлен");
            String response = sendCommandToServer(addCommand);
            if (response.contains("Введенный элемент добавлен")){
                String[] subStr = response.split(" ");
                responseLabel.setText(successAdding + subStr[7]);
            }else {responseLabel.setText(notUnique);}
        } catch (IllegalArgumentException e) {
            responseLabel.setText("Ошибка при добавлении объекта: " + e.getMessage());
        } catch (UniqueException e) {
            responseLabel.setText( "Название организации уже занято");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}