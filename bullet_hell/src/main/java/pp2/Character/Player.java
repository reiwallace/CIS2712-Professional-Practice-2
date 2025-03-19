package pp2.Character;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pp2.Entity.Entity;

public class Player extends Entity{

    private double x_pos = 300; // Horizontal position
    private double y_pos = 250; // Vertical position
    private int widthHeight = 50; // Width and Height
    private int speed; // Player movement speed


    // Player character constructor
    public Player(String imagePath, int health, int speed, GridPane gamePane) {
        super(imagePath, health); // Call Entity constructor
        this.speed = speed;
        this.gamePane = gamePane; // Assign game pane
    }

    // Moves the player based on input direction.
    @Override
    public void move(KeyCode key) {
        double x = entityImage.getLayoutX();
        double y = entityImage.getLayoutY();

        switch (key) {
            case LEFT:
                setPosition((int) (x - speed), (int) y); // Move left
                break;
            case RIGHT:
                setPosition((int) (x + speed), (int) y); // Move right
                break;
            case UP:
                setPosition((int) x, (int) (y - speed)); // Move up
                break;
            case DOWN:
                setPosition((int) x, (int) (y + speed)); // Move down
                break;
        }
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
}
