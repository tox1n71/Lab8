package ru.itmo.lab8.lab8v2.bundles.add;

import java.util.ListResourceBundle;

public class Add_bg extends ListResourceBundle {
    private Object[][] contents = {
            {"nameLabel", "Име:"},
            {"coordinatesXLabel", "Координата X:"},
            {"coordinatesYLabel", "Координата Y:"},
            {"salaryLabel", "Заплата:"},
            {"startDateLabel", "Дата на постъпване на работа:"},
            {"endDateLabel", "Дата на напускане на работа:"},
            {"positionLabel", "Длъжност:"},
            {"organizationFullNameLabel", "Пълно име на организацията:"},
            {"organizationAnnualTurnoverLabel", "Годишен оборот на организацията:"},
            {"organizationEmployeesCountLabel", "Брой служители на организацията:"},
            {"organizationAddressStreetLabel", "Адрес на организацията (улица):"},
            {"organizationAddressZipCodeLabel", "Адрес на организацията (пощенски код):"},
            {"organizationAddressTownLabel", "Адрес на организацията (град):"},
            {"organizationAddressLocationXLabel", "Адрес на организацията (координата X):"},
            {"organizationAddressLocationYLabel", "Адрес на организацията (координата Y):"},
            {"organizationAddressLocationZLabel", "Адрес на организацията (координата Z):"},
            {"addingLabel", "Добавлячинъя напускного работияги"},
            {"submitButton", "Добавытъ"},
            {"successAdding", "Въведеният елемент е добавен към колекцията с id: "},
            {"notUnique", "Служителят не е уникален и не бе добавен в колекцията"},
    };
    protected Object[][] getContents() {
        return contents;
    }

}

