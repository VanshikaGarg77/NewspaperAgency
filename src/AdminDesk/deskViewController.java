package AdminDesk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class deskViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void allCustomers(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/CustomerPanel/customerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void allHawkers(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/HawkerTable/tableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void billCollect(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BillCollector/CollectorView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void billGen(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BillGenerator/BillGenView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void billstatus(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BillBoard/billView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void developer(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/Developers/meetView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doCustomer(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/CustomerManager/CustomerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doHawker(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/HawkerManager/hawkerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doPaper(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/PaperMaster/PaperView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }

}
