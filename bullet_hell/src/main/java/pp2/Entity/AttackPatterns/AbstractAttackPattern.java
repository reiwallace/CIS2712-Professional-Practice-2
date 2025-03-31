package pp2.Entity.AttackPatterns;

import pp2.Entity.Entity;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public abstract class AbstractAttackPattern {
    protected MainWindow mainWindow; // Main game window
    protected Entity entity; // Attacking enemy
    protected int fireRate; // Rate of fire
    protected int speed; // Speed of bullets

    /** Allows an enemy to shoot in a chosen pattern
     * @param mainWindow - Main window to get player and grid from
     * @param entity - Entity to shoot from
     */
    public AbstractAttackPattern(MainWindow mainWindow, Entity entity, int fireRate, int speed) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.fireRate = fireRate;
        this.speed = speed;

        if(entity instanceof Enemy) ((Enemy)entity).setAttackPattern(this);
    }

    /** Start entity firing
     */
    public abstract void startFiring();

    /** Stop entity firing
     */
    public abstract void stopFiring();

    /** Bullet firing function
     */
    public abstract void fire();
}
