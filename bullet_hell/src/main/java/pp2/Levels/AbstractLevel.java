package pp2.Levels;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pp2.Entity.Entity;
import pp2.Entity.Enemies.Enemy;
import pp2.Entity.MovementPatterns.MovementPattern;

public abstract class AbstractLevel {
    protected Double[] entryPositionTopLeft; // Template entry points
    protected Double[] entryPositionTopRight;
    protected Double[] entryPositionLeft;
    protected Double[] entryPositionRight;

    public AbstractLevel(Rectangle gameFrame) {
        this.entryPositionTopLeft = new Double[] {gameFrame.getWidth() * 0.25, -50.0};
        this.entryPositionTopRight = new Double[] {gameFrame.getWidth() * 0.75, -50.0};
        this.entryPositionLeft = new Double[] {-50.0, gameFrame.getHeight() * 0.3};
        this.entryPositionRight = new Double[] {gameFrame.getWidth() + 50, gameFrame.getHeight() * 0.3};
    }

    /** Spawns an enemy at a set time with a set despawn time.
     * @param spawnDelay - Time after starting the level to spawn an enemy in seconds
     * @param despawnDelay - Time after spawning the enemy to despawn the enemy in seconds
     * @param enemy - Enemy to spawn
     * @param movementPattern - Enemy movement pattern
     * @param entryPosition - [x, y] coordinates to spawn enemy at
     */
    public void spawnEnemy(int spawnDelay, int despawnDelay, Entity enemy, MovementPattern movementPattern, Double[] entryPosition, Rectangle gameFrame) {
        // Define despawn task
        Timer despawnTimer = new Timer();
        TimerTask despawnEnemy = new TimerTask() {
            @Override
            public void run() {
                movementPattern.stopMovement();
                if(enemy instanceof Enemy) ((Enemy)enemy).getAttackPattern().stopFiring();
                despawn(findClosestEdge(enemy, gameFrame), enemy, movementPattern);
            }
        };
        // Define spawn task
        Timer spawnTimer = new Timer();
        TimerTask spawnEnemy = new TimerTask() {
            @Override
            public void run() {
                enemy.setPosition(entryPosition[0], entryPosition[1]);
                movementPattern.setEntity(enemy);
                enemy.getImage().setVisible(true);
                enemy.setIsTargetable(true);
                movementPattern.startMovement();
                // Start despawn timer
                despawnTimer.schedule(despawnEnemy, despawnDelay * 1000);
            }
        };
        spawnTimer.schedule(spawnEnemy, spawnDelay * 1000);
    }

    /** Finds the closest edge to an entity
     * @param entity - Entity to find closest edge from
     * @param gameFrame - The game play area
     * @return - Coordinates of the closest edge
     */
    private Double[] findClosestEdge(Entity entity, Rectangle gameFrame) {
        if(entity == null) return new Double[] {0.0, 0.0};
        Double x = entity.getPos()[0];
        Double y = entity.getPos()[1];
        Double frameWidth = gameFrame.getWidth();

        if(x > frameWidth/2) { // If on the right side of the frame
            if(frameWidth - x < y) { // If closer to the right edge than top of the screen
                return new Double[] {gameFrame.getWidth() + entity.getImage().getFitWidth() + 2, entity.getPos()[1]};
            } else {
                return new Double[] {entity.getPos()[0], 0 - entity.getImage().getFitHeight() - 2};
            }
        } else { // If on the left side of the frame
            if(0 + x < y) { // If closer to the left edge than the top of the screen
                return new Double[] {0 - entity.getImage().getFitWidth() - 2, entity.getPos()[1]};
            } else {
                return new Double[] {entity.getPos()[0], 0 - entity.getImage().getFitHeight() - 2};
            }
        }
    }

        /** Finds the closest edge to an entity
     * @param entity - Entity to find closest edge from
     * @param gameFrame - The game play area
     * @return - Coordinates of the closest edge
     */
    protected Double[] findClosestEdgePosition(Double[] pos, Rectangle gameFrame) {
        Double x = pos[0];
        Double y = pos[1];
        Double frameWidth = gameFrame.getWidth();

        if(x > frameWidth/2) { // If on the right side of the frame
            if(frameWidth - x < y) { // If closer to the right edge than top of the screen
                return new Double[] {gameFrame.getWidth() + 50 + 2, pos[1]};
            } else {
                return new Double[] {pos[0], 0 - 50.0 - 2};
            }
        } else { // If on the left side of the frame
            if(0 + x < y) { // If closer to the left edge than the top of the screen
                return new Double[] {0 - 50.0 - 2, pos[1]};
            } else {
                return new Double[] {pos[0], 0 - 50.0 - 2};
            }
        }
    }

    /** Moves an entity off screen then despawns it
     * @param coordinates - Coordinates to move the enemy to upon ending its movement [x, y]
     * @param entity - Entity to move and despawn
     * @param movementPattern - Movement pattern used by the entity to cancel hitbox animation
     */
    private void despawn(Double[] coordinates, Entity entity, MovementPattern movementPattern) {
        TranslateTransition despawnMovement = new TranslateTransition(Duration.seconds(2), entity.getImage());
        despawnMovement.setToX(coordinates[0]);
        despawnMovement.setToY(coordinates[1]);
        despawnMovement.setCycleCount(1);
        despawnMovement.setAutoReverse(false);
        despawnMovement.setOnFinished(e -> {
            movementPattern.closePositionTracker();
            entity.destroy();
        });
        despawnMovement.play();
    }
}
