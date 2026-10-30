public class Week7_Abstraction_Interface_Category_B_Assignment_P5_SkylineDeliveryFleet {

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }

    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            return ((Trackable) o).getLocation();
        }
        return "Tracking not available";
    }
}

abstract class Drone {
    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    private final String id;

    public DeliveryDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " flying to deliver package";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    private final String id;

    public ScoutDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " scouting ahead";
    }
}

class GroundRobot implements Trackable {
    private final String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
