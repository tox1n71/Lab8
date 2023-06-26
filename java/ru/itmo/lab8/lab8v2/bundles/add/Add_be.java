package ru.itmo.lab8.lab8v2.bundles.add;

import java.util.ListResourceBundle;

public class Add_be extends ListResourceBundle {
    private Object[][] contents = {
            {"nameLabel", "Імя:"},
            {"coordinatesXLabel", "Каардыната X:"},
            {"coordinatesYLabel", "Каардыната Y:"},
            {"salaryLabel", "Заработная плата:"},
            {"startDateLabel", "Дата пачатку працы:"},
            {"endDateLabel", "Дата заканчэння працы:"},
            {"positionLabel", "Пазіцыя:"},
            {"organizationFullNameLabel", "Назва арганізацыі:"},
            {"organizationAnnualTurnoverLabel", "Гадавы абарот арганізацыі:"},
            {"organizationEmployeesCountLabel", "Колькасць супрацоўнікаў арганізацыі:"},
            {"organizationAddressStreetLabel", "Адрас арганізацыі (вуліца):"},
            {"organizationAddressZipCodeLabel", "Адрас арганізацыi(пiчтвовiй iндыкс"},
            {"organizationAddressTownLabel", "Адрас арганізацыі (горад):"},
            {"organizationAddressLocationXLabel", "Адрас арганізацыі (каардыната X):"},
            {"organizationAddressLocationYLabel", "Адрас арганізацыі (каардыната Y):"},
            {"organizationAddressLocationZLabel", "Адрас арганізацыі (каардыната Z):"},
            {"addingLabel", "Даданне новага работніка"},
            {"submitButton", "Дадаць"},
            {"successAdding", "Уводжаны элемент дададзены ў калекцыю з id: "},
            {"notUnique", "Працоўнік не ўнікальны і не быў дададзены ў калекцыю"},
    };
    protected Object[][] getContents() {
        return contents;
    }

}

