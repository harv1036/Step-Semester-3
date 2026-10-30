import java.util.ArrayList;
import java.util.List;

class ShoppingPaymentProcessing {

    public static void main(String[] args) {
        Order orderX = new Order("Customer X");
        orderX.addProduct(new Product("Product A", 2));
        orderX.addProduct(new Product("Product B", 1));
        System.out.println("Order created for Customer X.");

        orderX.pay(new CreditCardPayment(true));

        Order orderY = new Order("Customer Y");
        orderY.pay(new CreditCardPayment(true));

        Order orderZ = new Order("Customer Z");
        orderZ.addProduct(new Product("Product C", 1));
        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPalPayment(false));
    }
}

class Product {
    private final String name;
    private final int quantity;

    Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    private final boolean willSucceed;

    CreditCardPayment(boolean willSucceed) {
        this.willSucceed = willSucceed;
    }

    public boolean processPayment(double amount) { return willSucceed; }
    public String getName() { return "Credit Card"; }
}

class PayPalPayment implements PaymentMethod {
    private final boolean willSucceed;

    PayPalPayment(boolean willSucceed) {
        this.willSucceed = willSucceed;
    }

    public boolean processPayment(double amount) { return willSucceed; }
    public String getName() { return "PayPal"; }
}

class BankTransferPayment implements PaymentMethod {
    private final boolean willSucceed;

    BankTransferPayment(boolean willSucceed) {
        this.willSucceed = willSucceed;
    }

    public boolean processPayment(double amount) { return willSucceed; }
    public String getName() { return "Bank Transfer"; }
}

enum OrderStatus { PENDING, PAID }

class Order {
    private final String customerName;
    private final List<Product> items;
    private OrderStatus status;

    Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    void addProduct(Product product) {
        items.add(product);
    }

    OrderStatus getStatus() { return status; }

    boolean pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }
        System.out.println("Payment initiated via " + method.getName() + " for Order " + customerName.replace("Customer ", "") + ".");
        boolean success = method.processPayment(0);
        if (success) {
            status = OrderStatus.PAID;
            System.out.println("Payment for Order " + customerName.replace("Customer ", "") + " successful. Order status: " + status + ".");
        } else {
            System.out.println("Payment for Order " + customerName.replace("Customer ", "") + " failed. Order status: " + status + ".");
        }
        return success;
    }
}
