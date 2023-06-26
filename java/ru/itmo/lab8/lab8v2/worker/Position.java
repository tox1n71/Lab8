package ru.itmo.lab8.lab8v2.worker;


import ru.itmo.lab8.lab8v2.exceptions.InputException;

import java.io.Serializable;

public enum Position implements Serializable {
    LABORER,
    ENGINEER,
    HEAD_OF_DEPARTMENT,
    LEAD_DEVELOPER,
    BAKER;

    public static Position stringToPosition(String pos) throws InputException {
        return switch (pos) {
            case "LABORER" -> Position.LABORER;
            case "ENGINEER" -> Position.ENGINEER;
            case "HEAD_OF_DEPARTMENT" -> Position.HEAD_OF_DEPARTMENT;
            case "LEAD_DEVELOPER" -> Position.LEAD_DEVELOPER;
            case "BAKER" -> Position.BAKER;
            default -> throw new InputException();
        };
    }
}
