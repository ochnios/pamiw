module pl.ochnios.todofrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.ochnios.todofrontend to javafx.fxml;
    exports pl.ochnios.todofrontend;
}