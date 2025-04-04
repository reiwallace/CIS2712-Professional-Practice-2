package pp2.GUI;

import pp2.Score;
import pp2.Entity.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlayerStatisticsPanel {
    private MainWindow mainWindow;
    private Score score = new Score();
    private Player player;
    
    private Label scoreDisplay = new Label();
    private Label livesDisplay = new Label();

    /** Initialise Player stats panel
     * @param mainWindow - Window to attach panel to
     */
    public PlayerStatisticsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.player = mainWindow.getGameFrame().getPlayer();
        
        // Configure labels
        configureLabel(scoreDisplay, mainWindow.getGrid(), 1);
        updateLabel(scoreDisplay, "Score: " + score.getScore());
        configureLabel(livesDisplay, mainWindow.getGrid(), 5);
        updateLabel(livesDisplay, "Lives: " + player.getLives());
    }

    /** Sets up a label with the correct size visibility and position.
     * @param label - Label to configure
     * @param grid - Grid to attach label to
     * @param row - Row to place label on
     */
    public void configureLabel(Label label, GridPane grid, int row) {
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setStyle("-fx-background-color: black; -fx-alignment: center; -fx-font-size: 35; -fx-font-weight: bold; -fx-text-background-color: white;");
        label.setVisible(true);
        grid.add(label, 29, row, 9, 3);
    }

    /** 
     * @param label - Label to set text of
     * @param text - Text to set label to
     */
    public void updateLabel(Label label, String text) {
        label.setText(text);
    }

    public void updateScore() {
        score.increaseScore(100);
        updateLabel(scoreDisplay, "Score: " + score.getScore());
    }
}
