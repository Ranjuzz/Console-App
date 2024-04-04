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
    private String BankName;

    public Bank(String BankName) {
        this.accounts = new ArrayList<>();
        this.BankName = BankName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public void removeAccount(Account account) {
        if (accounts.contains(account))
            accounts.remove(account);
        else
            System.out.println("No accounts found!");
    }

    public Account getAccount(int accountNumber, int pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.verifyPin(pin)) {
                return account;
            }
        }
        return null;
    }

    public Account getAccount(long accountNumber, int pin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccount'");
    }
}

class Admin {
    private int userid;
    private int password;
    Scanner sc = new Scanner(System.in);

    private List<Bank> banks;

    public Admin(int userid, int password) {
        this.banks = new ArrayList<>();
        this.userid = userid;
        this.password = password;
    }

    public void display() {
        for (Bank a : banks) {
            System.out.println(a);
        }
    }

    public boolean verify(int userid, int pass) {
        return this.userid == userid && this.password == pass;
    }

    public void addBank() {
        System.out.println("Bank Name");
        String name = sc.next();
        Bank b1 = new Bank(name);
        if (banks.contains(b1))
            System.out.println("Bank is already added");
        else {
            banks.add(b1);
            System.out.println("Bank added successfully");
        }
    }

    public void removeBank() {
        display();
        System.out.println("Enter the index of the bank to remove");
        int index = sc.nextInt();
        if (index >= 0 && index < banks.size()) {
            banks.remove(index);
            System.out.println("Bank removed successfully.");
        } else {
            System.out.println("Invalid bank index.");
        }
    }

}

public class ATM {
    private Bank bank;
    private Admin admin;
    private Scanner scanner;

    public ATM(Bank bank, int userid, int password) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        this.admin = new Admin(userid, password);
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
                System.out.println("4. Back");
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
        Bank bank1 = new Bank("KVB");
        Bank bank2 = new Bank("IOB");
        // Add some sample accounts
        bank1.addAccount(new Account("Ranjith", 123456789, 1000.0, 1234));
        bank1.addAccount(new Account("Sanjay", 987654321, 500.0, 4321));
        bank2.addAccount(new Account("Ranjith", 9791383135l, 1000.0, 1234));
        bank2.addAccount(new Account("Sanjay", 9876543210l, 500.0, 4321));
        ATM atm = new ATM(bank1, 8056, 1698);
        atm.start();
    }
}
