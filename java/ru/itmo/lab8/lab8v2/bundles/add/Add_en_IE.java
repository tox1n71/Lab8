package ru.itmo.lab8.lab8v2.bundles.add;

import java.util.ListResourceBundle;

public class Add_en_IE extends ListResourceBundle {
    private Object[][] contents = {
            {"nameLabel", "Name:"},
            {"coordinatesXLabel", "X coordinate:"},
            {"coordinatesYLabel", "Y coordinate:"},
            {"salaryLabel", "Salary:"},
            {"startDateLabel", "Start date:"},
            {"endDateLabel", "End date:"},
            {"positionLabel", "Position:"},
            {"organizationFullNameLabel", "Organization full name:"},
            {"organizationAnnualTurnoverLabel", "Organization annual turnover:"},
            {"organizationEmployeesCountLabel", "Organization employees count:"},
            {"organizationAddressStreetLabel", "Organization address (street):"},
            {"organizationAddressZipCodeLabel", "Organization address (zip code):"},
            {"organizationAddressTownLabel", "Organization address (town):"},
            {"organizationAddressLocationXLabel", "Organization address (X coordinate):"},
            {"organizationAddressLocationYLabel", "Organization address (Y coordinate):"},
            {"organizationAddressLocationZLabel", "Organization address (Z coordinate):"},
            {"addingLabel", "New worker adding"},
            {"submitButton", "add"},
            {"successAdding", "The entered element has been added to the collection with id: "},
            {"notUnique", "The worker is not unique and was not added to the collection"},
    };
    protected Object[][] getContents() {
        return contents;
    }

}

