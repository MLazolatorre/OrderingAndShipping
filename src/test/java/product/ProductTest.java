package product;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class ProductTest {

    @Test
    public void getInitialQuantity() throws Exception {
        int initialCarrotQuantity = 20;
        int initialTomatoQuantity = 10;
        int initialPotatoQuantity = 30;

        assertEquals(initialCarrotQuantity, Product.CARROT.getInitialQuantity());
        assertEquals(initialPotatoQuantity, Product.POTATO.getInitialQuantity());
        assertEquals(initialTomatoQuantity, Product.TOMATO.getInitialQuantity());
    }

    @Test
    public void getPrice() throws Exception {
        double carrotPrice = 1.25;
        double tomatoPrice = 2.00;
        double potatoPrice = 3.10;

        assertEquals(carrotPrice, Product.CARROT.getPrice(), 0.01);
        assertEquals(tomatoPrice, Product.TOMATO.getPrice(), 0.01);
        assertEquals(potatoPrice, Product.POTATO.getPrice(), 0.01);
    }

}