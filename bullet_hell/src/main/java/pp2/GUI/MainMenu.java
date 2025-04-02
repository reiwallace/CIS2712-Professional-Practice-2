package pp2.GUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainMenu {
    private VBox mainMenuBox;
    private MainWindow mainWindow;
    private final String titlePath = "file:resources/Backgrounds/Nostrmo-Title-big.png";

    public MainMenu(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainMenuBox = new VBox(20); // Spacing between buttons

        // Create menu buttons
        ImageView title = new ImageView(new Image(titlePath));
        Button startButton = createStyledButton("Start Game");
        Button optionsButton = createStyledButton("Options");
        Button quitButton = createStyledButton("Quit");

        // Configure title image
        title.setFitWidth(mainWindow.getStage().getWidth() * 0.5);
        title.setFitHeight(mainWindow.getGrid().getHeight() * 0.2);


        // Add action events to buttons
        startButton.setOnAction(e -> mainWindow.startGame()); // This action will start the game 
        optionsButton.setOnAction(e -> showOptions()); // Placeholder for options
        quitButton.setOnAction(e -> System.exit(0));

        // Add buttons to layout and centre align them
        mainMenuBox.getChildren().addAll(title, startButton, optionsButton, quitButton);
        mainMenuBox.setStyle("-fx-background-color: black; -fx-alignment: center;");
    }        
    
    /*
     * creates a styled button
     * @param buttonLabel - text displayed on the button
     */
    private Button createStyledButton(String buttonLabel) {
        Button styledButton;
        styledButton = new Button(buttonLabel);
        styledButton.setStyle("-fx-font-size: 20px; -fx-background-color: gray; -fx-text-fill: white");
        return styledButton;
    }

    public void showMenu(GridPane pane) {
        pane.add(mainMenuBox, 0, 0, 40, 30); // Place main menu in the same spot as GameFrame
    }

    private void showOptions() {
        System.out.println("Options menu not implemented yet"); // Placeholder for options
    }

    public void removeMenu(GridPane pane) {
        pane.getChildren().remove(mainMenuBox); // Remove menu when game starts
    }
}