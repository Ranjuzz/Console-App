import java.util.*;;

public class Bank {
    public ArrayList<Account> accounts;
    private String BankName;

    public Bank(String BankName) {
        this.accounts = new ArrayList<>();
        this.BankName = BankName;
    }

    public String dispname() {
        return this.BankName;
    }

    public boolean verifyacc(long accountNumber, int pin) {
        for (Account a : accounts) {
            if (a.getAccountNumber() == accountNumber)
                return a.pin == pin;
        }
        return false;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        // System.out.println("Account added successfully.");
    }

    public void removeAccount(Account account) {
        if (accounts.contains(account))
            accounts.remove(account);
        else
            System.out.println("No accounts found!");
    }

    public Account getAccount(long accountNumber, int pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.verifyPin(pin)) {
                return account;
            }
        }
        return null;
    }

}