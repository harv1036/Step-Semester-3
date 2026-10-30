import java.util.ArrayList;
import java.util.List;

class HotelBookingSystem {

    public static void main(String[] args) {
        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        boolean available = room101.isAvailable(1, 5);
        System.out.println("Standard Room 101 is " + (available ? "available" : "not available") + " from Jan 1 to Jan 5.");

        Reservation resA = room101.reserve("Customer A", 1, 5);
        if (resA != null) {
            System.out.printf("Reservation confirmed for Customer A, Standard Room 101 (Jan 1-5). Price: $%.2f%n", resA.getPrice());
        }

        Reservation resB = room101.reserve("Customer B", 3, 7);
        if (resB == null) {
            System.out.println("Standard Room 101 is not available from Jan 3 to Jan 7.");
        }

        boolean cancelled = resA.cancel(0);
        if (cancelled) {
            System.out.println("Reservation for Customer A, Standard Room 101 (Jan 1-5) cancelled successfully.");
        }

        Reservation resC = room201.reserve("Customer C", 10, 12);
        if (resC != null) {
            System.out.printf("Reservation confirmed for Customer C, Deluxe Room 201 (Feb 10-12). Price: $%.2f%n", resC.getPrice());
        }
    }
}

abstract class Room {
    private final String number;
    private final List<Reservation> activeReservations;

    Room(String number) {
        this.number = number;
        this.activeReservations = new ArrayList<>();
    }

    String getNumber() { return number; }

    abstract double calculatePrice(int startDay, int endDay);

    boolean isAvailable(int startDay, int endDay) {
        for (Reservation r : activeReservations) {
            if (r.isActive() && r.overlaps(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    Reservation reserve(String customerName, int startDay, int endDay) {
        if (!isAvailable(startDay, endDay)) {
            return null;
        }
        Reservation reservation = new Reservation(customerName, this, startDay, endDay, calculatePrice(startDay, endDay));
        activeReservations.add(reservation);
        return reservation;
    }
}

class StandardRoom extends Room {
    StandardRoom(String number) { super(number); }
    double calculatePrice(int startDay, int endDay) { return (endDay - startDay) * 80.0; }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String number) { super(number); }
    double calculatePrice(int startDay, int endDay) { return (endDay - startDay) * 130.0; }
}

class Suite extends Room {
    Suite(String number) { super(number); }
    double calculatePrice(int startDay, int endDay) { return (endDay - startDay) * 220.0; }
}

class Reservation {
    private final String customerName;
    private final Room room;
    private final int startDay;
    private final int endDay;
    private final double price;
    private boolean active;

    Reservation(String customerName, Room room, int startDay, int endDay, double price) {
        this.customerName = customerName;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
        this.price = price;
        this.active = true;
    }

    boolean isActive() { return active; }
    double getPrice() { return price; }

    boolean overlaps(int otherStart, int otherEnd) {
        return startDay < otherEnd && otherStart < endDay;
    }

    boolean cancel(int currentDay) {
        if (currentDay >= startDay) {
            return false;
        }
        active = false;
        return true;
    }
}
