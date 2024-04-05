import java.util.*;;

public class Bank {
    private List<Account> accounts;
    private String BankName;

    public Bank(String BankName) {
        this.accounts = new ArrayList<>();
        this.BankName = BankName;
    }

    public String dispname() {
        return this.BankName;
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