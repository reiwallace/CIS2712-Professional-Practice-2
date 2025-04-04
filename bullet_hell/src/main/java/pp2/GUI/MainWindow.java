package pp2.GUI;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private MainMenu mainMenu;
    private String title = "Nostromo"; // Stage title
    private int rows = 30; // Number of rows in the grid pane
    private int columns = 40; // Number of columns in the grid pane

    private ImageView background = new ImageView(new Image("file:resources/Backgrounds/outside-background.png"));
    private ArrayList<Rectangle> backDrop = new ArrayList<Rectangle>();

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
        }

        // Add backdrop for window
        for(int i = 0; i < 40; i++) {
            for(int x = 0; x < 30; x++) {
                if((i < 2 || i > 26) || (x < 1 || x > 28)) {
                    Rectangle squaretangle = new Rectangle();
                    squaretangle.setWidth(0);
                    squaretangle.setHeight(0);
                    squaretangle.setVisible(false);
                    squaretangle.setFill(Color.WHITE);
                    pane.add(squaretangle, i, x);
                    backDrop.add(squaretangle);
                }
            }
        }

        // Configure stage settings
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);

        pane.add(background, 0, 0, 30, 40);
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

    /** Opens main menu
     */
    public void startMainMenu() {
        // create MainMenu object with reference to MainWindow
        mainMenu = new MainMenu(this);
        mainMenu.showMenu(pane);
    }

    /** Starts the game and enables stats panel
     */
    public void startGame() {
        try {
            mainMenu.removeMenu(pane);
            gameFrame = new GameFrame(stage.getHeight() - titleBarHeight, stage.getWidth(), this);
            gameFrame.startLevel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        statsPanel = new PlayerStatisticsPanel(this);
    }

    // Getters.
    public Stage getStage() { return stage; }
    public Scene getScene() { return scene; }
    public GridPane getGrid() { return pane; }
    public PlayerStatisticsPanel getStatsPanel() { return statsPanel; }
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
        resizeBackdrop();
    }

    /** Resize squares behind the screen
     */
    private void resizeBackdrop() {
        for(int i = 0; i < backDrop.size(); i++) {
            backDrop.get(i).setWidth(stage.getWidth()/40 + 1);
            backDrop.get(i).setHeight((stage.getHeight() - titleBarHeight)/30 + 1);
        }
    }
}