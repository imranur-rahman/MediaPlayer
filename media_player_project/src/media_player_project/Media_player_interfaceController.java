
package media_player_project;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Media_player_interfaceController implements Initializable,Runnable {

    @FXML
    private MenuBar menuBar;

    @FXML
    public MediaView mediaView;
    
    MediaPlayer mediaplayer;
    Stage stage;
    private Media musicfile;
    private boolean playingStat=true;
    @FXML
    public Button play;
    @FXML
    public Slider timeSlider;
    @FXML
    public Slider volumeSlider;
    @FXML
    private MenuItem open_menu;
    @FXML
    private MenuItem playlist_menu;

    private Image play_image;
    private Image pause_image;

    private boolean mediastat=false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(timeSlider.getLayoutX());
        play_image=new Image("file:/D:/play-icon.png");
        pause_image=new Image("file:/D:/pause-icon.png");

        //play.setGraphic(new ImageView(play_image));
        Media_player_interfaceController_intial(null);
     }
    public  void Media_player_interfaceController_intial(String uri){
              if(uri!=null)
              {
              this.musicfile = new Media(uri);
              if(mediaplayer!=null){
                  mediaplayer.stop();
              }
              mediaplayer=new MediaPlayer(musicfile);

              mediaplayer.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                      timeSlider.setMin(0.0);
                      timeSlider.setValue(0.0);
                      timeSlider.setMax(mediaplayer.getTotalDuration().toSeconds());


                  }
              });
              volumeSlider.setValue(100);
                
              mediaplayer.setAutoPlay(true);
                  mediastat=true;
                  //play.setGraphic(new ImageView(pause_image));

              mediaView.setMediaPlayer(mediaplayer);

                 mediaplayer .currentTimeProperty().addListener((observableValue, duration, current) -> {
                     timeSlider.setValue(current.toSeconds());
                 });

                  timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                          mediaplayer.seek(javafx.util.Duration.seconds((timeSlider.getValue())));

                      }
                  });

                  volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                      @Override
                      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                          mediaplayer.setVolume((newValue.doubleValue()) / 100);

                      }

                  });


            
            }
        }

    public DoubleProperty timesliderwidth()
    {
        return timeSlider.prefWidthProperty();


    }
    public DoubleProperty mediaviewwidth()
    {
        return mediaView.fitWidthProperty();
    }
    public DoubleProperty mediaviewheight()
    {
        return mediaView.fitHeightProperty();
    }

    public void run(){
        
    }
   @FXML
    private void playButtonAction(ActionEvent event) {
       if (mediastat == true)
       {
           mediaplayer.pause();
           //play.setGraphic(new ImageView(play_image));
           mediastat=false;
       }
       else
       {
           mediastat=true;
           mediaplayer.play();
           //play.setGraphic(new ImageView(pause_image));
       }

    }



    @FXML
    private void open_menu_action(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file=fileChooser.showOpenDialog(null);
        if(file!=null){
           Media_player_interfaceController_intial(file.toURI().toString());
        }
     }
    @FXML
    private void  playlist_menu_action(ActionEvent event)
    {
        FileChooser fileChooser=new FileChooser();
        configureFileChooser(fileChooser);
        List<File>list=fileChooser.showOpenMultipleDialog(null);
        if(list!=null)
        {
            for(File file:list){
                if(mediaplayer==null){
                    Media_player_interfaceController_intial(file.toURI().toString());
                }

                else
                {
                   mediaplayer.setOnEndOfMedia(new Runnable() {
                       @Override
                       public void run() {
                           mediaplayer.stop();;
                           Media_player_interfaceController_intial(file.toURI().toString());
                       }
                   });

                }
            }
        }
    }
    private void configureFileChooser(FileChooser fileChooser)
    {
                fileChooser.setTitle("Media Files");
                fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Media Files", "*.*"),
                new FileChooser.ExtensionFilter("mp3","*.mp3"),
               new FileChooser.ExtensionFilter( "mp4","*.mp4")
               );
     }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    
   

    
    
    
    
    
    
    
    
    
    
    
    
}
