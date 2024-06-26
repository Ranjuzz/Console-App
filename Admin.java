import java.util.*;

public class Admin {
    private int userid;
    private int password;
    Scanner sc = new Scanner(System.in);
    public ArrayList<Bank> banks;
    // private Bank ab;

    Admin() {
        this.userid = 8056;
        this.password = 1698;
        this.banks = new ArrayList<>();
        DefaultBanks();
    }

    public void DefaultBanks() {
        Bank bank1 = new Bank("IOB");
        Bank bank2 = new Bank("KVB");

        bank1.addAccount(new Account("Venk", 1234567890, 1000.0, 1234));
        bank1.addAccount(new Account("udhay", 9876543210l, 500.0, 4321));
        bank2.addAccount(new Account("Anu", 1234567890, 1500.0, 5678));

        banks.add(bank1);
        banks.add(bank2);

        // ATM atm = new ATM(banks);
    }

    public void bankstart() {
        ATM atm = new ATM(banks);
        atm.start();
    }

    public void addBank(Bank bank) {
        banks.add(bank);
        System.out.println(bank.dispname() + " bank  added Succefuuly");
    }

    public void display() {
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(i + "." + banks.get(i).dispname());
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
