package customerDashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;

//import allhawkers.hawkerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class customerDataViewController {
	Connection con;
	PreparedStatement ps;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboAreas;

    @FXML
    private ComboBox<String> comboPapers;
    
    @FXML
    private TableView<cdBean> table;

//=====fill details with area=====
    @FXML
    void doFetchAll(ActionEvent event) throws Exception
    {
    	
    	if(comboAreas.getSelectionModel().getSelectedItem()!=null)
    	{
    		TableColumn<cdBean, String> name=new TableColumn<cdBean, String>("Customer Name");
        	name.setCellValueFactory(new PropertyValueFactory<>("name"));
        	
        	TableColumn<cdBean, String> mobile=new TableColumn<cdBean, String>("Mobile number");
        	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        	
        	TableColumn<cdBean, String> address=new TableColumn<cdBean, String>("Address");
        	address.setCellValueFactory(new PropertyValueFactory<>("address"));
        	
        	TableColumn<cdBean, String> spapers=new TableColumn<cdBean, String>("Papers");
        	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
        	
        	TableColumn<cdBean, String> dos=new TableColumn<cdBean, String>("Date of Joining");
        	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        	
        	table.getColumns().clear();
        	
        	table.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,address,spapers,dos)));
        	ObservableList<cdBean> a=fillDetailsWitharea();
        	System.out.println(a);
        	table.setItems(a);
    	}

    	
    	else if(comboPapers.getSelectionModel().getSelectedItem()!=null)
    	{
    		TableColumn<cdBean, String> name=new TableColumn<cdBean, String>("Customer Name");
        	name.setCellValueFactory(new PropertyValueFactory<>("name"));
        	
        	TableColumn<cdBean, String> mobile=new TableColumn<cdBean, String>("Mobile number");
        	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        	
        	TableColumn<cdBean, String> address=new TableColumn<cdBean, String>("Address");
        	address.setCellValueFactory(new PropertyValueFactory<>("address"));
        	
        	TableColumn<cdBean, String> spapers=new TableColumn<cdBean, String>("Papers");
        	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
        	
        	TableColumn<cdBean, String> dos=new TableColumn<cdBean, String>("Date of Joining");
        	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        	table.getColumns().clear();
        	
        	table.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,address,spapers,dos)));
        	ObservableList<cdBean> a=fillDetailsWithPaper();
        	System.out.println(a);
        	table.setItems(a);
    	}
    	
    	else if(comboAreas.getSelectionModel().getSelectedItem()!=null && comboPapers.getSelectionModel().getSelectedItem()!=null)
    	{
    		
    		TableColumn<cdBean, String> name=new TableColumn<cdBean, String>("Customer Name");
        	name.setCellValueFactory(new PropertyValueFactory<>("name"));
        	
        	TableColumn<cdBean, String> mobile=new TableColumn<cdBean, String>("Mobile number");
        	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        	
        	TableColumn<cdBean, String> address=new TableColumn<cdBean, String>("Address");
        	address.setCellValueFactory(new PropertyValueFactory<>("address"));
        	
        	TableColumn<cdBean, String> spapers=new TableColumn<cdBean, String>("Papers");
        	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
        	
        	TableColumn<cdBean, String> dos=new TableColumn<cdBean, String>("Date of Joining");
        	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        	table.getColumns().clear();
        	
        	table.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,address,spapers,dos)));
        	ObservableList<cdBean> a=fillDetailsWithBoth();
        	System.out.println(a);
        	table.setItems(a);
    	}
    	
    	comboAreas.getSelectionModel().clearSelection();
    	comboPapers.getSelectionModel().clearSelection();
    	

    }
    ObservableList<cdBean> fillDetailsWitharea() throws Exception

    
    {
ObservableList<cdBean> ary=FXCollections.observableArrayList();
	
		String a=comboAreas.getSelectionModel().getSelectedItem();
    	
    	ps = con.prepareStatement("select * from customer_manager where area=? ");
    	ps.setString(1, a);
    	
		ResultSet table=ps.executeQuery();
	
		while(table.next()) {
    		String mno=table.getString("mobile");
    		String name = table.getString("name");
    		String pprs = table.getString("spapers");
    		String add = table.getString("address");
    		String DOS = String.valueOf(table.getDate("dos").toLocalDate());
    		
    		cdBean ref=new cdBean(name, mno,add,pprs,DOS);
    		ary.add(ref);
		}
		
		return ary;
   
		
    }
    
 //======fill details with papers======
    ObservableList<cdBean> fillDetailsWithPaper() throws Exception
    {
ObservableList<cdBean> ary=FXCollections.observableArrayList();
    	String p=comboPapers.getSelectionModel().getSelectedItem();
    	ps = con.prepareStatement("select * from customer_manager where spapers like ?");
    	ps.setString(1, "%"+p+"%");
		ResultSet table=ps.executeQuery();
	
		while(table.next()) {
    		String mno=table.getString("mobile");
    		String name = table.getString("name");
    		String pprs = table.getString("spapers");
    		String add = table.getString("address");
    		String DOS = String.valueOf(table.getDate("dos").toLocalDate());
    		
    		cdBean ref=new cdBean(name, mno,add,pprs,DOS);
    		ary.add(ref);
		}
		
		return ary;
    }
    
   //=======fill details with both area and paper======
    ObservableList<cdBean> fillDetailsWithBoth() throws Exception
    {
ObservableList<cdBean> ary=FXCollections.observableArrayList();
    	String p=comboPapers.getSelectionModel().getSelectedItem();
    	String a=comboAreas.getSelectionModel().getSelectedItem();
    	ps = con.prepareStatement("select * from customer_manager where area=? and spapers like ?");
    	ps.setString(1, a);
    	ps.setString(2, "%"+p+"%");
		ResultSet table=ps.executeQuery();
	
		while(table.next()) {
    		String mno=table.getString("mobile");
    		String name = table.getString("name");
    		String pprs = table.getString("spapers");
    		String add = table.getString("address");
    		String DOS = String.valueOf(table.getDate("dos").toLocalDate());
    		
    		cdBean ref=new cdBean(name, mno,pprs,add,DOS);
    		ary.add(ref);
		}
		
		return ary;
    }
    //===========================
    
    
    void doFillAreas() throws Exception
    {
    	ps=con.prepareStatement("select area from customer_manager");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		comboAreas.getItems().add(rs.getString("area"));
    	}
    	
    }
    //======= fill details with papers=====
    
    
    
    void doFillPapers() throws Exception
    {
    	ps=con.prepareStatement("select paper from paper_master");
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		comboPapers.getItems().add(rs.getString("paper"));
    	}
    }
    
   

    @FXML
    void initialize() throws Exception {
    	con=paperMaster.MysqlConnector.doConnect();
       doFillAreas();
       doFillPapers();
       
    }

}
