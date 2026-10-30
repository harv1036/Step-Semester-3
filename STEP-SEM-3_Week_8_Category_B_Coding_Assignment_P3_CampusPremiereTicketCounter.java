import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CampusPremiereTicketCounter {

    public static void main(String[] args) {
        Show show = new Show("7 PM Show");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        Booking ashaBooking = show.book("Asha", List.of(a1, a2, f5));
        if (ashaBooking != null) {
            System.out.println("Booking confirmed for Asha: A1, A2, F5. Total: Rs." + ashaBooking.getTotal());
        }

        Booking raviAttempt = show.book("Ravi", List.of(a2));
        if (raviAttempt == null) {
            System.out.println("Seat A2 is already booked for this show.");
        }

        Booking raviBooking = show.book("Ravi", List.of(r1));
        if (raviBooking != null) {
            System.out.println("Booking confirmed for Ravi: R1. Total: Rs." + raviBooking.getTotal());
        }

        ashaBooking.cancel();
        System.out.println("Asha's booking cancelled. Seats A1, A2, F5 released.");

        Booking nehaBooking = show.book("Neha", List.of(a2));
        if (nehaBooking != null) {
            System.out.println("Booking confirmed for Neha: A2. Total: Rs." + nehaBooking.getTotal());
        }
    }
}

abstract class Seat {
    private final String code;

    Seat(String code) {
        this.code = code;
    }

    String getCode() { return code; }
    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String code) { super(code); }
    double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    PremiumSeat(String code) { super(code); }
    double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String code) { super(code); }
    double getPrice() { return 400.0; }
}

class Booking {
    private final String customerName;
    private final Show show;
    private final List<Seat> seats;
    private boolean cancelled;

    Booking(String customerName, Show show, List<Seat> seats) {
        this.customerName = customerName;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.cancelled = false;
    }

    double getTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }

    void cancel() {
        if (cancelled) {
            return;
        }
        cancelled = true;
        show.releaseSeats(seats);
    }
}

class Show {
    private static final int MAX_SEATS_PER_BOOKING = 6;

    private final String name;
    private final Set<String> bookedSeatCodes;

    Show(String name) {
        this.name = name;
        this.bookedSeatCodes = new HashSet<>();
    }

    Booking book(String customerName, List<Seat> seats) {
        if (seats.isEmpty() || seats.size() > MAX_SEATS_PER_BOOKING) {
            return null;
        }
        for (Seat seat : seats) {
            if (bookedSeatCodes.contains(seat.getCode())) {
                return null;
            }
        }
        for (Seat seat : seats) {
            bookedSeatCodes.add(seat.getCode());
        }
        return new Booking(customerName, this, seats);
    }

    void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeatCodes.remove(seat.getCode());
        }
    }
}
