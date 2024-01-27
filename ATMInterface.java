package org.task.temp.task_three;

import java.util.Scanner;

// Bank Account class to store account balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// ATM class with user interface
public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM Interface!");

        // Create a bank account for the user with an initial balance
        BankAccount userAccount = new BankAccount(1000.0);

        // Main loop for the ATM interface
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    // Withdraw
                    handleWithdraw(userAccount);
                    break;
                case 2:
                    // Deposit
                    handleDeposit(userAccount);
                    break;
                case 3:
                    // Check Balance
                    checkBalance(userAccount);
                    break;
                case 4:
                    // Exit
                    exit = true;
                    System.out.println("Thank you for using the ATM Interface. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    // Display ATM menu
    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Get user choice from the menu
    private static int getUserChoice() {
        return scanner.nextInt();
    }

    // Handle the withdraw operation
    private static void handleWithdraw(BankAccount account) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    // Handle the deposit operation
    private static void handleDeposit(BankAccount account) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    // Display the account balance
    private static void checkBalance(BankAccount account) {
        System.out.println("Your current balance: " + account.getBalance());
    }
}

