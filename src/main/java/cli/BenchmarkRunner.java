package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        String outputDir = "docs/performance-plots";
        String csvFile = outputDir + "/benchmark-results.csv";

        try {
            // создаём папку, если её нет
            Files.createDirectories(Path.of(outputDir));

            try (FileWriter writer = new FileWriter(csvFile)) {
                writer.write("Size,Time(ms),Comparisons,Swaps,Reads,Writes\n");

                int[] sizes = {100, 1000, 10000};
                Random rand = new Random();

                for (int n : sizes) {
                    int[] arr = rand.ints(n, 0, 10000).toArray();
                    PerformanceTracker tracker = new PerformanceTracker();

                    long start = System.nanoTime();
                    SelectionSort.sort(arr, tracker);
                    long end = System.nanoTime();

                    double timeMs = (end - start) / 1_000_000.0;
                    writer.write(String.format("%d,%.3f,%s%n", n, timeMs, tracker.toString()));

                    System.out.printf("n=%d | Time=%.3f ms | %s%n", n, timeMs, tracker.toString());
                }
            }

            System.out.println("✅ Results saved to: " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
