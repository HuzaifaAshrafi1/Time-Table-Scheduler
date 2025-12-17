package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class GenerateTimetableController {

    @FXML
    private ComboBox<String> algorithmSelector;

    @FXML
    private Button generateBtn;

    @FXML
    private TextArea outputArea;

    @FXML
    public void initialize() {
        // Algorithms originally in Algorithms tab now merged here
        algorithmSelector.getItems().addAll(
                "Basic Slot Allocation",
                "Room-Based Allocation",
                "Student Cluster Strategy",
                "Teacher Availability Strategy",
                "Genetic Algorithm",
                "AI Optimized Timetable");
    }

    @FXML
    private void generateTimetable() {
        String selected = algorithmSelector.getValue();

        if (selected == null) {
            outputArea.setText("⚠ Please select an algorithm first.");
            return;
        }

        // Placeholder output (actual logic comes later)
        outputArea.setText("Running algorithm: " + selected +
                "\n\nTimetable generation in progress...\n\n[Sample output]");
    }
}
