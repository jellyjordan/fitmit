package gui.controllers;

/*
    Class used to access the main viewHolder fxml
    attributes and modify children to show new views
 */

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ViewController {
    @FXML private StackPane viewHolder;
    @FXML private HBox overlayHolder;

    /*
        Replaces all children with the param node
     */
    public void setView(Node node){
        viewHolder.getChildren().addAll(node);
    }
    public void setOverlay(Node node){
        overlayHolder.getChildren().addAll(node);
    }
}
