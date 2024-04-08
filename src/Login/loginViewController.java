package Login;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class loginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;
    
    @FXML
    private Label lblStatus;

    @FXML
    private PasswordField password;

    @FXML
    void doLogin(ActionEvent event) {
    	URL url;
    	Media media;
    	MediaPlayer mediaplayer;
    	AudioClip audio;
/*    	void playSound(){
        	url=getClass().getResource("click.wav");
    		audio=new AudioClip(url.toString());
    		audio.play();
        }*/
    		url=getClass().getResource("mouse-click-153941.mp3");
    		media=new Media(url.toString());
    		mediaplayer=new MediaPlayer(media);
    		mediaplayer.play();
    	if(password.getText().equals("11223344"))
    	{lblStatus.setText("Login successfull..");
    		try{
    		Parent root=FXMLLoader.load(getClass().getResource("/AdminDesk/deskView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
    		
			//to hide the opened window
			Scene scene1=(Scene)login.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	}
    	else
    	{
    		lblStatus.setText("Incorrect Password..");
    	}
    	/*try {
           Desktop.getDesktop().browse(new URI("http://www.realJavaOnline.com"));
           //Desktop.getDesktop().mail();
          // Desktop.getDesktop().open(new File("C:\\Users\\hp\\Dropbox\\NewsProject\\src\\AdminDesk\\bill.png"));
    		//Desktop.getDesktop().open(new File("C:\\Users\\Rajesh K. Bansal\\Dropbox\\data structure learning plan.docx"));
            
    	} catch (Exception e1) 
    	{
            e1.printStackTrace();
       } */
    	
    }

    @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'loginView.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'loginView.fxml'.";

    }

}
