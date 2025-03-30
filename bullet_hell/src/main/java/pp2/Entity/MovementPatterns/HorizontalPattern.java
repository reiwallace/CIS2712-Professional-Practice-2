package pp2.Entity.MovementPatterns;

import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class HorizontalPattern extends MovementPattern{
    private Double[] coordinates1 = new Double[2];
    private Double[] coordinates2 = new Double[2];
    private boolean atCoordinates1 = false;
    private SequentialTransition moveImage;
    private SequentialTransition moveHitbox;

    public HorizontalPattern(Entity entity, MainWindow mainWindow, int speed, Boolean moving, Double[] coordinates1, Double[] coordinates2) {
        super(entity, mainWindow, speed, moving);
        this.coordinates1 = coordinates1;
        this.coordinates2 = coordinates2;
    }

    @Override
    public void moveEntity() {
        TranslateTransition movementLeft = new TranslateTransition();
        movementLeft.setByX(entity.getPos()[0] - coordinates1[0]);
        movementLeft.setByY(entity.getPos()[1] - coordinates1[1]);
        movementLeft.setCycleCount(1);
        TranslateTransition movementRight = new TranslateTransition();
        movementRight.setByX(entity.getPos()[0] - coordinates2[0]);
        movementRight.setByY(entity.getPos()[1] - coordinates2[1]);
        movementLeft.setCycleCount(1);

        moveImage = new SequentialTransition(entity.getImage(), movementLeft, movementRight);
        moveImage.setCycleCount(TranslateTransition.INDEFINITE);
        moveHitbox = new SequentialTransition(entity.getImage(), movementLeft, movementRight);
        moveHitbox.setCycleCount(TranslateTransition.INDEFINITE);
    }

    @Override
    public void stopMovement() {
        moveImage.stop();
        moveHitbox.stop();
    }
}
