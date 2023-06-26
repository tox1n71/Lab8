package ru.itmo.lab8.lab8v2.bundles.auth;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class Auth_ru extends ListResourceBundle {
    private Object[][] contents = {
        {"welcomeText", "Авторизация"},
        {"registerButtonText", "Регистрация"},
        {"signInButton", "Войти"},
        {"loginField", "Логин"},
        {"passwordField", "Пароль"},
        {"signIn.userNotFound", "Пользователь не найден!\n\tПовторите попытку"},
        {"signIn.invalidPassword", "Неверный пароль!\n\tПовторите попытку"},
        {"signIn.authError", "Ошибка авторизации!\n\tПовторите попытку позже."},
        {"nullPassword", "Пароль не может быть пустым"},
        {"nullLogin", "Логин не может быть пустым"}
    };
    protected Object[][] getContents() {
        return contents;
    }

}
