package pp2.Entity;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pp2.Collision.Bullet;

import java.util.Random;

public class Enemy extends Entity {

    private static String enemyImagePath = "";
    private double x_pos;
    private double y_pos;
    private int damage;
    private Timeline shootTimer;
    private AnimationTimer positionTracker;

    /**
     * Constructor for Enemy
     * @param health Starting health
     * @param damage Damage dealt to the player
     * @param gameGrid The game pane to spawn in
     * @param gameFrame - Game area to restrict movement to
     */
    public Enemy(int health, int damage, GridPane gameGrid, Rectangle gameFrame) {
        super(enemyImagePath, health); // Entity handles image loading
        this.gameGrid = gameGrid;
        this.gameFrame = gameFrame;
        this.damage = damage;

        // Sprite characteristics
        entityImage.setFitWidth(50);
        entityImage.setFitHeight(50);
        entityImage.setFocusTraversable(true);
        entityImage.setPickOnBounds(true);
        entityImage.setPreserveRatio(true);
        entityImage.setSmooth(true);

        // Set random spawn position
        Random random = new Random();
        entityImage.setLayoutX(random.nextInt((int) gameGrid.getPrefWidth())); // Enemy appears randomly on the pane.
        entityImage.setLayoutY(0);

        // Ensure enemy spawns inside pane bounds !!ANOTHER WAY TO GET SAME RESULT AS ABOVE.!!
        /*double enemyWidth = entityImage.getFitWidth();
        double maxX = gameGrid.getWidth() - enemyWidth;
        Random random = new Random();
        x_pos = random.nextDouble() * maxX;
        y_pos = 0;
        entityImage.setLayoutX(x_pos);
        entityImage.setLayoutY(y_pos);*/

        // Initialize hitbox
        setHitbox();

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
                x_pos = getImage().getLayoutX() + getImage().getTranslateX();
                y_pos = getImage().getLayoutY() + getImage().getTranslateY();

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
        double bulletX = x_pos + getImage().getFitWidth() / 2;
        double bulletY = y_pos + getImage().getFitHeight();

        System.out.println("Enemy shoots from: X=" + bulletX + " Y=" + bulletY);

        System.out.println("Enemy shoots from: X=" + bulletX + " Y=" + bulletY);
        Bullet enemyBullet = new Bullet(gameFrame, gameGrid, bulletX, bulletY, true, 10, "");
    }

    // Optional getters for x and y
    public double getX() {
        return x_pos;
    }

    public double getY() {
        return y_pos;
    }

    public static String getEnemyImagePath() {
        return enemyImagePath;
    }

    public static void setEnemyImagePath(String enemyImagePath) {
        Enemy.enemyImagePath = enemyImagePath;
    }

    public double getX_pos() {
        return x_pos;
    }

    public void setX_pos(double x_pos) {
        this.x_pos = x_pos;
    }

    public double getY_pos() {
        return y_pos;
    }

    public void setY_pos(double y_pos) {
        this.y_pos = y_pos;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
