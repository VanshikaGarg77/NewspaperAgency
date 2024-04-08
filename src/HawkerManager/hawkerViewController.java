package HawkerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class hawkerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ComboBox<String> idArea;

    @FXML
    private DatePicker idDate;
    
    @FXML
    private Label lblPicPath;

    @FXML
    private Label lblStatus;
    
    @FXML
    private ImageView idPic;

    @FXML
    private TextField idSelArea;

    @FXML
    private Button idUpload;

    @FXML
    private TextField txtAdd;

    @FXML
    private TextField txtMobile;
    
    Connection con;
    PreparedStatement pst;
   

    @FXML
    void doArea(ActionEvent event) {
    	String s=idArea.getSelectionModel().getSelectedItem();
    	String sarea=idSelArea.getText();
    	if(sarea=="")
    		sarea=sarea+s;
    	else
    		sarea=sarea+","+s;
    	idSelArea.setText(sarea);
    }

    @FXML
    void doEnroll(ActionEvent event) {
    	String hname=combo.getSelectionModel().getSelectedItem();
    	String m=txtMobile.getText();
    	String add=txtAdd.getText();
    	String area=idSelArea.getText();
    	String path=lblPicPath.getText();
    	
    	try {
			pst=con.prepareStatement("insert into hawker values(?,?,?,?,?,current_date())");
			pst.setString(1, hname);
			pst.setString(2, m);
			pst.setString(3, add);
			pst.setString(4, area);
			pst.setString(5, path);
			pst.executeUpdate();
			lblStatus.setText("record saved..");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doFire(ActionEvent event) {
    	String hname=combo.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("delete from hawker where hname=?");
			pst.setString(1, hname);
			int count=pst.executeUpdate();
			if(count==0)
				lblStatus.setText("Invalid name");
			else
				lblStatus.setText("record deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doNew(ActionEvent event) {
    	txtMobile.setText("");
    	txtAdd.setText("");
    	idSelArea.setText("");
    	lblPicPath.setText("");
    }

    @FXML
    void doSearch(ActionEvent event) throws FileNotFoundException {
    	String s=combo.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select * from hawker where hname=?");
			pst.setString(1, s);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String m=table.getString("mobile");
				String add=table.getString("address");
				String area=table.getString("areas");
				String path=table.getString("picpath");
				//String date=table.getString("daj");
				java.sql.Date dobb= table.getDate("daj");
				txtMobile.setText(m);
				txtAdd.setText(add);
				idSelArea.setText(area);
				lblPicPath.setText(path);
				idDate.setValue(dobb.toLocalDate());
				idPic.setImage(new Image(new FileInputStream(path)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doSelect(ActionEvent event) {

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	String hname=combo.getSelectionModel().getSelectedItem();
    	String m=txtMobile.getText();
    	String add=txtAdd.getText();
    	String area=idSelArea.getText();
    	String path=lblPicPath.getText();
    	
    	try {
			pst=con.prepareStatement("update hawker set mobile=?,address=?,areas=?,picpath=?,daj=current_date() where hname=?");
			pst.setString(5, hname);
			pst.setString(1, m);
			pst.setString(2, add);
			pst.setString(3, area);
			pst.setString(4, path);
			int count=pst.executeUpdate();
			if(count!=0)
			lblStatus.setText("record updated..");
			else
			lblStatus.setText("Invalid name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doUpload(ActionEvent event) throws FileNotFoundException {
    	FileChooser fc=new FileChooser();
    	File file=fc.showOpenDialog(null);
    	if(file!=null)
    	{
    		 idPic.setImage(new Image(new FileInputStream(file)));
    		 lblPicPath.setText(file.getPath());
    	}else
    		lblPicPath.setText("nopic.jpg");
    }

    void doFill()
    {
    	try {
			pst=con.prepareStatement("select hname from hawker");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String s=table.getString("hname");
				combo.getItems().add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void initialize() {
    	
    	con=MySqlConnector.doConnect();
    	if(con==null)
    		System.out.println("Connection problemm..");
    	else
    		System.out.println("connected");
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("Select","Ajit Road","Model Town","Shakti Nagar","Veer Colony"));
    	idArea.getItems().addAll(items);
    	doFill();
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert idArea != null : "fx:id=\"idArea\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert idPic != null : "fx:id=\"idPic\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert idSelArea != null : "fx:id=\"idSelArea\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert idUpload != null : "fx:id=\"idUpload\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert txtAdd != null : "fx:id=\"txtAdd\" was not injected: check your FXML file 'hawkerView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'hawkerView.fxml'.";

    }

}
