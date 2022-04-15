package GUI;

import Business.Server.TerminalServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class TerminalView extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 4}, 449220l);
        System.out.println(terminalServer.depositCash(999));
        terminalServer.cashWithdrawal(120);
        terminalServer.cashWithdrawal(9900);
        System.out.println(terminalServer.cashWithdrawal(200));
        launch();
    }

}