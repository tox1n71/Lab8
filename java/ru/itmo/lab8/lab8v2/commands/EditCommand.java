package ru.itmo.lab8.lab8v2.commands;

import ru.itmo.lab8.lab8v2.CollectionManager;
import ru.itmo.lab8.lab8v2.worker.Worker;

public class EditCommand implements Command{
    private String name = "edit";
    private CollectionManager cm;
    private Worker worker;
    public EditCommand(Worker worker){
        this.worker = worker;
    }

    @Override
    public String execute(){
        return cm.edit(worker);
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
