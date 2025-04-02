package pp2.Entity.AttackPatterns;

import javafx.scene.effect.Light.Point;
import pp2.Entity.Bullet;
import pp2.Entity.Entity;
import pp2.Entity.Player;
import pp2.Entity.Enemies.Enemy;
import pp2.GUI.MainWindow;

public class AimedShotPattern extends AbstractAttackPattern {
    private Player player; // Used for directional calculation

    public AimedShotPattern(MainWindow mainWindow, Entity entity, Player target, Double fireRate, Double speed) {
        super(mainWindow, entity, fireRate, speed);
        this.player = target;
    }

    @Override
    public void fire() {  
        entity.getImage().setRotate(getAngle(entity, player));
        Bullet enemyBullet = new Bullet(mainWindow, entity.getPos()[0], entity.getPos()[1], true, false, ((Enemy)entity).getDamage());
        enemyBullet.moveBullet(calculateDirection());
        mainWindow.getGameFrame().getEntities().add(enemyBullet);
    }

    /** Calculates direction to fire bullet from to the player
     */
    public Double[] calculateDirection() {
        Double angle = Math.atan2(entity.getPos()[1] - player.getPos()[1], entity.getPos()[0] - player.getPos()[0]);
        return new Double[] {-Math.cos(angle) * speed, -Math.sin(angle) * speed};
    }

    public float getAngle(Entity entity, Player player) {
    float angle = (float) Math.toDegrees(Math.atan2(entity.getPos()[1] - player.getPos()[1], entity.getPos()[0] - player.getPos()[0]));

    if(angle < 0){
        angle += 360;
    }

    return angle - 90;
}
}
