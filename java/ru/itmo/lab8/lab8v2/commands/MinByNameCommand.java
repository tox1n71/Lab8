package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;

public class MinByNameCommand implements Command {
    private String name = "min_by_name";
    private CollectionManager cm;

    @Override
    public String execute(){
        return cm.minByName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCollectionManager(CollectionManager cm) {
        this.cm = cm;
    }
}