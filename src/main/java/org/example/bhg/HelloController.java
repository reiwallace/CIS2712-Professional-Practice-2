/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

package org.example.bhg;

/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML // fx:id="bulletButton"
    private Button bulletButton; // Value injected by FXMLLoader

    @FXML // fx:id="gamePane"
    private Pane gamePane; // Value injected by FXMLLoader

    @FXML // fx:id="enemy"
    private ImageView enemy; // Value injected by FXMLLoader

    @FXML // fx:id="player"
    private ImageView player; // Value injected by FXMLLoader
    private List<Bullet> bullets = new ArrayList<>();

    private boolean canShoot = true; // Prevents rapid firing
    private final double fireDelay = 200; // Cooldown time in milliseconds

    private Rectangle playerHitbox;
    private Rectangle enemyHitbox;
    private Random random = new Random();

    public void initialize() {
        enemy.setImage(Utils.loadImage("images/enemy.png"));
        enemy.setLayoutX(random.nextInt((int) gamePane.getPrefWidth())); // Enemy-ship appears randomly
        enemyHitbox();

        player.setImage(Utils.loadImage("images/player.png"));
        playerHitbox();

        // Handle keypress for shooting
        gamePane.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        gamePane.setFocusTraversable(true); // Ensure it can receive key input
        gamePane.requestFocus(); // Ensure scene receives key events

//        moveDownward();

        // Enemy shooting every 2 seconds
        Timeline enemyShootTimer = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            enemyBullet();
        }));
        enemyShootTimer.setCycleCount(Timeline.INDEFINITE);
        enemyShootTimer.play();

        // checks collision
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkCollision(bullets); // Continuously check for bullet collisions
            }
        };
        gameLoop.start();
    }

    public void moveDownward() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(10), enemy);
        transition.setByY(400);
        transition.setOnFinished(e -> destroy());
        transition.play();
    }

    public void destroy() {
        System.out.println("Enemy destroyed!");
        gamePane.getChildren().removeAll(enemy, enemyHitbox);
    }

    public void checkCollision(List<Bullet> bullets) {
        for (Bullet bullet: bullets) {
            if (bullet.isEnemyBullet()) {
                // Enemy bullets hitting the player
                if (bullet.getHitbox().getBoundsInParent().intersects(this.playerHitbox.getBoundsInParent())) {
                    System.out.println("Player died!");
//                    player.takeDamage(bullet.getDamage()); // Player loses health
                    bullet.removeBullet(); // Remove bullet
//                    bulletIterator.remove();
                }
            }
            else {
                // Player bullets hitting enemies
                if (bullet.getHitbox().getBoundsInParent().intersects(this.enemyHitbox.getBoundsInParent())) {
//                    enemy.takeDamage(bullet.getDamage()); // Reduce enemy health
                    System.out.println("Boom!! enemy died! (:");
                    bullet.removeBullet(); // Remove bullet
//                    bulletIterator.remove();
//                    if (enemy.getHealth() <= 0) {
//                        enemy.destroy(); // Remove enemy if health reaches 0
//                        enemyIterator.remove();
//                    }
//                    break; // One bullet should only hit one enemy
                }
            }
        }
    }

    private void playerHitbox() {
        playerHitbox = new Rectangle(player.getLayoutX(), player.getLayoutY(), player.getFitWidth(), player.getFitHeight());
        playerHitbox.setVisible(false); // Hide hitbox
    }

    private void enemyHitbox() {
        enemyHitbox = new Rectangle(enemy.getLayoutX(), enemy.getLayoutY(), enemy.getFitWidth(), enemy.getFitHeight());
        enemyHitbox.setVisible(false); // Hide hitbox
    }

    public void fireCooldown () {
        // Set cooldown
        canShoot = false;
        Timeline cooldown = new Timeline(new KeyFrame(Duration.millis(fireDelay), e -> canShoot = true));
        cooldown.setCycleCount(1);
        cooldown.play();
    }

    private void handleKeyPress(KeyCode key) {
        switch (key){
            case ESCAPE:
                System.exit(0);
                break;
            case SPACE:
                playerBullet();
                fireCooldown();
                break;
            case E:
                enemyBullet();
                break;
            case LEFT:
                player.setLayoutX(player.getLayoutX() - 10);
                break;
            case RIGHT:
                player.setLayoutX(player.getLayoutX() + 10);
                break;
            case UP:
                player.setLayoutY(player.getLayoutY() - 10);
                break;
            case DOWN:
                player.setLayoutY(player.getLayoutY() + 10);
                break;
        }
    }

    // Player bullet
    private void playerBullet() {
        Bullet bullet = new Bullet(gamePane, player.getLayoutX(), player.getLayoutY(), false, 10, "images/playerBullet.png");
        bullets.add(bullet);
    }

    // Enemy Bullet
    private void enemyBullet() {
        Bullet bullet = new Bullet(gamePane, enemy.getLayoutX() + 20, enemy.getLayoutY() + 20, true, 10, "images/enemyBullet.png");
        bullets.add(bullet);
    }

    // Enemy bullet by button
    @FXML
    void bulletButton(ActionEvent event) {
        System.out.println("The button is clicked");
        // check details
        System.out.println("layout x" + enemy.getLayoutX());
        System.out.println("layout y" + enemy.getLayoutY());

//        Bullet bullet = new Bullet(gamePane, player.getLayoutX() + 19, player.getLayoutY());
//        Bullet bullet = new Bullet(gamePane, enemy.getLayoutX() + 20, enemy.getLayoutY() + 20, true, 10, "images/enemyBullet.png");
        enemyBullet();
    }
}
