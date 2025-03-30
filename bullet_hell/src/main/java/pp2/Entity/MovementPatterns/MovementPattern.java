package pp2.Entity.MovementPatterns;

import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public abstract class MovementPattern {
    protected Entity entity;
    protected MainWindow mainWindow;
    protected int speed = 1;
    protected boolean moving = false;
    
    public MovementPattern(Entity entity, MainWindow mainWindow, int speed, boolean moving) {
        this.entity = entity;
        this.mainWindow = mainWindow;
        this.speed = speed;
        this.moving = moving;
    }

    /** Move the entity in a specific pattern
     */
    protected abstract void moveEntity();
    /** Stop entity movement
     */
    public abstract void stopMovement();
}
