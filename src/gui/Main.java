/********************************************************************************
 * Creator: Jordan La Croix                                                     *
 * GitHub: https://github.com/jellyjordan/fitmit                                *
 *******************************************************************************/

package gui;

import account.ProfileParser;
import account.Session;
import gui.controllers.ViewController;
import gui.viewport.ViewManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static final int RESOLUTION_X = 800;
    public static final int RESOLUTION_Y = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        /*
            Loads the view holder as the root of the scene, and gets it's controller
            reference which allows the viewholder to update from other classes
         */
        Pane root = loader.load(getClass().getResourceAsStream(ViewManager.VIEW_HOLDER));
        ViewController viewController = loader.getController();
        ViewManager.setViewController(viewController);

        /*
            Initializes the scene and stores it's reference in the ViewManager to
            allow dynamic loading of CSS files with fxml loading
         */
        Scene mainScene = new Scene(root , RESOLUTION_X , RESOLUTION_Y);
        ViewManager.setMainScene(mainScene);

        /*
            Loads the login page and style sheet into the viewport
         */
        ViewManager.loadView(ViewManager.LOGIN_PAGE);
        ViewManager.loadStyle(ViewManager.LOGIN_STYLE);

        /*
            If a session still exists and the window is closed, the
            profile state will be saved with any changes made.
         */
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if(Session.getProfile() != null){
                    ProfileParser.saveProfile(Session.getProfile());
                }
            }
        });
        /*
            Initializes the window and its attributes
         */
        primaryStage.setTitle("FitMit");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
