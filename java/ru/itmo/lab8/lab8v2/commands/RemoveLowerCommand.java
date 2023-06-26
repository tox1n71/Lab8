package ru.itmo.lab8.lab8v2.commands;


import ru.itmo.lab8.lab8v2.CollectionManager;
import ru.itmo.lab8.lab8v2.utils.User;
import ru.itmo.lab8.lab8v2.worker.Worker;

public class RemoveLowerCommand implements Command {


    private Worker worker;
    private String name = "remove_lower";
    private User user;
    CollectionManager collectionManager;
    public RemoveLowerCommand(Worker worker, User user) {
        this.worker = worker;
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String execute(){
        return collectionManager.removeLower(worker, user);
    }

    @Override
    public String getName() {
        return name;
    }
    public void setCollectionManager(CollectionManager cm) {
        this.collectionManager = cm;
    }
}