module pl.ochnios.pamiw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens pl.ochnios.pamiw to javafx.fxml;
    exports pl.ochnios.pamiw;
}