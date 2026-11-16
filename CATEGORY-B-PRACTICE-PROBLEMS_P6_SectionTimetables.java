import java.util.ArrayList;
import java.util.List;

class SectionTimetables {

    static class Timetable {
        String section;
        List<String> periods;

        Timetable(String section, List<String> periods) {
            this.section = section;
            this.periods = periods;
        }

        Timetable copy(String newSection) {
            return new Timetable(newSection, new ArrayList<>(periods));
        }

        void setPeriod(int period, String subject) {
            periods.set(period - 1, subject);
        }

        void print() {
            System.out.println(section + ": " + periods);
        }
    }

    public static void main(String[] args) {
        List<String> original = new ArrayList<>();
        original.add("Java");
        original.add("DSA");
        original.add("Maths");

        Timetable cseA = new Timetable("CSE-A", original);
        Timetable cseB = cseA.copy("CSE-B");
        cseB.setPeriod(2, "Physics");

        cseA.print();
        cseB.print();

        System.out.println();
        System.out.println("Time complexity: O(n), copying n periods into a new list");
        System.out.println("Extra space: O(n), for the new list holding the copied periods");
    }
}
