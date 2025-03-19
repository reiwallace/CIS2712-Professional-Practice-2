package pp2.GUI;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles
    private final String bgPath = "resources/images/gameBGTEST.jpg";

    private Image imagePath = new Image("https://i.ibb.co/YBZzsddp/game-BGTEST.jpg");
    public ImageView backgroundImage  = new ImageView(imagePath); // Background image

    // Set up default game frame with its size
    public GameFrame(double sceneHeight, double stageWidth) throws FileNotFoundException{
        gameFrame.setWidth((stageWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
        backgroundImage.setFitWidth(gameFrame.getWidth());
        backgroundImage.setFitHeight(gameFrame.getHeight());
        // Set up background image
    }

    // Setters and getters
    public Rectangle getGameFrame() { return gameFrame; }
    public ImageView getBG() { return backgroundImage; }

}