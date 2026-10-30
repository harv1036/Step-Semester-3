import java.util.Arrays;

// Problem 2: Merging Two Token Queues
// Merges two already-sorted lists of token numbers into one sorted list
// using two pointers, without re-sorting.
class MergeTokenQueues {

    // Time: O(m + n), Space: O(m + n) for the output array.
    // Joining the lists and sorting would cost O((m+n) log(m+n)) time instead.
    static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }
        while (i < m) {
            merged[k++] = counterA[i++];
        }
        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] a1 = { 3, 8, 15, 20 };
        int[] b1 = { 5, 8, 12 };
        System.out.println("Merged: " + Arrays.toString(mergeTokens(a1, b1)));
        System.out.println("Expected: [3, 5, 8, 8, 12, 15, 20]");

        int[] a2 = {};
        int[] b2 = { 4, 9 };
        System.out.println("Merged: " + Arrays.toString(mergeTokens(a2, b2)));
        System.out.println("Expected: [4, 9]");
    }
}
