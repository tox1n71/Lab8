package ru.itmo.lab8.lab8v2.commands;


import ru.itmo.lab8.lab8v2.CollectionManager;
import ru.itmo.lab8.lab8v2.utils.User;

public class RemoveByIdCommand implements Command {
    private int id;
    private String name = "remove_by_id";
    private CollectionManager collectionManager;
    private User user;

    public RemoveByIdCommand(int id, User user) {
        this.id = id;
        this.user = user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String execute(){
        return collectionManager.removeById(id, user);
    }

    @Override
    public String getName() {
        return name;
    }
    public void setCollectionManager(CollectionManager cm) {
        this.collectionManager = cm;
    }
}
    // Геттеры и сеттеры

