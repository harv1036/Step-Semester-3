import java.util.HashSet;
import java.util.Set;

// Problem 3: Pair With Target Sum (Unsorted)
// Checks whether any two distinct elements of an unsorted array sum to a target.
class PairWithTargetSumUnsorted {

    // Brute force (checking every pair) is O(n^2) time, O(1) extra space.
    // This hash-set solution is O(n) time, O(n) extra space: for each number
    // we check whether its complement was already seen before adding it.
    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = { 2, 7, 11, 15 };
        System.out.println(hasPairWithSum(nums1, 9) + " | Expected: true");

        int[] nums2 = { 3, 4, 6 };
        System.out.println(hasPairWithSum(nums2, 20) + " | Expected: false");
    }
}
