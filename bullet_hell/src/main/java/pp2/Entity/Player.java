package pp2.Entity;

import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public class Player extends Entity {
    private static String playerImageURL = "https://i.ibb.co/TD8pxWmR/ship-stopped.png";
    private ProgressBar healthBar = new ProgressBar(1);
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
        super(playerImageURL, health, 1, mainWindow, gameFrame, new int[] {50, 50}); // Call Entity constructor
        this.speed = speed;
        this.gameFrame = gameFrame; // Assign game area

        // Set up health bar
        healthBar.setPrefWidth(50);
        healthBar.setPrefHeight(10);
        healthBar.setVisible(true);
        mainWindow.getGrid().add(healthBar, 2, 1, 3, 1);

        // Enable controls and add player to grid
        GameplayInputs movements = new GameplayInputs(this, mainWindow.getGrid(), gameFrame, speed);
        addEntity(1, 3);
    }

    // Edited set hitbox for smaller player hitbox
    @Override
    public void setHitbox() {
        if (entityImage != null) {
            entityHitbox = new Rectangle(
                    entityImage.getFitWidth() / 2,
                    entityImage.getFitHeight() / 2
            );
            entityHitbox.setFill(Color.BLACK);
            entityHitbox.setVisible(false);
        }
    }

    // Edited set position for smaller player hitbox
    @Override
    public void setPosition(double x, double y) {
        // Update character image position
        entityImage.setTranslateX(x);
        entityImage.setTranslateY(y);
        // Update hitbox position
        entityHitbox.setTranslateX(entityImage.getTranslateX() + entityImage.getFitWidth() * 0.25);
        entityHitbox.setTranslateY(entityImage.getTranslateY());
        // Update healthbar position
        healthBar.setTranslateX(entityImage.getTranslateX() - entityImage.getFitWidth() * 0.01);
        healthBar.setTranslateY(entityImage.getTranslateY() - entityImage.getFitHeight() * 0.2);
    }

    /** Reduces the player's health when hit.
     * @param damage - Damage to reduce player's health by
     */
    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        healthBar.setProgress((double)health/(double)maxHealth);

        if (getHealth() <= 0) {
            destroy();
        }
    }

    /** Removes the player from the grid when health reaches 0.
     */
    @Override
    public void destroy() {
        mainWindow.getGrid().getChildren().removeAll(entityImage, entityHitbox);
        healthBar.setVisible(false);
        setIsTargetable(false);
    }

    public int getSpeed() { return speed; }
    public int getLives() { return lives; }
}
