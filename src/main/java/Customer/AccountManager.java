package customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */

public class AccountManager {

    private List<Account> accounts;

    private static AccountManager ourInstance;

    private AccountManager(){
        accounts = new ArrayList<Account>();
    }

    public static AccountManager getInstance() {
        if (ourInstance == null){
            ourInstance = new AccountManager();
        }
        return ourInstance;
    }

    public Account createAccount (String address, int phone, String firstName, String secondName, String userName, String password){
        Customer owner = new Customer(address,phone,firstName, secondName);
        Account account = new Account(owner, userName, password);

        accounts.add(account);

        return account;
    }


    public Account connection (String userName, String password){
        for (Account account : accounts){
            if (account.connection(userName,password))
                return account;
        }
        return null;
    }


    public int getNumberOfAccount() {
        return accounts.size();
    }
}
