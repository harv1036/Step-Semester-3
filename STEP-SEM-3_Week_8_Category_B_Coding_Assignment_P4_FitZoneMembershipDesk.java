class FitZoneMembershipDesk {

    public static void main(String[] args) {
        Membership asha = new Membership("Asha", new QuarterlyPlan());
        System.out.printf("Quarterly membership created for Asha. Fee: Rs.%.2f. Status: %s.%n",
                asha.getFee(), asha.getStatus());

        Membership ravi = new Membership("Ravi", new MonthlyPlan());
        System.out.printf("Monthly membership created for Ravi. Fee: Rs.%.2f. Status: %s.%n",
                ravi.getFee(), ravi.getStatus());

        if (asha.checkIn()) {
            System.out.println("Asha checked in successfully.");
        }

        asha.freeze();
        System.out.println("Asha's membership frozen. Status: " + asha.getStatus() + ".");

        if (!asha.checkIn()) {
            System.out.println("Check-in denied: Asha's membership is " + asha.getStatus() + ".");
        }

        ravi.expire();
        System.out.println("Ravi's membership expired. Status: " + ravi.getStatus() + ".");

        if (!ravi.freeze()) {
            System.out.println("Cannot freeze an Expired membership.");
        }
    }
}

abstract class MembershipPlan {
    private static final double BASE_RATE = 1000.0;

    abstract int getMonths();
    abstract double getDiscount();
    abstract String getName();

    double calculateFee() {
        return BASE_RATE * getMonths() * (1 - getDiscount());
    }
}

class MonthlyPlan extends MembershipPlan {
    int getMonths() { return 1; }
    double getDiscount() { return 0.0; }
    String getName() { return "Monthly"; }
}

class QuarterlyPlan extends MembershipPlan {
    int getMonths() { return 3; }
    double getDiscount() { return 0.10; }
    String getName() { return "Quarterly"; }
}

class AnnualPlan extends MembershipPlan {
    int getMonths() { return 12; }
    double getDiscount() { return 0.25; }
    String getName() { return "Annual"; }
}

enum MembershipStatus { ACTIVE, FROZEN, EXPIRED }

class Membership {
    private final String memberName;
    private final MembershipPlan plan;
    private final double fee;
    private MembershipStatus status;

    Membership(String memberName, MembershipPlan plan) {
        this.memberName = memberName;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.ACTIVE;
    }

    double getFee() { return fee; }
    MembershipStatus getStatus() { return status; }

    boolean checkIn() {
        return status == MembershipStatus.ACTIVE;
    }

    boolean freeze() {
        if (status != MembershipStatus.ACTIVE) {
            return false;
        }
        status = MembershipStatus.FROZEN;
        return true;
    }

    boolean unfreeze() {
        if (status != MembershipStatus.FROZEN) {
            return false;
        }
        status = MembershipStatus.ACTIVE;
        return true;
    }

    void expire() {
        status = MembershipStatus.EXPIRED;
    }
}
