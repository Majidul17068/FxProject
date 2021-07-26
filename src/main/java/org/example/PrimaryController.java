package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {
    private AccountsService accountsService;
    private ObservableList<BankAccount> accounts;

    @FXML
    private TableView<BankAccount> accountTableView;
    @FXML
    private TableColumn<BankAccount, Number> numberColumn;
    @FXML
    private TableColumn<BankAccount, String> nameColumn;
    @FXML
    private TableColumn<BankAccount, Number> balanceColumn;

    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField balanceField;


    public PrimaryController() {
        accountTableView = new TableView<>();
        numberColumn = new TableColumn<>();
        nameColumn = new TableColumn<>();
        balanceColumn = new TableColumn<>();
        accountsService = new AccountsService();

        numberField = new TextField();
        nameField = new TextField();
        balanceField = new TextField();
    }

    private boolean isExistingAccount(int number) {
        for (BankAccount account : accounts)
            if (account.getNumber() == number)
                return true;
        return false;
    }

    @FXML
    public void handleSaveAction() {
        int number = Integer.parseInt(numberField.getText());
        String name = nameField.getText();
        double balance = Double.parseDouble(balanceField.getText());

        BankAccount bankAccount = new BankAccount(number, name, balance);

        if (!isExistingAccount(number)) {
            accounts.add(bankAccount);
            accountsService.writeAccounts("accounts.txt", accounts);

        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accounts = FXCollections.observableArrayList();
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        List<BankAccount> bankAccounts = accountsService.readAccounts("accounts.txt");

        accounts.addAll(bankAccounts);

        accountTableView.setItems(accounts);
    }

}
