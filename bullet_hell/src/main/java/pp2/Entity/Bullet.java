package pp2.Entity;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public class Bullet extends Entity {
    private final double speed; // Speed of the bullet
    private static String enemyImagePath = "https://i.ibb.co/RpGfBNNN/bullet-Photoroom.png"; // Path of enemy bullet
    private boolean isActive = true;
    private boolean isEnemyBullet; // To identify the enemy or player bullet
    private int damage; // Depend on either enemy or player bullets, the damage is different.
    private AnimationTimer checkLoop;

    /** Initialise a bullet
     * @param mainWindow - Main window to attach bullet to
     * @param x_pos - Initial X position of bullet
     * @param y_pos - Initial Y position of bullet
     * @param isEnemyBullet - Whether the bullet is fired from enemy or player
     * @param damage - Damage of the bullet
     * @param speed - Speed of bullet (1.5x for enemies)
     */
    public Bullet(MainWindow mainWindow, double initialX, double initialY, boolean isEnemyBullet, int damage, int speed) {
        super(enemyImagePath, 1, 3, mainWindow, mainWindow.getGameFrame().getGameFrame(), new int[] {30, 30});
        this.isEnemyBullet = isEnemyBullet;
        this.damage = damage;
        this.speed = isEnemyBullet ? speed : -speed*1.5; // Enemy bullets move down, player bullets move up
        
        // Set the bullet to an imageView
        setPosition(initialX, initialY);
        addEntity(1, 3);

        setIsTargetable(false);
        // Move bullet
        moveBullet(new Double[] {1.1, 1.1});
        checkLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkCollision(); // Continuously check for bullet collisions
            }
        };
        checkLoop.start();
    }

    // Moves the bullet upwards
    /* UNUSED METHOD?
    private void moveBullet(GridPane gameFrame) {
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
    } */

    /** Animation timer method to move the bullet in the appropriate ditection
     */
    public void moveBullet(Double[] direction) {
        AnimationTimer timer = new AnimationTimer() { // Move bullet in an animation timer
            @Override
            public void handle(long now) {
                if (!isActive) {
                    stop();
                    return;
                }

                // Move bullet and hitbox
                setPosition(getPos()[0], getPos()[1] + speed);

                // Remove bullet if off-screen
                if (getPos()[1] < -10 || getPos()[1] > gameFrame.getHeight()) {
                    destroy();
                    stop();
                }
            }
        };
        timer.start();
    }

    private void checkCollision() {
        if(isEnemyBullet) {
            Player player = mainWindow.getGameFrame().getPlayer();
            if(entityHitbox.getBoundsInParent().intersects(player.getHitbox().getBoundsInParent()) && player.isTargetable) {
                destroy();
                player.takeDamage(damage);
            }
        }
    }

    // Adjust hitbox for bullets smaller size
    @Override
    public void setHitbox() {
        if (entityImage != null) {
            entityHitbox = new Rectangle(
                    entityImage.getFitWidth() * 0.20 ,
                    entityImage.getFitHeight() * 0.4
            );
            entityHitbox.setFill(Color.BLACK);
            entityHitbox.setVisible(false);
        }
    }

    // Edited set position for smaller bullet hitbox
    @Override
    public void setPosition(double x, double y) {
        // Update character image position
        entityImage.setTranslateX(x);
        entityImage.setTranslateY(y);
        // Update hitbox position
        entityHitbox.setTranslateX(entityImage.getTranslateX() + entityImage.getFitWidth() * 0.40);
        entityHitbox.setTranslateY(entityImage.getTranslateY());
    }

    /** Remove bullet from grid
     */
    @Override
    public void destroy() {
        isActive = false;
        checkLoop.stop();
        mainWindow.getGrid().getChildren().removeAll(entityImage, entityHitbox);
    }

    @Override
    public void takeDamage(int Damage) {}
    
    // Getter/Setter methods
    public int getDamage() { return damage; }
    public boolean isActive() { return isActive; }
    public boolean isEnemyBullet() { return isEnemyBullet; }

    public void setActive(boolean active) { isActive = active; }
    public void setEnemyBullet(boolean enemyBullet) { this.isEnemyBullet = enemyBullet; }
    public void setDamage(int damage) { this.damage = damage; }
}