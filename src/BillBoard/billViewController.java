package BillBoard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import BillGenerator.*;

public class billViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup bill;

    @FXML
    private RadioButton idPaid;

    @FXML
    private RadioButton idPending;

    @FXML
    private TableView<BillBean> tableview;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtTotal;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doBill(ActionEvent event) {
    	tableview.getColumns().clear();
    	TableColumn<BillBean,String> m=new TableColumn<BillBean,String>("Mobile no.");
    	m.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	m.setMinWidth(100);
    	tableview.getColumns().add(m);
    	
    	TableColumn<BillBean,String> from=new TableColumn<BillBean,String>("Bill From");
    	from.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
    	from.setMinWidth(100);
    	tableview.getColumns().add(from);
    	
    	TableColumn<BillBean,String> to=new TableColumn<BillBean,String>("Bill to");
    	to.setCellValueFactory(new PropertyValueFactory<>("dateto"));
    	to.setMinWidth(100);
    	tableview.getColumns().add(to);
    	
    	TableColumn<BillBean,String> a=new TableColumn<BillBean,String>("Amount");
    	a.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	a.setMinWidth(100);
    	tableview.getColumns().add(a);
    	tableview.setItems(FetchDetails());
    	
    }

  
    ObservableList<BillBean> FetchDetails() 
    {
    	String m=txtMobile.getText();
    	String s;
    	float total=0;
    	ObservableList<BillBean>ary=FXCollections.observableArrayList();
    	try {
    		pst = con.prepareStatement("select * from bill where mobile=?");
    		pst.setString(1, m);
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String from = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String to = String.valueOf(table.getDate("dateto").toLocalDate());
	    		String amount=table.getString("amount");
	    		total+=Float.parseFloat(amount);
	    		BillBean ref=new BillBean( mno,from,to,amount);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
    		}
    		txtTotal.setText(String.valueOf(total));
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    		
    }

    @FXML
    void doExport(ActionEvent event) {

    }

    @FXML
    void doPaid(ActionEvent event) {

    }

    @FXML
    void doPending(ActionEvent event) {

    }

    @FXML
    void doSearch(ActionEvent event) {
    	tableview.getColumns().clear();
    	TableColumn<BillBean,String> m=new TableColumn<BillBean,String>("Mobile no.");
    	m.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	m.setMinWidth(100);
    	tableview.getColumns().add(m);
    	
    	TableColumn<BillBean,String> from=new TableColumn<BillBean,String>("Bill From");
    	from.setCellValueFactory(new PropertyValueFactory<>("datefrom"));
    	from.setMinWidth(100);
    	tableview.getColumns().add(from);
    	
    	TableColumn<BillBean,String> to=new TableColumn<BillBean,String>("Bill to");
    	to.setCellValueFactory(new PropertyValueFactory<>("dateto"));
    	to.setMinWidth(100);
    	tableview.getColumns().add(to);
    	
    	TableColumn<BillBean,String> a=new TableColumn<BillBean,String>("Amount");
    	a.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	a.setMinWidth(100);
    	tableview.getColumns().add(a);
    	tableview.setItems(SearchDetails());
		
    }
    ObservableList<BillBean> SearchDetails() 
    {
    	String m=txtMobile.getText();
    	String s;
    	if(idPending.isSelected())
    		s="0";
    	else
    		s="1";
    	  float total=0;
    	ObservableList<BillBean>ary=FXCollections.observableArrayList();
    	try {
    		pst = con.prepareStatement("select * from bill where mobile=? and status=?");
    		pst.setString(1, m);
    		pst.setString(2, s);
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String from = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String to = String.valueOf(table.getDate("dateto").toLocalDate());
	    		String amount=table.getString("amount");
	    		total+=Float.parseFloat(amount);
	    		BillBean ref=new BillBean( mno,from,to,amount);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
    		}
    		txtTotal.setText(String.valueOf(total));
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    		
    }

    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
    	
    }

}
