package View;

import Model.Client.TerminalImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TerminalController implements Initializable {
    @FXML
    private TextField accountNumberTextField;
    @FXML
    private PasswordField pinField;
    @FXML
    private TextField balanceTextField;
    @FXML
    private TextField depositMoneyTextField;
    @FXML
    private TextField withdrawalMoneyTextField;
    @FXML
    private Button chkAccountButton;
    @FXML
    private Button chkPinButton1;
    @FXML
    private Button getBalanceButton;
    @FXML
    private Button depositMoneyButton;
    @FXML
    private Button withdrawalMoneyButton;
    @FXML
    private Button closeSessionButton;
    @FXML
    private Label pinLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label depositMoneyLabel;
    @FXML
    private Label withdrawalMoneyLabel;
    @FXML
    private TextField messageTextField;
    @FXML
    private TextArea info;
    private TerminalImpl terminal = new TerminalImpl(this);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getReady();
        pinField.textProperty().addListener((observable, oldValue, newValue) -> {
            checkPinSymbol();
        });

    }

    public void checkPin(ActionEvent actionEvent) {
        String temp = pinField.getText();
        Integer pinCode = Integer.parseInt(temp);
        Boolean checkPin = terminal.verifyPinCode(pinCode);
        if (checkPin) {
            printMessage("Введен верный Pin-код");
            balanceLabel.setDisable(false);
            depositMoneyLabel.setDisable(false);
            withdrawalMoneyLabel.setDisable(false);
            balanceTextField.setDisable(false);
            depositMoneyTextField.setDisable(false);
            withdrawalMoneyTextField.setDisable(false);
            getBalanceButton.setDisable(false);
            depositMoneyButton.setDisable(false);
            withdrawalMoneyButton.setDisable(false);
            closeSessionButton.setDisable(false);
            pinField.clear();
        } else {
            pinField.clear();
        }
    }



    public void checkAccount(ActionEvent actionEvent) {
        String temp = accountNumberTextField.getText();
        Integer accountNumber = null;
        try {
            accountNumber = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            printMessage("Номер аккаунта может состоять только из цифр");
        }
        if(accountNumber != null) {
                boolean isFind = terminal.checkAccount(accountNumber);
                if(isFind) {
                    printMessage("Аккаунт найден, введите Pin");
                    pinLabel.setDisable(false);
                    pinField.setDisable(false);
                    chkPinButton1.setDisable(false);
                }

        }
    }

    public void checkPinSymbol() {
        try {
            String temp = pinField.getText();
            if (temp.equals("")) return;
            Integer.parseInt(temp);
            if (temp.length() > 4) {
                printMessage("Длина Pin-кода составляет 4 символа");
                Platform.runLater(() -> pinField.clear());
            }
        } catch (NumberFormatException e) {
            printMessage("Pin-код может содержать только цифры");
            Platform.runLater(() -> pinField.clear());
        }
    }

    public void getBalance(ActionEvent actionEvent) {
        balanceTextField.setText(String.valueOf(terminal.checkAmount()));
    }

    public void depositMoney(ActionEvent actionEvent) {
        try {
            Integer cash = Integer.parseInt(depositMoneyTextField.getText());
            balanceTextField.setText(String.valueOf(terminal.deposit(cash)));
        } catch (NumberFormatException e) {
            printMessage("Введите числовое значение!");
            depositMoneyTextField.clear();
        }
    }

    public void withdrawalMoney(ActionEvent actionEvent) {
        try {
            Integer cash = Integer.parseInt(withdrawalMoneyTextField.getText());
            balanceTextField.setText(String.valueOf(terminal.withdraw(cash)));
        } catch (NumberFormatException e) {
            printMessage("Введите числовое значение!");
            withdrawalMoneyTextField.clear();
        }
    }

    public void closeSession(ActionEvent actionEvent) {
        getReady();
        pinField.clear();
        balanceTextField.clear();
        depositMoneyTextField.clear();
        withdrawalMoneyTextField.clear();
    }

    public void printMessage(String message){
        messageTextField.setText(message);
    }
    public void getReady(){
        pinField.setDisable(true);
        balanceTextField.setDisable(true);
        balanceTextField.setEditable(false);
        depositMoneyTextField.setDisable(true);
        withdrawalMoneyTextField.setDisable(true);
        chkPinButton1.setDisable(true);
        getBalanceButton.setDisable(true);
        depositMoneyButton.setDisable(true);
        withdrawalMoneyButton.setDisable(true);
        closeSessionButton.setDisable(true);
        pinLabel.setDisable(true);
        balanceLabel.setDisable(true);
        depositMoneyLabel.setDisable(true);
        withdrawalMoneyLabel.setDisable(true);
        info.setText("449220\n459211\n448221\n142239");

    }

}
