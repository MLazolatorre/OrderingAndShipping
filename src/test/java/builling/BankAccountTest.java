package builling;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class BankAccountTest {

    private BankAccount bankAccount;

    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccount(123456789, 1234, 2000);
    }

    @Test
    public void connection() throws Exception {
        Assert.assertTrue(bankAccount.connection(123456789,1234));
        Assert.assertFalse(bankAccount.connection(123456780,1234));
        Assert.assertFalse(bankAccount.connection(123456789,1233));
    }

    @Test
    public void dropMoney() throws Exception {
        Assert.assertEquals(2000, bankAccount.getMoney(), 0.01);
        Assert.assertTrue(bankAccount.dropMoney(1000));
        Assert.assertEquals(1000, bankAccount.getMoney(), 0.01);
        Assert.assertTrue(bankAccount.dropMoney(1000));
        Assert.assertEquals(0, bankAccount.getMoney(), 0.01);
        Assert.assertFalse(bankAccount.dropMoney(1000));
        Assert.assertEquals(0, bankAccount.getMoney(), 0.01);
    }

}