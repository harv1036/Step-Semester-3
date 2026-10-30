import java.util.*;

interface VehicleChargeable {
    String bookChargingBay();
}

abstract class CVPVehicle {
    String passNumber;
    String owner;

    CVPVehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract String typeName();
    abstract double passFee();
}

class CVPBike extends CVPVehicle {
    CVPBike(String passNumber, String owner) { super(passNumber, owner); }
    String typeName() { return "Bike"; }
    double passFee() { return 300; }
}

class CVPCar extends CVPVehicle {
    CVPCar(String passNumber, String owner) { super(passNumber, owner); }
    String typeName() { return "Car"; }
    double passFee() { return 1000; }
}

class CVPEBike extends CVPVehicle implements VehicleChargeable {
    CVPEBike(String passNumber, String owner) { super(passNumber, owner); }
    String typeName() { return "EBike"; }
    double passFee() { return 300; }
    public String bookChargingBay() { return passNumber + " charging bay allotted"; }
}

class CVPECar extends CVPVehicle implements VehicleChargeable {
    CVPECar(String passNumber, String owner) { super(passNumber, owner); }
    String typeName() { return "ECar"; }
    double passFee() { return 1000; }
    public String bookChargingBay() { return passNumber + " charging bay allotted"; }
}

class CampusVehiclePass {

    static String fmt(double v) {
        long l = (long) v;
        return (v == l) ? String.valueOf(l) : String.valueOf(v);
    }

    static CVPVehicle create(String type, String passNumber, String owner) {
        switch (type) {
            case "Bike": return new CVPBike(passNumber, owner);
            case "Car": return new CVPCar(passNumber, owner);
            case "EBike": return new CVPEBike(passNumber, owner);
            case "ECar": return new CVPECar(passNumber, owner);
            default: throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }

    public static void main(String[] args) {
        String[] input = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };

        Map<String, CVPVehicle> passes = new LinkedHashMap<>();

        for (String line : input) {
            String[] parts = line.trim().split("\\s+");
            String op = parts[0];

            if (op.equals("PASS")) {
                String type = parts[1];
                String passNumber = parts[2];
                String owner = parts[3];
                CVPVehicle vehicle = create(type, passNumber, owner);
                passes.put(passNumber, vehicle);
                System.out.println(passNumber + " (" + vehicle.typeName() + ") pass fee " + fmt(vehicle.passFee()));
            } else if (op.equals("CHARGE")) {
                String passNumber = parts[1];
                CVPVehicle vehicle = passes.get(passNumber);
                if (vehicle == null) {
                    System.out.println(passNumber + " not found");
                } else if (vehicle instanceof VehicleChargeable) {
                    System.out.println(((VehicleChargeable) vehicle).bookChargingBay());
                } else {
                    System.out.println(passNumber + " rejected: charging unsupported");
                }
            }
        }
    }
}
