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

    /** Initialise debug menu with its options
     * @param mainGrid - Grid to attach debug options to
     */
    public DebugMenu(MainWindow mainWindow) {
        configureToggleHitboxes(mainWindow);
    }

    /** Set up a button to toggle all entity hitboxes
     * @param mainWindow - Window entities are attached to
     */
    private void configureToggleHitboxes(MainWindow mainWindow) {
        toggleHitboxes.setVisible(true); // Sets button to visable
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { // Creates a method to be ran when button is pressed
            public void handle(ActionEvent e) 
            { 
                ArrayList<Entity> entities = new ArrayList<Entity>(); 
                entities.add(mainWindow.getGameFrame().getPlayer());
                if(toggleHitboxes.getId() != null && toggleHitboxes.getId().equals("21")) { // If button is ID 21 -- Toggles each press
                    for(int i = 0; i < entities.size(); i++) {
                        var entity = entities.get(i); // VAR MENTIONED :speaking_head:
                        if(entities.get(i) != null) {
                            entity.toggleHitboxVisability(false);
                        }
                    }
                    toggleHitboxes.setId("22"); // Toggle ID
                } else {
                    for(int i = 0; i < entities.size(); i++) {
                        var entity = entities.get(i); // VAR MENTIONED :speaking_head:
                        if(entities.get(i) != null) {
                            entity.toggleHitboxVisability(true);
                        }
                    }
                    toggleHitboxes.setId("21"); // Toggle ID
                }
            } 
        }; 
        toggleHitboxes.setOnAction(event);
        toggleHitboxes.setFocusTraversable(false);
        toggleHitboxes.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Make button fill grid
        mainWindow.getGrid().add(toggleHitboxes, 29, rowSlot, 9, 1);
        rowSlot++;
    }
}
