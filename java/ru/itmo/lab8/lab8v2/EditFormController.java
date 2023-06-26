package ru.itmo.lab8.lab8v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.itmo.lab8.lab8v2.commands.AddCommand;
import ru.itmo.lab8.lab8v2.commands.EditCommand;
import ru.itmo.lab8.lab8v2.commands.UpdateIdCommand;
import ru.itmo.lab8.lab8v2.exceptions.UniqueException;
import ru.itmo.lab8.lab8v2.readers.OrganizationReader;
import ru.itmo.lab8.lab8v2.worker.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;



import static ru.itmo.lab8.lab8v2.Client.receiveOrgReaderFromServer;
import static ru.itmo.lab8.lab8v2.Client.sendCommandToServer;
import static ru.itmo.lab8.lab8v2.HelloApplication.currentUser;
import static ru.itmo.lab8.lab8v2.HelloApplication.getCurrentUser;

public class EditFormController {

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
    private Button editButton;


    @FXML
    private Label responseLabel;
    private OrganizationReader organizationReader;
    private TableWorker tableWorker;


    public TableWorker getTableWorker() {
        return tableWorker;
    }

    public void setTableWorker(TableWorker tableWorker) {
        this.tableWorker = tableWorker;
    }
    public EditFormController(TableWorker tableWorker){
        this.tableWorker = tableWorker;
    }

    @FXML
    private void initialize() {
        // Заполнение choice box с возможными значениями должности
        positionBox.getItems().addAll("LABORER", "ENGINEER", "HEAD_OF_DEPARTMENT", "LEAD_DEVELOPER", "BAKER");
        nameField.setText(tableWorker.getName());
        coordinatesXField.setText(String.valueOf(tableWorker.getCoordinatesX()));
        coordinatesYField.setText(String.valueOf(tableWorker.getCoordinatesY()));
        salaryField.setText(String.valueOf(tableWorker.getSalary()));
        startDateField.setValue(tableWorker.getStartDate());
        if (tableWorker.getEndDate() != null) {
            endDateField.setValue(tableWorker.getEndDate().toLocalDate());
        }
        positionBox.setValue(tableWorker.getPosition());
        organizationFullNameField.setText(tableWorker.getOrganizationFullName());
        organizationAnnualTurnoverField.setText(String.valueOf(tableWorker.getOrganizationAnnualTurnover()));
        organizationEmployeesCountField.setText(String.valueOf(tableWorker.getOrganizationEmployeesCount()));
        organizationAddressStreetField.setText(tableWorker.getOrganizationAddressStreet());
        organizationAddressZipCodeField.setText(tableWorker.getOrganizationAddressZipCode());
        organizationAddressTownField.setText(tableWorker.getOrganizationAddressTown());
        organizationAddressLocationXField.setText(String.valueOf(tableWorker.getOrganziationAddressLocationX()));
        organizationAddressLocationYField.setText(String.valueOf(tableWorker.getOrganziationAddressLocationY()));
        organizationAddressLocationZField.setText(String.valueOf(tableWorker.getOrganziationAddressLocationZ()));
        editButton.setOnAction(event -> handleEditButtonAction());
    }


    @FXML
    private void handleEditButtonAction() {
        try {
            organizationReader = receiveOrgReaderFromServer();
            organizationReader.removeOrgNameFromList(tableWorker.getOrganizationFullName());
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
            worker.setId(tableWorker.getId());
            worker.setUser(currentUser);
            // Отправка команды на сервер
//            client.sendCommandWithWorkerToServer(new AddCommand(worker));
            EditCommand editCommand = new EditCommand(worker);
            responseLabel.setText(sendCommandToServer(editCommand));
        } catch (IllegalArgumentException e) {
            responseLabel.setText("Ошибка при добавлении объекта: " + e.getMessage());
        } catch (UniqueException e) {
            responseLabel.setText( "Название организации уже занято");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}