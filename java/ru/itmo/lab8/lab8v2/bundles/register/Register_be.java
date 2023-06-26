package ru.itmo.lab8.lab8v2.bundles.register;

import java.util.ListResourceBundle;

public class Register_be extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Рэгістрацыя"},
            {"registerButton", "Зарэгістравацца"},
            {"loginField", "Лагін"},
            {"passwordField", "Пароль"},
            {"userAlreadyExist", "Карыстальнік з такім лагінам ужо існуе"},
            {"signUpException", "Памылка рэгістрацыі"},
            {"nullPassword", "Пароль не можа быць пустым"},
            {"nullLogin", "Лагін не можа быць пустым"},
            {"returnToAuthButton", "Вярнуцца да аўтарызацыі"}
    };
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
