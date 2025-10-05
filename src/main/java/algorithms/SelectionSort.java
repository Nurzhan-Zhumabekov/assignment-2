package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {
    public static void sort(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length < 2) return;

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            boolean sorted = true;

            for (int j = i + 1; j < n; j++) {
                tracker.incComparison();
                tracker.incRead();
                tracker.incRead();
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
                if (arr[j - 1] > arr[j]) {
                    sorted = false;
                }
            }

            if (minIdx != i) {
                int temp = arr[i];
                tracker.incRead();
                tracker.incWrite();
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                tracker.incSwap();
            }

            // если суффикс уже отсортирован
            if (sorted) break;
        }
    }
}
