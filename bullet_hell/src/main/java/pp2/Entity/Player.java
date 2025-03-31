package pp2.Entity;

import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public class Player extends Entity {
    private static String playerImageURL = "https://i.ibb.co/TD8pxWmR/ship-stopped.png";

    private int speed; // Player movement speed

    private int lives = 5;

    /** Initialise player character
     * @param health - Health of the player
     * @param speed - Speed player moves at
     * @param gameGrid - Main window grid to attach player to
     * @param gameFrame - Game area to restrict movement to
     * @param id - id of entity
     */
    public Player(int health, int speed, MainWindow mainWindow, Rectangle gameFrame) {
        super(playerImageURL, health, 1, mainWindow, gameFrame); // Call Entity constructor
        this.speed = speed;
        this.gameFrame = gameFrame; // Assign game area

        // Enable controls and add player to grid
        GameplayInputs movements = new GameplayInputs(this, mainWindow.getGrid(), gameFrame, speed);
        addEntity(1, 3);
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
        mainWindow.getGrid().getChildren().removeAll(entityImage, getHitbox());
    }

    public int getSpeed() { return speed; }
    public int getLives() { return lives; }
}
