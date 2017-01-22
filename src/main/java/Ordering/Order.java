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
        Stock stock = Stock.getInstance();
        for (LineProduct lineProduct : products){
            if (!stock.checkProductInStock(lineProduct))
                return false;
        }
        stock.takeProductsInStock(products);
        return true;
    }

    public void statusChanged(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Account getAccount() {
        return account;
    }
}
