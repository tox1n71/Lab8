package ru.itmo.lab8.lab8v2;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import ru.itmo.lab8.lab8v2.worker.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static javafx.application.Application.launch;
import static ru.itmo.lab8.lab8v2.Client.getWorkersFromServer;

public class Test2 implements Initializable {

    @FXML
    private AnchorPane anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        UserAuth.setUser(new User("VASYAAAAA", "bibaboba"));

        try {
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {

            new Thread(() -> {
                try {
                    refreshTable();
                } catch (Exception e) {
                }
            }).start();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


//        inputSearchingGroup.textProperty().addListener((observable, oldValue, newValue) ->
//        {
//            filteredList.setPredicate(studyGroupSearchModel -> {
//                if (newValue.isEmpty() || newValue.isBlank()) {
//                    return true;
//                }
//                String searchKeyword = newValue.toLowerCase();
//
//                if (studyGroupSearchModel.getName().toLowerCase().contains(searchKeyword)) {
//                    return true;
//                }
//                return false;
//            });
//        });
//        sortedList = new SortedList<>(filteredList);
//        try {
//            sortedList.comparatorProperty().bind(studyGroupsTable.comparatorProperty());
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }

    }

    private FilteredList<TableWorker> filteredList = null;

    private void refreshTable() throws IOException, ClassNotFoundException {
        try {
            LinkedList<TableWorker> tableData = new LinkedList<>();
            TreeSet<Worker> workers = getWorkersFromServer();
            for (Worker worker : workers) {
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

            // очистка контейнера меток
            Platform.runLater(() -> markersGroup.getChildren().clear());
            // добавление новых меток на карту
            for (TableWorker studyGroupTableView : tableData) {

                Platform.runLater(() -> writeObjectOnMap(studyGroupTableView));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createMark(Integer x, Integer y, Integer radius, String color) {
        Label label = new Label();
        Circle circle = new Circle(radius);
        circle.setStroke(Paint.valueOf("blue"));
        label.setGraphic(circle);
        label.setLayoutX(100);
        label.setLayoutY(200);
        anchorPane.getChildren().add(label);
    }

    private Group markersGroup = new Group(); // создание контейнера для меток

    private void writeObjectOnMap(TableWorker studyGroupTableView) {

        String str = studyGroupTableView.getOwner(); // ваша строка
        int hashCode = str.hashCode(); // получаем хеш-код строки

// создаем цвет на основе хеш-кода
        java.awt.Color awtColor = new java.awt.Color(hashCode);
// преобразуем цвет в JavaFX
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());

// создаем объект Circle с цветом


        Label label = new Label();
        Circle circle = new Circle(studyGroupTableView.getSalary(), fxColor);
        circle.setStroke(Paint.valueOf("black"));
        label.setGraphic(circle);
        label.setLayoutX(studyGroupTableView.getCoordinatesX());
        label.setLayoutY(studyGroupTableView.getCoordinatesY());
        if (!markersGroup.getChildren().contains(label)) {
            label.setOnMouseClicked((event)->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "privet");
                alert.show();
            });
            markersGroup.getChildren().add(label); // добавление метки в контейнер

        }
        if (!anchorPane.getChildren().contains(markersGroup)) {
            anchorPane.getChildren().add(markersGroup); // добавление контейнера на AnchorPane
        }
    }

}
