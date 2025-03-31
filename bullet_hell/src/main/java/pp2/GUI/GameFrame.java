package pp2.GUI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.Entity.Entity;
import pp2.Entity.Player;
import pp2.Entity.Enemies.Enemy;
import pp2.Levels.Level1;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles
    private final String gameFrameId = "1";

    private MainWindow mainWindow;
    private GridPane mainGrid;

    private Image imagePath = new Image("https://i.ibb.co/YBZzsddp/game-BGTEST.jpg"); // Path of background image (URL)
    private ImageView backgroundImage  = new ImageView(imagePath); // Background image

    private Player player;
    private Enemy enemy;
    protected ArrayList<Entity> entities = new ArrayList<Entity>();

    /** Set up default game frame with its size
     * @param sceneHeight - Height of scene to size area by
     * @param stageWidth - Width of scene to size area by
     * @param mainGrid - grid to attach game frame to
     * @throws FileNotFoundException - Yeets image errors
     */
    public GameFrame(double sceneHeight, double stageWidth, MainWindow mainWindow) throws FileNotFoundException {
        this.mainWindow = mainWindow;
        mainGrid = mainWindow.getGrid(); 
        gameFrame.setId(gameFrameId);
        gameFrame.setWidth((stageWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
        backgroundImage.setFitWidth(gameFrame.getWidth());
        backgroundImage.setFitHeight(gameFrame.getHeight());
        // Set up background image
        mainGrid.add(gameFrame, 2, 1, 24, 28); // Add the game window to the game frame 2 columns in and 1 row down
        mainGrid.add(backgroundImage, 2, 1, 24, 28); // Add background image to frame

        entities.add(player = new Player(10, 5, mainWindow, gameFrame, 1)); // Initialise player character
        //HorizontalPattern enemy1 = new HorizontalPattern(enemy, null, 0, null, null, null)
    }

    public void startLevel() {
        Level1 level = new Level1(mainWindow);
    }

    // Setters and getters
    public Rectangle getGameFrame() { return gameFrame; }
    public ImageView getBG() { return backgroundImage; }
    public Player getPlayer() { return player; }
    public ArrayList<Entity> getEntities() { return entities; }

    public void setBackgroundImage(Image image) { this.backgroundImage.setImage(image); }
}