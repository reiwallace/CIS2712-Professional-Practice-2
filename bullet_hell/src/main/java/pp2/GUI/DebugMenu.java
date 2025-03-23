package pp2.GUI;

import javafx.event.EventHandler; 
import javafx.scene.layout.GridPane;
import pp2.Entity.Entity;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button; 

public class DebugMenu {
    private Button toggleHitboxes = new Button("Toggle Hitboxes");
    private int rowSlot = 20;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    /** Initialise debug menu with its options
     * @param mainGrid - Grid to attach debug options to
     */
    public DebugMenu(MainWindow mainWindow) {
        entities.add(mainWindow.getGameFrame().getPlayer());
        configureToggleHitboxes(mainWindow.getGrid());
    }

    private void configureToggleHitboxes(GridPane grid) {
        toggleHitboxes.setVisible(true);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                if(toggleHitboxes.getId() != null && toggleHitboxes.getId().equals("21")) {

                } else {
                    for(int i = 0; i < entities.size(); i++) {
                        var entity = entities.get(i); // VAR MENTIONED :speaking_head:
                        if(entities.get(i))
                    }
                }
            } 
        }; 
        toggleHitboxes.setOnAction(event);
        toggleHitboxes.setFocusTraversable(false);
        grid.add(toggleHitboxes, 29, rowSlot, 8, 1);
        rowSlot++;
    }
}
