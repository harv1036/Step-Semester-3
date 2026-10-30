public class Week7_Abstraction_Interface_Category_B_Assignment_P1_MorningWakeUpCircuit {

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        ringAll(new Ringable[]{ a, d });
    }

    static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }
}

interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private final String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {
    private final String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}
