public class Account {
    private String name;
    private long accountNumber;
    private double balance;
    public int pin;

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