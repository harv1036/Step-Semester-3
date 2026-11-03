class AttendanceDisorderScore {

    static long count;

    static long countInversions(int[] roll) {
        count = 0;
        int[] copy = roll.clone();
        mergeSort(copy, 0, copy.length - 1);
        return count;
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count += (mid - i + 1);
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(String[] args) {
        int[] roll1 = {2, 4, 1, 3, 5};
        System.out.println(countInversions(roll1));

        int[] roll2 = {5, 4, 3, 2, 1};
        System.out.println(countInversions(roll2));

        System.out.println();
        System.out.println("Time complexity: O(n log n)");
        System.out.println("Extra space: O(n), for the temporary merge arrays");
        System.out.println("Stable: yes, equal rolls are taken from the left half first during merge");
    }
}
