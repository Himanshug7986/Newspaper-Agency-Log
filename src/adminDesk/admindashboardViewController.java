package adminDesk;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class admindashboardViewController 
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void gotoArea(ActionEvent event) throws Exception
    {
    	Parent root=FXMLLoader.load(getClass().getResource("/areaMaster/areamasterView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void gotoCusdata(ActionEvent event)throws Exception {
    	Parent root=FXMLLoader.load(getClass().getResource("/customerDashboard/customerDataView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoCustomer(ActionEvent event)throws Exception {
    	Parent root=FXMLLoader.load(getClass().getResource("/customerManager/customerRecordView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoGenbill(ActionEvent event) throws Exception{
    	Parent root=FXMLLoader.load(getClass().getResource("/generateBill/generateBillView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoHawcker(ActionEvent event) throws Exception{
    	Parent root=FXMLLoader.load(getClass().getResource("/hawkerManager/hawkermanagerView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoHawkerdata(ActionEvent event)throws Exception {
    	Parent root=FXMLLoader.load(getClass().getResource("/allhawkers/allHawkersRecordView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoPaper(ActionEvent event) throws Exception{
    	Parent root=FXMLLoader.load(getClass().getResource("/paperMaster/papermasterView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotoStatus(ActionEvent event) throws Exception{
    	Parent root=FXMLLoader.load(getClass().getResource("/billCollector/billCollectorView.fxml")); 
		Scene scene = new Scene(root);
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.show();
		
    }

    @FXML
    void gotodevloprs(ActionEvent event) throws Exception{

    }

    @FXML
    void initialize() {

    }

}

