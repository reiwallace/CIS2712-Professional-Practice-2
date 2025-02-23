import java.io.IOException;

import GameWindow.appStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        appStage gameWindow = new appStage();
        gameWindow.getStage().show();
    }

    public static void main(String[] args) {

        launch();
    }

}