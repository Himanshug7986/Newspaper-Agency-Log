
package paperMaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class papermasterViewController {
	
	Connection con;
	PreparedStatement ps;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboNewsppr;

    @FXML
    private TextField txtPrice;

    @FXML
    void doDeleteNewsppr(ActionEvent event) throws Exception
    {
    	String ppr=comboNewsppr.getSelectionModel().getSelectedItem();
    	ps=con.prepareStatement("delete from paper_master where paper=?");
    	ps.setString(1, ppr);
    	ps.executeUpdate();
    	
    	doFillPapers();

    }

    @FXML
    void doSaveNewsppr(ActionEvent event) throws Exception
    {
    	String ppr=comboNewsppr.getSelectionModel().getSelectedItem();
    	float price=Float.valueOf(txtPrice.getText());

		ps=con.prepareStatement("insert into paper_master values (?,?)");
    	ps.setString(1, ppr);
    	ps.setFloat(2, price);
    	
    	if(ps.executeUpdate()!=0)
    		System.out.println("saved..");
    	else
    		System.out.println("nott");
    	
    	comboNewsppr.getItems().clear();
    	doFillPapers();
    }

    @FXML
    void doSearchNewsppr(ActionEvent event) throws Exception
    {
    	String ppr=comboNewsppr.getSelectionModel().getSelectedItem();
    	ps=con.prepareStatement("select * from paper_master where paper=?");
    	ps.setString(1, ppr);
    	ResultSet r=ps.executeQuery();
    	while(r.next()) {
    	float pr=r.getFloat("price");
    	
    	txtPrice.setText(String.valueOf(pr));
    }
    }

    @FXML
    void doUpdateNewsppr(ActionEvent event) throws Exception
    {
    	String ppr=comboNewsppr.getSelectionModel().getSelectedItem();
    	float price=Float.valueOf(txtPrice.getText());

		ps=con.prepareStatement("update paper_master set price=? where paper=?");
    	ps.setString(2, ppr);
    	ps.setFloat(1, price);
    	
    	if(ps.executeUpdate()!=0)
    		System.out.println("updated..");
    	else
    		System.out.println("nott updated");
    	
    	doFillPapers();

    }
    void doFillPapers() throws Exception
    {
    	ps=con.prepareStatement("select paper from paper_master");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		comboNewsppr.getItems().add(rs.getString("paper"));
    	}
    }

    @FXML
    void initialize() throws Exception {
    	
    	con=MysqlConnector.doConnect();
        if(con==null)
        	System.out.println("Connection Problem..");
        else
        	System.out.println("connected..");
        
        doFillPapers();
       // ArrayList<String> lst=new ArrayList<String>(Arrays.asList("select","English Tribune","Hindustan Times","Dainik Jagran","Dainik Bhaskar","Dainik Savera","Jagbani"));
    	//comboNewsppr.getItems().addAll(lst);
        
    }

}
