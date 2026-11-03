class PlacementCutoff {

    static int kthHighest(int[] marks, int k) {
        int[] arr = marks.clone();
        int targetIndex = arr.length - k;
        int left = 0, right = arr.length - 1;

        while (true) {
            int pivotIndex = partition(arr, left, right);
            if (pivotIndex == targetIndex) {
                return arr[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] marks1 = {72, 95, 60, 88, 79};
        System.out.println(kthHighest(marks1, 2));

        int[] marks2 = {50, 90, 90, 70};
        System.out.println(kthHighest(marks2, 2));

        System.out.println();
        System.out.println("Time complexity: O(n) average case, O(n^2) worst case");
        System.out.println("Extra space: O(1) extra beyond the cloned array, partitioning is done in place");
        System.out.println("Stable: not applicable, quickselect reorders elements and does not preserve relative order of equal marks");
    }
}
