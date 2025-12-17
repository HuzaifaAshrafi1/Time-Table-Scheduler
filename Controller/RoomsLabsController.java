package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomsLabsController implements Initializable {

    // UI Components
    @FXML
    private Label pageTitle;
    @FXML
    private TableView<RoomLab> roomsTable;
    @FXML
    private TableView<RoomLab> labsTable;
    @FXML
    private TextField roomNameField;
    @FXML
    private TextField roomCapacityField;
    @FXML
    private ComboBox<String> roomDepartmentCombo;
    @FXML
    private TextField labNameField;
    @FXML
    private TextField labCapacityField;
    @FXML
    private ComboBox<String> labDepartmentCombo;
    @FXML
    private ComboBox<String> labTypeCombo;
    @FXML
    private TextArea roomDescriptionField;
    @FXML
    private TextArea labDescriptionField;
    @FXML
    private Label totalRoomsLabel;
    @FXML
    private Label totalLabsLabel;
    @FXML
    private Label totalCapacityLabel;
    @FXML
    private Label utilizationLabel;

    // Data Lists
    private ObservableList<RoomLab> roomsList = FXCollections.observableArrayList();
    private ObservableList<RoomLab> labsList = FXCollections.observableArrayList();

    // Model Class
    public static class RoomLab {
        private String id;
        private String name;
        private String type; // "Room" or "Lab"
        private int capacity;
        private String department;
        private String description;
        private boolean isAvailable;

        public RoomLab(String id, String name, String type, int capacity,
                String department, String description, boolean isAvailable) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.capacity = capacity;
            this.department = department;
            this.description = description;
            this.isAvailable = isAvailable;
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("RoomsLabsController initialized");

        // Initialize dropdowns
        initializeDropdowns();

        // Initialize tables
        initializeTables();

        // Load sample data (Replace with SQL later)
        loadSampleData();

        // Update statistics
        updateStatistics();
    }

    private void initializeDropdowns() {
        // Department options
        ObservableList<String> departments = FXCollections.observableArrayList(
                "Computer Science", "BBA", "Electronics",
                "Electrical", "Mechanical", "Civil", "All Departments");
        roomDepartmentCombo.setItems(departments);
        labDepartmentCombo.setItems(departments);

        // Lab types
        ObservableList<String> labTypes = FXCollections.observableArrayList(
                "Computer Lab", "Physics Lab", "Chemistry Lab",
                "Electronics Lab", "Research Lab", "General Lab");
        labTypeCombo.setItems(labTypes);
    }

    private void initializeTables() {
        // Set up columns (you'll need to define these in FXML or add programmatically)
        // This is just the controller logic - columns should be defined in FXML
    }

    private void loadSampleData() {
        // Sample rooms (Replace with SQL query later)
        roomsList.addAll(
                new RoomLab("R001", "Room 101", "Room", 50, "Computer Science",
                        "Main classroom with projector", true),
                new RoomLab("R002", "Room 102", "Room", 40, "BBA",
                        "Case study room", true),
                new RoomLab("R003", "Room 201", "Room", 60, "Electronics",
                        "Lecture hall", true));

        // Sample labs (Replace with SQL query later)
        labsList.addAll(
                new RoomLab("L001", "Computer Lab A", "Lab", 30, "Computer Science",
                        "Programming lab with 30 PCs", true),
                new RoomLab("L002", "Physics Lab", "Lab", 25, "Electronics",
                        "Physics experiments lab", true),
                new RoomLab("L003", "Research Lab", "Lab", 15, "Computer Science",
                        "AI Research lab", false));

        // Set data to tables if they exist
        if (roomsTable != null)
            roomsTable.setItems(roomsList);
        if (labsTable != null)
            labsTable.setItems(labsList);
    }

    // ========== ROOM MANAGEMENT METHODS ==========

    @FXML
    private void addRoom() {
        try {
            String name = roomNameField.getText().trim();
            String capacityText = roomCapacityField.getText().trim();
            String department = roomDepartmentCombo.getValue();
            String description = roomDescriptionField.getText().trim();

            // Validation
            if (name.isEmpty() || capacityText.isEmpty() || department == null) {
                showAlert("Error", "Please fill all required fields", Alert.AlertType.ERROR);
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    showAlert("Error", "Capacity must be greater than 0", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid number for capacity", Alert.AlertType.ERROR);
                return;
            }

            // Generate ID (Replace with SQL auto-increment later)
            String id = "R" + String.format("%03d", roomsList.size() + 1);

            // Create new room
            RoomLab newRoom = new RoomLab(id, name, "Room", capacity, department, description, true);
            roomsList.add(newRoom);

            // Clear form
            clearRoomForm();

            // Update table
            if (roomsTable != null)
                roomsTable.refresh();

            // Update statistics
            updateStatistics();

            showAlert("Success", "Room added successfully!\nID: " + id, Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add room: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editRoom() {
        RoomLab selected = roomsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a room to edit", Alert.AlertType.WARNING);
            return;
        }

        // Populate form with selected room data
        roomNameField.setText(selected.getName());
        roomCapacityField.setText(String.valueOf(selected.getCapacity()));
        roomDepartmentCombo.setValue(selected.getDepartment());
        roomDescriptionField.setText(selected.getDescription());

        // Change add button to update
        // You might want to add an update button or handle this differently
    }

    @FXML
    private void deleteRoom() {
        RoomLab selected = roomsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a room to delete", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText("Delete Room");
        confirmation.setContentText("Are you sure you want to delete room: " + selected.getName() + "?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            roomsList.remove(selected);
            roomsTable.refresh();
            updateStatistics();
            showAlert("Success", "Room deleted successfully", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void clearRoomForm() {
        roomNameField.clear();
        roomCapacityField.clear();
        roomDepartmentCombo.setValue(null);
        roomDescriptionField.clear();
    }

    // ========== LAB MANAGEMENT METHODS ==========

    @FXML
    private void addLab() {
        try {
            String name = labNameField.getText().trim();
            String capacityText = labCapacityField.getText().trim();
            String department = labDepartmentCombo.getValue();
            String type = labTypeCombo.getValue();
            String description = labDescriptionField.getText().trim();

            // Validation
            if (name.isEmpty() || capacityText.isEmpty() || department == null || type == null) {
                showAlert("Error", "Please fill all required fields", Alert.AlertType.ERROR);
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    showAlert("Error", "Capacity must be greater than 0", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid number for capacity", Alert.AlertType.ERROR);
                return;
            }

            // Generate ID (Replace with SQL auto-increment later)
            String id = "L" + String.format("%03d", labsList.size() + 1);

            // Create new lab
            RoomLab newLab = new RoomLab(id, name, type, capacity, department, description, true);
            labsList.add(newLab);

            // Clear form
            clearLabForm();

            // Update table
            if (labsTable != null)
                labsTable.refresh();

            // Update statistics
            updateStatistics();

            showAlert("Success", "Lab added successfully!\nID: " + id, Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add lab: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void editLab() {
        RoomLab selected = labsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a lab to edit", Alert.AlertType.WARNING);
            return;
        }

        // Populate form with selected lab data
        labNameField.setText(selected.getName());
        labCapacityField.setText(String.valueOf(selected.getCapacity()));
        labDepartmentCombo.setValue(selected.getDepartment());
        labTypeCombo.setValue(selected.getType());
        labDescriptionField.setText(selected.getDescription());
    }

    @FXML
    private void deleteLab() {
        RoomLab selected = labsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Error", "Please select a lab to delete", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText("Delete Lab");
        confirmation.setContentText("Are you sure you want to delete lab: " + selected.getName() + "?");

        if (confirmation.showAndWait().get() == ButtonType.OK) {
            labsList.remove(selected);
            labsTable.refresh();
            updateStatistics();
            showAlert("Success", "Lab deleted successfully", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void clearLabForm() {
        labNameField.clear();
        labCapacityField.clear();
        labDepartmentCombo.setValue(null);
        labTypeCombo.setValue(null);
        labDescriptionField.clear();
    }

    // ========== UTILITY METHODS ==========

    private void updateStatistics() {
        int totalRooms = roomsList.size();
        int totalLabs = labsList.size();
        int totalCapacity = roomsList.stream().mapToInt(RoomLab::getCapacity).sum() +
                labsList.stream().mapToInt(RoomLab::getCapacity).sum();

        // Calculate utilization (for demo: random 70-95%)
        int utilization = 70 + (int) (Math.random() * 25);

        // Update labels
        if (totalRoomsLabel != null)
            totalRoomsLabel.setText(String.valueOf(totalRooms));
        if (totalLabsLabel != null)
            totalLabsLabel.setText(String.valueOf(totalLabs));
        if (totalCapacityLabel != null)
            totalCapacityLabel.setText(String.valueOf(totalCapacity));
        if (utilizationLabel != null)
            utilizationLabel.setText(utilization + "%");
    }

    @FXML
    private void exportData() {
        // TODO: Implement export to CSV/Excel
        showAlert("Export", "Export functionality will be implemented soon", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void generateReport() {
        // TODO: Implement report generation
        showAlert("Report", "Report generation will be implemented soon", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void goBack() {
        // Navigate back to dashboard
        DashboardController.getInstance().loadDashboardHome();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleButtonHover(javafx.event.Event event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            button.setStyle(button.getStyle() +
                    "; -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 10, 0.5, 0, 0);");
        }
    }

    @FXML
    private void handleButtonExit(javafx.event.Event event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            button.setStyle(button.getStyle()
                    .replace("; -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 10, 0.5, 0, 0);", ""));
        }
    }

    // ========== SQL READY METHODS (TO BE IMPLEMENTED) ==========

    private void loadDataFromDatabase() {
        // TODO: Load rooms from database
        // String query = "SELECT * FROM rooms";
        // Execute query and populate roomsList

        // TODO: Load labs from database
        // String query = "SELECT * FROM labs";
        // Execute query and populate labsList
    }

    private void saveRoomToDatabase(RoomLab room) {
        // TODO: Save room to database
        // String query = "INSERT INTO rooms (id, name, type, capacity, department,
        // description, is_available) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Use PreparedStatement to execute
    }

    private void saveLabToDatabase(RoomLab lab) {
        // TODO: Save lab to database
        // String query = "INSERT INTO labs (id, name, type, capacity, department,
        // description, is_available) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Use PreparedStatement to execute
    }

    private void updateRoomInDatabase(RoomLab room) {
        // TODO: Update room in database
        // String query = "UPDATE rooms SET name=?, capacity=?, department=?,
        // description=?, is_available=? WHERE id=?";
    }

    private void updateLabInDatabase(RoomLab lab) {
        // TODO: Update lab in database
        // String query = "UPDATE labs SET name=?, capacity=?, department=?,
        // description=?, is_available=? WHERE id=?";
    }

    private void deleteFromDatabase(String id, String type) {
        // TODO: Delete from database
        // String table = type.equals("Room") ? "rooms" : "labs";
        // String query = "DELETE FROM " + table + " WHERE id=?";
    }
}