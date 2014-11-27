/********************************************************************************
 * Creator: Jordan La Croix                                                     *
 * GitHub: https://github.com/jellyjordan/fitmit                                *
 *******************************************************************************/

package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        primaryStage.setTitle("Fit Mit");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
