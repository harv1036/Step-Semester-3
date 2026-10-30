class HostelLaundryQueue {

    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle c1 = m1.startWash("Asha", new QuickWash());
        if (c1 != null) {
            System.out.printf("Quick wash started on M1 for Asha (%d min). Charge: Rs.%.2f%n",
                    c1.getWashType().getDurationMinutes(), c1.getWashType().getCharge());
        }

        WashCycle attempt = m1.startWash("Ravi", new HeavyWash());
        if (attempt == null) {
            System.out.println("Machine M1 is currently busy.");
        }

        WashCycle c2 = m2.startWash("Ravi", new HeavyWash());
        if (c2 != null) {
            System.out.printf("Heavy wash started on M2 for Ravi (%d min). Charge: Rs.%.2f%n",
                    c2.getWashType().getDurationMinutes(), c2.getWashType().getCharge());
        }

        m1.completeCycle();
        System.out.println("M1 cycle completed. M1 is now free.");

        WashCycle c3 = m1.startWash("Neha", new NormalWash());
        if (c3 != null) {
            System.out.printf("Normal wash started on M1 for Neha (%d min). Charge: Rs.%.2f%n",
                    c3.getWashType().getDurationMinutes(), c3.getWashType().getCharge());
        }
    }
}

abstract class WashType {
    abstract int getDurationMinutes();
    abstract double getCharge();
}

class QuickWash extends WashType {
    int getDurationMinutes() { return 30; }
    double getCharge() { return 20.0; }
}

class NormalWash extends WashType {
    int getDurationMinutes() { return 45; }
    double getCharge() { return 30.0; }
}

class HeavyWash extends WashType {
    int getDurationMinutes() { return 60; }
    double getCharge() { return 45.0; }
}

class WashCycle {
    private final String studentName;
    private final WashingMachine machine;
    private final WashType washType;

    WashCycle(String studentName, WashingMachine machine, WashType washType) {
        this.studentName = studentName;
        this.machine = machine;
        this.washType = washType;
    }

    WashType getWashType() { return washType; }
    String getStudentName() { return studentName; }
    WashingMachine getMachine() { return machine; }
}

class WashingMachine {
    private final String id;
    private boolean busy;

    WashingMachine(String id) {
        this.id = id;
        this.busy = false;
    }

    boolean isBusy() { return busy; }
    String getId() { return id; }

    WashCycle startWash(String studentName, WashType washType) {
        if (busy) {
            return null;
        }
        busy = true;
        return new WashCycle(studentName, this, washType);
    }

    void completeCycle() {
        busy = false;
    }
}
