module pl.ochnios.pamiw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires lombok;
    requires com.google.guice;

    exports pl.ochnios.pamiw;
    opens pl.ochnios.pamiw to javafx.fxml;
    exports pl.ochnios.pamiw.core;
    opens pl.ochnios.pamiw.core to javafx.fxml;
    exports pl.ochnios.pamiw.views;
    opens pl.ochnios.pamiw.views to javafx.fxml;
    exports pl.ochnios.pamiw.models;
    exports pl.ochnios.pamiw.models.services;

    exports pl.ochnios.pamiw.models.auxiliary.location to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.auxiliary.currentconditions to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.auxiliary.hourlyforecast to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.auxiliary.dailyforecast to com.fasterxml.jackson.databind;
}