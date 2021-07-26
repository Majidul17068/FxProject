package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class AccountsService {
    public List<BankAccount> readAccounts(String filename) {
        List<BankAccount> bankAccounts = new ArrayList<>();

        try {
            RandomAccessFile inputFile = new RandomAccessFile(filename, "r");

            String line;
            while ((line = inputFile.readLine()) != null) {
                String tokens[] = line.split(",");
                int number = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double balance = Double.parseDouble(tokens[2]);

                BankAccount account = new BankAccount(number, name, balance);
                bankAccounts.add(account);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bankAccounts;
    }

    public void writeAccounts(String filename, List<BankAccount> bankAccounts) {
        try {
            RandomAccessFile outputFile = new RandomAccessFile(filename, "rw");
            outputFile.setLength(0);

            for (BankAccount bankAccount : bankAccounts) {
                // creating a string using + inside a loop is a horrible idea (inefficient)
                String record = bankAccount.getNumber() + "," + bankAccount.getName() + "," + bankAccount.getBalance() + "\n";
                outputFile.writeBytes(record);
            }

            outputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
