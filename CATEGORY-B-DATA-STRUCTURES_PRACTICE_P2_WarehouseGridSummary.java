import java.util.Arrays;

// Problem 2: Warehouse Grid Summary
// Sums all bins in a 2D grid and finds the coordinates of the bin holding
// the most items, breaking ties by the first bin found scanning row by row.
class WarehouseGridSummary {

    static class Summary {
        int totalItems;
        int maxRow;
        int maxCol;

        Summary(int totalItems, int maxRow, int maxCol) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        public String toString() {
            return "(" + totalItems + ", (" + maxRow + ", " + maxCol + "))";
        }
    }

    // Time: O(m*n), additional space: O(1) beyond the input grid.
    static Summary warehouseSummary(int[][] grid) {
        int total = 0;
        int maxValue = -1;
        int maxRow = -1;
        int maxCol = -1;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                total += value;
                if (value > maxValue) {
                    maxValue = value;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        return new Summary(total, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            { 4, 9, 2 },
            { 7, 1, 6 },
            { 3, 12, 5 }
        };

        System.out.println("grid = " + Arrays.deepToString(grid));
        System.out.println("Summary: " + warehouseSummary(grid));
        System.out.println("Expected: (49, (2, 1))");
    }
}
