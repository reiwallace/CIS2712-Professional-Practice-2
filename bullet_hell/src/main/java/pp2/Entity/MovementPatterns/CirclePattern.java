package pp2.Entity.MovementPatterns;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class CirclePattern extends MovementPattern {
    private int[] centrePoint = new int[2];
    private int radius = 100; // Default radius

    private Timeline motionTimeline;
    private double angle = 0; // in radians

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
        centrePoint[0] = (int) (mainWindow.getGameFrame().getGameFrame().getWidth() / 2);
        centrePoint[1] = (int) (mainWindow.getGameFrame().getGameFrame().getHeight() * 0.6);
        radius = (int) (mainWindow.getGameFrame().getGameFrame().getWidth() / 3);

        System.out.println("This is x: " + centrePoint[0]);
        System.out.println("This is y: " + centrePoint[1]);
        System.out.println("This is radius: " + radius);
    }

    @Override
    protected void moveEntity() {
        if (!moving) return;

        // Update angle
        angle += 0.05; // smaller = smoother and slower

        // Calculate new x and y
        double x = centrePoint[0] + radius * Math.cos(angle);
        double y = centrePoint[1] + radius * Math.sin(angle);

        // Update entity position
        entity.setPosition((int) x, (int) y);
    }

    @Override
    public void startMovement() {
        moving = true;

        // Update position every 16ms
        motionTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> moveEntity()));
        motionTimeline.setCycleCount(Timeline.INDEFINITE);
        motionTimeline.play();
    }

    @Override
    public void stopMovement() {
        moving = false;
        if (motionTimeline != null) {
            motionTimeline.stop();
        }
    }
}
