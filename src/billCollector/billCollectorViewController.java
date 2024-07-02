package billCollector;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class billCollectorViewController {

		Connection con;
		PreparedStatement ps,ps1;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox chkno;

    @FXML
    private CheckBox chkyes;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private ListView<String> listbill;

    @FXML
    private ListView<String> listmobile;

    @FXML
    private ListView<String> listname;

    @FXML
    private TextField txtmobile;

    @FXML
    void doUpdateStatus(ActionEvent event) throws Exception
    {
    	if(chkno.isSelected())
    	{
    		String mob=txtmobile.getText();
        	ps=con.prepareStatement("update bills set status=? where mobile=?");
        	ps.setString(2, mob);
        	ps.setInt(1,0);
        	ps.executeUpdate();
        	System.out.println("ghh");
    	}
    	else
    	{
    	String mob=txtmobile.getText();
    	ps=con.prepareStatement("update bills set status=? where mobile=?");
    	ps.setString(2, mob);
    	ps.setInt(1,1);
    	ps.executeUpdate();
    }}
    @FXML
    void doUpdateRemove(MouseEvent event)throws Exception {
    	
    	if(event.getClickCount()==2)
    	{
    	System.out.println("xsdsdsd");
    		int selindx=listname.getSelectionModel().getSelectedIndex();
    	 System.out.println(selindx);
    		listmobile.getSelectionModel().select(selindx);
    		listbill.getSelectionModel().select(selindx);
    		
	    	String mob=listmobile.getSelectionModel().getSelectedItem();
	    //System.out.println(mob);
	    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	          alert.setTitle("Confirmation Dialog");
	          alert.setHeaderText("Do you want to remove?");
	          
	          // Customize the buttons (Yes, No)
	          alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

	          // Show the dialog and get the result
	          alert.showAndWait().ifPresent(response -> {
	              if (response == ButtonType.YES) {
	                  System.out.println("User clicked Yes");
	                  listmobile.getSelectionModel().clearSelection(selindx);
	      	    	listname.getSelectionModel().clearSelection(selindx);
	      	    	listbill.getSelectionModel().clearSelection(selindx);
	      	    
	      	    	try {
						ps=con.prepareStatement("update bills set status=? where mobile=?");
						ps.setString(2, mob);
		      	    	ps.setInt(1,1);
		      	    	ps.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	      	    	
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
    }

    void doFillComboAreas() throws Exception
    	    {

    	    	HashSet np=new HashSet();
    	    	comboArea.getSelectionModel().clearSelection();
    	    	comboArea.getItems().clear();
    	    	ps=con.prepareStatement("select area from areas");
    	    	ResultSet rs=ps.executeQuery();
    	    	while(rs.next())
    	    	{
    	    		String n=rs.getString("area");
    	    		np.add(n);
    	    		
    	    	}
    	    	comboArea.getItems().addAll(np);
    	     }
    @FXML
    void dogetmembers(ActionEvent event) throws Exception {
    	String mob;
    	listmobile.getItems().clear();
    	listname.getItems().clear();
    	listbill.getItems().clear();
    	ps=con.prepareStatement("select * from customer_manager where area=?");
    	ps.setString(1, comboArea.getSelectionModel().getSelectedItem());
    	ResultSet rs=ps.executeQuery();
    	while(rs.next()) {
    	 mob=rs.getString("mobile");
    	 String name=rs.getString("name");
    	
    	 ps1=con.prepareStatement("select bill from bills where status=0 and mobile=?");
     	ps1.setString(1, mob);
     	
    	ResultSet r=ps1.executeQuery();
    	while(r.next())
    	{
    	  listmobile.getItems().add(mob);
    	  listname.getItems().add(name);
    	  listbill.getItems().add(String.valueOf(r.getFloat("bill")));
    	}
    	}
    }
    
    @FXML
    void initialize() throws Exception {
       con=paperMaster.MysqlConnector.doConnect();
       doFillComboAreas();
    }

}
