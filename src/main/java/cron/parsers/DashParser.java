package cron.parsers;

import cron.dimensions.Dimension;

/**
 * Parser used to evaluate dash cron expression items (e.g. '5-10')
 */
public class DashParser extends Parser {
    @Override
    public int[] parse(String expression, Dimension dimension) throws IllegalArgumentException {
        final String[] values = expression.split("-");
        if (values.length != 2) {
            throw new IllegalArgumentException("Dash expression must contain only one dash character ('-')");
        }
        final int lowerBound = parseInt(values[0], dimension);
        final int upperBound = parseInt(values[1], dimension);
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Dash expression lower bound " + lowerBound +
                    " is higher than upper bound " + upperBound);
        }
        final int[] results = new int[upperBound - lowerBound + 1];
        for (int s = lowerBound, i = 0; s <= upperBound; s++, i++) {
            results[i] = s;
        }
        return results;
    }

    @Override
    public boolean expressionApplies(String expression) {
        return expression.contains("-");
    }
}
