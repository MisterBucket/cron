package cron.dimensions;

public class DayOfWeek implements Dimension {
    @Override
    public int getSmallest() {
        return 0;
    }

    @Override
    public int getLargest() {
        return 6;
    }

    @Override
    public String toString() {
        return "day of week";
    }
}
