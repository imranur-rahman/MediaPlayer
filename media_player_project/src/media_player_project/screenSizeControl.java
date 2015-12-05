
package media_player_project;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class screenSizeControl implements Runnable {
    
    Stage stage;
    MediaPlayer mediaplayer;
    MediaView mediaview;

    
    
    public void run(){
                 int w = (int) stage.getWidth(); ;
                 int h = (int) stage.getHeight();  

                 mediaview.setFitHeight(h);
                 mediaview.setFitWidth(w);
     }

    public screenSizeControl(Stage stage,MediaView mediaview) {
        this.mediaview = mediaview;
        this.stage=stage;
    }

    



   
    
    
}
