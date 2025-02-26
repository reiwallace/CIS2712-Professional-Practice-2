package pp2.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.ImageHandler;

public class GameFrame {
    private final Rectangle gameFrame = new Rectangle();
    
    private final int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles
    private final ImageHandler backgroundImage = new ImageHandler(); // Background image

    public GameFrame(double sceneHeight, double stageWidth) {
        gameFrame.setWidth((stageWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
    }

    public Rectangle getGameFrame() { return gameFrame; }
}
