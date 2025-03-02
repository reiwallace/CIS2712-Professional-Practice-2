package pp2.Entity;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
    private ImageView entityImage; // Image to display on entity
    private int health; // Starting health of entity
    private Rectangle entityHitbox; // Hitbox of entity
    private boolean isTargetable; // Whether entity can be hit or not

    private final int[] hitboxScale = {1, 1};

    public Entity(ImageView image) {
        setImage(image);
        setHitbox();
    }




    //Getters
    public int getHealth() { return health; }
    public Rectangle getHitbox() { return entityHitbox; }
    public boolean isTargetable() { return isTargetable; }

    // Setters
    public void setHealth(int health) { this.health = health; }
    public void setIsTargetable(boolean targetable) { this.isTargetable = targetable; }

    public void setImage(ImageView image) { // Set the entity's image and update hitbox size
        entityImage = image;
        setHitbox();
    }

    public void setPosition(int x, int y) { // Set entity position and tie hitbox to it
        entityImage.setLayoutX(x);
        entityImage.setLayoutY(y);
        entityHitbox.setLayoutX(x);
        entityHitbox.setLayoutY(y);
    }

    private void setHitbox() { // Update hitbox size based on image
        entityHitbox = new Rectangle(entityImage.getFitWidth() * hitboxScale[0], entityImage.getFitHeight() * hitboxScale[1]);
    }
}
