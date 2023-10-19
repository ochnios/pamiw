module pl.ochnios.pamiw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports pl.ochnios.pamiw;
    opens pl.ochnios.pamiw to javafx.fxml;
    exports pl.ochnios.pamiw.core;
    opens pl.ochnios.pamiw.core to javafx.fxml;
    exports pl.ochnios.pamiw.views;
    opens pl.ochnios.pamiw.views to javafx.fxml;

    exports pl.ochnios.pamiw.models.location to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.currentconditions to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.hourlyforecast to com.fasterxml.jackson.databind;
    exports pl.ochnios.pamiw.models.dailyforecast to com.fasterxml.jackson.databind;
}