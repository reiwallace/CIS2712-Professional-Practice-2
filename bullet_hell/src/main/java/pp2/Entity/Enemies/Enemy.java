package pp2.Entity.Enemies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.Entity.MovementPatterns.CirclePattern;
import pp2.GUI.MainWindow;

import java.util.Random;

public class Enemy extends Entity {

    private static String enemyImageURL = "https://i.ibb.co/LhYpPskV/player.png";
    private int damage;
    private Timeline shootTimer;
    private CirclePattern circlePattern;

    /**
     * Constructor for Enemy
     * @param health Starting health
     * @param damage Damage dealt to the player
     * @param gameGrid The game pane to spawn in
     * @param gameFrame - Game area to restrict movement to
     * @param id - Id of entity
     */
    public Enemy(int health, int damage, MainWindow mainWindow, Rectangle gameFrame, int id) {
        super(enemyImageURL, health, 2, mainWindow, gameFrame); // Entity handles image loading
        this.gameFrame = gameFrame;
        this.damage = damage;

        // Set random spawn position
        Random random = new Random();
        setPosition(random.nextInt((int) gameFrame.getWidth()), 0);

        // Add enemy to gamePane
        addEntity(1, 3);

        entityImage.setRotate(180);
        toggleHitboxVisability(true);

        // Start moving and shooting
        startShooting();

        // Variables for circular movement pattern
        double centreX = gameFrame.getWidth() / 2;  // center of the screen width
        double centreY = gameFrame.getHeight() / 2; // center of the screen height
        double radius = (gameFrame.getWidth() / 3);  // radius determines how large the orbit of the entity is
        int speed = 1; // entity movement speed around the orbit
    
        circlePattern = new CirclePattern(this, mainWindow, centreX, centreY, radius, speed);

        startMoving();
    }

    private void startMoving() {
        // used a thread instead of timeline -- could implement delta time
        new Thread(() -> {
            while (true) {
                if (circlePattern != null) {
                    circlePattern.moveEntity();
                }

                try {
                    Thread.sleep(16); // roughly 60 frames per second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    private void startShooting() {
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), e -> shoot()));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
    }

    private void stopShooting() {
        if (shootTimer != null) {
            shootTimer.stop();
        }
    }

    private void shoot() {
        System.out.println("Enemy shoots from: X=" + getPos()[0] + " Y=" + getPos()[1] + getPos()[1] / 2);
        Bullet enemyBullet = new Bullet(gameFrame, mainWindow, getPos()[0], getPos()[1] + entityImage.getFitHeight() / 2 , true, 10, "https://i.ibb.co/RpGfBNNN/bullet-Photoroom.png");
    }

    // optional getters for x and y
    public static String getEnemyImagePath() { return enemyImageURL; }
    public static void setEnemyImagePath(String enemyImageURL) { Enemy.enemyImageURL = enemyImageURL; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}
