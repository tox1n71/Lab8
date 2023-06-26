package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;

public class PrintDescendingCommand implements Command{
    private String name = "print_descending";
    private CollectionManager cm;

    @Override
    public String execute(){
        return cm.printDescending();
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



