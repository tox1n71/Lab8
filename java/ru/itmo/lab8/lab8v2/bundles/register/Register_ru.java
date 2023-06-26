package ru.itmo.lab8.lab8v2.bundles.register;

import java.util.ListResourceBundle;

public class Register_ru extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Регистрация"},
            {"registerButton", "Зарегистрироваться"},
            {"loginField", "Логин"},
            {"passwordField", "Пароль"},
            {"userAlreadyExist", "Пользователь с таким именем уже существует"},
            {"signUpException", "Ошибка регистрации"},
            {"nullPassword", "Пароль не может быть пустым"},
            {"nullLogin", "Логин не может быть пустым"},
            {"returnToAuthButton", "Вернуться к авторизации"}
    };
    @Override
    public Object[][] getContents() {
        return contents;
    }
}