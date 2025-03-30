package pp2.Entity.MovementPatterns;


import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class CirclePattern extends MovementPattern {
    private int[] centrePoint = new int[2]; 
    private int radius = 10;

    private Double[] nextPoint = new Double[2];
    /** Start a circle pattern for an entity with a given speed
     * @param entity - Entity to move
     * @param mainWindow - Window entity is on
     * @param speed - Speed of movement
     */
    public CirclePattern(Entity entity, MainWindow mainWindow, int speed, boolean moving) {
        super(entity, mainWindow, speed, moving);
        centrePoint[0] = (int) (mainWindow.getGameFrame().getGameFrame().getWidth() / 2);
        centrePoint[1] = (int) (mainWindow.getGameFrame().getGameFrame().getHeight() * 0.6);
        radius = (int) (mainWindow.getGameFrame().getGameFrame().getWidth() / 3);

        nextPoint[0] = (double) centrePoint[0] + radius;
        nextPoint[1] = (double) centrePoint[1];
    }

    @Override
    protected void moveEntity() {
        TranslateTransition movement = new TranslateTransition(Duration.seconds(speed));   
        // CIRCLE MATH
    }

    @Override
    public void stopMovement() {
        
    }
}   
