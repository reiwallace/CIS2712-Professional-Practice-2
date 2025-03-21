package pp2.GUI;

import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles

    private Image imagePath = new Image("https://i.ibb.co/YBZzsddp/game-BGTEST.jpg");
    private ImageView backgroundImage  = new ImageView(imagePath); // Background image

    /** Set up default game frame with its size
     * @param sceneHeight - Height of scene to size area by
     * @param stageWidth - Width of scene to size area by
     * @throws FileNotFoundException - Yeets image errors
     */
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

    public void setBackgroundImage(Image image) { this.backgroundImage.setImage(image); }
}