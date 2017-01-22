package builling;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class BankAccount {

    private int bankAccountNumber;
    private int secretCode;
    private double money;

    public BankAccount(int bankAccountNumber, int secretCode, double money) {
        this.bankAccountNumber = bankAccountNumber;
        this.secretCode = secretCode;
        this.money = money;
    }

    public boolean connection (int bankAccountNumber, int secretCode){
        return (this.bankAccountNumber == bankAccountNumber && this.secretCode == secretCode);
    }

    public boolean dropMoney (double money){
        if (money > this.money) {
            System.out.println("BankAccount : You don't have enough money to pay");
            return false;
        }
        this.money -= money;
        return true;
    }

    public double getMoney() {
        return money;
    }
}
