import java.util.*;

abstract class BAWAccount {
    String id;
    double balance;

    BAWAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract String withdraw(double amount);
}

class BAWSavingsAccount extends BAWAccount {
    BAWSavingsAccount(String id, double balance) {
        super(id, balance);
    }

    String withdraw(double amount) {
        if (balance - amount < 1000) {
            return "rejected: minimum balance 1000";
        }
        balance -= amount;
        return null;
    }
}

class BAWCurrentAccount extends BAWAccount {
    BAWCurrentAccount(String id, double balance) {
        super(id, balance);
    }

    String withdraw(double amount) {
        if (balance - amount < -5000) {
            return "rejected: overdraft limit 5000";
        }
        balance -= amount;
        return null;
    }
}

class BankAccountWithdrawal {

    static String fmt(double v) {
        long l = (long) v;
        return (v == l) ? String.valueOf(l) : String.valueOf(v);
    }

    public static void main(String[] args) {
        String[] input = {
            "Savings S1 5000",
            "WITHDRAW S1 4500",
            "Current C1 2000",
            "WITHDRAW C1 6000",
            "WITHDRAW S1 3000",
            "WITHDRAW C1 2000"
        };

        Map<String, BAWAccount> accounts = new LinkedHashMap<>();

        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            String op = parts[0];

            if (op.equals("Savings")) {
                accounts.put(parts[1], new BAWSavingsAccount(parts[1], Double.parseDouble(parts[2])));
            } else if (op.equals("Current")) {
                accounts.put(parts[1], new BAWCurrentAccount(parts[1], Double.parseDouble(parts[2])));
            } else if (op.equals("WITHDRAW")) {
                String id = parts[1];
                double amount = Double.parseDouble(parts[2]);
                BAWAccount acc = accounts.get(id);
                if (acc == null) {
                    System.out.println("Account not found");
                    continue;
                }
                String rejection = acc.withdraw(amount);
                if (rejection == null) {
                    System.out.println(id + " balance " + fmt(acc.balance));
                } else {
                    System.out.println(id + " " + rejection);
                }
            }
        }
    }
}
