package pp2.Entity.Enemies;

import javafx.scene.shape.Rectangle;
import pp2.Entity.Entity;
import pp2.Entity.AttackPatterns.AbstractAttackPattern;
import pp2.GUI.MainWindow;

import java.util.Random;

public class Boss extends Entity {
    private AbstractAttackPattern attackPattern; // Attack pattern used by enemy
    private static String enemyImageURL = "file:resources/Entities/boss-ship.png";
    private static int health = 200; // Set health of boss
    private static int damage = 2;

    /**
     * Constructor for Boss
     * @param health Starting health
     * @param damage Damage dealt to the player
     * @param gameGrid The game pane to spawn in
     * @param gameFrame - Game area to restrict movement to
     * @param id - Id of entity
     */
    public Boss(MainWindow mainWindow, Rectangle gameFrame) {
        super(enemyImageURL, health, 2, mainWindow, gameFrame, new int[] {100, 100}); // Entity handles image loading
        this.gameFrame = gameFrame;

        // Set random spawn position
        Random random = new Random();
        setPosition(random.nextInt((int) gameFrame.getWidth()), 0);

        // Add enemy to gamePane
        addEntity(1, 3);

        entityImage.setRotate(180);
        entityImage.setVisible(false);
    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage); // Default damage
        System.out.println("Enemy hit! Health: " + getHealth());

        if (getHealth() <= 0) {
            destroy();
        }
    }

    @Override
    public void destroy() {
        if (this.mainWindow != null) {
            mainWindow.getGrid().getChildren().removeAll(getImage(), getHitbox());
        }
    }

    // Optional getters for x and y
    public static String getEnemyImagePath() { return enemyImageURL; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setAttackPattern(AbstractAttackPattern attackPattern) { this.attackPattern = attackPattern; } 
    public AbstractAttackPattern getAttackPattern() { return attackPattern; }
}