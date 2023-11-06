module pl.ochnios.todofrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.guice;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires static lombok;

    opens pl.ochnios.todofrontend to javafx.fxml;
    exports pl.ochnios.todofrontend;
    opens pl.ochnios.todofrontend.core to javafx.fxml, com.fasterxml.jackson.databind;
    exports pl.ochnios.todofrontend.core;
    opens pl.ochnios.todofrontend.views to javafx.fxml;
    exports pl.ochnios.todofrontend.views;
    opens pl.ochnios.todofrontend.viewmodels to javafx.fxml;
    exports pl.ochnios.todofrontend.viewmodels;

    exports pl.ochnios.todofrontend.models;
    exports pl.ochnios.todofrontend.models.services;
    exports pl.ochnios.todofrontend.models.dtos;
}