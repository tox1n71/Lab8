package ru.itmo.lab8.lab8v2;

import javafx.scene.image.ImageView;

public class LanguageItem {
    private final String languageName;
    private final String flagImagePath;
    private ImageView imageView;

    public LanguageItem(String languageName, String flagImagePath) {
        this.languageName = languageName;
        this.flagImagePath = flagImagePath;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getFlagImagePath() {
        return flagImagePath;
    }
    public void setImageView(){
        imageView = new ImageView(flagImagePath);
    }
}