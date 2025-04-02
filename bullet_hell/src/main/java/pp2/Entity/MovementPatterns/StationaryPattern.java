package pp2.Entity.MovementPatterns;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public class StationaryPattern extends MovementPattern{
    private Double[] positionChoice;
    private AnimationTimer positionTracker;

    /** Keep enemy stationary in a set position
     * @param mainWindow - Window to move entity in
     * @param position - coordinates to move entity to [x, y]
     * @param speed - how long it takes enemy to position in seconds
     */
    public StationaryPattern(MainWindow mainWindow, Double[] position, int speed) {
        super(mainWindow, speed);
        positionChoice = position;
    }

    @Override
    public void startMovement() {
        // Update position and hitbox continuously
        positionTracker = new AnimationTimer() {
        @Override
        public void handle(long now) {
            entity.setPosition(entity.getPos()[0], entity.getPos()[1]);
            }
        };
        positionTracker.start();
        // Move entity to position
        TranslateTransition initialMovement = new TranslateTransition(Duration.seconds(speed), entity.getImage());
        initialMovement.setToX(positionChoice[0]);
        initialMovement.setToY(positionChoice[1]);
        initialMovement.setCycleCount(1);
        initialMovement.setAutoReverse(false);
        initialMovement.setOnFinished( e -> { // Start enemy firing when in position
            if(entity instanceof Enemy) ((Enemy)entity).getAttackPattern().startFiring();
        });
        initialMovement.play();
    }

    @Override
    public void closePositionTracker() {
        positionTracker.stop();
    }

    // Redundant methods
    public void moveEntity() {}
    public void stopMovement() {}
}
