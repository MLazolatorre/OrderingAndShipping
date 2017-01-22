package customer;

import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public class AccountManager {

    private List<Account> accounts;


    public void creatAccount (String address, int phone, String firstName, String secondName, String userName, String password){
        Customer owner = new Customer(address,phone,firstName, secondName);
        Account account = new Account(owner, userName, password);

        accounts.add(account);
    }

    /*
    public boolean connection (String userName, String password){

    }
    */

}
