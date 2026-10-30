import java.util.HashSet;
import java.util.Set;

// Problem 4: Pair With Target Sum (Unsorted Array)
// Same question as before, but both discussed approaches are implemented
// here so they can be compared directly.
class PairWithTargetSumUnsortedArray {

    // Approach 1 - brute force: check every pair of distinct indices.
    // Time: O(n^2), additional space: O(1).
    static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // Approach 2 - hash set: look up each number's complement in one pass.
    // Time: O(n), additional space: O(n).
    static boolean hasPairWithSumHashSet(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(target - num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = { 2, 7, 11, 15 };
        System.out.println("Brute force: " + hasPairWithSumBruteForce(nums1, 9) + " | Expected: true");
        System.out.println("Hash set:    " + hasPairWithSumHashSet(nums1, 9) + " | Expected: true");

        int[] nums2 = { 3, 4, 6 };
        System.out.println("Brute force: " + hasPairWithSumBruteForce(nums2, 20) + " | Expected: false");
        System.out.println("Hash set:    " + hasPairWithSumHashSet(nums2, 20) + " | Expected: false");
    }
}
