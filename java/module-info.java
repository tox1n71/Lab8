module ru.itmo.lab8.lab8v2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires java.desktop;


    exports ru.itmo.lab8.lab8v2.commands;
    opens ru.itmo.lab8.lab8v2 to javafx.fxml;
    exports ru.itmo.lab8.lab8v2;
    exports ru.itmo.lab8.lab8v2.bundles.auth;
    opens ru.itmo.lab8.lab8v2.bundles.auth to javafx.fxml;
}