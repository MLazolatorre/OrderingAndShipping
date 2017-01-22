package product;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public class LineProduct {

    private int quantity;
    private Product product;

    public LineProduct(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void takeFromStock (int quantity){
        this.quantity -= quantity;
    }

    public void addOne (){
        quantity ++;
    }

    public boolean subOne(){
        quantity --;
        if (quantity == 0){
            return false;
        }
        return true;
    }
}
