package customer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class AccountManagerTest {

    private AccountManager accountManager;

    @Before
    public void setUp() throws Exception {
        accountManager = AccountManager.getInstance();
        accountManager.createAccount("Tav", 258963, "Flav", "Caron", "COUCOU", "pwd");
    }

    @Test
    public void createAccount() throws Exception {
        int numberAtTheBeginOfTheTest = accountManager.getNumberOfAccount();
        accountManager.createAccount("Paris", 258741, "Marc", "Lazo", "yo", "no");
        assertEquals("second test", numberAtTheBeginOfTheTest + 1, accountManager.getNumberOfAccount());
    }

    @Test
    public void connection() throws Exception {

        assertNotNull(accountManager.connection("COUCOU", "pwd"));
        assertNull(accountManager.connection("Coucou", "pwd"));
        assertNull(accountManager.connection("COUCOU", "pwD"));

        accountManager.createAccount("Paris", 258741, "Marc", "Lazo", "yo", "no");
        assertNotNull(accountManager.connection("yo", "no"));
    }

}