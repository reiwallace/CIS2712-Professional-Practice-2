package pp2.Levels;

import java.util.Timer;
import java.util.TimerTask;

import pp2.Entity.Entity;
import pp2.Entity.MovementPatterns.MovementPattern;

public abstract class AbstractLevel {
    public AbstractLevel() {}

    /** Spawns an enemy at a set time with a set despawn time.
     * @param spawnDelay - Time after starting the level to spawn an enemy
     * @param despawnDelay - Time after spawning the enemy to despawn the enemy
     * @param enemy - Enemy to spawn
     * @param movementPattern - Enemy movement pattern
     * @param entryPosition - [x, y] coordinates to spawn enemy at
     */
    public void spawnEnemy(int spawnDelay, int despawnDelay, Entity enemy, MovementPattern movementPattern, int[] entryPosition) {
        Timer spawnTimer = new Timer();
        TimerTask spawnEnemy = new TimerTask() {
            @Override
            public void run() {
                
            }
        };
        spawnTimer.schedule(spawnEnemy, spawnDelay * 1000);

        Timer despawnTimer = new Timer();
        TimerTask despawnEnemy = new TimerTask() {
            @Override
            public void run() {
                
            }
        };
        despawnTimer.schedule(despawnEnemy, despawnDelay * 1000);
    }
}