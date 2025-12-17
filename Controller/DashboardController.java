package Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class DashboardController {

    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private StackPane rootStack;
    @FXML
    private StackPane contentArea;

    // Animation variables
    private static final int PARTICLES = 50;
    private double[] x = new double[PARTICLES];
    private double[] y = new double[PARTICLES];
    private double[] speed = new double[PARTICLES];
    private final Random random = new Random();
    private AnimationTimer timer;

    // Singleton instance
    private static DashboardController instance;

    public static DashboardController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        instance = this;

        // Setup canvas
        backgroundCanvas.widthProperty().bind(rootStack.widthProperty());
        backgroundCanvas.heightProperty().bind(rootStack.heightProperty());

        // Start animation
        initParticles();
        startAnimation();

        // Load dashboard home by default
        loadDashboardHome();
    }

    // ================= PAGE LOADING METHODS =================

    @FXML
    public void openRoomsLabs() {
        System.out.println("Opening Rooms & Labs...");
        loadPage("/UI/rooms_labs.fxml");
    }

    @FXML
    public void openStudents() {
        System.out.println("Opening Students...");
        loadPage("/UI/students.fxml");
    }

    @FXML
    public void openTimetable() {
        System.out.println("Opening Timetable...");
        loadPage("/UI/timetable.fxml");
    }

    @FXML
    public void openSettings() {
        System.out.println("Opening Settings...");
        loadPage("/UI/settings.fxml");
    }

    // Main method to load dashboard home
    public void loadDashboardHome() {
        System.out.println("Loading Dashboard Home...");
        loadPage("/UI/dashboard_home.fxml");
    }

    private void loadPage(String fxmlPath) {
        try {
            System.out.println("Attempting to load: " + fxmlPath);

            // Clear current content
            contentArea.getChildren().clear();

            // Load new FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent page = loader.load();

            // Add to content area
            contentArea.getChildren().add(page);

            System.out.println("Successfully loaded: " + fxmlPath);

        } catch (IOException e) {
            System.err.println("ERROR: Failed to load " + fxmlPath);
            e.printStackTrace();
            showError("Cannot load page", e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("ERROR: FXML file not found at " + fxmlPath);
            e.printStackTrace();
            showError("File not found", "Could not find: " + fxmlPath);
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ================= ANIMATION METHODS =================

    private void initParticles() {
        for (int i = 0; i < PARTICLES; i++) {
            x[i] = random.nextDouble() * 1200;
            y[i] = random.nextDouble() * 800;
            speed[i] = 0.3 + random.nextDouble();
        }
    }

    private void startAnimation() {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();

        timer = new AnimationTimer() {
            double hue = 190;

            @Override
            public void handle(long now) {
                double w = backgroundCanvas.getWidth();
                double h = backgroundCanvas.getHeight();

                hue = (hue + 0.15) % 360;

                // Background gradient
                LinearGradient gradient = new LinearGradient(
                        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.hsb(hue, 0.6, 0.35)),
                        new Stop(1, Color.hsb((hue + 80) % 360, 0.7, 0.55)));

                gc.setFill(gradient);
                gc.fillRect(0, 0, w, h);

                // Particles
                for (int i = 0; i < PARTICLES; i++) {
                    y[i] += speed[i];
                    if (y[i] > h) {
                        y[i] = -10;
                        x[i] = random.nextDouble() * w;
                    }

                    gc.setFill(Color.hsb((hue + 120) % 360, 1, 1, 0.85));
                    gc.fillOval(x[i], y[i], 4, 4);
                }
            }
        };

        timer.start();
    }

    // ================= LOGOUT =================

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/UI/login.fxml"));
            Stage stage = (Stage) rootStack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Logout Error", e.getMessage());
        }
    }
}