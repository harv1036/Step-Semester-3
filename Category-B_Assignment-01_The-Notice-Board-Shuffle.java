import java.util.Arrays;

class NoticeBoardShuffle {

    static int[] bubbleStats(int[] scores) {
        int n = scores.length;
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        int[] scores1 = {5, 1, 4, 2, 8};
        int[] stats1 = bubbleStats(scores1);
        System.out.println(Arrays.toString(scores1) + " passes = " + stats1[0] + ", swaps = " + stats1[1]);

        int[] scores2 = {1, 2, 3, 4};
        int[] stats2 = bubbleStats(scores2);
        System.out.println(Arrays.toString(scores2) + " passes = " + stats2[0] + ", swaps = " + stats2[1]);

        System.out.println();
        System.out.println("Time complexity: O(n^2) worst case, O(n) best case (already sorted)");
        System.out.println("Extra space: O(1), sorts in place");
        System.out.println("Stable: yes, equal elements are never swapped past each other");
        System.out.println("An already-sorted board needs only one pass because the inner loop makes");
        System.out.println("zero swaps on that pass, so the swapped flag stays false and the outer loop breaks immediately.");
    }
}
