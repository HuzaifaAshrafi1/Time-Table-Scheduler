package Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.Random;

public class LoginController {

    @FXML
    private Canvas backgroundCanvas;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label msgLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final int PARTICLES = 60;
    private double[] x, y, speed;
    private Random random = new Random();
    private AnimationTimer timer; // ⭐ store reference

    @FXML
    public void initialize() {

        backgroundCanvas.widthProperty().bind(rootPane.widthProperty());
        backgroundCanvas.heightProperty().bind(rootPane.heightProperty());

        initParticles();
        startAnimation();

        // ⭐ Pause animation when minimized
        setupMinimizeHandler();
    }

    private void setupMinimizeHandler() {
        rootPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {

                newScene.windowProperty().addListener((obs2, oldWindow, newWindow) -> {
                    if (newWindow != null) {

                        Stage stage = (Stage) newWindow;

                        // Add listener AFTER window fully appears
                        stage.iconifiedProperty().addListener((o, oldV, minimized) -> {
                            if (minimized)
                                timer.stop();
                            else
                                timer.start();
                        });
                    }
                });
            }
        });
    }

    private void initParticles() {
        x = new double[PARTICLES];
        y = new double[PARTICLES];
        speed = new double[PARTICLES];
        for (int i = 0; i < PARTICLES; i++) {
            x[i] = random.nextDouble() * 900;
            y[i] = random.nextDouble() * 600;
            speed[i] = 0.4 + random.nextDouble();
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
                Color c1 = Color.hsb(hue, 0.6, 0.35);
                Color c2 = Color.hsb((hue + 80) % 360, 0.7, 0.55);

                gc.setFill(new LinearGradient(
                        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, c1), new Stop(1, c2)));
                gc.fillRect(0, 0, w, h);

                for (int i = 0; i < PARTICLES; i++) {
                    y[i] += speed[i];
                    if (y[i] > h) {
                        y[i] = -10;
                        x[i] = random.nextDouble() * w;
                    }
                    gc.setFill(Color.hsb((hue + 120) % 360, 1, 1, 0.85));
                    gc.fillOval(x[i], y[i], 4, 4);
                }

                Color glow = Color.hsb((hue + 140) % 360, 1, 1);
                titleLabel.setStyle(
                        String.format("-fx-font-size: 42px; -fx-font-weight: bold; -fx-text-fill: white; "
                                + "-fx-effect: dropshadow(gaussian, rgba(%d,%d,%d,0.9), 30, 0.4, 0, 0);",
                                (int) (glow.getRed() * 255),
                                (int) (glow.getGreen() * 255),
                                (int) (glow.getBlue() * 255)));
            }
        };

        timer.start();
    }

    @FXML
    private void handleLogin() {
        String u = usernameField.getText();
        String p = passwordField.getText();

        if ("admin".equals(u) && "1234".equals(p)) {
            try {
                Parent dashboard = FXMLLoader.load(getClass().getResource("/UI/dashboard.fxml"));
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.getScene().setRoot(dashboard);
            } catch (Exception e) {
                e.printStackTrace();
                msgLabel.setText("Dashboard load failed!");
                msgLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            msgLabel.setText("Invalid Username or Password");
            msgLabel.setStyle("-fx-text-fill: #ff5252;");
        }
    }
}
