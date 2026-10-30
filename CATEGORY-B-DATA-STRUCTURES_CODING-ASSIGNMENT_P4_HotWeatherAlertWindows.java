// Problem 4: Hot Weather Alert Windows
// Counts how many k-hour windows have an average reading at least the threshold,
// using a sliding window instead of recomputing each block's sum from scratch.
class HotWeatherAlertWindows {

    // Recomputing the sum of every block from scratch is O(n * k) time.
    // This sliding-window solution is O(n) time, O(1) additional space.
    static int countAlerts(int[] readings, int k, int threshold) {
        int n = readings.length;
        int windowSum = 0;
        int alerts = 0;

        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }
        if (windowSum >= k * threshold) {
            alerts++;
        }

        for (int i = k; i < n; i++) {
            windowSum += readings[i] - readings[i - k];
            if (windowSum >= k * threshold) {
                alerts++;
            }
        }

        return alerts;
    }

    public static void main(String[] args) {
        int[] readings = { 2, 2, 2, 2, 5, 5, 5, 8 };
        int alerts = countAlerts(readings, 3, 4);
        System.out.println("Alerts: " + alerts);
        System.out.println("Expected: 3");
    }
}
