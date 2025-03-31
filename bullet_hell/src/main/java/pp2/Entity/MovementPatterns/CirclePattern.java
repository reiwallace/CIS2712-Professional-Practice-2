package pp2.Entity.MovementPatterns;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public class CirclePattern extends MovementPattern {
    private Double[] centrePoint = new Double[2];
    private int radius = 100; // Default radius
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
    public CirclePattern(Entity entity, MainWindow mainWindow, int speed, boolean moving) {
        super(mainWindow, speed);
        this.entity = entity;
        this.moving = moving;

        // Set the center of the circle to be somewhere in the game frame
        centrePoint[0] = (mainWindow.getGameFrame().getGameFrame().getWidth() * 0.5  - entity.getImage().getFitWidth()/2);
        centrePoint[1] = (mainWindow.getGameFrame().getGameFrame().getHeight() * 0.3);
        radius = (int) (mainWindow.getGameFrame().getGameFrame().getWidth() / 4);
    }

    @Override
    public void startMovement() {
        positionTracker = new AnimationTimer() {
        @Override
        public void handle(long now) {
            entity.setPosition(entity.getPos()[0], entity.getPos()[1]);
            }
        };
        positionTracker.start();
        TranslateTransition initialMovement = new TranslateTransition(Duration.seconds(speed), entity.getImage());
        initialMovement.setToX(centrePoint[0] + radius * Math.cos(angle));
        initialMovement.setToY(centrePoint[1] + radius * Math.sin(angle));
        initialMovement.setCycleCount(1);
        initialMovement.setAutoReverse(false);
        initialMovement.setOnFinished( e -> {
            moveEntity();
            if(entity instanceof Enemy) ((Enemy)entity).startShooting();
        });
        initialMovement.play();
    }

    @Override
    protected void moveEntity() {
        if (!moving) return;
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
        moving = false;
        circleMovement.stop();
        positionTracker.stop();
    }
}
