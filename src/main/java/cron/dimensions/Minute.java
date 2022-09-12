package cron.dimensions;

public class Minute implements Dimension {
    @Override
    public int getSmallest() {
        return 0;
    }

    @Override
    public int getLargest() {
        return 59;
    }

    @Override
    public String toString() {
        return "minute";
    }
}
