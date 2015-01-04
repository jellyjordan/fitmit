package gui.viewport;

import account.Profile;
import account.ProfileParser;
import gui.controllers.DailyLogController;
import gui.controllers.LoginController;
import gui.controllers.OverlayController;
import gui.controllers.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import nutrition.DailyLog;

import java.io.IOException;
import java.util.ArrayList;

/*
    Class handles the transitioning of views by loading
    all resources onto the main viewport.
 */
public class ViewManager {
    public static final String LOGIN_PAGE =     "../../res/xml/login.fxml";
    public static final String NUTRILOG_PAGE =  "../../res/xml/dailylog.fxml";
    public static final String OVERLAY_PAGE =   "../../res/xml/profileoverlay.fxml";
    public static final String VIEW_HOLDER =    "../res/xml/mainview.fxml";

    public static final String LOGIN_STYLE = "res/css/loginStyle.css";
    public static final String NUTRILOG_STYLE = "res/css/dailylogStyle.css";
    public static final String OVERLAY_STYLE = "res/css/overlayStyle.css";

    private static ViewController viewController;
    private static Scene mainScene;

    public static void setViewController(ViewController viewController){
        ViewManager.viewController = viewController;
    }

    public static void setMainScene(Scene scene){
        mainScene = scene;
    }

    /*
        Loads an fxml file provided in the viewPath into
        the viewHolder which updates the viewport.

        Throws IO Exception if viewpath is invalid
     */
    public static void loadView(String viewPath){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            /*
                Loads the new view and does dynamic page updating in
                special cases
             */
            Node node = fxmlLoader.load(ViewManager.class.getResourceAsStream(viewPath));

            switch (viewPath){
                /*
                    Updates the list of available profiles to select
                    from the load file and add them to the gui as buttons
                 */
                case LOGIN_PAGE:
                    LoginController loginController = fxmlLoader.getController();
                    ArrayList<Profile> profiles = ProfileParser.loadProfiles();
                    if(!profiles.isEmpty()){
                        loginController.updatePage(profiles);
                    }
                    break;
                /*
                    Updates the daily logs gui to match values
                    of the loaded profile
                 */
                case NUTRILOG_PAGE:
                    loadOverlay(OVERLAY_PAGE);
                    DailyLogController dailyLogController = fxmlLoader.getController();
                    dailyLogController.updateTotalLables();
                    dailyLogController.updateCurrentLabels();
                    break;

            }
            viewController.setView(node);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /*
        Loads the overlay into the main viewport
     */
    public static void loadOverlay(String overlayPath){
        FXMLLoader fxmlLoader = new FXMLLoader();
            /*
                Loads overlay fxml and updates the label to match
                the user profile
             */
        try{
            Node node = fxmlLoader.load(ViewManager.class.getResourceAsStream(overlayPath));
            OverlayController overlayController = fxmlLoader.getController();
            overlayController.setProfileLabel();
            viewController.setOverlay(node);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }


    /*
        Loads/replaces the current style sheet
     */
    public static void loadStyle(String stylepath){
        mainScene.getStylesheets().clear();
        mainScene.getStylesheets().add(stylepath);
        mainScene.getStylesheets().add(OVERLAY_STYLE);
    }
}
