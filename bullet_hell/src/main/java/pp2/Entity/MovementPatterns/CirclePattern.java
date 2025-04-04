package pp2.Entity.MovementPatterns;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public class CirclePattern extends MovementPattern {
    private Double[] centrePoint;
    private Double radius = 10.0; // Default radius
    private AnimationTimer positionTracker;
    private TranslateTransition circleMovement;

    private double angle = 180; // in radians

    /**
     * Start a circle pattern for an entity with a given speed
     * @param entity - Entity to move
     * @param mainWindow - Window entity is on
     * @param speed - Speed of movement (lower = faster)
     * @param moving - If entity is moving
     */
    public CirclePattern(MainWindow mainWindow, Double speed, Entity entity, Double radius, Double[] centre) {
        super(mainWindow, speed);  
        this.radius = radius;
        this.centrePoint = centre;
    }

    /* Move entity to starting position and start main movement loop
     */
    @Override
    public void startMovement() {
        positionTracker = new AnimationTimer() { // Update entity hitbox with transition
        @Override
        public void handle(long now) {
            entity.setPosition(entity.getPos()[0], entity.getPos()[1]);
            }
        };
        positionTracker.start();
        // Move entity to position
        TranslateTransition initialMovement = new TranslateTransition(Duration.seconds(speed), entity.getImage());
        initialMovement.setToX(centrePoint[0] + radius * Math.cos(angle));
        initialMovement.setToY(centrePoint[1] + radius * Math.sin(angle));
        initialMovement.setCycleCount(1);
        initialMovement.setAutoReverse(false);
        initialMovement.setOnFinished( e -> { // Start enemy firing when in position
            moveEntity();
            if(entity instanceof Enemy) ((Enemy)entity).getAttackPattern().startFiring();
        });
        initialMovement.play();
    }

    // Main movememnt loop
    @Override
    protected void moveEntity() {
        circleMovement = new TranslateTransition(Duration.millis(16), entity.getImage());
        circleMovement.setToX(centrePoint[0] + radius * Math.cos(angle));
        circleMovement.setToY(centrePoint[1] + radius * Math.sin(angle));
        circleMovement.setCycleCount(1);
        circleMovement.setAutoReverse(false);
        circleMovement.setOnFinished( e -> {
            moveEntity();
        });
        circleMovement.play();
        // Update angle
        angle += 0.05; // smaller = smoother and slower
    }

    @Override
    public void stopMovement() {
        circleMovement.stop();
    }

    @Override
    public void closePositionTracker() {
        positionTracker.stop();
    }
}
