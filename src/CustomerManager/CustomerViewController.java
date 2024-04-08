package CustomerManager;

import java.net.URL;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idAdd;

    @FXML
    private ComboBox<String> idArea;

    @FXML
    private DatePicker idDate;

    @FXML
    private TextField idEmail;

    @FXML
    private TextField idHawker;

    @FXML
    private ListView<String> showlist1;

    @FXML
    private ListView<String> showlist2;

    @FXML
    private ListView<String> showlist3;

    @FXML
    private ListView<String> showlist4;
    
    @FXML
    private Label idStatus;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void Subscribe(ActionEvent event) {
    	String m=txtMobile.getText();
    	String name=txtName.getText();
    	String mail=idEmail.getText();
    	String add=idAdd.getText();
    	LocalDate ld=idDate.getValue();
    	java.sql.Date date=java.sql.Date.valueOf(ld);
    	String hname=idHawker.getText();
    	String area=idArea.getSelectionModel().getSelectedItem();
    	ObservableList <String> sel=showlist3.getItems();
    	String selPapers="";
    	for(String string:sel)
    	{
    		selPapers+=string+",";
    	}
    	ObservableList <String> selPrice=showlist4.getItems();
    	String prices="";
    	for(String string:selPrice)
    	{
    		prices+=string+",";
    	}
    	selPapers=selPapers.substring(0, selPapers.length()-1);
    	prices=prices.substring(0, prices.length()-1);
    	try {
			pst=con.prepareStatement("insert into customer values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, m);
			pst.setString(2, name);
			pst.setString(3,selPapers);
			pst.setString(4,prices);
			pst.setString(5,area);
			pst.setString(6, hname);
			pst.setString(7, mail);
			pst.setString(8, add);
			pst.setString(9, String.valueOf(date));
			pst.executeUpdate();
			idStatus.setText("Customer registered");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void doArea(ActionEvent event) {
    	String sel=idArea.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select hname from hawker where areas like ?");
			pst.setString(1, "%"+sel+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String n=table.getString("hname");
				idHawker.setText(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doFetch(ActionEvent event) {
    	String m=txtMobile.getText();
    	try {
			pst=con.prepareStatement("select * from customer where mobile=?");
			pst.setString(1, m);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("cname");
				String p=table.getString("papers");
				String arr[]=p.split(",");
				String pr=table.getString("prices");
				String arr2[]=pr.split(",");
				for(int i=0;i<arr.length;i++)
				{
					showlist3.getItems().add(arr[i]);
					showlist4.getItems().add(arr2[i]);
				}
				String mail=table.getString("email");
				String add=table.getString("address");
				String h=table.getString("hawker");
				String area=table.getString("area");
				java.sql.Date date= table.getDate("dos");
				txtMobile.setText(m);
				idAdd.setText(add);
				txtName.setText(name);
				idEmail.setText(mail);
				idHawker.setText(h);
				idArea.setValue(area);
				idDate.setValue(date.toLocalDate());
				idStatus.setText("Record Searched");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void unsubscribe(ActionEvent event) {
    	String m=txtMobile.getText();
    	try {
			pst=con.prepareStatement("delete from customer where mobile=?");
			pst.setString(1, m);
			int count=pst.executeUpdate();
			if(count!=0)
				idStatus.setText("Customer Unsubscribed");
			else
				idStatus.setText("Invalid Mobile number");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void update(ActionEvent event) {
    	String m=txtMobile.getText();
    	String name=txtName.getText();
    	String mail=idEmail.getText();
    	String add=idAdd.getText();
    	LocalDate ld=idDate.getValue();
    	java.sql.Date date=java.sql.Date.valueOf(ld);
    	String hname=idHawker.getText();
    	String area=idArea.getSelectionModel().getSelectedItem();
    	ObservableList <String> sel=showlist3.getItems();
    	String selPapers="";
    	for(String string:sel)
    	{
    		selPapers+=string+",";
    	}
    	ObservableList <String> selPrice=showlist4.getItems();
    	String prices="";
    	for(String string:selPrice)
    	{
    		prices+=string+",";
    	}
    	selPapers=selPapers.substring(0, selPapers.length()-1);
    	prices=prices.substring(0, prices.length()-1);
    	try {
			pst=con.prepareStatement("update customer set cname=?,papers=?,prices=?,area=?,hawker=?,email=?,address=?,dos=? where mobile=?");
			pst.setString(1, name);
			pst.setString(2,selPapers);
			pst.setString(3,prices);
			pst.setString(4,area);
			pst.setString(5, hname);
			pst.setString(6, mail);
			pst.setString(7, add);
			pst.setString(8, String.valueOf(date));
			pst.setString(9, m);
			int count=pst.executeUpdate();
			if(count!=0)
			idStatus.setText("Record Updated");
			else
				idStatus.setText("Invalid mobile number");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doClick(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{String item=showlist1.getSelectionModel().getSelectedItem();
    	showlist3.getItems().add(item);
    	int in=showlist1.getSelectionModel().getSelectedIndex();
    	showlist2.getSelectionModel().clearAndSelect(in);
    	String it=showlist2.getSelectionModel().getSelectedItem();
    	showlist4.getItems().add(it);}
    }
    @FXML
    void doDelete(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{
    		int m=showlist3.getSelectionModel().getSelectedIndex();
    		showlist3.getItems().remove(m);
    		showlist4.getItems().remove(m);	
    	}
    }
    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
    	if(con==null)
    		System.out.println("connection problem");
    	else
    		System.out.println("connected...");
    	try {
			pst=con.prepareStatement("select * from master");
			ResultSet table=pst.executeQuery();
	    	while(table.next())
	    	{
	    		String p=table.getString("paper");
	    		showlist1.getItems().add(p);
	    		Float pr=table.getFloat("price");
	    		showlist2.getItems().add(String.valueOf(pr));
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("select","Ajit Road","Veer Colony","Shakti Nagar","Model Town"));
    	idArea.getItems().addAll(items);
    	
    }

}
