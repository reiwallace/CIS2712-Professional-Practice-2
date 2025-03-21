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
        GameFrame gameFrame = new GameFrame(gameStage.getHeight() - gameWindow.titleBarHeight, gameStage.getWidth()); // Subtract title bar from height to get accurate size
        gameWindow.getGrid().add(gameFrame.getGameFrame(), 2, 1, 24, 28); // Add the game window to the game frame 2 columns in and 1 row down
        gameWindow.getGrid().add(gameFrame.getBG(), 2, 1, 24, 28);
        Player p = new Player("https://i.ibb.co/LhYpPskV/player.png", 1, 10, gameWindow.getGrid(), gameFrame.getGameFrame());

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