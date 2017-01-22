package product;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public enum Product {
    ;

    private final String name;
    private final String image;
    private final int price;

    private final int initialQuantity;


    Product (String name, String image, int initialQuantity, int price){
        this.image = image;
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.price = price;
    }


    public int getInitialQuantity (){
        return initialQuantity;
    }

    public int getPrice() {
        return price;
    }
}
