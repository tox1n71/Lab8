package ru.itmo.lab8.lab8v2.exceptions;

import java.io.IOException;

public class InputException extends IOException {

    public InputException(String msg){
        super(msg);
    }
    public InputException(){}
}
