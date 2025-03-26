package pp2.Entity;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Player extends Entity {
    private static String playerImagePath = "https://i.ibb.co/LhYpPskV/player.png";

    private int speed; // Player movement speed
    private int x_pos, y_pos; // Player position

    private int lives = 5;

    /** Initialise player character
     * @param health - Health of the player
     * @param speed - Speed player moves at
     * @param gameGrid - Main window grid to attach player to
     * @param gameFrame - Game area to restrict movement to
     */
    public Player(int health, int speed, GridPane gameGrid, Rectangle gameFrame) {
        super(playerImagePath, health); // Call Entity constructor
        this.speed = speed;
        this.gameGrid = gameGrid; // Assign main window grid
        this.gameFrame = gameFrame; // Assign game area
        this.x_pos = (int) entityImage.getTranslateX();
        this.y_pos = (int) entityImage.getTranslateY();

        // Set player characteristics
        entityImage.setId("player");
        entityImage.setFitWidth(50);
        entityImage.setFitHeight(50);
        entityImage.setFocusTraversable(true);
        entityImage.setPickOnBounds(true);
        entityImage.setPreserveRatio(true);
        entityImage.setSmooth(true);

        setHitbox(); // Initialize hitbox

        GameplayInputs m = new GameplayInputs(this, gameGrid, gameFrame, speed);

        // Add the player to the main pane
        gameGrid.add(entityHitbox, 2, 1, 1, 3);
        gameGrid.add(getImage(), 2, 1, 1, 3);
    }


    /** Reduces the player's health when hit.
     * @param damage - Damage to reduce player's health by
     */
    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        System.out.println("Player hit! Health: " + getHealth());

        if (getHealth() <= 0) {
            destroy();
        }
    }

    /** Removes the player from the grid when health reaches 0.
     */
    @Override
    public void destroy() {
        System.out.println("Player destroyed!");
        gameGrid.getChildren().removeAll(entityImage, getHitbox());
    }

    public int getSpeed() { return speed; }
    public int getLives() { return lives; }
}
