package pp2.Entity.AttackPatterns;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class EightShotPattern extends AbstractAttackPattern{
    private Double[][] trajectories = {
        {0.0, 0.5},
        {0.3, 0.3},
        {0.5, 0.0},
        {0.3, -0.3},
        {0.0, -0.5},
        {-0.3, -0.3},
        {-0.5, 0.0},
        {-0.3, 0.3}
    };
    private int arrayCount = 0;
    /** Start in an 8 directional pattern
     * @param mainWindow - Main window to attach bullets to
     * @param entity - Entity to fire bullets from
     * @param fireRate - Rate of fire for bullets in seconds :3
     * @param speed - speed of bullet
     */
    public EightShotPattern(MainWindow mainWindow, Entity entity, int fireRate) {
        super(mainWindow, entity, fireRate, 0.0);
    }

    @Override
    public void startFiring() {
        if(fireRate < 1) fireRate = 100;
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(fireRate), e -> fire()));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
    }

    @Override
    public void fire() {
        for(int i = 0; i < trajectories.length; i++) {
            Bullet enemyBullet = new Bullet(mainWindow, entity.getPos()[0], entity.getPos()[1], true, true, 1);
            enemyBullet.moveBullet(trajectories[i]);
            mainWindow.getGameFrame().getEntities().add(enemyBullet);
        }
    }

}
