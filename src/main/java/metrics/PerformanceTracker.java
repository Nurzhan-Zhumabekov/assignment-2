package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long reads;
    private long writes;

    public void incComparison() { comparisons++; }
    public void incSwap() { swaps++; }
    public void incRead() { reads++; }
    public void incWrite() { writes++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getReads() { return reads; }
    public long getWrites() { return writes; }

    public void reset() {
        comparisons = swaps = reads = writes = 0;
    }

    @Override
    public String toString() {
        return String.format("Comparisons=%d, Swaps=%d, Reads=%d, Writes=%d",
                comparisons, swaps, reads, writes);
    }
}
