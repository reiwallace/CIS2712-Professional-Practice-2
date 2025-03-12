package pp2.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.ImageHandler;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles
    private final String bgPath = "images/gameBGTEST.jpg";

    private Image imagePath = new Image("");
    private ImageView backgroundImage  = new ImageView(imagePath); // Background image

    // Set up default game frame with its size
    public GameFrame(double sceneHeight, double stageWidth) {
        gameFrame.setWidth((stageWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
        // Set up background image
    }

    // Setters and getters
    public Rectangle getGameFrame() { return gameFrame; }

    public void setBackgroundImage(String imagePath) { backgroundImage.setImage(ImageHandler.loadImage(imagePath)); }
}
