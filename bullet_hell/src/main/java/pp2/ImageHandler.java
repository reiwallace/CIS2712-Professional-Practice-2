package pp2;

import java.io.File;

import javafx.scene.image.Image;

public class ImageHandler {

    // Load image
    public static Image loadImage(String pathname) {
        File file = new File(pathname);
        return new Image(file.toURI().toString());
    }
}