package cron.dimensions;

public class Month implements Dimension {
    @Override
    public int getSmallest() {
        return 1;
    }

    @Override
    public int getLargest() {
        return 12;
    }

    @Override
    public String toString() {
        return "month";
    }
}
