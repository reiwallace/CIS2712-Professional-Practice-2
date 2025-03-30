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

    private static String enemyImageURL = "https://i.ibb.co/LhYpPskV/player.png";
    private int damage;
    private Timeline shootTimer;

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
    }

    /* Redundant
    public void moveDown() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), getImage());
        transition.setByY(400);
        transition.setCycleCount(1);
        transition.play();

        // Update position and hitbox continuously
        positionTracker = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double x_pos = getImage().getLayoutX() + getImage().getTranslateX();
                double y_pos = getImage().getLayoutY() + getImage().getTranslateY();

                getHitbox().setLayoutX(x_pos);
                getHitbox().setLayoutY(y_pos);
            }
        };
        positionTracker.start();

        transition.setOnFinished(e -> {
            positionTracker.stop();
            destroy();
        });
    } */

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

    // Optional getters for x and y
    public static String getEnemyImagePath() { return enemyImageURL; }
    public static void setEnemyImagePath(String enemyImageURL) { Enemy.enemyImageURL = enemyImageURL; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}
