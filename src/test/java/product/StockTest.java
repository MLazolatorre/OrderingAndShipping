package product;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class StockTest {
    private Stock stock;

    @Before
    public void setUp() throws Exception {
        stock = Stock.getInstance();
    }

    @Test
    public void takeProductsInStock() throws Exception {
        int initialNumberOfCarrot = stock.getQuantityInStock(Product.CARROT);
        int initialNumberOfPotato = stock.getQuantityInStock(Product.POTATO);

        int numberOfCarrotDropFromStock = 12;
        int numberOfPotatoDropFromStock = 30;

        List<LineProduct> commend = new ArrayList<LineProduct>();
        commend.add(new LineProduct(numberOfCarrotDropFromStock, Product.CARROT));
        commend.add(new LineProduct(numberOfPotatoDropFromStock, Product.POTATO));

        stock.takeProductsInStock(commend);

        int numberOfCarrotExpectInTheStock = initialNumberOfCarrot - numberOfCarrotDropFromStock;
        int numberOfPotatoExpectInTheStock = initialNumberOfPotato - numberOfPotatoDropFromStock;

        Assert.assertEquals(numberOfCarrotExpectInTheStock, stock.getQuantityInStock(Product.CARROT));
        Assert.assertEquals(numberOfPotatoExpectInTheStock, stock.getQuantityInStock(Product.POTATO));
    }

    @Test
    public void checkProductInStock() throws Exception {
        LineProduct firstCommend = new LineProduct(10, Product.TOMATO);
        LineProduct secondCommend = new LineProduct(50,Product.TOMATO);

        Assert.assertTrue(stock.checkProductInStock(firstCommend));
        Assert.assertFalse(stock.checkProductInStock(secondCommend));

    }

}