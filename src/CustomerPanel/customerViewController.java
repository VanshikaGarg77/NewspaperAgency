package CustomerPanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import BillGenerator.*;

public class customerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button iDExport;

    @FXML
    private ComboBox<String> idArea;

    @FXML
    private ComboBox<String> idPaper;

    @FXML
    private TableView<CustomerBean> tableview;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doArea(ActionEvent event) {

    }

    @FXML
    void doExport(ActionEvent event) {
    	
    	try {
			writeExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void writeExcel() throws Exception
    {
    	ObservableList<CustomerBean>list=FXCollections.observableArrayList();
    	Writer writer=null;
    	try {
    	File file=new File("Users.csv");
    	writer=new BufferedWriter(new FileWriter(file));
    	String text="mobile,cname,papers,area,dos\n";
    	writer.write(text);
    	for(CustomerBean c:list)
    	{
    		text=c.getMobile()+","+c.getCname()+","+c.getPapers()+","+c.getArea()+","+c.getDos()+"\n";
    		writer.write(text);
    	}
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	finally {
    		writer.flush();
    		writer.close();
    	}
    }
    
    @FXML
    void doFetch(ActionEvent event) {
    	tableview.getColumns().clear();
    	TableColumn<CustomerBean,String> m=new TableColumn<CustomerBean,String>("Mobile no.");
    	m.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	m.setMinWidth(100);
    	tableview.getColumns().add(m);
    	
    	TableColumn<CustomerBean,String> name=new TableColumn<CustomerBean,String>("Customer name");
    	name.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	name.setMinWidth(100);
    	tableview.getColumns().add(name);
    	
    	TableColumn<CustomerBean,String> p=new TableColumn<CustomerBean,String>("Papers");
    	p.setCellValueFactory(new PropertyValueFactory<>("papers"));
    	p.setMinWidth(100);
    	tableview.getColumns().add(p);
    	
    	TableColumn<CustomerBean,String> a=new TableColumn<CustomerBean,String>("Area");
    	a.setCellValueFactory(new PropertyValueFactory<>("area"));
    	a.setMinWidth(100);
    	tableview.getColumns().add(a);
    	
    	TableColumn<CustomerBean,String> dt=new TableColumn<CustomerBean,String>("Date of start");
    	dt.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	dt.setMinWidth(100);
    	tableview.getColumns().add(dt);
    	
    	tableview.setItems(FetchPapers());
    }
    ObservableList<CustomerBean> FetchPapers() 
    {
    	String a=idPaper.getSelectionModel().getSelectedItem();
    	ObservableList<CustomerBean>ary=FXCollections.observableArrayList();
    	try {
    		pst = con.prepareStatement("select * from customer where papers like ?");
    		pst.setString(1, "%"+a+"%");
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("cname");
	    		String paper = table.getString("papers");
	    		String d = String.valueOf(table.getDate("dos").toLocalDate());
	    		String alloarea=table.getString("area");
	    		System.out.println(name);
	    		CustomerBean ref=new CustomerBean( mno,name, paper,alloarea, d);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);	
    		}
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }

    @FXML
    void doFilter(ActionEvent event) {
    	tableview.getColumns().clear();
    	TableColumn<CustomerBean,String> m=new TableColumn<CustomerBean,String>("Mobile no.");
    	m.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	m.setMinWidth(100);
    	tableview.getColumns().add(m);
    	
    	TableColumn<CustomerBean,String> name=new TableColumn<CustomerBean,String>("Customer name");
    	name.setCellValueFactory(new PropertyValueFactory<>("cname"));
    	name.setMinWidth(100);
    	tableview.getColumns().add(name);
    	
    	TableColumn<CustomerBean,String> p=new TableColumn<CustomerBean,String>("Papers");
    	p.setCellValueFactory(new PropertyValueFactory<>("papers"));
    	p.setMinWidth(100);
    	tableview.getColumns().add(p);
    	
    	TableColumn<CustomerBean,String> a=new TableColumn<CustomerBean,String>("Area");
    	a.setCellValueFactory(new PropertyValueFactory<>("area"));
    	a.setMinWidth(100);
    	tableview.getColumns().add(a);
    	
    	TableColumn<CustomerBean,String> dt=new TableColumn<CustomerBean,String>("Date of start");
    	dt.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	dt.setMinWidth(100);
    	tableview.getColumns().add(dt);
    	
    	tableview.setItems(FetchAllHawkers());
    }
    ObservableList<CustomerBean> FetchAllHawkers() 
    {
    	String a=idArea.getSelectionModel().getSelectedItem();
    	ObservableList<CustomerBean>ary=FXCollections.observableArrayList();
    	try {
    		pst = con.prepareStatement("select * from customer where area=?");
    		pst.setString(1, a);
    		ResultSet table=pst.executeQuery();
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("cname");
	    		String paper = table.getString("papers");
	    		String d = String.valueOf(table.getDate("dos").toLocalDate());
	    		String alloarea=table.getString("area");
	    		System.out.println(name);
	    		CustomerBean ref=new CustomerBean( mno,name, paper,alloarea, d);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);	
    		}
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
    @FXML
    void doPaper(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	con=MySqlConnector.doConnect();
       try {
		pst=con.prepareStatement("select distinct area from customer");
		ResultSet table=pst.executeQuery();
		while(table.next())
		{
			idArea.getItems().add(table.getString("area"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       try {
   		pst=con.prepareStatement("select * from master");
   		ResultSet table=pst.executeQuery();
   		while(table.next())
   		{
   			idPaper.getItems().add(table.getString("paper"));
   		}
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
    }

}
