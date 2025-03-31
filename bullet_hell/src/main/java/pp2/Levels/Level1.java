package pp2.Levels;

import javafx.scene.shape.Rectangle;
import pp2.Entity.Enemies.Enemy;
import pp2.Entity.MovementPatterns.HorizontalPattern;
import pp2.GUI.MainWindow;

public class Level1 extends AbstractLevel{
    public Level1(MainWindow mainWindow) {
        super(mainWindow.getGameFrame().getGameFrame());
        Rectangle gameFrame = mainWindow.getGameFrame().getGameFrame();

        // Enemy spawns
        for(int i = 5; i < 20; i++) {
            Enemy enemy1 = new Enemy(10, 10, mainWindow, gameFrame, 0);
            mainWindow.getGameFrame().getEntities().add(enemy1);
            HorizontalPattern pattern1 = new HorizontalPattern(mainWindow, 1, new Double[] {gameFrame.getWidth() * 0.2  - enemy1.getImage().getFitWidth()/2, gameFrame.getHeight() * 0.25 - i}, new Double[] {gameFrame.getWidth() * 0.8 - enemy1.getImage().getFitWidth()/2, gameFrame.getHeight() * 0.25 - i});
            spawnEnemy(i, 10, enemy1, pattern1, entryPositionTopLeft, gameFrame);
        }

        mainWindow.getGameFrame().getPlayer().setPosition(gameFrame.getWidth()/2 - mainWindow.getGameFrame().getPlayer().getImage().getFitWidth() /2, gameFrame.getHeight() * 0.8);
    }
}
