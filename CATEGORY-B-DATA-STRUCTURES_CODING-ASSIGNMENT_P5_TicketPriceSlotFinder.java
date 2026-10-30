// Problem 5: Ticket Price Slot Finder
// Finds the index of a price if present, or the insertion index that keeps
// the sorted list in order, using binary search.
class TicketPriceSlotFinder {

    // A linear scan is O(n) time, O(1) extra space.
    // This binary-search solution is O(log n) time, O(1) extra space.
    static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] prices = { 120, 150, 200, 260 };

        System.out.println("Slot: " + findSlot(prices, 150) + " | Expected: 1");
        System.out.println("Slot: " + findSlot(prices, 210) + " | Expected: 3");
        System.out.println("Slot: " + findSlot(prices, 300) + " | Expected: 4");
    }
}
