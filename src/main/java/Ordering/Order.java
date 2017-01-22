package ordering;

import customer.Account;
import product.LineProduct;
import product.Product;
import product.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public class Order {

    private static int idCounter = 0;
    private int id;

    private Date date;
    private OrderStatus status;
    private List<LineProduct> products;
    private double totalPrice;

    private Account account;

    public Order (List<LineProduct> commend, Account account){
        idCounter++;
        id = idCounter;

        date = new Date();
        status = OrderStatus.New;
        products = new ArrayList<LineProduct>(commend);
        totalPrice = 0;

        this.account = account;

        for (LineProduct lineProduct : products){
            Product product = lineProduct.getProduct();
            int quantity = lineProduct.getQuantity();
            double priceProduct = product.getPrice();

            totalPrice += quantity * priceProduct;
        }
    }

    public boolean makeTheOrder (){
        for (LineProduct lineProduct : products){
            if (!Stock.checkProductInStock(lineProduct))
                return false;
        }
        Stock.takeProductsInStock(products);
        return true;
    }


}
