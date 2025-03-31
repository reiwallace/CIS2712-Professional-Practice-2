package pp2.Entity.AttackPatterns;

import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.GUI.MainWindow;

public class DownwardFiring extends AbstractAttackPattern{
    /** Start firing downwards
     * @param mainWindow - Main window to attach bullets to
     * @param entity - Entity to fire bullets from
     * @param fireRate - Rate of fire for bullets in seconds
     * @param speed - speed of bullet
     */
    public DownwardFiring(MainWindow mainWindow, Entity entity, int fireRate, Double speed) {
        super(mainWindow, entity, fireRate, speed);
    }

    @Override
    public void fire() {
        Bullet enemyBullet = new Bullet(mainWindow, entity.getPos()[0], entity.getPos()[1] + entity.getImage().getFitHeight() / 2 , true, false, 1);
        enemyBullet.moveBullet(new Double[] {0.0, speed});
        mainWindow.getGameFrame().getEntities().add(enemyBullet);
    }
}
