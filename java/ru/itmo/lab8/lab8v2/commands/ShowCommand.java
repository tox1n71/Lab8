package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;

public class ShowCommand implements Command{
    private String name = "show";
    private CollectionManager cm;

    @Override
    public String execute(){
        return cm.show();
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
