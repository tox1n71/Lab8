package ru.itmo.lab8.lab8v2.bundles.auth;

import java.util.ListResourceBundle;

public class Auth_be extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Аўтарызацыя"},
            {"registerButtonText", "Рэгістрацыя"},
            {"signInButton", "Уваход"},
            {"loginField", "Лагін"},
            {"passwordField", "Пароль"},
            {"signIn.userNotFound", "Карыстальнік не знойдзены!\n\tКалі ласка, паспрабуйце зноў"},
            {"signIn.invalidPassword", "Няправільны пароль!\n\tКалі ласка, паспрабуйце зноў"},
            {"signIn.authError", "Памылка аўтарызацыі!\n\tКалі ласка, паспрабуйце пазней."},
            {"nullPassword", "Пароль не можа быць пустым"},
            {"nullLogin", "Лагін не можа быць пустым"}
    };
    protected Object[][] getContents() {
        return contents;
    }
}
