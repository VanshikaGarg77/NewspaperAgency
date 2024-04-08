package PaperMaster;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class PaperViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private TextField txtPrice;
    
    @FXML
    private Label lblStatus;

    
    Connection con;
    PreparedStatement pst;
    
    @FXML
    void doAvail(ActionEvent event) {
    	String paper=combo.getSelectionModel().getSelectedItem();
    	float price=Float.parseFloat(txtPrice.getText());
    	try {
			pst=con.prepareStatement("insert into master values(?,?)");
			pst.setString(1,paper);
			pst.setFloat(2, price);
			pst.executeUpdate();
			lblStatus.setText("Record saved..");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }

    @FXML
    void doEdit(ActionEvent event) {
    	String p=combo.getSelectionModel().getSelectedItem();
    	float pr=Float.parseFloat(txtPrice.getText());
    	try {
			pst=con.prepareStatement("update master set price=? where paper=?");
		
			pst.setFloat(1, pr);
			pst.setString(2, p);
			int count=pst.executeUpdate();
			lblStatus.setText(count+" records updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doSearch(ActionEvent event) {
    	String s=combo.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select * from master where paper=?");
			pst.setString(1, s);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				float pr=table.getFloat("price");
				txtPrice.setText(String.valueOf(pr));
				System.out.println(s+"\t"+pr);
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
    void doWithdraw(ActionEvent event) {
    	String del=combo.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("delete from master where paper=?");
			pst.setString(1,del);
			int count=pst.executeUpdate();
			if(count==0)
				lblStatus.setText("Invalid paper");
			else
				lblStatus.setText(count+" records deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    void doFill()
    {
    	try {
			pst=con.prepareStatement("select paper from master");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String p=table.getString("paper");
				combo.getItems().add(p);
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
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected");
    	doFill();

        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'PaperView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'PaperView.fxml'.";

    }

}
