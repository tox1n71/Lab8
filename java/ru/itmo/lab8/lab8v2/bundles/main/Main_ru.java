package ru.itmo.lab8.lab8v2.bundles.main;

import java.util.ListResourceBundle;

public class Main_ru extends ListResourceBundle {
    private Object[][] contents ={ {"currentUser", "Текущий пользователь:"},
            {"logOutButton", "Сменить пользователя"},
            {"idColumn", "идентификатор"},
            {"nameColumn", "имя"},
            {"ownerColumn", "владелец"},
            {"coordinateXColumn", "координата X"},
            {"coordinateYColumn", "координата Y"},
            {"creationDateColumn", "дата создания"},
            {"salaryColumn", "зарплата"},
            {"startDateColumn", "дата начала"},
            {"endDateColumn", "дата окончания"},
            {"positionColumn", "должность"},
            {"organizationFullNameColumn", "полное название организации"},
            {"organizationAnnualTurnoverColumn", "годовой оборот"},
            {"organizationEmployeesCountColumn", "количество сотрудников"},
            {"organizationAddressStreetColumn", "улица"},
            {"organizationAddressZipCodeColumn", "почтовый индекс"},
            {"organizationAddressTownColumn", "город"},
            {"organziationAddressLocationXColumn", "координата X"},
            {"organziationAddressLocationYColumn", "координата Y"},
            {"organziationAddressLocationZColumn", "координата Z"},
            {"collectionType", "Тип коллекции: "},
            {"collectionSize", "\nКоличество элементов в коллекции: "},
            {"collectionDate", "\nДата инициализации: "},
            {"infoHeader", "Информация о коллекции"},
            {"clearHeader", "Удаление ваших обьектов"},
            {"minByName", "Минимальный по имени"},
            {"editHeader", "Ошибка!"},
            {"editText", "Этот обьект не принадлежит вам"},
            {"historyHeader", "История вводимых команд"},
            {"help", "Справка"},
            {"helpText", "help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} : добавить новый элемент в коллекцию\n" +
                    "edit {element} : обновить значение элемента коллекции" +
                    "remove : удалить элемент из коллекции по его id\n" +
                    "clear : очистить вашу часть коллекции\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                    "history : вывести последние 8 команд (без их аргументов)\n" +
                    "min_by_name : вывести любой объект из коллекции, значение поля name которого является минимальным\n" +
                    "sort_descending : вывести элементы коллекции в порядке убывания"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
