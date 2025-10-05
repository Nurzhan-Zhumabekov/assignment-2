package metrics;

/** Counts primitive operations for benchmarking. */
public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long reads;
    private long writes;

    // --- increments ---
    public void incComparison() { comparisons++; }
    public void incSwap()       { swaps++; }
    public void incRead()       { reads++; }
    public void incRead(long k) { reads += k; }
    public void incWrite()      { writes++; }
    public void incWrite(long k){ writes += k; }

    // --- getters ---
    public long getComparisons() { return comparisons; }
    public long getSwaps()       { return swaps; }
    public long getReads()       { return reads; }
    public long getWrites()      { return writes; }

    public void reset() { comparisons = swaps = reads = writes = 0; }
}
