package ru.itmo.lab8.lab8v2;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LanguageListCell extends ListCell<LanguageItem> {
    private final HBox container;
    private final Label languageLabel;
    private final ImageView flagImageView;

    public LanguageListCell() {
        super();
        container = new HBox();
        languageLabel = new Label();
        flagImageView = new ImageView();
        container.getChildren().addAll(flagImageView, languageLabel);
        container.setSpacing(10);
        setGraphic(container);
    }

    @Override
    protected void updateItem(LanguageItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            languageLabel.setText(item.getLanguageName());
            languageLabel.setTranslateY(5);
            flagImageView.setImage(new Image(getClass().getResourceAsStream(item.getFlagImagePath())));
            flagImageView.setFitHeight(24);
            flagImageView.setFitWidth(24);
            languageLabel.setTextFill(Color.BLACK);
            setGraphic(container);
        }
    }

}