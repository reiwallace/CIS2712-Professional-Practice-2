package pp2.Entity;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameplayInputs {
    private Player player;
    private Rectangle gameFrame;
    private int speed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public String direction;
    
    /** Creates keyboard input detection for the character
     * @param player - The player entity to assign inputs to
     * @param gameGrid - Grid to enable inputs on
     * @param gameFrame - Game frame to check boundries of
     * @param speed - Speed to move player at
     */
    public GameplayInputs(Player player, GridPane gameGrid, Rectangle gameFrame, int speed) {
        this.player = player;
        this.gameFrame = gameFrame;
        this.speed = speed;
        gameGrid.setOnKeyPressed(event -> move(event.getCode()));
        gameGrid.setOnKeyReleased(event -> moveChecker(event.getCode())); // moveChecker just lets it turn back to false
    }

    /** Moves player based on input
     * 
     * @param key - Key press input
     */
    public void move(KeyCode key) {

        if(key == KeyCode.SPACE) {
            player.shoot();
        }
        // creates a boolean for each direction
        if(key == KeyCode.LEFT) {
            leftPressed = true;
        }
        if(key == KeyCode.RIGHT) {
            rightPressed = true;
        }
        if(key == KeyCode.UP) {
            upPressed = true;
        }
        if(key == KeyCode.DOWN) {
            downPressed = true;
        }

        // Determines which of the 9 directions and stores it as string

        // hold all keys no move
        if(upPressed == true && downPressed == true && leftPressed == true && rightPressed == true) {
            direction = "";
        }
        // up movement
        else if(upPressed == true && leftPressed == false && rightPressed == false) {
            direction = "up";
        }
        // down movement
        else if(downPressed == true && leftPressed == false && rightPressed == false) {
            direction = "down";
        }
        // left movement
        else if(leftPressed == true && upPressed == false && downPressed == false) {
            direction = "left"; 
        }
        // right movement
        else if(rightPressed == true && upPressed == false && downPressed == false) {
            direction = "right";
        }
        // left-up movement
        else if(upPressed == true && leftPressed == true) {
            direction = "left-up";
        }
        // right-up movement
        else if(upPressed == true && rightPressed == true) {
            direction = "right-up";
        }
        // left-down movement
        else if(downPressed == true && leftPressed == true) {
            direction = "left-down";
        }
        // right-down movement
        else if(downPressed == true && rightPressed == true) {
            direction = "right-down";
        }
        // no keys no movement
        else {
            direction = "";
        }

        // Based on direction it will move the player ship
        switch(direction) {
            case "up":
                if(!checkBorderY(player.getPos()[1] - speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] - speed); // Move up
                }
            break;
            case "down":
                if(!checkBorderY(player.getPos()[1] + speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] + speed); // Move down
                }
            break;
            case "left":
                if(!checkBorderX(player.getPos()[0] - speed)) {
                    player.setPosition((int) player.getPos()[0] - speed, (int) player.getPos()[1]); // Move left
                }
            break;
            case "right":
                if(!checkBorderX(player.getPos()[0] + speed)) {
                    player.setPosition((int) player.getPos()[0] + speed, (int) player.getPos()[1]); // Move right
                }
            break;
            case "left-up":
                if(!checkBorderY(player.getPos()[1] - speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] - speed); // Move up
                }
                if(!checkBorderX(player.getPos()[0] - speed)) {
                    player.setPosition((int) player.getPos()[0] - speed, (int) player.getPos()[1]); // Move left
                }
            break;
            case "right-up":
                if(!checkBorderY(player.getPos()[1] - speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] - speed); // Move up
                }
                if(!checkBorderX(player.getPos()[0] + speed)) {
                    player.setPosition((int) player.getPos()[0] + speed, (int) player.getPos()[1]); // Move right
                }
                break;
            case "left-down":
                if(!checkBorderX(player.getPos()[0] - speed)) {
                    player.setPosition((int) player.getPos()[0] - speed, (int) player.getPos()[1]); // Move left
                }
                if(!checkBorderY(player.getPos()[1] + speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] + speed); // Move down
                }
                break;
            case "right-down":
                if(!checkBorderX(player.getPos()[0] + speed)) {
                    player.setPosition((int) player.getPos()[0] + speed, (int) player.getPos()[1]); // Move right
                }
                if(!checkBorderY(player.getPos()[1] + speed)) {
                    player.setPosition((int) player.getPos()[0], (int) player.getPos()[1] + speed); // Move down
                }
                break;
        }

    }

    // Upon release of key converts move direction back into false.
    public void moveChecker(KeyCode key) {
        if(key == KeyCode.LEFT) {
            leftPressed = false;
        }
        if(key == KeyCode.RIGHT) {
            rightPressed = false;
        }
        if(key == KeyCode.UP) {
            upPressed = false;
        }
        if(key == KeyCode.DOWN) {
            downPressed = false;
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
