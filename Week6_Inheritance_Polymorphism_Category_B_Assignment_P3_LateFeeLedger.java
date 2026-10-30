import java.util.Arrays;

public class Week6_Inheritance_Polymorphism_Category_B_Assignment_P3_LateFeeLedger {

    public static void main(String[] args) {
        PremiumMemberA3 p = new PremiumMemberA3("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}

class GymMemberA3 {
    protected String memberId;
    protected int monthlyFee;
    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    public GymMemberA3(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {
        lateFeeHistory[feeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }
}

class PremiumMemberA3 extends GymMemberA3 {
    private String trainerName;

    public PremiumMemberA3(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}
