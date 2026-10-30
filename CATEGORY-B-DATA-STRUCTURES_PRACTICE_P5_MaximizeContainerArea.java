// Problem 5: Maximize Area Between Two Boundaries
// Finds the two walls that, together with the x-axis, hold the most water.
class MaximizeContainerArea {

    // Brute force (checking every pair of walls) is O(n^2) time.
    // This two-pointer solution is O(n) time, O(1) additional space:
    // start at both ends and always move the pointer at the shorter wall,
    // since moving the taller one can never increase the area.
    static int maxContainerArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int best = 0;

        while (left < right) {
            int height = Math.min(heights[left], heights[right]);
            int area = height * (right - left);
            best = Math.max(best, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return best;
    }

    public static void main(String[] args) {
        int[] heights = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println("Max area: " + maxContainerArea(heights));
        System.out.println("Expected: 49");
    }
}
