public class Week6_Inheritance_Polymorphism_Category_B_Practice_P2_ThreeBranchesMembership {

    static String classifyGeneration(LibraryMemberP2 member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMemberP2) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMemberP2) {
            return "Direct subclass";
        } else {
            return "Base member";
        }
    }

    static int getTotalBooksBorrowed(LibraryMemberP2[] members) {
        int total = 0;
        for (LibraryMemberP2 m : members) {
            total += m.getBooksBorrowed();
        }
        return total;
    }

    public static void main(String[] args) {
        LibraryMemberP2 m1 = new LibraryMemberP2("STU1", 3);
        StudentMemberP2 m2 = new StudentMemberP2("STU2", 3, "CSE");
        HonorsStudentMember m3 = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMemberP2 m4 = new FacultyMemberP2("STU4", 5, "Physics");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println(classifyGeneration(m3));
        System.out.println(classifyGeneration(m4));

        m2.borrowBook();
        m2.borrowBook();
        m3.borrowBook();
        m4.borrowBook();
        m4.borrowBook();
        m4.borrowBook();

        System.out.println(getTotalBooksBorrowed(new LibraryMemberP2[]{m2, m3, m4}));
    }
}

class LibraryMemberP2 {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMemberP2(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }
}

class StudentMemberP2 extends LibraryMemberP2 {
    protected String course;

    public StudentMemberP2(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class HonorsStudentMember extends StudentMemberP2 {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    String displayInfo() {
        return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class FacultyMemberP2 extends LibraryMemberP2 {
    private String department;

    public FacultyMemberP2(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
    }
}
