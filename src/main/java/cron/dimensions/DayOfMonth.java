package cron.dimensions;

public class DayOfMonth implements Dimension {
    @Override
    public int getSmallest() {
        return 1;
    }

    @Override
    public int getLargest() {
        return 31;
    }

    @Override
    public String toString() {
        return "day of month";
    }
}
