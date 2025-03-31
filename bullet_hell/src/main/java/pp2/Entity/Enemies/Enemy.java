package pp2.Entity.Enemies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

import java.util.Random;

public class Enemy extends Entity {

    private static String enemyImageURL = "https://i.ibb.co/M5PPgw2r/enemy-ship.png";
    private int damage;
    private Timeline shootTimer;
    private int fireRate;

    /**
     * Constructor for Enemy
     * @param health Starting health
     * @param damage Damage dealt to the player
     * @param gameGrid The game pane to spawn in
     * @param gameFrame - Game area to restrict movement to
     * @param id - Id of entity
     */
    public Enemy(int health, int damage, MainWindow mainWindow, Rectangle gameFrame, int fireRate) {
        super(enemyImageURL, health, 2, mainWindow, gameFrame, new int[] {50, 50}); // Entity handles image loading
        this.gameFrame = gameFrame;
        this.damage = damage;
        this.fireRate = fireRate;

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
        stopShooting();
        if (this.mainWindow != null) {
            mainWindow.getGrid().getChildren().removeAll(getImage(), getHitbox());
        }
        System.out.println("Enemy destroyed!");
    }

    /** Begins a loop where the entity fires bullet every 2 seconds
     */
    public void startShooting() {
        if(fireRate < 1) fireRate = 2;
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(fireRate), e -> shoot()));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
    }

    /* Stops bullet firing loop
     */
    private void stopShooting() {
        if (shootTimer != null) {
            shootTimer.stop();
        }
    }

    /** Shoots a bullet below the enemy
     */
    private void shoot() {
        Bullet enemyBullet = new Bullet(gameFrame, mainWindow, getPos()[0], getPos()[1] + entityImage.getFitHeight() / 2 , true, 10);
        mainWindow.getGameFrame().getEntities().add(enemyBullet);
    }

    // Optional getters for x and y
    public static String getEnemyImagePath() { return enemyImageURL; }
    public static void setEnemyImagePath(String enemyImageURL) { Enemy.enemyImageURL = enemyImageURL; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}
