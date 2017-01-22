package builling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class BankAccountManager {

    List<BankAccount> accounts;

    private static BankAccountManager ourInstance;

    public static BankAccountManager getInstance() {
        if (ourInstance == null){
            ourInstance = new BankAccountManager();
        }
        return ourInstance;
    }

    private BankAccountManager() {
        accounts = new ArrayList<BankAccount>();
        accounts.add(new BankAccount(123456789, 1234, 100));
        accounts.add(new BankAccount(987654321, 9876, 100));
    }

    public boolean dropMoneyFromAccount(BankAccount bankAccount, double money){
        for (BankAccount account : accounts){
            if (account.equals(bankAccount)){
                return account.dropMoney(money);
            }
        }
        return false;
    }

    public BankAccount findBankAccount (int bankAccountNumber, int securityCode){
        for (BankAccount account : accounts){
            if (account.connection(bankAccountNumber, securityCode)){
                return account;
            }
        }
        return null;
    }
}
