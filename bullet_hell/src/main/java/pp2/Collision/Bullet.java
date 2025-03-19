package pp2.Collision;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import pp2.ImageHandler;

public class Bullet {

    private final double speed; // Speed of the bullet
    private ImageView bulletView;
    private Rectangle gameFrame; // The Main Game play area
    private GridPane gamePane; // The game pane to add items to
    private boolean isActive = true;
    private double x_pos; // Horizontal position
    private double y_pos; // Vertical position
    private Rectangle hitbox; // Hitbox for collision detection
    private boolean isEnemyBullet; // To identify the enemy or player bullet
    private int damage; // Depend on either enemy or player bullets, the damage is different.

    public Bullet(Rectangle gameFrame, GridPane gamePane, double x_pos, double y_pos, boolean isEnemyBullet, int damage, String bulletPath) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.gameFrame = gameFrame;
        this.gamePane = gamePane;
        this.isEnemyBullet = isEnemyBullet;
        this.damage = damage;
        this.speed = isEnemyBullet ? 4.0 : -6.0; // Enemy bullets move down, player bullets move up

        // Set the bullet to an imageView
        bulletView = new ImageView(ImageHandler.loadImage(bulletPath));
        bulletView.setX(this.x_pos);
        bulletView.setY(this.y_pos);

        // Create hitbox the same size as bullet
        hitbox = new Rectangle(this.x_pos, this.y_pos, bulletView.getFitWidth(), bulletView.getFitHeight());
        hitbox.setVisible(false); // Hide hitbox


        // Add the bullet to the Main gameFrame
        this.gamePane.getChildren().addAll(bulletView, hitbox);


        // Move bullet
        moveBullet();
    }

    // Moves the bullet upwards
    private void moveBullet(Pane gameFrame) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                y_pos -= speed; // Move up
                bulletView.setY(y_pos);

                // Remove bullet when off-screen
                if (y_pos < -10) {
                    gameFrame.getChildren().remove(bulletView);
                    stop();
                }
            }
        };
        timer.start();
    }

    private void moveBullet() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isActive) {
                    stop();
                    return;
                }

                // Move bullet and hitbox
                bulletView.setY(bulletView.getY() + speed);
                hitbox.setY(hitbox.getY() + speed);

                // Remove bullet if off-screen
                if (bulletView.getY() < -10 || bulletView.getY() > gameFrame.getHeight()) {
                    removeBullet();
                    stop();
                }
            }
        };
        timer.start();
    }

    public void removeBullet() {
        isActive = false;
        gamePane.getChildren().removeAll(bulletView, hitbox);
    }

    // Getter/Setter methods
    public ImageView getBulletView() {
        return bulletView;
    }

    public void setBulletView(ImageView bulletView) {
        this.bulletView = bulletView;
    }

    public Rectangle getgameFrame() {
        return gameFrame;
    }

    public void setGameFrame(Rectangle gameFrame) {
        this.gameFrame = gameFrame;
    }

    public GridPane getgamePane() {
        return gamePane;
    }

    public void setGamePane(GridPane gamePane) {
        this.gamePane = gamePane;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public double getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public void setX_pos(double x_pos) {
        this.x_pos = x_pos;
    }

    public void setY_pos(double y_pos) {
        this.y_pos = y_pos;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }

    public void setEnemyBullet(boolean enemyBullet) {
        isEnemyBullet = enemyBullet;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}