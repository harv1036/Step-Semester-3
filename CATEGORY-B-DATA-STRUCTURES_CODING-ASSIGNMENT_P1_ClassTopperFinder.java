import java.util.Arrays;

// Problem 1: Class Topper Finder
// Finds the student (row) with the highest total marks in a 2D grid.
class ClassTopperFinder {

    // Returns {rowIndex, total} for the student with the highest total marks.
    // Ties are broken by the smallest row index.
    // Time: O(m*n) - every mark is visited once.
    // Additional space: O(1) beyond the input grid.
    static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = i;
            }
        }

        return new int[] { bestRow, bestTotal };
    }

    public static void main(String[] args) {
        int[][] marks = {
            { 78, 85, 90 },
            { 88, 92, 79 },
            { 65, 70, 95 }
        };

        int[] result = findTopper(marks);
        System.out.println("marks = " + Arrays.deepToString(marks));
        System.out.println("Topper: (" + result[0] + ", " + result[1] + ")");
        System.out.println("Expected: (1, 259)");
    }
}
