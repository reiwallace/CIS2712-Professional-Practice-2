package pp2.Entity.MovementPatterns;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public class HorizontalPattern extends MovementPattern{
    private Double[] coordinates1 = new Double[2];
    private Double[] coordinates2 = new Double[2];
    private TranslateTransition movementLeft;
    private TranslateTransition movementRight;
    private AnimationTimer positionTracker;

    /** Move entity in a horizontal pattern back and fourth
     * @param entity - Entity to move
     * @param mainWindow - Window to move entity in
     * @param speed - Speed of movement in seconds
     * @param coordinates1 - Coordinates of left-most point
     * @param coordinates2 - Coordinates of right-most point
     */
    public HorizontalPattern(MainWindow mainWindow, int speed, Double[] coordinates1, Double[] coordinates2) {
        super(mainWindow, speed);
        this.coordinates1 = coordinates1;
        this.coordinates2 = coordinates2;
    }

    public void startMovement() {
        // Update position and hitbox continuously
        positionTracker = new AnimationTimer() {
        @Override
        public void handle(long now) {
            entity.setPosition(entity.getPos()[0], entity.getPos()[1]);
            }
        };
        positionTracker.start();
        // Get closest movement point
        Double[] coordinates = findClosestCoordinate();
        // Move to initial point
        TranslateTransition initialMovement = new TranslateTransition(Duration.seconds(speed/2), entity.getImage());
        initialMovement.setToX(coordinates[0]);
        initialMovement.setToY(coordinates[1]);
        initialMovement.setCycleCount(1);
        initialMovement.setAutoReverse(false);
        initialMovement.setOnFinished( e -> {
            moveEntity();
            if(entity instanceof Enemy) ((Enemy)entity).getAttackPattern().startFiring();
        });
        initialMovement.play();
    }

    /** Finds the closest initial coordinate to the player
     * @return - The closest initial coordinate
     */
    private Double[] findClosestCoordinate() {
        Double distanceTo1 = Math.abs((coordinates1[0] - entity.getPos()[0]) + (coordinates1[1] - entity.getPos()[1]));
        Double distanceTo2 = Math.abs((coordinates2[0] - entity.getPos()[0]) + (coordinates2[1] - entity.getPos()[1]));
        if(distanceTo1 < distanceTo2) {
            return coordinates1;
        } else {
            return coordinates2;
        }
    }

    @Override
    public void moveEntity() {
        // Left side movement 
        movementLeft = new TranslateTransition(Duration.seconds(speed), entity.getImage());
        movementLeft.setToX(coordinates1[0]);
        movementLeft.setToY(coordinates1[1]);
        movementLeft.setCycleCount(1);
        movementLeft.setAutoReverse(false);
        // Right side movement
        movementRight = new TranslateTransition(Duration.seconds(speed), entity.getImage());
        movementRight.setToX(coordinates2[0]);
        movementRight.setToY(coordinates2[1]);
        movementRight.setCycleCount(1);
        movementRight.setAutoReverse(false);

        // Loop onto other movement upon finishing
        movementLeft.setOnFinished(e -> {
            movementRight.play();
        });
        movementRight.setOnFinished(e -> {
            movementLeft.play();
        });
        movementLeft.play();
    }

    @Override
    public void stopMovement() {
        movementLeft.stop();
        movementRight.stop();
    }
}
