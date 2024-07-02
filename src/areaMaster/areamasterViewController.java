package areaMaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class areamasterViewController {
	Connection con;
	PreparedStatement ps;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtArea;

    @FXML
    void doAdd(ActionEvent event) throws Exception
    {
    	ps=con.prepareStatement("insert into areas values (?)");
    	ps.setString(1, txtArea.getText());
    	ps.executeUpdate();
    	
    	txtArea.setText("");
    }

    @FXML
    void initialize() {
       con=paperMaster.MysqlConnector.doConnect();
    }

}
