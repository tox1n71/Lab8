package ru.itmo.lab8.lab8v2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import ru.itmo.lab8.lab8v2.commands.LoginCommand;
import ru.itmo.lab8.lab8v2.readers.UserReader;
import ru.itmo.lab8.lab8v2.utils.User;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static ru.itmo.lab8.lab8v2.HelloApplication.*;

public class HelloController {
    //TODO: заменить enBundle на банлд, который будет доставать по локали которая будет определена уже

    @FXML
    private Label welcomeText = new Label();
    @FXML
    private Button toRegisterButton;
    @FXML
    private Button signInButton;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox<String> languageChoiceBox;
    @FXML
    private Label serverAuthResponse;
    private int authResponseNumber;
    @FXML
    private Label loginResponse;
    @FXML
    private Label passwordResponse;
    @FXML
    private ImageView imageView;



    public void initialize() {
        setupLanguageChoiceBox(languageChoiceBox, this::updateText, "ru.itmo.lab8.lab8v2.bundles.auth.Auth", imageView);
    }

    private void updateText(ResourceBundle bundle) {
        String loginFieldText = bundle.getString("loginField");
        String passwordFieldText = bundle.getString("passwordField");
        String welcomeTextString = bundle.getString("welcomeText");
        welcomeText.setText(welcomeTextString);
        login.setPromptText(loginFieldText);
        password.setPromptText(passwordFieldText);
        String registerButtonText = bundle.getString("registerButtonText");
        toRegisterButton.setText(registerButtonText);

        String signInButtonText = bundle.getString("signInButton");
        signInButton.setText(signInButtonText);

        switch (authResponseNumber) {
            case 1:
                serverAuthResponse.setText(bundle.getString("signIn.invalidPassword"));
                break;
            case 2:
                serverAuthResponse.setText(bundle.getString("signIn.userNotFound"));
                break;
            case 3:
                serverAuthResponse.setText(bundle.getString("signIn.authError"));
                break;
            default:
                break;
        }
        if (!loginResponse.getText().isEmpty()) {
            loginResponse.setText(bundle.getString("nullLogin"));
        }
        if (!passwordResponse.getText().isEmpty()) {
            passwordResponse.setText(bundle.getString("nullPassword"));
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        String path = "registerScene.fxml";
        try {
            changeScene(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }


    @FXML
    protected void onSignInButtonClick() {
        //TODO: сделать проверку на то что пользователь зарегистрировался
        String loginText = login.getText().trim();
        String passwordText = password.getText().trim();
        if (!loginText.isEmpty() && !passwordText.isEmpty()) {
            passwordResponse.setText("");
            loginResponse.setText("");
            User user = new User(UserReader.getFreeId(), loginText, passwordText);
            LoginCommand loginCommand = new LoginCommand(user);
            String loginResponse = null;
            try {
                loginResponse = Client.sendCommandWithUserToServer(loginCommand);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (loginResponse.equals("Вход выполнен")) {
                setCurrentUser(user);
                try {
                    changeScene("mainProgrammScene.fxml");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth", currentLocale);
                switch (loginResponse) {
                    case "Неверный пароль.":
                        authResponseNumber = 1;
                        serverAuthResponse.setText(bundle.getString("signIn.invalidPassword"));
                        break;
                    case "Такого пользователя нет в системе.":
                        authResponseNumber = 2;
                        serverAuthResponse.setText(bundle.getString("signIn.userNotFound"));
                        break;
                    case "Ошибка авторизации":
                        authResponseNumber = 3;
                        serverAuthResponse.setText(bundle.getString("signIn.authError"));
                        break;
                    default:
                        break;
                }


            }
        } else if (loginText.isEmpty() && !passwordText.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth", currentLocale);
            loginResponse.setText(bundle.getString("nullLogin"));
            passwordResponse.setText("");
        } else if (passwordText.isEmpty() && !loginText.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth", currentLocale);
            passwordResponse.setText(bundle.getString("nullPassword"));
            loginResponse.setText("");
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.auth", currentLocale);
            loginResponse.setText(bundle.getString("nullLogin"));
            passwordResponse.setText(bundle.getString("nullPassword"));
        }
    }


}