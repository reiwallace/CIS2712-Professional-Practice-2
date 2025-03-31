package pp2.Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pp2.GUI.MainWindow;

public abstract class Entity {
    protected ImageView entityImage; // Image to display on entity
    protected int health; // Starting health of entity
    protected Rectangle entityHitbox; // Hitbox of entity
    protected boolean isTargetable; // Whether entity can be hit or not
    protected MainWindow mainWindow; // Main window to attach entity to
    protected Rectangle gameFrame; // Reference to the game frame
    protected int entityId; // ID of entity

    private final int[] hitboxScale = {1, 1};

    /** Initialise an entity
     * @param imagePath - Image URL to use for entity
     * @param health - Health of entity
     * @param id - Numeric Id of entity
     * @param mainWindow - Main window to get grid from
     * @param gameFrame - Game frame to check bounds of
     */
    public Entity(String imagePath, int health, int id, MainWindow mainWindow, Rectangle gameFrame, int[] fitSize) {
        this.entityImage = new ImageView(new Image(imagePath));
        this.health = health;
        this.entityId = id;
        this.gameFrame = gameFrame;
        this.isTargetable = false; // Default: cannot be hit
        this.mainWindow = mainWindow;

        setFitSize(fitSize[0], fitSize[1]);
        entityImage.setFocusTraversable(true);
        entityImage.setPickOnBounds(true);
        entityImage.setPreserveRatio(true);
        entityImage.setSmooth(true);

        setHitbox();
    }

    /** Loads an image for the entity.
     * @param imagePath - Image URL
     */
    public void loadImage(String imagePath) {
        this.entityImage.setImage(new Image(imagePath));
        setHitbox(); // Update hitbox after changing image
    }

    /** Toggles visability of hitbox
     * @param toggle - Whether hitbox is displayed or not
     */
    public void toggleHitboxVisability(boolean toggle) {
        entityHitbox.setVisible(toggle);
    }

    /** Sets the position of the entity and updates the hitbox.
     * @param x - X position to set entity to
     * @param y - Y position to set entity to
     */
    public void setPosition(double x, double y) {
        // Update character image position
        entityImage.setTranslateX(x);
        entityImage.setTranslateY(y);
        // Update hitbox position
        entityHitbox.setTranslateX(x);
        entityHitbox.setTranslateY(y);
    }

    /** Updates hitbox size based on the entity's image.
     */
    public void setHitbox() {
        if (entityImage != null) {
            entityHitbox = new Rectangle(
                    entityImage.getFitWidth() * hitboxScale[0],
                    entityImage.getFitHeight() * hitboxScale[1]
            );
            entityHitbox.setFill(Color.BLACK);
            entityHitbox.setVisible(false);
        }
    }

    /** Set Fit size of the entity image
     * @param width - width
     * @param height - height
     */
    public void setFitSize(int width, int height) { 
        entityImage.setFitWidth(width);
        entityImage.setFitHeight(height);
        setHitbox();
    }

    /** Adds entity and hitbox to grid (columnspan and rowspan based on image size)
     * @param column - Columnspan of entity
     * @param row - Rowspan of entity
     */
    public void addEntity(int column, int row) {
        mainWindow.getGrid().add(entityImage, 2, 1, column, row);
        mainWindow.getGrid().add(entityHitbox, 2, 1, column, row);
    }

    // Getters
    public int getHealth() { return health; }
    public Rectangle getHitbox() { return entityHitbox; }
    public boolean isTargetable() { return isTargetable; }
    public ImageView getImage() { return entityImage; }

    /** Returns an array cotaining positions [x, y]
     * @return - Array [x, y]
     */
    public double[] getPos() { return new double[] {entityImage.getTranslateX(), entityImage.getTranslateY()}; }

    // Setters
    public void setHealth(int health) { this.health = health; }
    public void setIsTargetable(boolean targetable) { this.isTargetable = targetable; }

    // Abstract methods (must be implemented in subclasses)
    /** Deal damage to the entity's health bar
     * @param damage - Damage to deal
     */
    public abstract void takeDamage(int damage);

    /** Destroy the entity removing them from the screen
     */
    public abstract void destroy();
}
