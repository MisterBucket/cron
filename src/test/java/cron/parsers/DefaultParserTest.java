package cron.parsers;

import org.junit.Assert;
import org.junit.Test;

public class DefaultParserTest {
    final DefaultParser subject = new DefaultParser();

    @Test
    public void parse_all() {
        final int[] result = subject.parse("*", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32}, result);
    }

    @Test
    public void parse_one() {
        final int[] result = subject.parse("15", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {15}, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_outOfBounds() {
        subject.parse("99", ParserTest.testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormat() {
        subject.parse("lol~wut", ParserTest.testDimension);
    }
}
