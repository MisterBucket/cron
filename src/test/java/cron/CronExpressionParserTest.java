package cron;

import cron.dimensions.Dimension;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CronExpressionParserTest {
    final CronExpressionParser subject = new CronExpressionParser();

    @Test
    public void convertIntArrayToString() {
        final int[] input = new int[] { 4, 7, 10, 13, 16 };
        final String result = subject.convertIntArrayToString(input);
        Assert.assertEquals("4 7 10 13 16", result);
    }

    @Test
    public void parseExpression() {
        final String[] input = new String[] { "*/15", "0", "1,15", "*", "1-5" };
        final Map<Dimension, int[]> result = subject.parseExpression(input);
        Assert.assertEquals(5, result.size());
        Assert.assertTrue(result.containsKey(CronExpressionParser.minute));
        Assert.assertTrue(result.containsKey(CronExpressionParser.hour));
        Assert.assertTrue(result.containsKey(CronExpressionParser.dayOfMonth));
        Assert.assertTrue(result.containsKey(CronExpressionParser.month));
        Assert.assertTrue(result.containsKey(CronExpressionParser.dayOfWeek));
        Assert.assertEquals("0 15 30 45",
                subject.convertIntArrayToString(result.get(CronExpressionParser.minute)));
        Assert.assertEquals("0",
                subject.convertIntArrayToString(result.get(CronExpressionParser.hour)));
        Assert.assertEquals("1 15",
                subject.convertIntArrayToString(result.get(CronExpressionParser.dayOfMonth)));
        Assert.assertEquals("1 2 3 4 5 6 7 8 9 10 11 12",
                subject.convertIntArrayToString(result.get(CronExpressionParser.month)));
        Assert.assertEquals("1 2 3 4 5",
                subject.convertIntArrayToString(result.get(CronExpressionParser.dayOfWeek)));
    }
}
