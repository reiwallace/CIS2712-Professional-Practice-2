package pp2.Entity.Enemies;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pp2.Collision.Bullet;
import pp2.Entity.Entity;

import java.util.Random;

public class Enemy extends Entity {

    private static String enemyImageURL = "https://i.ibb.co/LhYpPskV/player.png";
    private int damage;
    private Timeline shootTimer;
    private AnimationTimer positionTracker;

    /**
     * Constructor for Enemy
     * @param health Starting health
     * @param damage Damage dealt to the player
     * @param gameGrid The game pane to spawn in
     * @param gameFrame - Game area to restrict movement to
     * @param id - Id of entity
     */
    public Enemy(int health, int damage, GridPane gameGrid, Rectangle gameFrame, int id) {
        super(enemyImageURL, health, id); // Entity handles image loading
        this.gameGrid = gameGrid;
        this.gameFrame = gameFrame;
        this.damage = damage;

        // Set random spawn position
        Random random = new Random();
        entityImage.setLayoutX(random.nextInt((int) gameFrame.getPreferredWidth())); // Enemy appears randomly on the pane.
        entityImage.setLayoutY(0);

        // Ensure enemy spawns inside pane bounds !!ANOTHER WAY TO GET SAME RESULT AS ABOVE.!!
        /*double enemyWidth = entityImage.getFitWidth();
        double maxX = gameGrid.getWidth() - enemyWidth;
        Random random = new Random();
        x_pos = random.nextDouble() * maxX;
        y_pos = 0;
        entityImage.setLayoutX(x_pos);
        entityImage.setLayoutY(y_pos);*/

        // Add enemy to gamePane
        gameGrid.getChildren().addAll(getImage(), getHitbox());

        // Start moving and shooting
        moveDown();
        startShooting();
    }

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
        if (this.gameGrid != null) {
            gameGrid.getChildren().removeAll(getImage(), getHitbox());
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
        double bulletX = getPos()[0] + getImage().getFitWidth() / 2;
        double bulletY = getPos()[1] + getImage().getFitHeight();

        System.out.println("Enemy shoots from: X=" + bulletX + " Y=" + bulletY);

        System.out.println("Enemy shoots from: X=" + bulletX + " Y=" + bulletY);
        Bullet enemyBullet = new Bullet(gameFrame, gameGrid, bulletX, bulletY, true, 10, "https://cdn-icons-png.flaticon.com/256/32/32463.png");
    }

    // Optional getters for x and y
    public static String getEnemyImagePath() { return enemyImageURL; }
    public static void setEnemyImagePath(String enemyImageURL) { Enemy.enemyImageURL = enemyImageURL; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
}
