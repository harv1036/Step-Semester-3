class VehicleRentalSystem {

    public static void main(String[] args) {
        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Rental rental1 = sedanA.rent("Customer 1", 3);
        if (rental1 != null) {
            System.out.printf("Sedan A rented successfully by Customer 1. Rental charge: $%.2f%n", rental1.getCharge());
        }

        Rental rental2 = sedanA.rent("Customer 2", 2);
        if (rental2 == null) {
            System.out.println("Sedan A is currently unavailable.");
        }

        sedanA.returnVehicle();
        System.out.println("Sedan A returned by Customer 1.");

        Rental rental3 = suvB.rent("Customer 3", 5);
        if (rental3 != null) {
            System.out.printf("SUV B rented successfully by Customer 3. Rental charge: $%.2f%n", rental3.getCharge());
        }
    }
}

abstract class Vehicle {
    private final String id;
    private boolean rented;

    Vehicle(String id) {
        this.id = id;
        this.rented = false;
    }

    String getId() { return id; }
    boolean isRented() { return rented; }

    abstract double calculateCharge(int days);

    Rental rent(String customerName, int days) {
        if (rented) {
            return null;
        }
        rented = true;
        return new Rental(customerName, this, days, calculateCharge(days));
    }

    void returnVehicle() {
        rented = false;
    }
}

class Sedan extends Vehicle {
    Sedan(String id) { super(id); }
    double calculateCharge(int days) { return days * 40.0; }
}

class SUV extends Vehicle {
    SUV(String id) { super(id); }
    double calculateCharge(int days) { return days * 60.0; }
}

class Truck extends Vehicle {
    Truck(String id) { super(id); }
    double calculateCharge(int days) { return days * 90.0; }
}

class Rental {
    private final String customerName;
    private final Vehicle vehicle;
    private final int days;
    private final double charge;

    Rental(String customerName, Vehicle vehicle, int days, double charge) {
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.days = days;
        this.charge = charge;
    }

    double getCharge() { return charge; }
}
