package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;

import java.io.Serializable;

public interface Command extends Serializable {
    String execute();
    String getName();
    void setCollectionManager(CollectionManager cm);
}

