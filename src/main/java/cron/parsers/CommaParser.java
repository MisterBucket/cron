package cron.parsers;

import cron.dimensions.Dimension;

/**
 * Parser used to evaluate comma cron expression items (e.g. '2,4,6,8')
 */
public class CommaParser extends Parser {
    @Override
    public int[] parse(String expression, Dimension dimension) throws IllegalArgumentException {
        final String[] values = expression.split(",");
        final int[] results = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            final int intValue = parseInt(values[i], dimension);
            results[i] = intValue;
        }
        return results;
    }

    @Override
    public boolean expressionApplies(String expression) {
        return expression.contains(",");
    }
}
