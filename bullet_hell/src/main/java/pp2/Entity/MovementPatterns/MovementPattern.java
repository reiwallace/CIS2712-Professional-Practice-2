package pp2.Entity.MovementPatterns;

import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public abstract class MovementPattern {
    protected Entity entity;
    protected MainWindow mainWindow;
    protected Double speed = 1.0;
    protected boolean moving = false;
    
    public MovementPattern(MainWindow mainWindow, Double speed) {
        this.mainWindow = mainWindow;
        this.speed = speed;
    }

    // Setters/getters
    public void setEntity(Entity e) { this.entity = e; }

    /** Move the entity in a specific pattern
     */
    protected abstract void moveEntity();
    /** Start entity movement
     */
    public abstract void startMovement();
    /** Stop entity movement
     */
    public abstract void stopMovement();
    /** Stops position tracker
     */
    public abstract void closePositionTracker();
}
