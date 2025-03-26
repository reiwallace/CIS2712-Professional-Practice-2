package pp2.Entity;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameplayInputs {
    private Player player;
    private Rectangle gameFrame;
    private int speed;
    
    public GameplayInputs(Player player, GridPane gameGrid, Rectangle gameFrame, int speed) {
        this.player = player;
        this.gameFrame = gameFrame;
        this.speed = speed;
        gameGrid.setOnKeyPressed(event -> move(event.getCode()));
    }

    /** Moves the player based on input direction.
     * @param key - Key press input
     */
    public void move(KeyCode key) {
        switch (key) {
            case LEFT:
                if(!checkBorderX(player.getPos()[0] - speed)) {
                    player.setPosition((int) player.getPos()[0] - speed, (int) player.getPos()[1]); // Move right
                }
                break;
            case RIGHT:
                if(!checkBorderX(player.getPos()[0] + speed)) {
                    player.setPosition((int) player.getPos()[0] + speed, (int) player.getPos()[1]); // Move right
                }
                break;
            case UP:
                if(!checkBorderY(player.getPos()[1] - speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] - speed); // Move up
                }
                break;
            case DOWN:
                if(!checkBorderY(player.getPos()[1] + speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] + speed); // Move down
                }
                break;
        }
    }

    /** Checks whether a new X coordinate is outside the game border
     * 
     */
    public boolean checkBorderX(double newX) {
        if(newX >= 0 && newX <= gameFrame.getWidth() - player.entityImage.getFitWidth()) {
            return false;
        }
        return true;
    }

    /** Checks whether a new Y coordinate is outside the game border
     * 
     */
    public boolean checkBorderY(double newY) {
        if(newY >= 0 && newY <= gameFrame.getHeight() - player.entityImage.getFitHeight()) {
            return false;
        }
        return true;
    }
}
