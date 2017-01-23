package ordering;

import GUI.AlertBox;
import builling.BankAccountManager;
import customer.Account;
import product.LineProduct;
import product.Product;
import product.Stock;
import shipping.ShippingOffice;

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

    public Order(){
        idCounter++;
        id = idCounter;

        date = new Date();
        status = OrderStatus.New;
        products = new ArrayList<LineProduct>();
        totalPrice = 0;
    }

    public Order(List<LineProduct> commend) {
        idCounter++;
        id = idCounter;

        date = new Date();
        status = OrderStatus.New;
        products = new ArrayList<LineProduct>(commend);
        totalPrice = 0;

        for (LineProduct lineProduct : products) {
            Product product = lineProduct.getProduct();
            int quantity = lineProduct.getQuantity();
            double priceProduct = product.getPrice();

            totalPrice += quantity * priceProduct;
        }
    }

    public Order(List<LineProduct> commend, Account account) {
        idCounter++;
        id = idCounter;

        date = new Date();
        status = OrderStatus.New;
        products = new ArrayList<LineProduct>(commend);
        totalPrice = 0;

        for (LineProduct lineProduct : products) {
            Product product = lineProduct.getProduct();
            int quantity = lineProduct.getQuantity();
            double priceProduct = product.getPrice();

            totalPrice += quantity * priceProduct;
        }
        this.account = account;
    }


    public boolean makeTheOrder() {

        Stock stock = Stock.getInstance();
        BankAccountManager bankAccountManager = BankAccountManager.getInstance();

        for (LineProduct lineProduct : products) {
            if (!stock.checkProductInStock(lineProduct))
                return false;
        }
        stock.takeProductsInStock(products);
        statusChanged(OrderStatus.StockChecked);

        if (!bankAccountManager.dropMoneyFromAccount(account.getBankAccount(), totalPrice))
            return false;
        statusChanged(OrderStatus.Builling);

        ShippingOffice.sendTheOrder(this);

        return true;
    }

    public boolean checkTheStock (){
        Stock stock = Stock.getInstance();

        for (LineProduct lineProduct : products) {
            if (!stock.checkProductInStock(lineProduct)) {
                AlertBox.display("Sock default", "We don't have enough " + lineProduct.getProduct().getName());
                return false;
            }
        }
        stock.takeProductsInStock(products);
        statusChanged(OrderStatus.StockChecked);
        return true;
    }

    public boolean checkBankAccountAndSent(){
        BankAccountManager bankAccountManager = BankAccountManager.getInstance();

        if (!bankAccountManager.dropMoneyFromAccount(account.getBankAccount(), totalPrice))
            return false;
        statusChanged(OrderStatus.Builling);

        ShippingOffice.sendTheOrder(this);

        return true;
    }

    public void statusChanged(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for (LineProduct lineProduct : products){
            totalPrice += lineProduct.getQuantity()*lineProduct.getProduct().getPrice();
        }
        return totalPrice;
    }

    public Account getAccount() {
        return account;
    }

    public void addAccount(Account account) {
        if (this.account == null) {
            this.account = account;
        }
    }

    public boolean addAProduct (Product p){
        for (LineProduct lineProduct : products){
            if (lineProduct.getProduct().equals(p)){
                lineProduct.addOne();
                return false;
            }
        }
        System.out.println("New");
        products.add(new LineProduct(1, p));
        return true;
    }

    public void subAProduct(Product p){
        for (LineProduct lineProduct : products){
            if (lineProduct.getProduct().equals(p)){
                if (!lineProduct.subOne()){
                    products.remove(lineProduct);
                    return;
                }
            }
        }
    }

    public List<LineProduct> getProducts() {
        return products;
    }

    public void showProduct (){
        for (LineProduct product : products){
            System.out.println(product.getProduct().getName() + " x" + product.getQuantity());
        }
        System.out.println();
    }
}
