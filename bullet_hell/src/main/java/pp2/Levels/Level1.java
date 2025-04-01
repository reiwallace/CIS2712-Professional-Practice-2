package pp2.Levels;

import javafx.scene.shape.Rectangle;
import pp2.Entity.AttackPatterns.EightShotPattern;
import pp2.Entity.Enemies.Enemy;
import pp2.Entity.MovementPatterns.CirclePattern;
import pp2.GUI.MainWindow;

public class Level1 extends AbstractLevel{
    public Level1(MainWindow mainWindow) {
        super(mainWindow.getGameFrame().getGameFrame());
        Rectangle gameFrame = mainWindow.getGameFrame().getGameFrame();

        // Enemy spawns
        for(int i = 5; i < 6; i++) {
            Enemy enemy1 = new Enemy(10, 10, mainWindow, gameFrame);
            EightShotPattern attackPattern = new EightShotPattern(mainWindow, enemy1, 100);
            mainWindow.getGameFrame().getEntities().add(enemy1);
            CirclePattern circlePattern1 = new CirclePattern(enemy1, mainWindow, 1, true);
            spawnEnemy(i, 10, enemy1, circlePattern1, entryPositionTopLeft, gameFrame);
        }

        mainWindow.getGameFrame().getPlayer().setIsTargetable(true);
        mainWindow.getGameFrame().getPlayer().setPosition(gameFrame.getWidth()/2 - mainWindow.getGameFrame().getPlayer().getImage().getFitWidth() /2, gameFrame.getHeight() * 0.8);
    }
}
