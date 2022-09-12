package cron.dimensions;

/**
 * The Dimension interface defines the lower bound and upper bound of a cron item
 */
public interface Dimension {
    /**
     * Get the lower bound of the Dimension
     * @return Lower bound value
     */
    int getSmallest();

    /**
     * Get the upper bound of the Dimension
     * @return Upper bound value
     */
    int getLargest();
}
