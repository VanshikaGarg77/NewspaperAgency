package BillCollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import BillGenerator.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblAmount;

    @FXML
    private Label lblFrom;

    @FXML
    private Label lblMsg;

    @FXML
    private Label lblTo;

    @FXML
    private TextField txtMobile;
    
    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doBill(ActionEvent event) {
    	String m=txtMobile.getText();
    	try {
			pst=con.prepareStatement("select * from bill where mobile=?");
			pst.setString(1, m);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				java.sql.Date d=table.getDate("datefrom");
				txtFrom.setText(String.valueOf(d));
				java.sql.Date d2=table.getDate("dateto");
				txtTo.setText(String.valueOf(d2));
				String a=table.getString("amount");
				txtAmount.setText(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doPayment(ActionEvent event) {
    	String m=txtMobile.getText();
    	lblMsg.setText("payment done..");
    	try {
			pst=con.prepareStatement("update bill set status=? where mobile=?");
			pst.setInt(1, 1);
			pst.setString(2, m);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
    	if(con==null)
    		System.out.println("Error");
    	else
    		System.out.println("connected!");
        assert lblAmount != null : "fx:id=\"lblAmount\" was not injected: check your FXML file 'CollectorView.fxml'.";
        assert lblFrom != null : "fx:id=\"lblFrom\" was not injected: check your FXML file 'CollectorView.fxml'.";
        assert lblMsg != null : "fx:id=\"lblMsg\" was not injected: check your FXML file 'CollectorView.fxml'.";
        assert lblTo != null : "fx:id=\"lblTo\" was not injected: check your FXML file 'CollectorView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'CollectorView.fxml'.";

    }

}

