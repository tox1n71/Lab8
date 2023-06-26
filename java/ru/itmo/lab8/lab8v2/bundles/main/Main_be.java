package ru.itmo.lab8.lab8v2.bundles.main;

import java.util.ListResourceBundle;

public class Main_be extends ListResourceBundle {
    private Object[][] contents = {
            {"currentUser", "Бягучы карыстальнік:"},
            {"logOutButton", "Змяніць карыстальніка"},
            {"idColumn", "ідэнтыфікатар"},
            {"nameColumn", "імя"},
            {"ownerColumn", "валадальнік"},
            {"coordinateXColumn", "каардыната X"},
            {"coordinateYColumn", "каардыната Y"},
            {"creationDateColumn", "дата стварэння"},
            {"salaryColumn", "зарплата"},
            {"startDateColumn", "дата пачатку"},
            {"endDateColumn", "дата заканчэння"},
            {"positionColumn", "пасада"},
            {"organizationFullNameColumn", "поўнае назва арганізацыі"},
            {"organizationAnnualTurnoverColumn", "гадавы абарот"},
            {"organizationEmployeesCountColumn", "колькасць супрацоўнікаў"},
            {"organizationAddressStreetColumn", "вуліца"},
            {"organizationAddressZipCodeColumn", "паштовы індэкс"},
            {"organizationAddressTownColumn", "горад"},
            {"organziationAddressLocationXColumn", "каардыната X"},
            {"organziationAddressLocationYColumn", "каардыната Y"},
            {"organziationAddressLocationZColumn", "каардыната Z"},
            {"collectionType", "Тып калекцыі: "},
            {"collectionSize", "\nКолькасць у элементаў калекцыі: "},
            {"collectionDate", "\nДата ініцыялізацыі: "},
            {"infoHeader", "Інфармацыя пра калекцыю"},
            {"minByName", "Мінімальны па імені"},
            {"clearHeader", "Выдаленне вашых аб'ектаў"},
            {"editHeader", "Памылка!"},
            {"editText", "Гэты аб'ект не належыць вам"},
            {"historyHeader", "Гісторыя ўводжаных каманд"},
            {"help", "Даведка"},
            {"helpText", "help : вывесці даведку па даступных камандах\n" +
                    "info : вывесці ў стандартны поток вываду інфармацыю аб калекцыі (тып, дата ініцыялізацыі, колькасць элемэнтаў і г.д.)\n" +
                    "show : вывесці ў стандартны поток вываду ўсе элементы калекцыі ў радковым прадстаўленні\n" +
                    "add {element} : дадаць новы элемент у калекцыю\n" +
                    "edit {element} : абнавіць значэнне элемента калекцыі" +
                    "remove : выдаліць элемент з калекцыі па яго id\n" +
                    "clear : ачысціць вашу частку калекцыі\n" +
                    "execute_script file_name : зчитаць і выканаць скрыпт з пазначанага файла. У скрыпце змяшчаюцца каманды ў тым жа выглядзе, ў якім іх уводзіць карыстальнік у інтэрактыўным рэжыме.\n" +
                    "add_if_min {element} : дадаць новы элемент у калекцыю, калі яго значэнне меншае, чым у самога меншага элемента гэтай калекцыі\n" +
                    "remove_lower {element} : выдаліць з калекцыі ўсе элементы, меншыя за зададзены\n" +
                    "history : вывесці апошнія 8 камандаў (без іх аргументаў)\n" +
                    "min_by_name : вывесці любы аб'ект з калекцыі, значэнне поля name якога з'яўляецца найменшым\n" +
                    "sort_descending : вывесці элементы калекцыі ў парадку змяншэння"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
