package pp2.Levels;

import java.util.concurrent.ThreadLocalRandom;

import javafx.geometry.HorizontalDirection;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import pp2.Entity.AttackPatterns.AimedShotPattern;
import pp2.Entity.AttackPatterns.DownwardFiring;
import pp2.Entity.AttackPatterns.EightShotPattern;
import pp2.Entity.Enemies.Enemy;
import pp2.Entity.MovementPatterns.CirclePattern;
import pp2.Entity.MovementPatterns.HorizontalPattern;
import pp2.Entity.MovementPatterns.StationaryPattern;
import pp2.GUI.MainWindow;

public class Level1 extends AbstractLevel{
    private boolean flip = false;
    private Double[][] peekerPositions; // Positions peekers can spawn at
    private Double[][] rushPositions; // Positions stationaries sit at during the rush
    private int peekerSize = 50;

    public Level1(MainWindow mainWindow) {
        super(mainWindow.getGameFrame().getGameFrame());
        Rectangle gameFrame = mainWindow.getGameFrame().getGameFrame();

        peekerPositions = new Double[][]{ 
            {gameFrame.getWidth() * 0.1 - peekerSize, gameFrame.getHeight() * 0.6},
            {gameFrame.getWidth() * 0.1 - peekerSize, gameFrame.getHeight() * 0.5},
            {gameFrame.getWidth() * 0.1 - peekerSize, gameFrame.getHeight() * 0.4},
            {gameFrame.getWidth() * 0.1 - peekerSize, gameFrame.getHeight() * 0.3},
            {gameFrame.getWidth() * 0.1 - peekerSize, gameFrame.getHeight() * 0.2},
            {gameFrame.getWidth() * 0.9 - peekerSize, gameFrame.getHeight() * 0.6},
            {gameFrame.getWidth() * 0.9 - peekerSize, gameFrame.getHeight() * 0.5},
            {gameFrame.getWidth() * 0.9 - peekerSize, gameFrame.getHeight() * 0.4},
            {gameFrame.getWidth() * 0.9 - peekerSize, gameFrame.getHeight() * 0.3},
            {gameFrame.getWidth() * 0.9 - peekerSize, gameFrame.getHeight() * 0.2},
        };

        rushPositions = new Double[][]{
            {gameFrame.getWidth() * 0.31, gameFrame.getHeight() * 0.27}, 
            {gameFrame.getWidth() * 0.40, gameFrame.getHeight() * 0.41}, 
            {gameFrame.getWidth() * 0.41, gameFrame.getHeight() * 0.33}, 
            {gameFrame.getWidth() * 0.49, gameFrame.getHeight() * 0.37}, 
            {gameFrame.getWidth() * 0.51, gameFrame.getHeight() * 0.53}, 
            {gameFrame.getWidth() * 0.54, gameFrame.getHeight() * 0.41}, 
            {gameFrame.getWidth() * 0.57, gameFrame.getHeight() * 0.13}, 
            {gameFrame.getWidth() * 0.58, gameFrame.getHeight() * 0.53}, 
            {gameFrame.getWidth() * 0.58, gameFrame.getHeight() * 0.50}, 
            {gameFrame.getWidth() * 0.67, gameFrame.getHeight() * 0.44}, 
            {gameFrame.getWidth() * 0.79, gameFrame.getHeight() * 0.60} 
        };

        mainWindow.getGameFrame().getPlayer().setIsTargetable(true);
        mainWindow.getGameFrame().getPlayer().setPosition(gameFrame.getWidth()/2 - mainWindow.getGameFrame().getPlayer().getImage().getFitWidth() /2, gameFrame.getHeight() * 0.8);

        // Enemy spawns

        // 3 - 20 seconds stationary slow firing enemies
        for(int i = 2; i < 19; i += 3) {
            Enemy enemy = new Enemy(1, 1, mainWindow, gameFrame);
            StationaryPattern pattern = new StationaryPattern(mainWindow, new Double[] {(gameFrame.getWidth() / 2 * i/19) - enemy.getImage().getFitWidth()/2, gameFrame.getHeight() * (0.1 + 0.2 * i/19)}, 1.0);
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 4.0, 2.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(i + 2, 10, enemy, pattern, entryPositionLeft, gameFrame);
        }
        for(int i = 2; i < 19; i += 3) {
            Enemy enemy = new Enemy(1, 1, mainWindow, gameFrame);
            StationaryPattern pattern = new StationaryPattern(mainWindow, new Double[] {gameFrame.getWidth() - (gameFrame.getWidth() / 2 * i/19) - enemy.getImage().getFitWidth()/2, gameFrame.getHeight() * (0.1 + 0.2 * i/19)}, 1.0);
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 4.0, 2.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(i + 2, 10, enemy, pattern, entryPositionRight, gameFrame);
        }

        // 18 seconds spawn a healthier more aggressive enemy
        Enemy enemyCenter18 = new Enemy(10, 3, mainWindow, gameFrame);
        enemyCenter18.setFitSize(80, 80);
        StationaryPattern patternCenter18 = new StationaryPattern(mainWindow, new Double[] {gameFrame.getWidth() / 2 - enemyCenter18.getImage().getFitWidth()/2, gameFrame.getHeight() * 0.15}, 1.0);
        EightShotPattern eightshotRight18 = new EightShotPattern(mainWindow, enemyCenter18, 2000.0);
        mainWindow.getGameFrame().getEntities().add(enemyCenter18);
        spawnEnemy(18, 20, enemyCenter18, patternCenter18, entryPositionLeft, gameFrame);

        // 22 - 40 seconds spawn a swarm of horizontal moving enemies
        for(int i = 1; i < 19; i += 2) {
            Double speed = 1.5;
            if(flip) speed = 1.5;
            else speed = 1.5;

            Enemy enemy = new Enemy(2, 1, mainWindow, gameFrame);
            HorizontalPattern pattern = new HorizontalPattern(mainWindow, speed, new Double[] {gameFrame.getWidth() * 0.1 - enemy.getImage().getFitWidth(), gameFrame.getHeight() * (0.4 - 0.25 * i/16)}, new Double[] {gameFrame.getWidth() * 0.9 - enemy.getImage().getFitWidth(), gameFrame.getHeight() * (0.4 - 0.25 * i/16)});
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 2.0 * i/19 + 1, 2.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            if(flip) spawnEnemy(21 + i, 10, enemy, pattern, entryPositionTopLeft, gameFrame);
            else spawnEnemy(21 + i, 10, enemy, pattern, entryPositionTopRight, gameFrame);
            flip = !flip;
        }

        // 38 - 50 seconds in spawn peekers
        for(int i = 1; i < 13; i += 3) {
            int arrayIndex = i - 1;
            if(arrayIndex > peekerPositions.length) arrayIndex = i - peekerPositions.length;
            Double[] entryPoint = new Double[2];
            if(peekerPositions[arrayIndex][0] > gameFrame.getWidth()/2) entryPoint = new Double[] {peekerPositions[arrayIndex][0] + gameFrame.getWidth() * 0.15, peekerPositions[arrayIndex][1]};
            else entryPoint = new Double[] {peekerPositions[arrayIndex][0] - gameFrame.getWidth() * 0.15, peekerPositions[arrayIndex][1]};
            Enemy enemy = new Enemy(10, 5, mainWindow, gameFrame);
            enemy.getImage().setImage(new Image("file:resources/Entities/peeker.png"));
            StationaryPattern pattern = new StationaryPattern(mainWindow, peekerPositions[arrayIndex], 0.5);
            AimedShotPattern attack = new AimedShotPattern(mainWindow, enemy, mainWindow.getGameFrame().getPlayer(), 1.0, 2.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(37 + i, 2, enemy, pattern, entryPoint, gameFrame);
        }

        // 46 - 90 seconds pre-boss rush
        // Stationary enemies
        for(int i = 1; i < 45; i += 4) {
            Enemy enemy = new Enemy(1, 1, mainWindow, gameFrame);
            StationaryPattern pattern = new StationaryPattern(mainWindow, rushPositions[(i-1)/4], 1.0);
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 3.0, 1.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(46 + i + 1, 12, enemy, pattern, findClosestEdgePosition(rushPositions[(i-1)/4], gameFrame), gameFrame);
        }

        // Circling enemies left side
        for(int i = 1; i < 45; i += 2) {
            Enemy enemy = new Enemy(2, 1, mainWindow, gameFrame);
            CirclePattern pattern = new CirclePattern(mainWindow, 1.0, enemy, gameFrame.getWidth() * 0.15, new Double[] {gameFrame.getWidth() * 0.3 - enemy.getImage().getFitWidth(), gameFrame.getHeight() * 0.3});
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 3.0, 1.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(46 + i + 1, 10, enemy, pattern, entryPositionTopLeft, gameFrame);
        }

        // Circling enemies right side
        for(int i = 1; i < 45; i += 2) {
            Enemy enemy = new Enemy(2, 1, mainWindow, gameFrame);
            CirclePattern pattern = new CirclePattern(mainWindow, 1.0, enemy, gameFrame.getWidth() * 0.15, new Double[] {gameFrame.getWidth() * 0.7 - enemy.getImage().getFitWidth(), gameFrame.getHeight() * 0.3});
            DownwardFiring attack = new DownwardFiring(mainWindow, enemy, 3.0, 1.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(46 + i + 1, 10, enemy, pattern, entryPositionTopRight, gameFrame);
        }

        // Peepers


        // Chonkers
        for(int i = 0; i < 2; i++) {
            Double stationary = 0.7;
            Double[] entryPosition = entryPositionRight;
            if(i == 0) {
                stationary = 0.3;
                entryPosition = entryPositionLeft;
            } 
            Enemy enemy = new Enemy(10, 3, mainWindow, gameFrame);
            enemy.setFitSize(80, 80);
            StationaryPattern pattern = new StationaryPattern(mainWindow, new Double[] {gameFrame.getWidth() * stationary - enemy.getImage().getFitWidth()/2, gameFrame.getHeight() * 0.3}, 1.0);
            EightShotPattern eightshot = new EightShotPattern(mainWindow, enemy, 2000.0);
            mainWindow.getGameFrame().getEntities().add(enemy);
            spawnEnemy(18, 20, enemy, pattern, entryPositionLeft, gameFrame);
        }
    }
}
