package customerManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class customerRecordViewController {
	Connection con;
	PreparedStatement ps;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboAreas;

    @FXML
    private DatePicker dos;

    @FXML
    private ListView<String> listPapers;

    @FXML
    private ListView<Float> listPrices;

    @FXML
    private ListView<String> listSelPapers;

    @FXML
    private Label lbl;
    
    @FXML
    private ListView<Float> listSelPrices;

    @FXML
    private TextField txtHawcker;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtname;

    @FXML
    void doFetchdata(ActionEvent event) throws Exception
    {
    	listSelPapers.getItems().clear();
    	listSelPrices.getItems().clear();
    	String mob=txtmob.getText();
    	ps=con.prepareStatement("select * from customer_manager where mobile=?");
    	ps.setString(1, mob);
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String nme=rs.getString("name");
    		txtname.setText(nme);
    		String ppr=rs.getString("spapers");
    		String pr=rs.getString("sprices");
    		String a=rs.getString("area");
    		comboAreas.getSelectionModel().select(a);
    		String h=rs.getString("hawker");
    		txtHawcker.setText(h);
    		String e=rs.getString("email");
    		txtemail.setText(e);
    		String add=rs.getString("address");
    		txtadd.setText(add);
    		java.sql.Date d=rs.getDate("dos");
    		LocalDate dt=d.toLocalDate();
    		dos.setValue(dt);
    		String ary[]=ppr.split(",");
    		listSelPapers.getItems().clear(); 
    		for(String s:ary)
    		{
    			listSelPapers.getItems().add(s);
    		}
    		
    		String aryprice[]=pr.split(",");
    		for(String s:aryprice)
    		{
    			listSelPrices.getItems().add(Float.valueOf(s));
    		}
    		
    	}
    	
    }

    @FXML
    void doRemove(ActionEvent event) {

    }

    @FXML
    void doRemoveSel(MouseEvent event) {
    	
    	if(event.getClickCount()==1)
    		
    	{
    		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
             alert.setHeaderText("Do you want to delete item??");
             
             // Customize the buttons (Yes, No)
             alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
             
             // Show the dialog and get the result
             alert.showAndWait().ifPresent(response -> {
                 if (response == ButtonType.YES) {
                     System.out.println("User clicked Yes");
                     int n=listSelPapers.getSelectionModel().getSelectedIndex();
             		listSelPrices.getSelectionModel().clearAndSelect(n);
             		String s=listSelPapers.getItems().remove(listSelPapers.getSelectionModel().getSelectedIndex());
             		listSelPrices.getItems().remove(listSelPrices.getSelectionModel().getSelectedIndex());
             		a.remove(s);
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
    @FXML
    void doGetHawker(ActionEvent event) throws Exception
    {
    	ResultSet rs;
    	String a=comboAreas.getSelectionModel().getSelectedItem();
    	
    	System.out.println(a);
    	ps=con.prepareStatement("select * from hawkers where alloareas like ?");
    	ps.setString(1,"%"+a+"%");
    	rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String s=rs.getString("name");
    	System.out.println(s);
    	txtHawcker.setText(s);
    	}

    }

    @FXML
    void doSave(ActionEvent event) throws Exception
    {
    	ObservableList<String> os=listSelPapers.getItems();
    	//System.out.println(os);
    String sel ="";
    for(int i=0;i<os.size();i++)
    {
    	sel=sel+os.get(i)+",";
    }
    sel=sel.substring(0, sel.length()-1);
    

	ObservableList<Float> op=listSelPrices.getItems();
	//System.out.println(op);
String selp ="";
for(int i=0;i<op.size();i++)
{
	selp=selp+op.get(i)+",";
}
selp=selp.substring(0, selp.length()-1);

    //System.out.println(sel);
    	ps=con.prepareStatement("insert into customer_manager values (?,?,?,?,?,?,?,?,?,0)");
    	String nme=txtname.getText();
    	String mob=txtmob.getText();
    	String hkr=txtHawcker.getText();
    	String email=txtemail.getText();
    	LocalDate dt=dos.getValue();
		java.sql.Date date=java.sql.Date.valueOf(dt);
		String add=txtadd.getText();
		ps.setString(1, mob);
		ps.setString(2, nme);
		ps.setString(3, sel);
		ps.setString(4, selp);
		ps.setString(5, comboAreas.getSelectionModel().getSelectedItem());
		ps.setString(6, hkr);
		ps.setString(7, email);
		ps.setString(8, add);
		ps.setDate(9, date);
		ps.executeUpdate();
		System.out.println("saved..");
		
		
		

    }
    HashSet<String> a=new HashSet<String>();
    @FXML
    void doSelect(MouseEvent event) {
    	
    	int i=listPapers.getSelectionModel().getSelectedIndex();
		listPrices.getSelectionModel().clearAndSelect(i);
		float p=listPrices.getSelectionModel().getSelectedItem();
		String bk=listPapers.getSelectionModel().getSelectedItem();
		
		if(a.add(bk)) {
			listSelPapers.getItems().add(bk);
			listSelPrices.getItems().add(p);
		}


    }

    @FXML
    void doUpdate(ActionEvent event) throws Exception
    {
    	ObservableList<String> os=listSelPapers.getItems();
    	//System.out.println(os);
    String sel ="";
    for(int i=0;i<os.size();i++)
    {
    	sel=sel+os.get(i)+",";
    }
    sel=sel.substring(0, sel.length()-1);
    

	ObservableList<Float> op=listSelPrices.getItems();
	//System.out.println(op);
String selp ="";
for(int i=0;i<op.size();i++)
{
	selp=selp+op.get(i)+",";
}
sel=sel.substring(0, sel.length()-1);

    //System.out.println(sel);
    	ps=con.prepareStatement("update customer_manager set name=?,spapers=?,sprices=?,area=?,hawker=?,email=?,address=? where mobile=?");
    	String nme=txtname.getText();
    	String mob=txtmob.getText();
    	String hkr=txtHawcker.getText();
    	String email=txtemail.getText();
    	LocalDate dt=dos.getValue();
		java.sql.Date date=java.sql.Date.valueOf(dt);
		String add=txtadd.getText();
		ps.setString(8, mob);
		ps.setString(1, nme);
		ps.setString(2, sel);
		ps.setString(3, selp);
		ps.setString(4, comboAreas.getSelectionModel().getSelectedItem());
		ps.setString(5, hkr);
		ps.setString(6, email);
		ps.setString(7, add);
		
		int res=ps.executeUpdate();
		System.out.println(res);
		
		

    }
    void fillList() throws Exception
    {
    	ps=con.prepareStatement("select paper from paper_master");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		listPapers.getItems().add(rs.getString("paper"));
    	}
    	ps=con.prepareStatement("select price from paper_master");
    	ResultSet rst=ps.executeQuery();
    	while(rst.next())
    	{
    		listPrices.getItems().add(rst.getFloat("price"));
    	}	
    }
    
    void doFillComboAreas() throws Exception
    {

    	HashSet np=new HashSet();
    	comboAreas.getSelectionModel().clearSelection();
    	comboAreas.getItems().clear();
    	ps=con.prepareStatement("select area from areas");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		String n=rs.getString("area");
    		np.add(n);
    		
    	}
    	comboAreas.getItems().addAll(np);
     }
    @FXML
    void initialize() throws Exception {
    	
    	con=paperMaster.MysqlConnector.doConnect();
    	fillList();
        
    	doFillComboAreas();
        
    	 }

}
