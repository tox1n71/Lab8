package ru.itmo.lab8.lab8v2;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static final Stage mainStage = new Stage();
    public static void main(String[] args) throws UnsupportedEncodingException {
        ResourceBundle enBundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth", new Locale("en"));
        ResourceBundle ruBundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth");
        System.out.println(enBundle.getString("welcomeText"));
        String welcomeText = new String(ruBundle.getString("welcomeText").getBytes("us-ascii"), "UTF-8");
        System.out.println(welcomeText);
        System.out.println(ruBundle.getString("welcomeText"));

    }
}
