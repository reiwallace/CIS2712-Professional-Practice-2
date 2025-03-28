package pp2.GUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainWindow {
    private Stage stage = new Stage();
    private GridPane pane = new GridPane();
    private Scene scene = new Scene(pane);
    private GameFrame gameFrame;
    private PlayerStatisticsPanel statsPanel;

    private String title = "Bullet Hell"; // Stage title
    private int rows = 30; // Number of rows in the grid pane
    private int columns = 40; // Number of columns in the grid pane

    private final int gameRowSpan = 28;
    private final int gameColumnSpan = 24;
    private final int gameInitialRow = 1;
    private final int gameInitialColumn = 2;
    private ArrayList

    public double titleBarHeight = 37.5999755859375;

    /** Initialises main game window
     * @param debug - Whether to load debug menu or not
     * @throws FileNotFoundException 
     */
    public MainWindow(boolean debug) throws FileNotFoundException {
        // Define height to calculate title bar height later
        stage.setMaxHeight(100);
        stage.setMaxWidth(100);

        // Create grid rows for pane
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rows);
            pane.getRowConstraints().add(row);
        }
        // Create grid columns for pane and add backdrop 
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / columns);
            pane.getColumnConstraints().add(column);

            Rectangle squaretangle = new Rectangle();
            squaretangle.setWidth(5);
            squaretangle.setHeight(5);
            squaretangle.setVisible(true);
            squaretangle.setFill(Color.BLACK);
            pane.add(squaretangle, i, i);
        }

        // Configure stage settings
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);

        // Enable debug mode
        if(debug) new DebugMenu(this);
    }

    /** Set stage to windowed mode.
     */
    public void windowedMode() {
        setSize(920);
    }

    /** Set stage to fullscreen mode.
     */
    public void fullScreenMode() {
        setSize(1404);
    }

    public void startGame() {
        try {
            gameFrame = new GameFrame(stage.getHeight() - titleBarHeight, stage.getWidth(), pane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        statsPanel = new PlayerStatisticsPanel(this);
    }

    // Getters.
    public Stage getStage() { return stage; }
    public Scene getScene() { return scene; }
    public GridPane getGrid() { return pane; }
    public double getTitleBarHeight() { return titleBarHeight; }
    public GameFrame getGameFrame() { return gameFrame; }

    /** Change stage size with an aspect ratio of 4:3 from the width.
     * @param width - Width to calculate size from
     */
    private void setSize(double width) {
        stage.setMaxWidth(width);
        stage.setMaxHeight(width * 0.75);
        stage.setMinWidth(width);
        stage.setMinHeight(width * 0.75);
    }
}