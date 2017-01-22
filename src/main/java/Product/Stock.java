package product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public class Stock {

    private static List<LineProduct> products;

    private static Stock ourInstance;

    public static Stock getInstance() {
        if (ourInstance == null){
            ourInstance = new Stock();
        }
        return ourInstance;
    }

    private Stock() {
        products = new ArrayList<LineProduct>();
        for (Product prod : Product.values()) {
            products.add(new LineProduct(prod.getInitialQuantity(),prod));
        }
    }

    public static void takeProductsInStock (List<LineProduct> commend){
        for (LineProduct LineProductCommended : commend){

            Product productCommended = LineProductCommended.getProduct();
            int quantityCommended = LineProductCommended.getQuantity();

            for (LineProduct LineProductInStock : products){
                Product productInStock = LineProductInStock.getProduct();

                if (productCommended.name().equals(productInStock.name())){
                    LineProductInStock.takeFromStock(quantityCommended);
                }
            }
        }
    }

    public static boolean checkProductInStock (LineProduct commend){
        Product productCommended = commend.getProduct();
        int quantityCommended = commend.getQuantity();

        for (LineProduct lineProductInStock : products){
            Product productInStock = lineProductInStock.getProduct();
            int quantityInStock = lineProductInStock.getQuantity();

            if (productCommended.name().equals(productInStock.name()))
                return quantityCommended <= quantityInStock;
        }
        return false;
    }

}
