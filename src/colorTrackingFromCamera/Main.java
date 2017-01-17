package colorTrackingFromCamera;

import colorTrackingFromFile.ControllerFromFile;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.opencv.core.Core;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../colorTrackingFromFile/colorTrackingFromFile.fxml"));
            BorderPane root = (BorderPane) loader.load();
            ControllerFromFile controller = loader.getController();


            primaryStage.setTitle("ColorTracking");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();

            primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we)
                {
                    controller.setClosed();
                }
            }));// why ??

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        // load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        launch(args);
    }
}
