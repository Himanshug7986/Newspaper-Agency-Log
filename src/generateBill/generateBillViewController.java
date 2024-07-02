package generateBill;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class generateBillViewController {
	Connection con;
	PreparedStatement ps;
	PreparedStatement ps2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboMob;

    @FXML
    private DatePicker dateLast;

    @FXML
    private DatePicker dateNow;

    @FXML
    private TextField mdays;

    @FXML
    private TextField papers;

    @FXML
    private TextField prices;

    @FXML
    private TextField totalBill;

    @FXML
    private TextField totalPrice;
    
    
    @FXML
    void doBill(ActionEvent event) {
    	LocalDate d1=dateLast.getValue();
    	LocalDate d2=dateNow.getValue();
    	
    	Period p=Period.between(d1, d2);
    	System.out.println(p);
    	int dy=p.getDays();
    	int	mnth=p.getMonths();
    	int diff=mnth*31+dy;
    	
    	int missing=Integer.valueOf(mdays.getText());
    	float bil=sum*diff-(missing*sum);
    	totalBill.setText(String.valueOf(bil));

    }
    float sum=0;
    @FXML
    void doFetchdata(ActionEvent event) throws Exception
    {
    	LocalDate d=null;
    	String m=comboMob.getSelectionModel().getSelectedItem();
    	ps=con.prepareStatement("select * from customer_manager where mobile=?");
    	ps2=con.prepareStatement("select max(datetill) from bills where mobile=?");
    	ps.setString(1,m);
    	ResultSet r=ps.executeQuery();
    	while(r.next())
    	{
    		String pprs=r.getString("spapers");
    		String pri=r.getString("sprices");
    		int st=r.getInt("Sts");
    		if(st==0)
    		{
    		java.sql.Date dt=r.getDate("dos");
    		 d=dt.toLocalDate();
    		 PreparedStatement ps3;
    		 ps3=con.prepareStatement("update customer_manager set Sts=1 where mobile=?");
    		 ps3.setString(1, m);
    		 ps3.executeUpdate();
    		 
    		}
    		else
    		{
    			ps2.setString(1, m);
    			ResultSet rs=ps2.executeQuery();
    			while(rs.next()) {
    				java.sql.Date dt=rs.getDate("max(datetill)");
    	    		 d=dt.toLocalDate();
    			}
    		}
    		 dateLast.setValue(d);
    		papers.setText(pprs);
    		prices.setText(pri);
    		
    		
    		
    		String aryprice[]=pri.split(",");
    		for(String s:aryprice)
    		{
    			 sum+=Float.valueOf(s);
    		}
    		totalPrice.setText(String.valueOf(sum));
    		
    	}
    	

    }
    @FXML
    void doSave(ActionEvent event) throws Exception
    {
    	
    	
    	ps=con.prepareStatement("insert into bills values (?,?,?,?,?)");
    	String mob=comboMob.getSelectionModel().getSelectedItem();
    	LocalDate d1=dateLast.getValue();
    	java.sql.Date dn1=java.sql.Date.valueOf(d1);
    	LocalDate d2=dateNow.getValue();
    	java.sql.Date dn2=java.sql.Date.valueOf(d2);
    	
    	
    	String b=totalBill.getText();
    	
    	ps.setString(1, mob);
    	ps.setDate(2, dn1);
    	ps.setDate(3, dn2);
    	ps.setString(4, b);
    	ps.setInt(5, 0);
    	ps.executeUpdate();
    	System.out.println("saved");
    	
    	
    }
    
    void dofillcombo() throws Exception 
    {
    	HashSet<String> h=new HashSet<String>();
    	con=paperMaster.MysqlConnector.doConnect();
        ps=con.prepareStatement(" select mobile from customer_manager");
        ResultSet r=ps.executeQuery();
        while(r.next())
        {
        	String m=r.getString("mobile");
        	h.add(m);
        	
        }
        comboMob.getItems().addAll(h);
    }

    @FXML
    void initialize() throws Exception
    {
        dofillcombo();
        
    	
    }

}
