package cron.parsers;

import org.junit.Assert;
import org.junit.Test;

public class CommaParserTest {
    final CommaParser subject = new CommaParser();

    @Test
    public void parse() {
        final int[] result = subject.parse("6,7,19,20", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {6, 7, 19, 20}, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormat() {
        subject.parse("10-fred,20-kate", ParserTest.testDimension);
    }

    @Test
    public void expressionApplies() {
        Assert.assertTrue(subject.expressionApplies("10,11"));
        Assert.assertFalse(subject.expressionApplies("10-11"));
    }
}
