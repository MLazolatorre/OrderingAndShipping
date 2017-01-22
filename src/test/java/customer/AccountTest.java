package customer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class AccountTest {
    private Account account1;
    private Account account2;


    @Before
    public void setUp (){

        Customer owner1 = new Customer("15 av Motte Piquet", 258147, "Marc", "Lazo la Torre");
        Customer owner2 = new Customer("Taverny", 258963, "Flavien", "Caron");

        account1 = new Account(owner1, "LALA", "pwd");
        account2 = new Account(owner2, "COUCOU", "no");

    }

    @Test
    public void connectionTest (){
        assertTrue("Marc's account true result", account1.connection("LALA","pwd"));
        assertTrue("Flavien's account true result", account2.connection("COUCOU", "no"));
        assertFalse("Marc's account false pwd", account1.connection("LALA", "fa"));
        assertFalse("Flavien's account false userName", account2.connection("coucou", "no"));
    }

    @Test
    public void getOrderingTest(){
        //TODO apres test de la class Order
    }

    @Test
    public void getOrderingAddress() throws Exception {
        assertEquals("Marc's account good address","15 av Motte Piquet", account1.getOrderingAddress());
        assertEquals("Flavien's account good address","Taverny", account2.getOrderingAddress());
    }

    @Test
    public void getHistory() throws Exception {
        //TODO apres test de la class Order
    }

}