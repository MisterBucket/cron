package cron.parsers;

import org.junit.Assert;
import org.junit.Test;

public class SlashParserTest {
    final SlashParser subject = new SlashParser();

    @Test
    public void parse() {
        final int[] resultBy3 = subject.parse("*/3", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {0,3,6,9,12,15,18,21,24,27,30}, resultBy3);
        final int[] resultBy11 = subject.parse("*/11", ParserTest.testDimension);
        Assert.assertArrayEquals(new int[] {0,11,22}, resultBy11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormat() {
        subject.parse("7~sup-buddy", ParserTest.testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalidFormatMultipleDashes() {
        subject.parse("*/6/10", ParserTest.testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_doesNotDividCleanly() {
        subject.parse("*/9", ParserTest.testDimension);
    }

    @Test
    public void expressionApplies() {
        Assert.assertTrue(subject.expressionApplies("0/11"));
        Assert.assertFalse(subject.expressionApplies("4,5,6,12"));
    }
}
