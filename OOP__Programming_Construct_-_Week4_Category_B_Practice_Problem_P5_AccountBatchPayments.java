class AccountBatchPayments {

    int hostelCount = 0;
    int dayScholarCount = 0;

    void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            ((HostelFeeAccount) account).payInInstallments(amount);
            hostelCount++;
        } else {
            account.payInOneGo(amount);
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };
        double amount = 60000;

        AccountBatchPayments batch = new AccountBatchPayments();
        for (FeeAccount account : accounts) {
            batch.processPayment(account, amount);
        }
        System.out.println("Hostel accounts processed: " + batch.hostelCount
                + " | Day-scholar accounts processed: " + batch.dayScholarCount);
    }
}

class FeeAccount {
    void payInOneGo(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    void payInInstallments(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}
