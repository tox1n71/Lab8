package ru.itmo.lab8.lab8v2.bundles.register;

import java.util.ListResourceBundle;

import java.util.ListResourceBundle;

public class Register_en_IE extends ListResourceBundle {
    private Object[][] contents = {
            {"welcomeText", "Registration"},
            {"registerButton", "Register"},
            {"loginField", "Login"},
            {"passwordField", "Password"},
            {"userAlreadyExist", "User with this username already exist"},
            {"signUpException", "Registration error"},
            {"nullPassword", "Password can't be empty"},
            {"nullLogin", "Login can't be empty"},
            {"returnToAuthButton", "Return to auth"}
    };
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
