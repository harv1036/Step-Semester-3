import java.util.*;

abstract class PRStaff {
    String name;

    PRStaff(String name) {
        this.name = name;
    }

    abstract double pay();
    abstract String type();

    @Override
    public String toString() {
        return "Payslip[name=" + name + ", type=" + type() + ", pay=" + PayrollRegister.fmt(pay()) + "]";
    }
}

class PRFullTime extends PRStaff {
    double salary;

    PRFullTime(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    double pay() { return salary; }
    String type() { return "FullTime"; }
}

class PRPartTime extends PRStaff {
    double hours;
    double rate;

    PRPartTime(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double pay() { return hours * rate; }
    String type() { return "PartTime"; }
}

class PRIntern extends PRStaff {
    double stipend;

    PRIntern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double pay() { return stipend; }
    String type() { return "Intern"; }
}

class PayrollRegister {

    static String fmt(double v) {
        long l = (long) v;
        return (v == l) ? String.valueOf(l) : String.valueOf(v);
    }

    public static void main(String[] args) {
        String[] input = {
            "FullTime Asha 50000",
            "PartTime Ravi 80 300",
            "Intern Neha 15000"
        };

        List<PRStaff> staffList = new ArrayList<>();

        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            String op = parts[0];

            if (op.equals("FullTime")) {
                staffList.add(new PRFullTime(parts[1], Double.parseDouble(parts[2])));
            } else if (op.equals("PartTime")) {
                staffList.add(new PRPartTime(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
            } else if (op.equals("Intern")) {
                staffList.add(new PRIntern(parts[1], Double.parseDouble(parts[2])));
            }
        }

        double total = 0;
        PRStaff topEarner = null;

        for (PRStaff staff : staffList) {
            System.out.println(staff);
            total += staff.pay();
            if (topEarner == null || staff.pay() > topEarner.pay()) {
                topEarner = staff;
            }
        }

        System.out.println("Total " + fmt(total));
        System.out.println("top earner " + topEarner.name);
    }
}
