public class Week6_Inheritance_Polymorphism_Category_B_Assignment_P2_ThreeTiersMembership {

    static String classifyGeneration(GymMemberA2 member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMemberA2) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMemberA2) {
            return "Direct subclass";
        } else {
            return "Base member";
        }
    }

    static int getTotalSessionsAttended(GymMemberA2[] members) {
        int total = 0;
        for (GymMemberA2 m : members) {
            total += m.getSessionsAttended();
        }
        return total;
    }

    public static void main(String[] args) {
        GymMemberA2 m1 = new GymMemberA2("MEM1", 1000);
        PremiumMemberA2 m2 = new PremiumMemberA2("MEM2", 2000, "Coach Riya");
        EliteMember m3 = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMemberA2 m4 = new GroupClassMemberA2("MEM4", 1500, "Zumba");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println(classifyGeneration(m3));
        System.out.println(classifyGeneration(m4));

        m2.attendSession();
        m2.attendSession();
        m2.attendSession();
        m3.attendSession();
        m3.attendSession();
        m4.attendSession();
        m4.attendSession();
        m4.attendSession();
        m4.attendSession();

        System.out.println(getTotalSessionsAttended(new GymMemberA2[]{m2, m3, m4}));
    }
}

class GymMemberA2 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMemberA2(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }
}

class PremiumMemberA2 extends GymMemberA2 {
    protected String trainerName;

    public PremiumMemberA2(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends PremiumMemberA2 {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    String displayInfo() {
        return "Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMemberA2 extends GymMemberA2 {
    private String className;

    public GroupClassMemberA2(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}
