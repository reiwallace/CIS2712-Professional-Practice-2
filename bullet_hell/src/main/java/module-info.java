module pp2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens pp2 to javafx.fxml;
    exports pp2;
}
