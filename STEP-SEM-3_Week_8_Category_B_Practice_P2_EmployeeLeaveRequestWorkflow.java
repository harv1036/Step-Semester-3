class EmployeeLeaveRequestWorkflow {

    public static void main(String[] args) {
        LeaveEmployee john = new FullTimeEmployee("John");
        LeaveEmployee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest = john.submitLeave("Jan 1-5", 5);
        System.out.println("Leave request submitted for John (Jan 1-5). Status: " + johnRequest.getStatus() + ".");

        johnRequest.approve();
        System.out.println("John's leave request (Jan 1-5) approved. Status: " + johnRequest.getStatus() + ".");

        LeaveRequest janeRequest = jane.submitLeave("Feb 10-11", 2);
        System.out.println("Leave request submitted for Jane (Feb 10-11). Status: " + janeRequest.getStatus() + ".");

        janeRequest.reject();
        System.out.println("Jane's leave request (Feb 10-11) rejected. Status: " + janeRequest.getStatus() + ".");

        boolean reverted = johnRequest.revertToPending();
        if (!reverted) {
            System.out.println("Cannot change leave request status from Approved to Pending.");
        }
    }
}

enum LeaveStatus { PENDING, APPROVED, REJECTED }

abstract class LeaveEmployee {
    private final String name;

    LeaveEmployee(String name) {
        this.name = name;
    }

    String getName() { return name; }

    abstract boolean isEligible(int days);

    LeaveRequest submitLeave(String period, int days) {
        return new LeaveRequest(this, period, days);
    }
}

class FullTimeEmployee extends LeaveEmployee {
    FullTimeEmployee(String name) { super(name); }
    boolean isEligible(int days) { return days <= 15; }
}

class PartTimeEmployee extends LeaveEmployee {
    PartTimeEmployee(String name) { super(name); }
    boolean isEligible(int days) { return days <= 7; }
}

class Contractor extends LeaveEmployee {
    Contractor(String name) { super(name); }
    boolean isEligible(int days) { return days <= 3; }
}

class LeaveRequest {
    private final LeaveEmployee employee;
    private final String period;
    private final int days;
    private LeaveStatus status;

    LeaveRequest(LeaveEmployee employee, String period, int days) {
        this.employee = employee;
        this.period = period;
        this.days = days;
        this.status = LeaveStatus.PENDING;
    }

    LeaveStatus getStatus() { return status; }

    boolean approve() {
        if (status != LeaveStatus.PENDING) {
            return false;
        }
        status = LeaveStatus.APPROVED;
        return true;
    }

    boolean reject() {
        if (status != LeaveStatus.PENDING) {
            return false;
        }
        status = LeaveStatus.REJECTED;
        return true;
    }

    boolean revertToPending() {
        if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
            return false;
        }
        status = LeaveStatus.PENDING;
        return true;
    }
}
