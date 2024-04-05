import java.util.*;

public class Admin {
    private int userid;
    private int password;
    Scanner sc = new Scanner(System.in);
    private List<Bank> banks;
    // private Bank ab;

    Admin() {
        this.userid = 8056;
        this.password = 1698;
        this.banks = new ArrayList<>();
        DefaultBanks();
    }

    private void DefaultBanks() {
        Bank bank1 = new Bank("IOB");
        Bank bank2 = new Bank("KVB");

        bank1.addAccount(new Account("Venk", 1234567890, 1000.0, 1234));
        bank1.addAccount(new Account("udhay", 9876543210l, 500.0, 4321));
        bank2.addAccount(new Account("Anu", 1234567890, 1500.0, 5678));

        banks.add(bank1);
        banks.add(bank2);
    }

    public void addBank(Bank bank) {
        banks.add(bank);
        System.out.println("Bank added Succefuuly");
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
