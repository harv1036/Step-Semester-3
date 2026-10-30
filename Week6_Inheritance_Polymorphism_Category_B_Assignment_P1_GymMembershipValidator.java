public class Week6_Inheritance_Polymorphism_Category_B_Assignment_P1_GymMembershipValidator {

    public static void main(String[] args) {
        try {
            new GymMemberA1("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PremiumMemberA1 p = new PremiumMemberA1("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended());

        String result = GymMemberA1.signUpBatch(new String[]{"MEM1", "GM1", "MEM2", " ", "MEM3"}, 1000);
        System.out.println(result);
    }
}

class GymMemberA1 {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMemberA1(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: " + memberId);
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                new GymMemberA1(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }
}

class PremiumMemberA1 extends GymMemberA1 {
    private String trainerName;

    public PremiumMemberA1(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
}
