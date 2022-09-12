package cron.parsers;

import cron.dimensions.Dimension;

/**
 * Parser used to evaluate slash cron expression items (e.g. '0/5')
 */
public class SlashParser extends Parser {
    @Override
    public int[] parse(String expression, Dimension dimension) throws IllegalArgumentException {
        final String[] values = expression.split("/");
        if (values.length != 2) {
            throw new IllegalArgumentException("Slash expression must contain only one slash character ('/')");
        }
        final int stepValue = parseInt(values[1], dimension);
        final int stepNumerator = dimension.getLargest() - dimension.getSmallest() + 1;
        if (stepValue == stepNumerator || stepNumerator % stepValue != 0) {
            throw new IllegalArgumentException("Slash expression step value for " + dimension +
                    " must evenly divide into " + stepNumerator + ", which " + stepValue + " does not.");
        }
        final int[] results = new int[stepNumerator / stepValue];
        for (int s = dimension.getSmallest(), i = 0; s < dimension.getLargest(); s += stepValue, i++) {
            results[i] = s;
        }
        return results;
    }

    @Override
    public boolean expressionApplies(String expression) {
        return expression.contains("/");
    }
}
