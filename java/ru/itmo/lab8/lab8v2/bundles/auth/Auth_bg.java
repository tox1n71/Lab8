package ru.itmo.lab8.lab8v2.bundles.auth;

import java.util.ListResourceBundle;

public class Auth_bg extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Упълномощаване"},
            {"registerButtonText", "Регистрация"},
            {"signInButton", "Войти"},
            {"loginField", "Потребителско име"},
            {"passwordField", "Парола"},
            {"signIn.userNotFound", "Потребителят не е намерен!\n\tМоля, опитайте отново"},
            {"signIn.invalidPassword", "Невалидна парола!\n\tМоля, опитайте отново"},
            {"signIn.authError", "Грешка при авторизация!\n\tМоля, опитайте отново по-късно."},
            {"nullPassword", "Паролата не може да бъде празна"},
            {"nullLogin", "Потребителското име не може да бъде празно"}
    };
    protected Object[][] getContents() {
        return contents;
    }
}
