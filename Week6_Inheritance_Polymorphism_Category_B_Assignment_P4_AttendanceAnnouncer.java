public class Week6_Inheritance_Polymorphism_Category_B_Assignment_P4_AttendanceAnnouncer {

    static String batchPrint(GymMemberA4[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMemberA4 m : members) {
            sb.append(m.displayInfo());
            if (m instanceof PremiumMemberA4) {
                PremiumMemberA4 pm = (PremiumMemberA4) m;
                sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GymMemberA4[] members = {
            new GymMemberA4("MEM6", 1000),
            new PremiumMemberA4("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(members));

        GymMemberA4 plain = new GymMemberA4("MEM8", 1000);
        try {
            PremiumMemberA4 bad = (PremiumMemberA4) plain;
            System.out.println(bad.getTrainerName());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}

class GymMemberA4 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMemberA4(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMemberA4 extends GymMemberA4 {
    private String trainerName;

    public PremiumMemberA4(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    String getTrainerName() {
        return trainerName;
    }

    @Override
    String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
    }
}
