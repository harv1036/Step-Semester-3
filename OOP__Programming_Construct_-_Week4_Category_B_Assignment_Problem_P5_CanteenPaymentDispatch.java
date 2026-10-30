class CanteenPaymentDispatch {

    double totalCollected = 0;

    void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            double charged = cardPayment.payWithProcessingFee(amount);
            totalCollected += charged;
        } else {
            payment.pay(amount);
            totalCollected += amount;
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };
        double[] amounts = {100, 50, 200, 75, 120};

        CanteenPaymentDispatch dispatcher = new CanteenPaymentDispatch();
        for (int i = 0; i < payments.length; i++) {
            dispatcher.processTransaction(payments[i], amounts[i]);
        }
        System.out.println("Total Collected: Rs " + dispatcher.totalCollected);
    }
}

class Payment {
    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {
    double payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);
        System.out.println("Charged (card, incl. fee): Rs " + total);
        return total;
    }
}
