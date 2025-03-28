package pp2.GUI;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.Entity.Player;
import pp2.Entity.Enemies.Enemy;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles
    private final String gameFrameId = "1";

    private GridPane mainGrid;

    private Image imagePath = new Image("https://i.ibb.co/YBZzsddp/game-BGTEST.jpg"); // Path of background image (URL)
    private ImageView backgroundImage  = new ImageView(imagePath); // Background image

    private Player player;
    private Enemy enemy;

    /** Set up default game frame with its size
     * @param sceneHeight - Height of scene to size area by
     * @param stageWidth - Width of scene to size area by
     * @param mainGrid - grid to attach game frame to
     * @throws FileNotFoundException - Yeets image errors
     */
    public GameFrame(double sceneHeight, double stageWidth, GridPane mainGrid) throws FileNotFoundException {
        this.mainGrid = mainGrid;
        gameFrame.setId(gameFrameId);
        gameFrame.setWidth((stageWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
        backgroundImage.setFitWidth(gameFrame.getWidth());
        backgroundImage.setFitHeight(gameFrame.getHeight());
        // Set up background image
        mainGrid.add(gameFrame, 2, 1, 24, 28); // Add the game window to the game frame 2 columns in and 1 row down
        mainGrid.add(backgroundImage, 2, 1, 24, 28); // Add background image to frame

        player = new Player(10, 5, mainGrid, gameFrame, 1); // Initialise player character
        enemy = new Enemy(10, 5, mainGrid, gameFrame, 2);
    }

    // Setters and getters
    public Rectangle getGameFrame() { return gameFrame; }
    public ImageView getBG() { return backgroundImage; }
    public Player getPlayer() { return player; }

    public void setBackgroundImage(Image image) { this.backgroundImage.setImage(image); }
}