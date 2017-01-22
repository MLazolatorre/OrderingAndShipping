package customer;

import org.junit.Before;
import org.junit.Test;
import product.LineProduct;
import product.Product;
import product.Stock;

import java.util.ArrayList;
import java.util.List;

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
        account1.setBankAccount(123456789, 1234);
        account2 = new Account(owner2, "COUCOU", "no");
        account2.setBankAccount(987654321, 9876);
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
        Stock stock = Stock.getInstance();

        List<LineProduct> commend = new ArrayList<LineProduct>();
        commend.add(new LineProduct(9, Product.TOMATO));
        commend.add(new LineProduct(5, Product.CARROT));

        assertTrue(account1.order(commend));
        assertEquals(1, stock.getQuantityInStock(Product.TOMATO));
        assertEquals(15, stock.getQuantityInStock(Product.CARROT));
        assertEquals(100-account1.getHistory().get(0).getTotalPrice(),account1.getBankAccount().getMoney(), 0.001);

        commend.clear();
        commend.add(new LineProduct(5, Product.TOMATO));
        assertFalse(account1.order(commend));
    }

    @Test
    public void getOrderingAddress() throws Exception {
        assertEquals("Marc's account good address","15 av Motte Piquet", account1.getOrderingAddress());
        assertEquals("Flavien's account good address","Taverny", account2.getOrderingAddress());
    }

    @Test
    public void getHistory() throws Exception {
        assertEquals(0, account2.getHistory().size());

        List<LineProduct> commend = new ArrayList<LineProduct>();
        commend.clear();
        commend.add(new LineProduct(5, Product.POTATO));
        account2.order(commend);

        assertEquals(1, account2.getHistory().size());

    }

}