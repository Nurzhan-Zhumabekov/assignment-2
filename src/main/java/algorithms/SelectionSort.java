package algorithms;

import metrics.PerformanceTracker;

/** Selection Sort with early stopping on already sorted suffix. */
public class SelectionSort {

    public static void sort(int[] a, PerformanceTracker t) {
        if (a == null || a.length < 2) return;

        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            boolean alreadySorted = true;

            for (int j = i + 1; j < n; j++) {
                // comparison a[j] < a[minIdx]
                t.incComparison();
                t.incRead(2);                 // read a[j] and a[minIdx]
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }

                // check non-decreasing order on the fly: a[j-1] <= a[j]
                if (j > i + 1) {
                    t.incComparison();
                    t.incRead(2);             // read a[j-1] and a[j]
                    if (a[j - 1] > a[j]) {
                        alreadySorted = false;
                    }
                }
            }

            // early stop if the remainder is already sorted and no smaller element found at i
            if (alreadySorted && minIdx == i) break;

            // swap a[i] and a[minIdx] when needed
            if (minIdx != i) {
                int tmp = a[i];      t.incRead();            // read a[i]
                a[i] = a[minIdx];    t.incRead(); t.incWrite(); // read a[minIdx], write a[i]
                a[minIdx] = tmp;     t.incWrite();           // write a[minIdx]
                t.incSwap();
            }
        }
    }
}
