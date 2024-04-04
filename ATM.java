import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the Account class
class Account {
    private String name;
    private long accountNumber;
    private double balance;
    private int pin;

    public Account(String name, long accountNumber, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
        this.name = name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. Current balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void changePin(int oldPin, int newPin) {
        if (verifyPin(oldPin)) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Incorrect PIN. PIN change failed.");
        }
    }
}

// Define the Bank class
class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public void removeAccount(Account account) {
        if (accounts.contains(account))
            accounts.remove(account);
        else
            System.out.println("Account not found!");
    }

    public Account getAccount(int accountNumber, int pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.verifyPin(pin)) {
                return account;
            }
        }
        return null;
    }
}

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, pin);
        if (account != null) {
            System.out.println("Your current balance is: " + account.getBalance());
        } else {
            System.out.println("Account not found or PIN incorrect.");
        }
    }

    private void deposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, pin);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found or PIN incorrect.");
        }
    }

    private void withdraw() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, pin);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found or PIN incorrect.");
        }
    }

    private void changePin() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter old PIN: ");
        int oldPin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, oldPin);
        if (account != null) {
            System.out.print("Enter new PIN: ");
            int newPin = scanner.nextInt();
            account.changePin(oldPin, newPin);
        } else {
            System.out.println("Account not found or PIN incorrect.");
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        // Add some sample accounts
        bank.addAccount(new Account("Ranjith", 123456789, 1000.0, 1234));
        bank.addAccount(new Account("Sanjay", 987654321, 500.0, 4321));
        ATM atm = new ATM(bank);
        atm.start();
    }
}
