package cron.parsers;

import cron.dimensions.Dimension;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
    static final int lowerBound = 0;
    static final int upperBound = 32;

    static final Dimension testDimension = new Dimension() {
        @Override
        public int getSmallest() {
            return lowerBound;
        }

        @Override
        public int getLargest() {
            return upperBound;
        }

        @Override
        public String toString() {
            return "test";
        }
    };

    final Parser subject = new Parser() {
        @Override
        public int[] parse(String expression, Dimension dimension) throws IllegalArgumentException {
            return new int[0];
        }

        @Override
        boolean expressionApplies(String expression) {
            return false;
        }
    };

    @Test
    public void isAll() {
        Assert.assertTrue(subject.isAll("*"));
        Assert.assertFalse(subject.isAll(null));
        Assert.assertFalse(subject.isAll("**"));
        Assert.assertFalse(subject.isAll("15"));
        Assert.assertFalse(subject.isAll("2-5"));
    }

    @Test
    public void parseInt() {
        Assert.assertEquals(24, subject.parseInt("24", testDimension));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInt_outOfRange() {
        subject.parseInt("66", testDimension);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInt_invalidInt() {
        subject.parseInt("Good Day", testDimension);
    }

    @Test
    public void factory() {
        Assert.assertEquals(Parser.Factory.commaParser, Parser.Factory.getParser("1,2,3"));
        Assert.assertEquals(Parser.Factory.slashParser, Parser.Factory.getParser("*/8"));
        Assert.assertEquals(Parser.Factory.dashParser, Parser.Factory.getParser("5-10"));
        Assert.assertEquals(Parser.Factory.defaultParser, Parser.Factory.getParser("9"));
        Assert.assertEquals(Parser.Factory.defaultParser, Parser.Factory.getParser("What"));
        Assert.assertEquals(Parser.Factory.defaultParser, Parser.Factory.getParser("is"));
        Assert.assertEquals(Parser.Factory.defaultParser, Parser.Factory.getParser("this?"));
    }
}
