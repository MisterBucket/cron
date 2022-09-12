package cron.parsers;

import org.junit.Assert;
import org.junit.Test;

public class DashParserTest {
    final DashParser subject = new DashParser();

    @Test
    public void parse() {
        final int[] result = subject.parse("15-20", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {15, 16, 17, 18, 19, 20}, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormat() {
        subject.parse("9~hey-bubba", ParserTest.testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormatMultipleDashes() {
        subject.parse("2-6-10", ParserTest.testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_outOfBounds() {
        subject.parse("9-70", ParserTest.testDimension);
    }

    @Test
    public void expressionApplies() {
        Assert.assertTrue(subject.expressionApplies("10-11"));
        Assert.assertFalse(subject.expressionApplies("10,11"));
    }
}
