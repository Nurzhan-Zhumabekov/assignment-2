package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Random;

/** Runs benchmarks and writes CSV results. */
public class BenchmarkRunner {
    public static void main(String[] args) {
        final String OUTPUT_DIR = "docs/performance-plots";
        final String CSV_FILE   = OUTPUT_DIR + "/benchmark_results_selection.csv";

        try {
            // Ensure output directory exists
            Files.createDirectories(Path.of(OUTPUT_DIR));

            // Write CSV header and rows
            try (FileWriter writer = new FileWriter(CSV_FILE)) {
                writer.write("size,time_ms,comparisons,swaps,reads,writes\n");

                int[] sizes = {100, 1000, 10_000, 100_000};
                Random rnd = new Random();

                for (int n : sizes) {
                    int[] arr = rnd.ints(n, 0, 10_000).toArray();
                    PerformanceTracker t = new PerformanceTracker();

                    long start = System.nanoTime();
                    SelectionSort.sort(arr, t);
                    long end = System.nanoTime();

                    double timeMs = (end - start) / 1_000_000.0;

                    // Use US locale to force dot as decimal separator
                    String row = String.format(Locale.US,
                            "%d,%.3f,%d,%d,%d,%d%n",
                            n, timeMs, t.getComparisons(), t.getSwaps(), t.getReads(), t.getWrites());

                    writer.write(row);

                    System.out.printf(Locale.US,
                            "n=%d | time=%.3f ms | cmp=%d | swaps=%d | reads=%d | writes=%d%n",
                            n, timeMs, t.getComparisons(), t.getSwaps(), t.getReads(), t.getWrites());
                }
            }

            System.out.println("CSV saved to: " + CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
