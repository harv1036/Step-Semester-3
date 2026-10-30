public class Week6_Inheritance_Polymorphism_Category_B_Practice_P5_CirculationAudit {

    public static void main(String[] args) {
        LibraryMemberP5 m1 = new LibraryMemberP5(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMemberP5.getMembersEnrolled());

        System.out.println(LibraryMemberP5.isValidRenewalCode("R12A"));
        System.out.println(LibraryMemberP5.isValidRenewalCode("R1A"));
        System.out.println(LibraryMemberP5.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMemberP5[] batch = {
            new FacultyMemberP5(5, "Physics"),
            null,
            new LibraryMemberP5(3)
        };
        System.out.println(LibraryMemberP5.processNightlyAudit(batch));
    }
}

class LibraryMemberP5 {
    private static int counter = 100;
    private static int membersEnrolled = 0;

    public final String memberNumber;
    protected int borrowLimit;
    private int booksBorrowed;

    public LibraryMemberP5(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        membersEnrolled++;
        counter++;
        this.memberNumber = "LIB-" + counter;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    void borrowBook(String genre) {
        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }

    static String processNightlyAudit(LibraryMemberP5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;
        for (LibraryMemberP5 m : members) {
            if (m == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (m instanceof FacultyMemberP5) {
                faculty++;
            } else {
                regular++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }
}

class FacultyMemberP5 extends LibraryMemberP5 {
    private String department;

    public FacultyMemberP5(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}
