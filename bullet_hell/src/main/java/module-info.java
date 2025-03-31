module pp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;

    opens pp2 to javafx.fxml;
    exports pp2;
}
