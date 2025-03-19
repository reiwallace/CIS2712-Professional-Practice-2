package pp2.Character;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pp2.Entity.Entity;

public class Player extends Entity{

    private final int speed; // Player movement speed
    private int x_pos, y_pos; // Player position


    // Player character constructor
    public Player(String imagePath, int health, int speed, GridPane gamePane) {
        super(imagePath, health); // Call Entity constructor
        this.speed = speed;
        this.gamePane = gamePane; // Assign game pane
        this.x_pos = (int) entityImage.getLayoutX();
        this.y_pos = (int) entityImage.getLayoutY();

        // Set player characteristics
        entityImage.setId("player");
        entityImage.setFitWidth(50);
        entityImage.setFitHeight(50);
        entityImage.setFocusTraversable(true);
        entityImage.setPickOnBounds(true);
        entityImage.setPreserveRatio(true);
        entityImage.setSmooth(true);

        // Add the player to the main pane
        gamePane.getChildren().add(getImage());
    }

    // Moves the player based on input direction.
    @Override
    public void move(KeyCode key) {
        double newX = x_pos;
        double newY = y_pos;

        switch (key) {
            case LEFT:
                newX -= speed; // Move left
                break;
            case RIGHT:
                newX += speed; // Move right
                break;
            case UP:
                newY -= speed; // Move up
                break;
            case DOWN:
                newY += speed; // Move down
                break;
        }

        // Ensure the player does not go out of bounds
        if (newX >= 0 && newX + entityImage.getFitWidth() <= gamePane.getWidth()) {
            x_pos = (int) newX;
        }
        if (newY >= 0 && newY + entityImage.getFitHeight() <= gamePane.getHeight()) {
            y_pos = (int) newY;
        }

        // Update position
        setPosition(x_pos, y_pos);
    }

    // Reduces the player's health when hit.
    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        System.out.println("Player hit! Health: " + getHealth());

        if (getHealth() <= 0) {
            destroy();
        }
    }

    // Removes the player from the pan when health reaches 0.

    @Override
    public void destroy() {
        System.out.println("Player destroyed!");
        gamePane.getChildren().removeAll(entityImage, getHitbox());
    }

    public int getSpeed() {
        return speed;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }
}
