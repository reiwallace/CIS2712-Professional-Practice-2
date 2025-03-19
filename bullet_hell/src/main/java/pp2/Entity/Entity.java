package pp2.Entity;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import pp2.ImageHandler;

public abstract class Entity {

    protected ImageView entityImage; // Image to display on entity
    protected int health; // Starting health of entity
    protected Rectangle entityHitbox; // Hitbox of entity
    protected boolean isTargetable; // Whether entity can be hit or not
    protected GridPane gamePane; // Reference to the game pane

    private final int[] hitboxScale = {1, 1};

    //  Constructor for Entity.
    public Entity(String imagePath, int health) {
        this.entityImage = new ImageView(ImageHandler.loadImage(imagePath));
        this.health = health;
        this.isTargetable = true; // Default: can be hit
        setHitbox(); // Initialize hitbox
    }

    // Getters
    public int getHealth() { return health; }
    public Rectangle getHitbox() { return entityHitbox; }
    public boolean isTargetable() { return isTargetable; }
    public ImageView getImage() { return entityImage; }
    public GridPane getGamePane() { return gamePane; }

    // Setters
    public void setHealth(int health) { this.health = health; }
    public void setIsTargetable(boolean targetable) { this.isTargetable = targetable; }
    public void setGamePane(GridPane gamePane) { this.gamePane = gamePane; }

    // Loads an image for the entity.
    public void loadImage(String imagePath) {
        this.entityImage.setImage(ImageHandler.loadImage(imagePath));
        setHitbox(); // Update hitbox after changing image
    }

    // Sets the position of the entity and updates the hitbox.
    public void setPosition(int x, int y) {
        // Update character image position
        entityImage.setLayoutX(x);
        entityImage.setLayoutY(y);
        // Update hitbox position
        entityHitbox.setLayoutX(x);
        entityHitbox.setLayoutY(y);
    }

    // Updates hitbox size based on the entity's image.
    public void setHitbox() {
        if (entityImage != null) {
            entityHitbox = new Rectangle(
                    entityImage.getFitWidth() * hitboxScale[0],
                    entityImage.getFitHeight() * hitboxScale[1]
            );
        }
    }

    // Abstract methods (must be implemented in subclasses)
    public abstract void move(KeyCode code);
    public abstract void takeDamage(int damage);
    public abstract void destroy();
}
