package pp2.Levels;

import javafx.scene.shape.Rectangle;
import pp2.Entity.Entity;
import pp2.Entity.AttackPatterns.EightShotPattern;
import pp2.Entity.Enemies.Enemy;
import pp2.Entity.MovementPatterns.CirclePattern;
import pp2.Entity.MovementPatterns.StationaryPattern;
import pp2.GUI.MainWindow;

public class Level1 extends AbstractLevel{
    public Level1(MainWindow mainWindow) {
        super(mainWindow.getGameFrame().getGameFrame());
        Rectangle gameFrame = mainWindow.getGameFrame().getGameFrame();

        mainWindow.getGameFrame().getPlayer().setIsTargetable(true);
        mainWindow.getGameFrame().getPlayer().setPosition(gameFrame.getWidth()/2 - mainWindow.getGameFrame().getPlayer().getImage().getFitWidth() /2, gameFrame.getHeight() * 0.8);

        // Enemy spawns

        // 3 - 20 seconds stationary enemies
        for(int i = 3; i < 21; i++) {
          
        }
    }
}
