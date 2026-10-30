public class Week7_Abstraction_Interface_Category_B_Assignment_P4_DigitalClassroomSetup {

    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}

abstract class ClassroomDevice {
    public abstract String operate();
}

interface DeviceChargeable {
    String charge();

    String charge(int minutes);
}

class Tablet extends ClassroomDevice implements DeviceChargeable {
    private final String assetTag;

    public Tablet(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}
