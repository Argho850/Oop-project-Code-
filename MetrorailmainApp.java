import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MetroRailGUI extends Application {
    private static final String FLAG_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/f/f9/Flag_of_Bangladesh.svg";
    private Stage primaryStage;
    private ImageView flagBackground;
    private double opacity = 0.15;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createFlagBackground();
        showMainMenu();
        primaryStage.setTitle("Bangladesh Metrorail System");
        primaryStage.show();
    }

    private void createFlagBackground() {
        flagBackground = new ImageView(new Image(FLAG_IMAGE));
        flagBackground.setOpacity(opacity);
        flagBackground.setPreserveRatio(true);
        flagBackground.setFitWidth(800);
    }

    private void showMainMenu() {
        StackPane root = new StackPane();
        root.getChildren().add(flagBackground);

        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(20));

        Text title = createStyledText("BANGLADESH METRORAIL SYSTEM", 26, Color.DARKGREEN);
        Button adminBtn = createHoverButton("Admin Panel");
        Button userBtn = createHoverButton("User Panel");
        Button exitBtn = createHoverButton("Exit System");

        adminBtn.setOnAction(e -> animateTransition(() -> showAdminLogin()));
        userBtn.setOnAction(e -> animateTransition(() -> showUserPanel()));
        exitBtn.setOnAction(e -> primaryStage.close());

        menuBox.getChildren().addAll(title, adminBtn, userBtn, exitBtn);
        root.getChildren().add(menuBox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    private Button createHoverButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-base: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-base: #FF0000; -fx-text-fill: white;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-base: #4CAF50; -fx-text-fill: white;"));
        return btn;
    }

    private Text createStyledText(String content, int size, Color color) {
        Text text = new Text(content);
        text.setFont(Font.font("Arial", FontWeight.BOLD, size));
        text.setFill(color);
        return text;
    }

    private void animateTransition(Runnable nextScene) {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), primaryStage.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            nextScene.run();
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), primaryStage.getScene().getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void showAdminLogin() {
        // Add admin login UI components here
        // Implement similar structure with your existing admin logic
    }

    private void showUserPanel() {
        // Add user panel UI components here
        // Implement similar structure with your existing user logic
    }

    public static void main(String[] args) {
        launch(args);
    }
}