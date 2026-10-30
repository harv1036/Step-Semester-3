public class Week6_Inheritance_Polymorphism_Category_B_Practice_P4_CirculationReport {

    static String batchPrint(LibraryMemberP4[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMemberP4 m : members) {
            sb.append(m.displayInfo());
            if (m instanceof StudentMemberP4) {
                StudentMemberP4 sm = (StudentMemberP4) m;
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryMemberP4[] members = {
            new LibraryMemberP4("LB5", 3),
            new StudentMemberP4("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(members));

        LibraryMemberP4 plain = new LibraryMemberP4("LB6", 3);
        try {
            StudentMemberP4 bad = (StudentMemberP4) plain;
            System.out.println(bad.getCourse());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}

class LibraryMemberP4 {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMemberP4(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMemberP4 extends LibraryMemberP4 {
    private String course;

    public StudentMemberP4(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course + " | Books: " + booksBorrowed;
    }
}
