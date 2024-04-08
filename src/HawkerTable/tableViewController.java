package HawkerTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import BillGenerator.*;

public class tableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HawkerBean> tableView;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doFetch(ActionEvent event) {

    	TableColumn<HawkerBean,String> name=new TableColumn<HawkerBean,String>("Hawker name");
    	name.setCellValueFactory(new PropertyValueFactory<>("hname"));
    	name.setMinWidth(100);
    	tableView.getColumns().add(name);
    	
    	TableColumn<HawkerBean,String> m=new TableColumn<HawkerBean,String>("Mobile no.");
    	m.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	m.setMinWidth(100);
    	tableView.getColumns().add(m);
    	
    	TableColumn<HawkerBean,String> alloareas=new TableColumn<HawkerBean,String>("Allocated areas");
    	alloareas.setCellValueFactory(new PropertyValueFactory<>("areas"));
    	alloareas.setMinWidth(150);
    	tableView.getColumns().add(alloareas);
    	
    	TableColumn<HawkerBean,String> date=new TableColumn<HawkerBean,String>("Date of joining");
    	date.setCellValueFactory(new PropertyValueFactory<>("daj"));
    	date.setMinWidth(100);
    	tableView.getColumns().add(date);
    	
    	tableView.setItems(FetchAllHawkers());
    }
    
   
    
    ObservableList<HawkerBean> FetchAllHawkers() 
    {
    	ObservableList<HawkerBean>ary=FXCollections.observableArrayList();
    	try {
    		pst = con.prepareStatement("select * from hawker");
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("hname");
	    		String DOJ = String.valueOf(table.getDate("daj").toLocalDate());
	    		String alloarea=table.getString("areas");
	    		System.out.println(name);
	    		HawkerBean ref=new HawkerBean(name, mno, alloarea, DOJ);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);	
    		}
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'tableView.fxml'.";

    }

}
