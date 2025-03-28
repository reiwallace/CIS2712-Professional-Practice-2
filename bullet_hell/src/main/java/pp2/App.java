package pp2;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import pp2.GUI.MainWindow;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainWindow gameWindow = new MainWindow(false);
        Stage gameStage = gameWindow.getStage();
        gameStage.show();
        gameWindow.windowedMode(); // Set default screen mode
        gameWindow.startGame();
    }


    public static void main(String[] args) {
        launch();
    }

}