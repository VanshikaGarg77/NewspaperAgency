module github {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml;
	opens PaperMaster to javafx.graphics, javafx.fxml;
	opens HawkerManager to javafx.graphics, javafx.fxml;
	opens CustomerManager to javafx.graphics, javafx.fxml;
	opens BillGenerator to javafx.graphics, javafx.fxml;
	opens BillCollector to javafx.graphics, javafx.fxml;
	opens HawkerTable to javafx.graphics, javafx.fxml,javafx.base;
	opens CustomerPanel to javafx.graphics, javafx.fxml,javafx.base;
	opens BillBoard to javafx.graphics, javafx.fxml,javafx.base;
	opens Login to javafx.graphics, javafx.fxml;
	opens AdminDesk to javafx.graphics, javafx.fxml;
	opens Developers to javafx.graphics, javafx.fxml;
}
