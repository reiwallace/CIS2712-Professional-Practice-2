package GameWindow;

import javafx.stage.Stage;

public class appStage {
    private Stage stage = new Stage();
    private String title = "Bullet Hell"; // Stage title

    public appStage() {
        stage.setResizable(false);
        stage.setTitle(title);
        windowedMode();
    }

    // Set stage to windowed mode.
    public void windowedMode() {
        setSize(920);
    }

    // Set stage to fullscreen mode.
    public void fullScreeMode() {
        setSize(1404);
    }

    // Get stage.
    public Stage getStage() { return stage; }

    // Change stage size with an aspect ratio of 4:3 from the width.
    private void setSize(double width) {
        stage.setMaxWidth(width);
        stage.setMaxHeight(width * 0.75);
    }
}
