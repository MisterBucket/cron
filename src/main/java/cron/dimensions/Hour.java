package cron.dimensions;

public class Hour implements Dimension {
    @Override
    public int getSmallest() {
        return 0;
    }

    @Override
    public int getLargest() {
        return 23;
    }

    @Override
    public String toString() {
        return "hour";
    }
}
