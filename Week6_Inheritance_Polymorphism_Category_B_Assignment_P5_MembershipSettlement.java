public class Week6_Inheritance_Polymorphism_Category_B_Assignment_P5_MembershipSettlement {

    public static void main(String[] args) {
        GymMemberA5 m1 = new GymMemberA5(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(GymMemberA5.getMembersEnrolled());

        System.out.println(GymMemberA5.isValidReferralCode("G45B"));
        System.out.println(GymMemberA5.isValidReferralCode("G4B"));
        System.out.println(GymMemberA5.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        GymMemberA5[] batch = {
            new GroupClassMemberA5(1500, "Zumba"),
            null,
            new GymMemberA5(1000)
        };
        System.out.println(GymMemberA5.processWeeklyCheckIn(batch));
    }
}

class GymMemberA5 {
    private static int counter = 2000;
    private static int membersEnrolled = 0;

    public final String membershipNumber;
    protected int monthlyFee;
    private int feesPaid;

    public GymMemberA5(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        membersEnrolled++;
        counter++;
        this.membershipNumber = "GYM-" + counter;
    }

    void payFee(int amount) {
        feesPaid += amount;
    }

    void payFee(int amount, String mode) {
        payFee(amount);
    }

    int getFeesPaid() {
        return feesPaid;
    }

    static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }

    static String processWeeklyCheckIn(GymMemberA5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        for (GymMemberA5 m : members) {
            if (m == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (m instanceof GroupClassMemberA5) {
                group++;
            } else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
}

class GroupClassMemberA5 extends GymMemberA5 {
    private String className;

    public GroupClassMemberA5(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}
