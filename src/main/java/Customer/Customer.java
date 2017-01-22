package customer;

/**
 * Created by MARC LAZOLA TORRE on 21/01/2017.
 */
public class Customer {

    private String address;
    private int phone;
    private String firstName;
    private String secondName;

    public Customer(String address, int phone, String firstName, String secondName) {
        this.address = address;
        this.phone = phone;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
