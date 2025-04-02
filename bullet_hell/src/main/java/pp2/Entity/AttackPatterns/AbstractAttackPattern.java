package pp2.Entity.AttackPatterns;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public abstract class AbstractAttackPattern {
    protected MainWindow mainWindow; // Main game window
    protected Entity entity; // Attacking enemy
    protected Double fireRate; // Rate of fire
    protected Double speed; // Speed of bullets
    protected Timeline shootTimer; // Timer to repeat shots

    /** Abstract method for firing bullets
     * @param mainWindow - Main window to attach bullets to
     * @param entity - Entity to fire bullets from
     * @param fireRate - Rate of fire for bullets in seconds
     * @param speed - speed of bullet
     */
    public AbstractAttackPattern(MainWindow mainWindow, Entity entity, Double fireRate, Double speed) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.fireRate = fireRate;
        this.speed = speed;

        if(entity instanceof Enemy) ((Enemy)entity).setAttackPattern(this);
    }

    /** Start entity firing
     */
    public void startFiring() {
        if(fireRate < 1) fireRate = 2.0;
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(fireRate), e -> fire()));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
    }

    /** Stop entity firing
     */
    public void stopFiring() {
        shootTimer.stop();
    }

    /** Bullet firing function
     */
    public abstract void fire();
}
