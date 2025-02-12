package org.example.bhg;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Bullet {

    private final double speed; // Speed of the bullet
    private ImageView imageView;
    private Pane gamePane; // The Main Game pane
    private boolean isActive = true;
    private double x_pos; // Horizontal position
    private double y_pos; // Vertical position
    private Rectangle hitbox; // Hitbox for collision detection
    private boolean isEnemyBullet; // To identify the enemy or player bullet
    private int damage; // Depend on either enemy or player bullets, the damage is different.

    public Bullet(Pane gamePane, double x_pos, double y_pos, boolean isEnemyBullet, int damage, String bulletPath) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.gamePane = gamePane;
        this.isEnemyBullet = isEnemyBullet;
        this.damage = damage;
        this.speed = isEnemyBullet ? 4.0 : -6.0; // Enemy bullets move down, player bullets move up

        // Set the bullet to an imageView
        imageView = new ImageView(Utils.loadImage(bulletPath));
        imageView.setX(this.x_pos);
        imageView.setY(this.y_pos);

        // Create hitbox the same size as bullet
        hitbox = new Rectangle(this.x_pos, this.y_pos, imageView.getFitWidth(), imageView.getFitHeight());
        hitbox.setVisible(false); // Hide hitbox


        // Add the bullet to the Main gamePane
        this.gamePane.getChildren().addAll(imageView, hitbox);


        // Move bullet
        moveBullet();
    }

    // Moves the bullet upwards
    private void moveBullet(Pane gamePane) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                y_pos -= speed; // Move up
                imageView.setY(y_pos);

                // Remove bullet when off-screen
                if (y_pos < -10) {
                    gamePane.getChildren().remove(imageView);
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
                imageView.setY(imageView.getY() + speed);
                hitbox.setY(hitbox.getY() + speed);

                // Remove bullet if off-screen
                if (imageView.getY() < -10 || imageView.getY() > gamePane.getHeight()) {
                    removeBullet();
                    stop();
                }
            }
        };
        timer.start();
    }

    public void removeBullet() {
        isActive = false;
        gamePane.getChildren().removeAll(imageView, hitbox);
    }

    // Getter/Setter methods
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
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
