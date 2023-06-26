package ru.itmo.lab8.lab8v2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import ru.itmo.lab8.lab8v2.commands.LoginCommand;
import ru.itmo.lab8.lab8v2.commands.RegisterCommand;
import ru.itmo.lab8.lab8v2.readers.UserReader;
import ru.itmo.lab8.lab8v2.utils.User;

import static ru.itmo.lab8.lab8v2.HelloApplication.*;

public class RegisterController {
    //TODO: заменить enBundle на банлд, который будет доставать по локали которая будет определена уже

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registerButton;

    @FXML
    private Label welcomeText;
    @FXML
    private ChoiceBox<String> languageChoiceBox;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label serverRegisterResponse;
    @FXML
    private Label loginResponse;

    private int registerResponseNumber;
    @FXML
    private Label passwordResponse;
    @FXML
    private Button returnToAuthButton;
    @FXML
    private ImageView imageView;

    @FXML
    void initialize() {
        setupLanguageChoiceBox(languageChoiceBox, this::updateText, "ru.itmo.lab8.lab8v2.bundles.register.Register", imageView);
    }

    @FXML
    void updateText(ResourceBundle bundle) {
        String loginFieldText = bundle.getString("loginField");
        String passwordFieldText = bundle.getString("passwordField");
        login.setPromptText(loginFieldText);
        password.setPromptText(passwordFieldText);
        welcomeText.setText(bundle.getString("welcomeText"));
        registerButton.setText(bundle.getString("registerButton"));
        returnToAuthButton.setText(bundle.getString("returnToAuthButton"));
    }

    @FXML
    protected void registerButtonOnClick() {
        String loginText = login.getText().trim();
        String passwordText = password.getText().trim();
        if (!loginText.isEmpty() && !passwordText.isEmpty()) {
            User user = new User(UserReader.getFreeId(), loginText, passwordText);
            RegisterCommand registerCommand = new RegisterCommand(user);
            String registerResponse = null;
            try {
                registerResponse = Client.sendCommandWithUserToServer(registerCommand);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (registerResponse.equals("Успешная регистрация. Воспользуйтесь командой login, чтобы авторизоваться")) {
                try {
                    Client.sendCommandWithUserToServer(new LoginCommand(user));
                    setCurrentUser(user);
                    changeScene("mainProgramScene.fxml");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.register", currentLocale);
                switch (registerResponse) {
                    case "Пользователь с таким именем уже существует":
                        registerResponseNumber = 1;
                        serverRegisterResponse.setText(bundle.getString("userAlreadyExist"));
                        break;
                    case "Ошибка регистрации":
                        registerResponseNumber = 2;
                        serverRegisterResponse.setText(bundle.getString("signUpException"));
                        break;
                    default:
                        break;
                }


            }
        } else if (loginText.isEmpty() && !passwordText.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.register", currentLocale);
            loginResponse.setText(bundle.getString("nullLogin"));
            passwordResponse.setText("");
        } else if (passwordText.isEmpty() && !loginText.isEmpty()) {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.register", currentLocale);
            passwordResponse.setText(bundle.getString("nullPassword"));
            loginResponse.setText("");
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("ru.itmo.lab8.lab8v2.register", currentLocale);
            loginResponse.setText(bundle.getString("nullLogin"));
            passwordResponse.setText(bundle.getString("nullPassword"));
        }
    }

    @FXML
    protected void returnToAuthButtonOnClik(){
        try {
            changeScene("hello-view.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
