package ru.itmo.lab8.lab8v2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ru.itmo.lab8.lab8v2.worker.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static java.lang.Math.abs;
import static ru.itmo.lab8.lab8v2.Client.getWorkersFromServer;

public class VisualController implements Initializable {
    @FXML
    private AnchorPane canvasPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button refreshButton;

    private TreeSet<Worker> workers;

    private final int CANVAS_WIDTH = 1115;
    private final int CANVAS_HEIGHT = 594;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        try {
            refreshButtonClick();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void refreshButtonClick() throws IOException {
        workers = getWorkersFromServer();
        canvasPane.getChildren().remove(canvas);
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvasPane.getChildren().add(canvas);

        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // нарисовать задний фон



        // нарисовать объекты на карте
        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker worker = iterator.next();

            double salary = worker.getSalary();
            double x = worker.getCoordinates().getX();
            double y = worker.getCoordinates().getY();
            Color color = getColorForUser(worker.getUser().getName());

            int size = (int) (salary / 500);
            Circle circle = new Circle(x, y, size, color);
            canvasPane.getChildren().add(circle);
        }
    }

    private Color getColorForUser(String userName) {
        int hashCode = userName.hashCode();
        return Color.rgb(hashCode % 256, (hashCode / 256) % 256, (hashCode / 65536) % 256);
    }


}