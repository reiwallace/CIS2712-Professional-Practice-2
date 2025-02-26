package pp2.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameFrame {
    private Rectangle gameFrame = new Rectangle();
    
    private int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles

    public GameFrame(double sceneHeight, double stageWidth) {
        gameFrame.setWidth((stageWidth/40)*24);
        gameFrame.setHeight((sceneHeight/30)*28);
        gameFrame.setFill(Color.BLACK);
    }

    public Rectangle getGameFrame() { return gameFrame; }
}
