package pp2;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainWindow gameWindow = new MainWindow();
        gameWindow.getStage().show();
        /* Uses rectangles to show game area
        for(int y = 1; y < 29; y++) {
            for(int x = 2; x < 26; x++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(gameWindow.getScene().getHeight()/30);
                rectangle.setWidth(gameWindow.getScene().getWidth()/40);
                gameWindow.getGrid().add(rectangle, x, y);
            }
        }*/
        Button b = new Button("1");
        gameWindow.getGrid().add(b, 0, 0);
    }


    public static void main(String[] args) {

        launch();
    }

}