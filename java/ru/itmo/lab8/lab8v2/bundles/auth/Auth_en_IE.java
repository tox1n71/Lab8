package ru.itmo.lab8.lab8v2.bundles.auth;

import java.util.ListResourceBundle;

public class Auth_en_IE extends ListResourceBundle {
    private Object[][] contets = {
            {"welcomeText", "Authorization"},
            {"registerButtonText", "Registration"},
            {"signInButton", "Sign in"},
            {"loginField", "Login"},
            {"passwordField", "Give me your password( ͡° ͜ʖ ͡°)"},
            {"signIn.userNotFound", "User not found!\n\tPlease try again"},
            {"signIn.invalidPassword", "Invalid password!\n\tPlease try again"},
            {"signIn.authError", "Authorization error!\n\tPlease try again later."},
            {"nullPassword", "Password can't be empty"},
            {"nullLogin", "Login can't be empty"}
    };
    @Override
    protected Object[][] getContents() {
        return contets;
    }
}
