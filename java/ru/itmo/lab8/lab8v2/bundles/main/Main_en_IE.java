package ru.itmo.lab8.lab8v2.bundles.main;

import java.util.ListResourceBundle;

public class Main_en_IE extends ListResourceBundle {
    private Object[][] contents ={ {"currentUser", "Current user:"},
            {"logOutButton", "Log out"},
            {"idColumn", "id"},
            {"nameColumn", "name"},
            {"ownerColumn", "owner"},
            {"coordinateXColumn", "coordinate x"},
            {"coordinateYColumn", "coordinate y"},
            {"creationDateColumn", "creation date"},
            {"salaryColumn", "salary"},
            {"startDateColumn", "start date"},
            {"endDateColumn", "end date"},
            {"positionColumn", "position"},
            {"organizationFullNameColumn", "organization fullname"},
            {"organizationAnnualTurnoverColumn", "annual turnover"},
            {"organizationEmployeesCountColumn", "employees count"},
            {"organizationAddressStreetColumn", "street"},
            {"organizationAddressZipCodeColumn", "zip code"},
            {"organizationAddressTownColumn", "town"},
            {"organziationAddressLocationXColumn", "location x"},
            {"organziationAddressLocationYColumn", "location y"},
            {"organziationAddressLocationZColumn", "location z"},
            {"collectionType", "Collection type: "},
            {"collectionSize", "\nCollection size: "},
            {"collectionDate", "\nInitialization date: "},
            {"infoHeader", "Information about collection"},
            {"minByName", "Minimum by name"},
            {"clearHeader", "Removing your objects"},
            {"editHeader", "Error!"},
            {"editText", "This object does not belong to you"},
            {"historyHeader", "Command's history"},
            {"help", "Help"},
            {"helpText", "help: display help for available commands\n" +
                    "info: display information about the collection (type, initialization date, number of elements, etc.)\n" +
                    "show: display all elements of the collection in string representation\n" +
                    "add {element}: add a new element to the collection\n" +
                    "edit {element}: update the value of an element in the collection\n" +
                    "remove: remove an element from the collection by its id\n" +
                    "clear: clear your part of the collection\n" +
                    "execute_script file_name: read and execute a script from the specified file. The script contains commands in the same format as the user enters them in interactive mode.\n" +
                    "add_if_min {element}: add a new element to the collection if its value is less than the smallest element in that collection\n" +
                    "remove_lower {element}: remove all elements from the collection that are smaller than the specified one\n" +
                    "history: display the last 8 commands (without their arguments)\n" +
                    "min_by_name: display any object from the collection whose name field value is minimal\n" +
                    "sort_descending: display the elements of the collection in descending order"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
