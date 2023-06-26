package ru.itmo.lab8.lab8v2.bundles.main;

import java.util.ListResourceBundle;

public class Main_bg extends ListResourceBundle {
    private Object[][] contents = {
            {"currentUser", "Текущ потребител:"},
            {"logOutButton", "Смяна на потребителя"},
            {"idColumn", "идентификатор"},
            {"nameColumn", "име"},
            {"ownerColumn", "собственик"},
            {"coordinateXColumn", "координата X"},
            {"coordinateYColumn", "координата Y"},
            {"creationDateColumn", "дата на създаване"},
            {"salaryColumn", "заплата"},
            {"startDateColumn", "дата на започване"},
            {"endDateColumn", "дата на приключване"},
            {"positionColumn", "позиция"},
            {"organizationFullNameColumn", "пълно име на организацията"},
            {"organizationAnnualTurnoverColumn", "годишен оборот"},
            {"organizationEmployeesCountColumn", "брой служители"},
            {"organizationAddressStreetColumn", "улица"},
            {"organizationAddressZipCodeColumn", "пощенски код"},
            {"organizationAddressTownColumn", "град"},
            {"organziationAddressLocationXColumn", "координата X"},
            {"organziationAddressLocationYColumn", "координата Y"},
            {"organziationAddressLocationZColumn", "координата Z"},
            {"collectionType", "Тип на колекцията: "},
            {"collectionSize", "\nРазмер на колекцията: "},
            {"collectionDate", "\nДата на инициализация: "},
            {"infoHeader", "Информация за колекцията"},
            {"minByName", "Мінімальны па імені"},
            {"clearHeader", "Премахване на вашите обекти"},
            {"editHeader", "Ошибка!"},
            {"editText", "Этот обьект не принадлежит вам"},
            {"editHeader", "Грешка!"},
            {"editText", "Този обект не принадлежи на вас"},
            {"historyHeader", "История на въведените команди"},
            {"help", "Помощ"},
            {"helpText", "help : извеждане на помощ за наличните команди\n" +
                    "info : извеждане на информация за колекцията (тип, дата на инициализация, брой елементи и т.н.)\n" +
                    "show : извеждане на всички елементи на колекцията във вид на текстов низ\n" +
                    "add {елемент} : добавяне на нов елемент към колекцията\n" +
                    "edit {елемент} : обновяване на стойността на определен елемент от колекцията\n" +
                    "remove : изтриване на елемент от колекцията по дадено id\n" +
                    "clear : изчистване на Вашата част от колекцията\n" +
                    "execute_script file_name : зареждане и изпълнение на скрипт от указания файл. Скриптът съдържа команди в същия формат, в който ги въвежда потребителят в интерактивен режим.\n" +
                    "add_if_min {елемент} : добавяне на нов елемент в колекцията, ако стойността му е по-малка от най-малкия елемент в тази колекция\n" +
                    "remove_lower {елемент} : изтриване на всички елементи от колекцията, по-малки от зададения\n" +
                    "history : извеждане на последните 8 команди (без техните аргументи)\n" +
                    "min_by_name : извеждане на който и да е обект от колекцията, чиято стойност на полето име е минимална\n" +
                    "sort_descending : извеждане на елементите на колекцията в низходящ ред"}


    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}