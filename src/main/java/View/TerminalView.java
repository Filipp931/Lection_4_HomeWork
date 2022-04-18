package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class TerminalView extends Application {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loader.load();
        primaryStage.setTitle("Terminal");
        primaryStage.setScene(new Scene(root, 782, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}