package ru.itmo.lab8.lab8v2.bundles.register;

import java.util.ListResourceBundle;

public class Register_bg extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Регистрация"},
            {"registerButton", "Регистрирай се"},
            {"loginField", "Потребителско име"},
            {"passwordField", "Парола"},
            {"userAlreadyExist", "Потребител с това потребителско име вече съществува"},
            {"signUpException", "Грешка при регистрация"},
            {"nullPassword", "Паролата не може да бъде празна"},
            {"nullLogin", "Потребителското име не може да бъде празно"},
            {"returnToAuthButton", "Върни се към авторизацията"}
    };
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
