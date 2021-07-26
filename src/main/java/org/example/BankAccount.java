package org.example;

public class BankAccount {
    private int number; // attribute/property
    private String name;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

}
