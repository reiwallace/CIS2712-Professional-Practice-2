package pp2.Entity.AttackPatterns;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class DownwardFiring extends AbstractAttackPattern{
    private Timeline shootTimer; // Timeline to repeatedly shoot bullet

    /** Start firing downwards
     * @param mainWindow - Main window to attach bullets to
     * @param entity - Entity to fire bullets from
     * @param fireRate - Rate of fire for bullets
     * @param speed - speed of bullet
     */
    public DownwardFiring(MainWindow mainWindow, Entity entity, int fireRate, int speed) {
        super(mainWindow, entity, fireRate, speed);
    }

    @Override
    public void startFiring() {
        if(fireRate < 1) fireRate = 2;
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(fireRate), e -> fire()));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
    }
    
    @Override
    public void stopFiring() {
        shootTimer.stop();
    }

    @Override
    public void fire() {
        Bullet enemyBullet = new Bullet(mainWindow, entity.getPos()[0], entity.getPos()[1] + entity.getImage().getFitHeight() / 2 , true, 1, speed);
        mainWindow.getGameFrame().getEntities().add(enemyBullet);
    }
}
