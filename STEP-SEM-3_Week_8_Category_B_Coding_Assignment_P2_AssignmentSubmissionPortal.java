import java.util.ArrayList;
import java.util.List;

class AssignmentSubmissionPortal {

    public static void main(String[] args) {
        Assignment codingLab = new CodingAssignment("Linked List Lab", 10, 50);
        Assignment designEssay = new WrittenAssignment("Design Essay", 12, 50);

        Submission asha = new Submission("Asha", codingLab, 10);
        asha.printReceivedStatus();

        Submission ravi = new Submission("Ravi", designEssay, 14);
        ravi.printReceivedStatus();

        asha.grade(45);
        ravi.grade(40);

        asha.attemptResubmit();
    }
}

abstract class Assignment {
    private final String title;
    private final int dueDay;
    private final int maxMarks;

    Assignment(String title, int dueDay, int maxMarks) {
        this.title = title;
        this.dueDay = dueDay;
        this.maxMarks = maxMarks;
    }

    String getTitle() { return title; }
    int getMaxMarks() { return maxMarks; }

    int getLateDays(int submittedDay) {
        return Math.max(0, submittedDay - dueDay);
    }

    abstract double applyLatePenalty(double awardedMarks, int lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int dueDay, int maxMarks) {
        super(title, dueDay, maxMarks);
    }

    double applyLatePenalty(double awardedMarks, int lateDays) {
        double penalty = 0.10 * lateDays;
        return awardedMarks * (1 - Math.min(1, penalty));
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int dueDay, int maxMarks) {
        super(title, dueDay, maxMarks);
    }

    double applyLatePenalty(double awardedMarks, int lateDays) {
        double penalty = 0.20 * lateDays;
        return awardedMarks * (1 - Math.min(1, penalty));
    }
}

enum SubmissionStatus { SUBMITTED, GRADED }

class Submission {
    private final String studentName;
    private final Assignment assignment;
    private final int submittedDay;
    private SubmissionStatus status;
    private double finalMarks;

    Submission(String studentName, Assignment assignment, int submittedDay) {
        this.studentName = studentName;
        this.assignment = assignment;
        this.submittedDay = submittedDay;
        this.status = SubmissionStatus.SUBMITTED;
    }

    void printReceivedStatus() {
        int lateDays = assignment.getLateDays(submittedDay);
        String lateText = lateDays == 0 ? "(on time)" : "(" + lateDays + " days late)";
        System.out.println(studentName + "'s submission for '" + assignment.getTitle()
                + "' received " + lateText + ". Status: " + status + ".");
    }

    void grade(double awardedMarks) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot grade: '" + assignment.getTitle() + "' already graded.");
            return;
        }
        int lateDays = assignment.getLateDays(submittedDay);
        finalMarks = assignment.applyLatePenalty(awardedMarks, lateDays);
        status = SubmissionStatus.GRADED;
        if (lateDays > 0) {
            System.out.printf("%s graded: %.0f/%d after %d%% late penalty. Status: %s.%n",
                    studentName, finalMarks, assignment.getMaxMarks(),
                    (int) (10 * lateDays * (assignment instanceof WrittenAssignment ? 2 : 1)),
                    status);
        } else {
            System.out.printf("%s graded: %.0f/%d. Status: %s.%n",
                    studentName, finalMarks, assignment.getMaxMarks(), status);
        }
    }

    void attemptResubmit() {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        } else {
            status = SubmissionStatus.SUBMITTED;
            System.out.println("Resubmission accepted for '" + assignment.getTitle() + "'.");
        }
    }
}
