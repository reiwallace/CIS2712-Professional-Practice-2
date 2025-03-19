package pp2.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainWindow {
    private Stage stage = new Stage();
    private GridPane pane = new GridPane();
    private Scene scene = new Scene(pane);

    private String title = "Bullet Hell"; // Stage title
    private int rows = 30; // Number of rows in the grid pane
    private int columns = 40; // Number of columns in the grid pane

    public double titleBarHeight = 37.5999755859375;

    public MainWindow() {
        // Define height to calculate title bar height later
        stage.setMaxHeight(100);
        stage.setMaxWidth(100);
        // Create grid rows for pane
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rows);
            pane.getRowConstraints().add(row);
        }
        // Create grid columns for pane
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / columns);
            pane.getColumnConstraints().add(column);
        }

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
    }

    // Set stage to windowed mode.
    public void windowedMode() {
        setSize(920);
    }

    // Set stage to fullscreen mode.
    public void fullScreenMode() {
        setSize(1404);
    }

    // Getters.
    public Stage getStage() { return stage; }
    public Scene getScene() { return scene; }
    public GridPane getGrid() { return pane; }
    public double getTitleBarHeight() {

        return titleBarHeight; }

    // Change stage size with an aspect ratio of 4:3 from the width.
    private void setSize(double width) {
        stage.setMaxWidth(width);
        stage.setMaxHeight(width * 0.75);
        stage.setMinWidth(width);
        stage.setMinHeight(width * 0.75);

    }
}