module pl.ochnios.pamiw {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.ochnios.pamiw to javafx.fxml;
    exports pl.ochnios.pamiw;
}