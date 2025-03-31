package pp2.Entity;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public class Bullet extends Entity {
    private static String enemyImagePath = "file:resources/Entities/enemy-bullet.png"; // Path of enemy bullet
    private boolean isActive = true;
    private boolean accelerating = false;
    private boolean isEnemyBullet; // To identify the enemy or player bullet
    private int damage; // Depend on either enemy or player bullets, the damage is different.
    private AnimationTimer checkLoop;
    private Double[] loopDirection = new Double[2]; // motion

    /** Initialise a bullet
     * @param mainWindow - Main window to attach bullet to
     * @param x_pos - Initial X position of bullet
     * @param y_pos - Initial Y position of bullet
     * @param isEnemyBullet - Whether the bullet is fired from enemy or player
     * @param damage - Damage of the bullet
     */
    public Bullet(MainWindow mainWindow, double initialX, double initialY, boolean isEnemyBullet, boolean accelerating, int damage) {
        super(enemyImagePath, 1, 3, mainWindow, mainWindow.getGameFrame().getGameFrame(), new int[] {40, 40});
        this.accelerating = accelerating;
        this.isEnemyBullet = isEnemyBullet;
        this.damage = damage;
        
        // Set the bullet to an imageView
        setPosition(initialX, initialY);
        addEntity(1, 3);

        setIsTargetable(false);

        checkLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                checkCollision(); // Continuously check for bullet collisions
            }
        };
        checkLoop.start();
    }

    /** Animation timer method to move the bullet in the appropriate ditection
     */
    public void moveBullet(Double[] direction) {
        this.loopDirection[0] = direction[0];
        this.loopDirection[1] = direction[1];
        AnimationTimer timer = new AnimationTimer() { // Move bullet in an animation timer
            @Override
            public void handle(long now) {
                if (!isActive) {
                    stop();
                    return;
                }

                // Accelerate bullet
                if(accelerating) {
                    loopDirection[0] = loopDirection[0] * 1.01;
                    loopDirection[1] = loopDirection[1] * 1.01;
                }

                // Move bullet and hitbox
                setPosition(getPos()[0] + loopDirection[0], getPos()[1] + loopDirection[1]);

                // Remove bullet if off-screen
                if (getPos()[1] < -10 || getPos()[1] > gameFrame.getHeight() || getPos()[0] < -10 || getPos()[0] > gameFrame.getWidth()) {
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
        mainWindow.getGameFrame().getEntities().remove(this);
        mainWindow.getGrid().getChildren().removeAll(entityImage, entityHitbox);
    }

    @Override
    public void takeDamage(int Damage) {}
    
    // Getter/Setter methods
    public int getDamage() { return damage; }
    public boolean isActive() { return isActive; }
    public boolean isEnemyBullet() { return isEnemyBullet; }

    public void setLoopDirection(Double x, Double y) { this.loopDirection[0] = x; this.loopDirection[1] = y; }
    public void setActive(boolean active) { isActive = active; }
    public void setEnemyBullet(boolean enemyBullet) { this.isEnemyBullet = enemyBullet; }
    public void setDamage(int damage) { this.damage = damage; }
}