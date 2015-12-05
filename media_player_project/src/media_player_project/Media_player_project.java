package media_player_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Media_player_project extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Angry Shouts");
       
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Media_player_interface.fxml"));
        Parent root=loader.load();
        
        Media_player_interfaceController controller=loader.getController();
       // controller.setStage(primaryStage);
        Scene scene = new Scene(root);
        bindsize(controller,scene);
        scene.getStylesheets().add("my.css");
        primaryStage.setScene(scene);
        primaryStage.show();
      }

    private void bindsize(Media_player_interfaceController controller,Scene scene)
    {
        //controller.timesliderwidth().bind(scene.widthProperty());
        controller.mediaviewwidth().bind(scene.widthProperty());
        controller.mediaviewheight().bind(scene.heightProperty().subtract(90));
    }
       
    public static void main(String[] args) {
        launch(args);
    }
    
}
