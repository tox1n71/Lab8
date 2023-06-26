package ru.itmo.lab8.lab8v2.bundles.add;

import java.util.ListResourceBundle;

public class Add_ru extends ListResourceBundle {
    private Object[][] contents = {
            {"nameLabel", "Имя:"},
            {"coordinatesXLabel", "Координата X:"},
            {"coordinatesYLabel", "Координата Y:"},
            {"salaryLabel", "Заработная плата:"},
            {"startDateLabel", "Дата начала работы:"},
            {"endDateLabel", "Дата окончания работы:"},
            {"positionLabel", "Должность:"},
            {"organizationFullNameLabel", "Название организации:"},
            {"organizationAnnualTurnoverLabel", "Годовой оборот организации:"},
            {"organizationEmployeesCountLabel", "Количество сотрудников организации:"},
            {"organizationAddressStreetLabel", "Адрес организации (улица):"},
            {"organizationAddressZipCodeLabel", "Адрес организации (индекс):"},
            {"organizationAddressTownLabel", "Адрес организации (город):"},
            {"organizationAddressLocationXLabel", "Адрес организации (координата X):"},
            {"organizationAddressLocationYLabel", "Адрес организации (координата Y):"},
            {"organizationAddressLocationZLabel", "Адрес организации (координата Z):"},
            {"addingLabel", "Добавление нового работника"},
            {"submitButton", "Добавить"},
            {"successAdding", "Введенный элемент добавлен в коллекцию с id: "},
            {"notUnique", "Работник не уникален и не был добавлен в коллекцию"},

    };
    protected Object[][] getContents() {
        return contents;
    }

}

