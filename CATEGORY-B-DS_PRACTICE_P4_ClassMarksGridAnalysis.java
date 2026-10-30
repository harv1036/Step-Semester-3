import java.util.*;
import java.util.regex.*;

class ClassMarksGridAnalysis {

    public static void main(String[] args) {
        String[] input = {
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        };

        List<String> names = new ArrayList<>();
        List<int[]> marksGrid = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+)\\s*\\[(.+)\\]");

        for (String line : input) {
            Matcher matcher = pattern.matcher(line.trim());
            if (!matcher.matches()) continue;
            String name = matcher.group(1);
            String[] markTokens = matcher.group(2).split(",");
            int[] marks = new int[markTokens.length];
            for (int i = 0; i < markTokens.length; i++) {
                marks[i] = Integer.parseInt(markTokens[i].trim());
            }
            names.add(name);
            marksGrid.add(marks);
        }

        int numStudents = names.size();
        int numSubjects = marksGrid.get(0).length;

        int[] totals = new int[numStudents];
        StringBuilder totalsLine = new StringBuilder("Totals ");
        int topperIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            int total = 0;
            for (int mark : marksGrid.get(i)) {
                total += mark;
            }
            totals[i] = total;
            if (total > totals[topperIndex]) {
                topperIndex = i;
            }
            totalsLine.append(names.get(i)).append(" ").append(total);
            if (i < numStudents - 1) totalsLine.append(", ");
        }
        System.out.println(totalsLine);

        StringBuilder averagesLine = new StringBuilder("averages ");
        for (int s = 0; s < numSubjects; s++) {
            int sum = 0;
            for (int[] marks : marksGrid) {
                sum += marks[s];
            }
            double avg = (double) sum / numStudents;
            averagesLine.append(String.format("%.2f", avg));
            if (s < numSubjects - 1) averagesLine.append(", ");
        }
        System.out.println(averagesLine);

        System.out.println("topper " + names.get(topperIndex) + " (" + totals[topperIndex] + ")");
    }
}
