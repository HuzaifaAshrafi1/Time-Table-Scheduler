package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;

public class DashboardHomeController {
    @FXML
    private Label roomsCount;
    @FXML
    private Label labsCount;
    @FXML
    private Label studentsCount;
    @FXML
    private Label teachersCount;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    public void initialize() {
        // Dummy values — later from DB
        roomsCount.setText("12");
        labsCount.setText("7");
        studentsCount.setText("322");
        teachersCount.setText("24");

        // Add chart data if needed (or leave for DashboardController reuse)
    }
}