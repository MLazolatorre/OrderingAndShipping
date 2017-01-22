package product;

import org.junit.Test;
import org.junit.Assert;


/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class LineProductTest {
    @Test
    public void takeFromStock() throws Exception {
        LineProduct lineProduct = new LineProduct(30, Product.CARROT);

        lineProduct.takeFromStock(15);
        Assert.assertEquals(30 - 15, lineProduct.getQuantity());
    }

}