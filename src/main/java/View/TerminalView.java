package View;

import Model.Server.TerminalServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class TerminalView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Terminal");
        primaryStage.setScene(new Scene(root, 782, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        TerminalServer terminalServer = new TerminalServer();
/*        terminalServer.verifyAndGetAccount(new int[]{1, 2, 3, 3}, 449220l);
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
        System.out.println(terminalServer.cashWithdrawal(200));*/
        launch();

    }

}