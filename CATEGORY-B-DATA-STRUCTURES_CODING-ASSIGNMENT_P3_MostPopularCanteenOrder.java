import java.util.HashMap;
import java.util.Map;

// Problem 3: Most Popular Canteen Order
// Finds the most frequently ordered item, breaking ties by first appearance.
class MostPopularCanteenOrder {

    static class Result {
        String item;
        int count;

        Result(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String toString() {
            return "(" + item + ", " + count + ")";
        }
    }

    // Counting each item by re-scanning the list for every distinct item is
    // O(n^2) time, O(1) extra space (not counting output).
    // This hash-based solution is O(n) time, O(u) space where u is the number
    // of distinct item names.
    static Result mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String item : orders) {
            counts.merge(item, 1, Integer::sum);
        }

        String bestItem = null;
        int bestCount = 0;
        for (String item : orders) {
            int count = counts.get(item);
            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }

        return new Result(bestItem, bestCount);
    }

    public static void main(String[] args) {
        String[] orders1 = { "dosa", "idli", "vada", "dosa", "idli", "dosa", "tea" };
        System.out.println("Most popular: " + mostPopular(orders1));
        System.out.println("Expected: (dosa, 3)");

        String[] orders2 = { "tea", "coffee", "coffee", "tea" };
        System.out.println("Most popular: " + mostPopular(orders2));
        System.out.println("Expected: (tea, 2)");
    }
}
