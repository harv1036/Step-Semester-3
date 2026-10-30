public class OOP_Week3_Category_B_Problems_P5_Student {

    static class Student {
        String name;
        int attendance;

        static String collegeName = "SRM Institute of Science and Technology";
        static int studentCount = 0;

        Student(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        static void printCollegeInfo() {
            System.out.println(collegeName);
            System.out.println("Students created: " + studentCount);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ravi", 85);
        Student s2 = new Student("Anitha", 92);

        Student.printCollegeInfo();
    }
}
