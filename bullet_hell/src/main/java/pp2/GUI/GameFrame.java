package pp2.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class GameFrame {
    private Rectangle gameFrame = new Rectangle();
    
    private int[] gameFrameSize = {24, 28}; // Size of gameFrame in tiles

    public GameFrame(double sceneHeight, double sceneWidth) {
        gameFrame.setWidth((sceneWidth/40)*gameFrameSize[0]);
        gameFrame.setHeight((sceneHeight/30)*gameFrameSize[1]);
        gameFrame.setFill(Color.BLACK);
    }

    public Rectangle getGameFrame() { return gameFrame; }
}
