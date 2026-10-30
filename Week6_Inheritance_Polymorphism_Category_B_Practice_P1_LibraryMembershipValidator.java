public class Week6_Inheritance_Polymorphism_Category_B_Practice_P1_LibraryMembershipValidator {

    public static void main(String[] args) {
        try {
            new LibraryMemberP1("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        StudentMemberP1 s = new StudentMemberP1("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());

        String result = LibraryMemberP1.enrollBatch(new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3);
        System.out.println(result);
    }
}

class LibraryMemberP1 {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMemberP1(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: " + memberId);
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                new LibraryMemberP1(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMemberP1 extends LibraryMemberP1 {
    private String course;

    public StudentMemberP1(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}
