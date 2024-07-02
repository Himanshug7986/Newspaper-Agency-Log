package allhawkers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class allHawkersRecordViewController {
	Connection con;
	PreparedStatement ps;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<hawkerBean> table;

    @FXML
    void doFetchAll(ActionEvent event) throws Exception {
    	
    	TableColumn<hawkerBean, String> name=new TableColumn<hawkerBean, String>("Hawcker Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<hawkerBean, String> mobile=new TableColumn<hawkerBean, String>("Mobile Number");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<hawkerBean, String> adhaarno=new TableColumn<hawkerBean, String>("Adhaar Card Number");
    	adhaarno.setCellValueFactory(new PropertyValueFactory<>("adhaarno"));
    	
    	TableColumn<hawkerBean, String> address=new TableColumn<hawkerBean, String>("Hawcker Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<hawkerBean, String> alloareas=new TableColumn<hawkerBean, String>("Areas Allocated");
    	alloareas.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	
    	TableColumn<hawkerBean, String> dojoining=new TableColumn<hawkerBean, String>("Enrolled on");
    	dojoining.setCellValueFactory(new PropertyValueFactory<>("dojoining"));
    	
    	table.getColumns().addAll(new ArrayList<>(Arrays.asList(name,mobile,adhaarno,address,alloareas,dojoining)));
    	ObservableList<hawkerBean> a=doFillTable();
    	System.out.println(a);
    	table.setItems(a);
    }
    
    ObservableList<hawkerBean> doFillTable() throws Exception
    {
    	ObservableList<hawkerBean> ary=FXCollections.observableArrayList();
    	
    	ps = con.prepareStatement("select * from hawkers");
		ResultSet table=ps.executeQuery();
	
		while(table.next()) {
    		String mno=table.getString("mobile");
    		String name = table.getString("name");
    		String adn = table.getString("adhaarno");
    		String add = table.getString("address");
    		String DOJ = String.valueOf(table.getDate("dojoining").toLocalDate());
    		String alloarea=table.getString("alloareas");
    		
    		hawkerBean ref=new hawkerBean(name, mno,adn,add, alloarea, DOJ);
    		ary.add(ref);
		}
		
		return ary;
    }
    
    @FXML
    void initialize() {
        con=paperMaster.MysqlConnector.doConnect();
        
    }

}
