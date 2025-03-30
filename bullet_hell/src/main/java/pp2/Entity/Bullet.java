package pp2.Entity;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public class Bullet extends Entity {
    private final double speed; // Speed of the bullet
    private boolean isActive = true;
    private boolean isEnemyBullet; // To identify the enemy or player bullet
    private int damage; // Depend on either enemy or player bullets, the damage is different.

    /** Initialise a bullet
     * @param gameFrame - Area the game is played in 
     * @param gameGrid - Window grid to attach bullet to
     * @param x_pos - Initial X position of bullet
     * @param y_pos - Initial Y position of bullet
     * @param isEnemyBullet - Whether the bullet is fired from enemy or player
     * @param damage - Damage of the bullet
     * @param imagePath - Image URL to use for bullet
     */
    public Bullet(Rectangle gameFrame, MainWindow mainWindow, double initialX, double initialY, boolean isEnemyBullet, int damage, String imagePath) {
        super(imagePath, 1, 3, mainWindow, gameFrame);
        this.gameFrame = gameFrame;
        this.mainWindow = mainWindow;
        this.isEnemyBullet = isEnemyBullet;
        this.damage = damage;
        this.speed = isEnemyBullet ? 4.0 : -6.0; // Enemy bullets move down, player bullets move up

        // Set the bullet to an imageView
        setPosition(initialX, initialY);
        addEntity(1, 3);

        // Move bullet
        moveBullet();
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
    private void moveBullet() {
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

    /** Remove bullet from grid
     */
    @Override
    public void destroy() {
        isActive = false;
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