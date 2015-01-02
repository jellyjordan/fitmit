/********************************************************************************
 * Creator: Jordan La Croix                                                     *
 * GitHub: https://github.com/jellyjordan/fitmit                                *
 *******************************************************************************/

package gui;

import gui.controllers.ViewController;
import gui.viewport.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
