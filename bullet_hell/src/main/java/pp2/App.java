package pp2;

import java.io.IOException;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.stage.Stage;
import pp2.Entity.Player;
import pp2.GUI.GameFrame;
import pp2.GUI.MainWindow;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainWindow gameWindow = new MainWindow(false);
        Stage gameStage = gameWindow.getStage();
        gameStage.show();
        gameWindow.windowedMode(); // Set default screen mode
        gameWindow.startGame();

        // Uses rectangles to show game area
        /*for(int y = 0; y < 30; y++) {
            for(int x = 0; x < 40; x++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(10);
                rectangle.setWidth(10);
                gameWindow.getGrid().add(rectangle, x, y);
            }
        }*/
    }


    public static void main(String[] args) {
        launch();
    }

}