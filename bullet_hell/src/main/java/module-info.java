module pp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens pp2 to javafx.fxml;
    exports pp2;
}
