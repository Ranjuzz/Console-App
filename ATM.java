
// import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

// import Account;
// import Admin;
// import Bank;

// Define the Account class

// Define the Bank class

public class ATM {
    private Bank bank;
    private Admin admin;
    private Scanner scanner;
    private long money = 50000;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        this.admin = new Admin();
    }

    public void start() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Admin");
            System.out.println("6. Exit");
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
                    admin();
                    break;
                case 6:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void admin() {
        System.out.println("Enter the admin userid");
        int userid = scanner.nextInt();
        System.out.println("Enter the password:");
        int password = scanner.nextInt();
        if (admin.verify(userid, password)) {
            int i = 1;
            do {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. List Banks");
                System.out.println("2. Add Bank");
                System.out.println("3. Delete Bank");
                System.out.println("4. Atm Balance");
                System.out.println("5. Back");
                i = scanner.nextInt();
                switch (i) {
                    case 1:
                        admin.display();
                        break;
                    case 2:
                        admin.addBank();
                        break;
                    case 3:
                        admin.removeBank();
                        break;
                    case 4:
                        System.out.println(atmbal());
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (i != 4);
        }
    }

    private void checkBalance() {
        System.out.print("Enter account number: ");
        long accountNumber = scanner.nextLong();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, pin);
        if (account != null) {
            System.out.println("Your current balance is: " + account.getBalance());
        } else {
            System.out.println("Account not found or PIN incorrect.");
        }
    }

    public long atmbal() {
        return money;
    }

    private void deposit() {
        System.out.print("Enter account number: ");
        Long accountNumber = scanner.nextLong();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account account = bank.getAccount(accountNumber, pin);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            money += amount;
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
            money -= amount;
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
        Bank bank1 = new Bank("KVB");
        Admin ad = new Admin();
        ATM atm = new ATM(bank1);
        atm.start();
    }
}
