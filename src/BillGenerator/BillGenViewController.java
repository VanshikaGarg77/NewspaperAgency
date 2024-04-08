package BillGenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;

public class BillGenViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker LastDate;

    @FXML
    private ComboBox<String> idMobile;

    @FXML
    private Label lblBill;

    @FXML
    private DatePicker todayDate;

    @FXML
    private TextField txtMiss;

    @FXML
    private TextField txtPapers;

    @FXML
    private TextField txtPrices;

    @FXML
    private TextField txtTotal;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doBill(ActionEvent event) {

    	String m=idMobile.getSelectionModel().getSelectedItem();
    	idMobile.getItems().remove(m);
    	LocalDate ld=LastDate.getValue();
    	java.sql.Date date=java.sql.Date.valueOf(ld);
    	LocalDate ld2=todayDate.getValue();
    	java.sql.Date date2=java.sql.Date.valueOf(ld2);
    	long days=ld2.toEpochDay()-ld.toEpochDay();
    	Float total=Float.parseFloat(txtTotal.getText());
    	float net=total*days;
    	try {
			pst=con.prepareStatement("insert into bill values (?,?,?,?,?)");
			pst.setString(1, m);
			pst.setDate(2,date);
			pst.setDate(3,date2);
			pst.setFloat(4,net);
			pst.setInt(5, 0);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lblBill.setText(String.valueOf(net));
    }

    @FXML
    void doSelMobile(ActionEvent event) {
    	String m=idMobile.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select * from customer where mobile=?");
			pst.setString(1, m);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
			String p=table.getString("papers");
			txtPapers.setText(p);
			String pr=table.getString("prices");
			txtPrices.setText(pr);
			//java.sql.Date d=table.getDate("dos");
			//LastDate.setValue(d.toLocalDate());
			String a[]=pr.split(",");
			float sum=0;
			for(int i=0;i<a.length;i++)
			{
				
				sum+=Float.parseFloat(a[i]);
			}
			txtTotal.setText(String.valueOf(sum));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void findlast(ActionEvent event) {
    	
    }

    @FXML
    void findtoday(ActionEvent event) {
    	
    }

    @FXML
    void lastDate(ActionEvent event) {
    	try {
    		String m=idMobile.getSelectionModel().getSelectedItem();
			pst=con.prepareStatement("select * from bill where mobile=? order by dateto desc limit 1");
			pst.setString(1, m);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
			java.sql.Date d=table.getDate("dateto");
			if(d==null)
			{
				pst=con.prepareStatement("select * from customer where mobile=?");
				pst.setString(1, m);
				ResultSet table2=pst.executeQuery();
				while(table2.next())
				{
				java.sql.Date d2=table2.getDate("dos");
				LastDate.setValue(d2.toLocalDate());
				}
			}
			else
			LastDate.setValue(d.toLocalDate());
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
    		System.out.println("Error");
    	else
    		System.out.println("connected!");
    	try {
			pst=con.prepareStatement("select * from customer");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String m=table.getString("mobile");
				idMobile.getItems().add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
      txtMiss.setText("0");

    }

}
