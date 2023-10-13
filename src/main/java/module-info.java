module pl.ochnios.pamiw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens pl.ochnios.pamiw to javafx.fxml;
    exports pl.ochnios.pamiw;
}