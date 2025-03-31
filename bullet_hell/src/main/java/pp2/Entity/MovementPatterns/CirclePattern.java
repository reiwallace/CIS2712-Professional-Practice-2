package pp2.Entity.MovementPatterns;

import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class CirclePattern extends MovementPattern {
    private double centreX, centreY; // centre of circular path
    private double radius; // radius of the circle
    private double pathAngle; // current angle of the entity in the circular path

    public CirclePattern(Entity entity, MainWindow mainWindow, double centreX, double centreY, double radius, int speed) {
        super(entity, mainWindow, speed, true);
        this.centreX = centreX;
        this.centreY = centreY;
        this.radius = radius;
        this.pathAngle = 0; // start at 0 degrees
    }

    @Override
    public void moveEntity() { // changed method from private to public
        pathAngle += speed;  // update the angle based on speed
        if (pathAngle >= 360) {
            pathAngle -= 360; // prevent the angle from going over 360
        }

        // convert the angle to radians for trigonometry
        double angleInRadians = Math.toRadians(pathAngle);

        // calculate the new position on the circumfrence of the circle
        double x = centreX + radius * Math.cos(angleInRadians); 
        double y = centreY + radius * Math.sin(angleInRadians);

        // update the entity's position -- this required changing the visiblity to public
        entity.setPosition(x, y);
    }

    @Override
    public void stopMovement() {
        moving = false;
    }
}
