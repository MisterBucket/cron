package cron.parsers;

import cron.dimensions.Dimension;

/**
 * Parser used to evaluate default cron expression items (e.g. '*' or '15')
 */
public class DefaultParser extends Parser {
    @Override
    public int[] parse(String expression, Dimension dimension) throws IllegalArgumentException {
        if (isAll(expression)) {
            final int[] results = new int[dimension.getLargest() - dimension.getSmallest() + 1];
            for (int s = dimension.getSmallest(), i = 0; s <= dimension.getLargest(); s++, i++) {
                results[i] = s;
            }
            return results;
        }
        return new int[] { parseInt(expression, dimension) };
    }

    @Override
    public boolean expressionApplies(String expression) {
        return true;
    }
}
