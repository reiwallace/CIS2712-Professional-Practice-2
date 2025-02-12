package org.example.bhg;

import javafx.scene.image.Image;

import java.io.File;

public class Utils {

    // Load image
    public static Image loadImage(String pathname) {
        File file = new File(pathname);
        return new Image(file.toURI().toString());
    }
}
