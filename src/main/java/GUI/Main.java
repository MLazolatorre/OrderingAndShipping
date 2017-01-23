package GUI;

import builling.BankAccount;
import builling.BankAccountManager;
import customer.Account;
import customer.AccountManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ordering.Order;
import product.LineProduct;
import product.Product;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class Main extends Application {

    Stage window;
    Scene scene;

    Order order;


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        orderingPage();

        window.show();

    }

    public void orderingPage() {
        order = new Order();

        window.setTitle("Select your items");

        HBox layout = new HBox(60);
        layout.setPadding(new Insets(10, 10, 10, 10));

        VBox shoppingCarLayout = new VBox(10);
        shoppingCarLayout.setAlignment(Pos.TOP_RIGHT);
        showSoppingCar(shoppingCarLayout);

        VBox selectionLayout = new VBox(10);
        selectionLayout.setAlignment(Pos.TOP_LEFT);

        for (Product product : Product.values()) {
            HBox hBox = new HBox(10);
            Label productName = new Label(product.getName());

            Button addButton = new Button(" +1 ");
            addButton.setOnAction(e -> {
                order.addAProduct(product);
                showSoppingCar(shoppingCarLayout);
            });

            Button suppButton = new Button(" -1 ");
            suppButton.setOnAction(e -> {
                order.subAProduct(product);
                showSoppingCar(shoppingCarLayout);
            });

            hBox.getChildren().addAll(productName, addButton, suppButton);
            selectionLayout.getChildren().add(hBox);
        }

        Button valid = new Button("Valid");
        valid.setOnAction(e -> {
            if (order.getTotalPrice() == 0) {
                AlertBox.display("Miss click ?", "\nYour shopping car is empty !\n\nSelect something to buy please.");
            } else {
                if (order.checkTheStock())
                    signInOrSignUp();
            }
        });

        selectionLayout.getChildren().add(valid);

        layout.getChildren().addAll(selectionLayout, shoppingCarLayout);
        scene = new Scene(layout, 400, 400);
        window.setScene(scene);
    }

    private void showSoppingCar(Pane shoppingCarLayout) {
        Label total = new Label();
        shoppingCarLayout.getChildren().clear();
        for (LineProduct lineProduct : order.getProducts()) {
            Label newProduct = new Label();
            newProduct.setText(lineProduct.getProduct().getName() + " x" + lineProduct.getQuantity());
            shoppingCarLayout.getChildren().add(newProduct);
        }
        total.setText("Total : " + order.getTotalPrice() + "€");
        shoppingCarLayout.getChildren().add(total);
    }

    public void signInOrSignUp() {

        window.setTitle("Connect your account");

        Button signIn = new Button("sign in");
        signIn.setOnAction(event -> signIn());

        Button signUp = new Button("sign up");
        signUp.setOnAction(event -> signUp());

        HBox layout = new HBox(50);
        layout.setPadding(new Insets(15, 15, 15, 15));
        layout.getChildren().addAll(signIn, signUp);

        Scene scene = new Scene(layout, 200, 50);
        window.setScene(scene);
    }

    private void signIn() {

        window.setTitle("Sign in");

        AccountManager accountManager = AccountManager.getInstance();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(8);

        //-------User name-------
        Label userNameLabel = new Label("User name :");
        GridPane.setConstraints(userNameLabel, 0, 0);

        TextField userNameInput = new TextField();
        GridPane.setConstraints(userNameInput, 1, 0);

        //-------Password-------
        Label passwordLabel = new Label("Password :");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");
        GridPane.setConstraints(passwordInput, 1, 1);

        //-------button-------
        Button button = new Button("Valid");
        button.setOnAction(e -> {
            Account account = accountManager.connection(userNameInput.getText(), passwordInput.getText());
            if (account == null) {
                AlertBox.display("Connecting error", "This account doesn't exist.\n" +
                        "Please check you id.");
            } else {
                order.checkBankAccountAndSent();
            }
        });
        GridPane.setConstraints(button, 1, 2);

        grid.getChildren().addAll(userNameLabel, userNameInput, passwordLabel, passwordInput, button);
        Scene scene = new Scene(grid, 300, 150);
        window.setScene(scene);
    }

    public void signUp() {

        window.setTitle("Sign Up");

        AccountManager accountManager = AccountManager.getInstance();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(8);


        //-------First name-------
        Label firstNameLabel = new Label();
        firstNameLabel.setText("First Name : ");
        GridPane.setConstraints(firstNameLabel, 0, 0);

        TextField firstNameInput = new TextField();
        GridPane.setConstraints(firstNameInput, 1, 0);

        //-------Second name-------
        Label secondNameLabel = new Label();
        secondNameLabel.setText("Second Name : ");
        GridPane.setConstraints(secondNameLabel, 0, 1);

        TextField secondNameInput = new TextField();
        GridPane.setConstraints(secondNameInput, 1, 1);

        //-------Address-------
        Label addressLabel = new Label();
        addressLabel.setText("Address : ");
        GridPane.setConstraints(addressLabel, 0, 2);

        TextField addressInput = new TextField();
        GridPane.setConstraints(addressInput, 1, 2);

        //-------Phone-------
        Label phoneLabel = new Label();
        phoneLabel.setText("Phone : ");
        GridPane.setConstraints(phoneLabel, 0, 3);

        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 3);

        //-------User Name-------
        Label userNameLabel = new Label();
        userNameLabel.setText("User Name : ");
        GridPane.setConstraints(userNameLabel, 0, 4);

        TextField userNameInput = new TextField();
        GridPane.setConstraints(userNameInput, 1, 4);

        //-------Password-------
        Label passwordLabel = new Label();
        passwordLabel.setText("Password : ");
        GridPane.setConstraints(passwordLabel, 0, 5);

        TextField passwordInput = new TextField();
        GridPane.setConstraints(passwordInput, 1, 5);

        //-------button-------
        Button button = new Button();
        button.setText("  Valid  ");
        button.setOnAction(e -> {
            if (isInt(phoneInput)) {
                Account newAccount = accountManager.createAccount(addressInput.getText(), Integer.parseInt(phoneInput.getText()),
                        firstNameInput.getText(), secondNameInput.getText(), userNameInput.getText(), passwordInput.getText());

                order.addAccount(newAccount);
                registerYourCreditCard();
            }
        });
        GridPane.setConstraints(button, 2, 5);

        grid.getChildren().addAll(firstNameLabel, firstNameInput, secondNameLabel, secondNameInput, addressLabel,
                addressInput, phoneLabel, phoneInput, userNameLabel, userNameInput, passwordLabel, passwordInput, button);
        scene = new Scene(grid, 450, 225);
        window.setScene(scene);
    }

    private void registerYourCreditCard() {
        window.setTitle("Register your credit card");

        BankAccountManager bankAccountManager = BankAccountManager.getInstance();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(8);

        //-------User name-------
        Label bankAccountLabel = new Label("Bank account :");
        GridPane.setConstraints(bankAccountLabel, 0, 0);

        TextField bankAccountInput = new TextField();
        GridPane.setConstraints(bankAccountInput, 1, 0);

        //-------Password-------
        Label secretCodeLabel = new Label("Secret code :");
        GridPane.setConstraints(secretCodeLabel, 0, 1);

        TextField secretCodeInput = new TextField();
        secretCodeInput.setPromptText("Secret code");
        GridPane.setConstraints(secretCodeInput, 1, 1);

        //-------button-------
        Button button = new Button("Valid");
        button.setOnAction(e -> {
            BankAccount account = bankAccountManager.findBankAccount(Integer.parseInt(bankAccountInput.getText()), Integer.parseInt(secretCodeInput.getText()));
            if (isInt(bankAccountInput) && isInt(secretCodeInput)) {
                if (account != null && account.dropMoney(order.getTotalPrice())) {
                    AlertBox.display("Commend finished", "Your commend is going to arrive in 2 days.\n" +
                            "Thank you.");
                    orderingPage();
                }
            }
        });
        GridPane.setConstraints(button, 1, 2);

        grid.getChildren().addAll(bankAccountLabel, bankAccountInput, secretCodeLabel, secretCodeInput, button);
        Scene scene = new Scene(grid, 300, 150);
        window.setScene(scene);
    }

    private boolean isInt(TextField textField) {
        try {
            int phone = Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            textField.setText("You should enter a Integer");
            return false;
        }
    }
}




















