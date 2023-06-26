package ru.itmo.lab8.lab8v2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ru.itmo.lab8.lab8v2.utils.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static ru.itmo.lab8.lab8v2.Test.mainStage;

public class HelloApplication extends Application {
    protected static Locale currentLocale = new Locale("ru");
    protected static User currentUser;


    @Override
    public void start(Stage stage) throws IOException {
        Client client = new Client();
        try {
            client.connect("localhost", 1236);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("visualScene.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registerScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 1050);
        mainStage.setTitle("Кровавая арена смерти");
        mainStage.setScene(scene);
        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }


    public static void changeScene(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
    }
    public static void setupLanguageChoiceBox(ChoiceBox<String> languageChoiceBox, Consumer<ResourceBundle> updateText, String pathToLocale, ImageView imageView) {
        // Наполняем ChoiceBox значениями языков
        languageChoiceBox.getItems().addAll("English", "Русский", "Беларуская", "Български");

        // Устанавливаем выбранный элемент по умолчанию

         switch (currentLocale.getLanguage()) {
            case "ru" -> {languageChoiceBox.setValue("Русский");
                imageView.setImage(new Image("ru/itmo/lab8/lab8v2/russia.png"));
            }
            case "en" -> {languageChoiceBox.setValue("English");
                imageView.setImage(new Image("ru/itmo/lab8/lab8v2/ireland.png"));}
            case "be" -> {languageChoiceBox.setValue("Беларуская");
                imageView.setImage(new Image("ru/itmo/lab8/lab8v2/belarus.png"));}
            case "bg" -> {languageChoiceBox.setValue("Български");
                imageView.setImage(new Image("ru/itmo/lab8/lab8v2/bulgaria.png"));}
            default -> {languageChoiceBox.setValue("Русский");
                imageView.setImage(new Image("ru/itmo/lab8/lab8v2/russia.png"));}
        }
        //TODO: сделать чтобы на всех страницах был одинаковый размер и центрирование, а то сейчас говно полное


        // Добавляем обработчик событий для выбора элемента
        languageChoiceBox.setOnAction(event -> {
            String selectedLanguage = languageChoiceBox.getValue();
            Locale locale = new Locale("ru"); // Локаль по умолчанию

            // Определяем локаль в зависимости от выбранного языка
            switch (selectedLanguage) {
                case "English":
                    locale = new Locale("en", "IE");
                    imageView.setImage(new Image("ru/itmo/lab8/lab8v2/ireland.png"));
                    break;
                case "Русский":
                    locale = new Locale("ru");
                    imageView.setImage(new Image("ru/itmo/lab8/lab8v2/russia.png"));
                    break;
                case "Беларуская":
                    locale = new Locale("be");
                    imageView.setImage(new Image("ru/itmo/lab8/lab8v2/belarus.png"));
                    break;
                case "Български":
                    locale = new Locale("bg");
                    imageView.setImage(new Image("ru/itmo/lab8/lab8v2/bulgaria.png"));
                    break;
                default:
                    break;
            }

            // Обновляем ResourceBundle с учетом новой локали
            ResourceBundle bundle = ResourceBundle.getBundle(pathToLocale, locale);
            updateText.accept(bundle);
            currentLocale = locale;
        });

        ResourceBundle bundle = ResourceBundle.getBundle(pathToLocale, currentLocale);
        updateText.accept(bundle);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        HelloApplication.currentUser = currentUser;
    }





}

