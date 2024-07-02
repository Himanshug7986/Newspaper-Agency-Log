package adminLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.util.Optional;

public class admindeskViewController {
	String p="123";
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Button btn;
    
    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pwd;
    @FXML
    private Label lbl;
    
    @FXML
    void dologin(ActionEvent event) throws IOException {
    	
    	if(pwd.getText().equals(p))
    	{
    		Parent root=FXMLLoader.load(getClass().getResource("/adminDesk/admindashboardView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
			Scene scene1=(Scene)lbl.getScene();
			   scene1.getWindow().hide();
    	}
    	else
    		lbl.setText("Incorrect Password..");
    	
    }
    
    @FXML
    void DodialogBox(ActionEvent event) {
    	  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText("Do you want to proceed?");
          
          // Customize the buttons (Yes, No)
          alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

          // Show the dialog and get the result
          alert.showAndWait().ifPresent(response -> {
              if (response == ButtonType.YES) {
                  System.out.println("User clicked Yes");
                  // Perform the action for Yes
              } else if (response == ButtonType.NO) {
                  System.out.println("User clicked No");
                  // Perform the action for No
              } else {
                  System.out.println("User closed the dialog");
                  // Perform the action for dialog closure (optional)
              }
          }); 

    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'admindeskView.fxml'.";
        assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'admindeskView.fxml'.";

    }

}
