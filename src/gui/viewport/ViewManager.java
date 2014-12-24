package gui.viewport;

import gui.controllers.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

/*
    Class handles the transitioning of views by loading
    all resources onto the main viewport.
 */
public class ViewManager {
    public static final String LOGIN_PAGE = "../../res/xml/login.fxml";
    public static final String LOGIN_STYLE = "res/css/loginStyle.css";
    public static final String VIEW_HOLDER = "../res/xml/mainview.fxml";

    private static ViewController viewController;

    public static void setViewController(ViewController viewController){
        ViewManager.viewController = viewController;
    }

    /*
        Loads an fxml file provided in the viewPath into
        the viewHolder which updates the viewport.

        Throws IO Exception if viewpath is invalid
     */
    public static void loadView(String viewPath){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            // Sets the main stackpane's children to the loaded fxml layout
            Node node = fxmlLoader.load(ViewManager.class.getResource(viewPath));
            viewController.setView(node);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
