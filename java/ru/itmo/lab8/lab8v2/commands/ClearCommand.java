package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;
import ru.itmo.lab8.lab8v2.utils.User;


public class ClearCommand implements Command{
    private String name = "clear";
    private CollectionManager cm;
    private User user;

    public ClearCommand(User user){
        this.user = user;
    }

    @Override
    public String execute(){
        return cm.clear(user);
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
