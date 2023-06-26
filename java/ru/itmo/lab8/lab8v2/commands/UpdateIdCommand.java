package ru.itmo.lab8.lab8v2.commands;


import ru.itmo.lab8.lab8v2.CollectionManager;
import ru.itmo.lab8.lab8v2.worker.Worker;

public class UpdateIdCommand implements Command {

    private String name = "update_by_id";
    private int id;
    private Worker worker;
    private CollectionManager collectionManager;
//    private OrganizationReader organizationReader;


    public UpdateIdCommand(int id, Worker worker) {
        this.id = id;
        this.worker = worker;
    }

    public String execute() {
        if (collectionManager.getWorkers().isEmpty()) {
            return "Коллекция пуста";
        } else if (!collectionManager.workerWithIdExist(id)) {
            return ("Работник с таким id не найден");
        } else {
            return collectionManager.updateById(id, worker);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCollectionManager(CollectionManager cm) {
        this.collectionManager = cm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    // Геттеры и сеттеры
}

