module NewspaperAgencyproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens paperMaster to javafx.graphics, javafx.fxml;
	opens hawkerManager to javafx.graphics, javafx.fxml;
	opens customerManager to javafx.graphics, javafx.fxml;
	opens generateBill to javafx.graphics, javafx.fxml;
	opens billCollector to javafx.graphics, javafx.fxml;
	opens allhawkers to javafx.graphics, javafx.fxml,javafx.base;
	opens customerDashboard to javafx.graphics, javafx.fxml,javafx.base;
	opens areaMaster to javafx.graphics, javafx.fxml;
	opens adminLogin to javafx.graphics, javafx.fxml;
	opens adminDesk to javafx.graphics, javafx.fxml;
}
