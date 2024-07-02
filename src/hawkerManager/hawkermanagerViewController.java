package hawkerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class hawkermanagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField allocated;

    @FXML
    private ImageView back;
    
    PreparedStatement ps;
    Connection con;

    @FXML
    private ComboBox<String> comboareas;

    @FXML
    private ImageView front;

    @FXML
    private TextField hadd;

    @FXML
    private TextField hadhaar;

    @FXML
    private TextField hmobile;

    @FXML
    private ComboBox<String> combonames;
    
    File frontSel,backSel;
    
    @FXML
    void doGetSel(ActionEvent event) {
    	
    	String s=comboareas.getSelectionModel().getSelectedItem();
    	String sarea=allocated.getText();
    	
	    	if(sarea.equals(""))
    		sarea=sarea+s;
    	else
    	    sarea=sarea+","+s;
    	
    	allocated.setText(sarea);
    }

    @FXML
    void doNew(ActionEvent event) throws Exception {
    	comboareas.getSelectionModel().clearSelection();
    	hmobile.clear();
		hadhaar.clear();
		hadd.clear();
		allocated.clear();
		
		
			front.setImage(new Image("uploadimg.png"));
			back.setImage(new Image("uploadimg.png"));
			
			doFillComboAreas();
	
    }
    PreparedStatement ps2;
    @FXML
    void doRemove(ActionEvent event) throws Exception
    {
    	String  nme=combonames.getSelectionModel().getSelectedItem();
    	ps=con.prepareStatement("delete from hawkers where name=?");
    	ps.setString(1, nme);
    	ps.executeUpdate();
    	
    	ps2=con.prepareStatement("update customer_manager set hawker=? where hawker=?");
    	ps2.setString(1, "not available");
    	ps2.setString(2, nme);
    	ps2.executeUpdate();
    	combonames.getSelectionModel().clearSelection();
    	hmobile.setText("");
		hadhaar.setText("");
		hadd.setText("");
		allocated.setText("");
			front.setImage(new Image(new FileInputStream("/Users/diya/Dropbox/eclipse-workspace/newsPaperAgency/images/uploadimg.png")));
			back.setImage(new Image(new FileInputStream("/Users/diya/Dropbox/eclipse-workspace/newsPaperAgency/images/uploadimg.png")));
		//front.setImage(new Image("upload.png"));
		//back.setImage(new Image("upload.png"));
			doFillComboNames();

    }

    @FXML
    void doSave(ActionEvent event) throws Exception
    {
    	
    	String  nme=combonames.getSelectionModel().getSelectedItem();
    	String mob=hmobile.getText();
    	String adhr=hadhaar.getText();
    	String add=hadd.getText();
    	String areas=allocated.getText();
    	ps=con.prepareStatement("insert into hawkers values (?,?,?,?,?,?,?,CURRENT_DATE())");
    	ps.setString(1, nme);
    	ps.setString(2, mob);
    	ps.setString(3, adhr);
    	ps.setString(4, add);
    	ps.setString(5, areas);
    	if(frontSel!=null)
    		ps.setString(6, frontSel.getPath());
    	else 
    		ps.setString(6, "");
    	if(backSel!=null)
    		ps.setString(7, backSel.getPath());
    	else 
    		ps.setString(7, "");
    	
    	ps.executeUpdate();
    	
    		System.out.println("saved");
    	
    	doNew(event);
    	

    }

    @FXML
    void doSearch(ActionEvent event) throws Exception
    {
    	String  nme=combonames.getSelectionModel().getSelectedItem();
    	ResultSet rs;
    	ps=con.prepareStatement("select * from hawkers where name=?");
    	ps.setString(1, nme);
    	rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String m=rs.getString("mobile");
    		String a=rs.getString("adhaarno");
    		String ad=rs.getString("address");
    		String aa=rs.getString("alloareas");
    		String pf=rs.getString("picfront");
    		String pb=rs.getString("picback");
    	
    		hmobile.setText(m);
    		hadhaar.setText(a);
    		hadd.setText(ad);
    		allocated.setText(aa);
    		if(pf=="")
    			front.setImage(new Image("uploadimg.png"));
    		else
    			front.setImage(new Image(new FileInputStream(pf)));
    		if(pb=="")
    			back.setImage(new Image("uploadimg.png"));
    		else
    			back.setImage(new Image(new FileInputStream(pb)));
    	}

    }

    @FXML
    void doUpdate(ActionEvent event) throws Exception
    {
    	
    	String  nme=combonames.getSelectionModel().getSelectedItem();
    	String mob=hmobile.getText();
    	String adhr=hadhaar.getText();
    	String add=hadd.getText();
    	String areas=allocated.getText();
    	ps=con.prepareStatement("update hawkers set mobile=?,adhaarno=?, address=?, alloareas=?, picfront=?,picback=? where name=?");
    	ps.setString(7, nme);
    	ps.setString(1, mob);
    	ps.setString(2, adhr);
    	ps.setString(3, add);
    	ps.setString(4, areas);
    	if(frontSel!=null)
    		ps.setString(5, frontSel.getPath());
    	else 
    		ps.setString(5, "");
    	if(backSel!=null)
    		ps.setString(6, backSel.getPath());
    	else 
    		ps.setString(6, "");
    	
    	if(ps.executeUpdate()!=0)
    		System.out.println("updated");
    	

    }
   

    @FXML
    void doUploadBack(ActionEvent event) {
    	FileChooser f=new FileChooser();
    	f.setInitialDirectory(new File("/Users/diya/"));
    	f.setTitle("Choose Image");
    	backSel=f.showOpenDialog(null);
    	if(backSel!=null)
    	{
    		Image img=new Image(backSel.toURI().toString());
    		back.setImage(img);
    	}


    }
   

    @FXML
    void doUploadFront(ActionEvent event) {
    	
    	FileChooser f=new FileChooser();
    	f.setInitialDirectory(new File("/Users/diya/"));
    	f.setTitle("Choose Image");
    	frontSel=f.showOpenDialog(null);
    	if(frontSel!=null)
    	{
    		Image img=new Image(frontSel.toURI().toString());
    		front.setImage(img);
    	}
    	
    		

    }
    void doFillComboNames() throws Exception
    {

    	HashSet<String> na=new HashSet<String>();
    	combonames.getSelectionModel().clearSelection();
    	combonames.getItems().clear();
    	ps=con.prepareStatement("select name from hawkers");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String n=rs.getString("name");
    		na.add(n);
    		
    	}
    	combonames.getItems().addAll(na);
     }
    
   void doFillComboAreas() throws Exception
    {

    	HashSet np=new HashSet();
    	comboareas.getSelectionModel().clearSelection();
    	comboareas.getItems().clear();
    	ps=con.prepareStatement("select area from areas");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String n=rs.getString("area");
    		np.add(n);
    		
    	}
    	comboareas.getItems().addAll(np);
     }

    @FXML
    void initialize() throws Exception {
    	con=paperMaster.MysqlConnector.doConnect();
        if(con==null)
        	System.out.println("Connection Problem..");
        else
        	System.out.println("connected..");
        
        doFillComboAreas();
        doFillComboNames();
        
        
    }

}
