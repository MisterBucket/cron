package cron.parsers;

import cron.dimensions.Dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract Parser implementation containing the general cron expression parsing contract as well as helpful utility
 * methods.
 */
public abstract class Parser {
    /**
     * Parse cron values based on a given expression and Dimension
     * @param expression Cron expression item
     * @param dimension Dimension to use for parsing this cron (e.g. minute, hour, etc)
     * @return Array of ints representing the cron values
     * @throws IllegalArgumentException An invalid cron expression was provided
     */
    public abstract int[] parse(String expression, Dimension dimension) throws IllegalArgumentException;

    /**
     * Determine if this particular Parser applies to given cron expression
     * @param expression Cron expression to evaluate
     * @return "true" if the expression applies, otherwise "false"
     */
    abstract boolean expressionApplies(String expression);

    /**
     * Determine if the cron expression applies to all values in the Dimension (e.g. '*')
     * @param value Cron expression to evaluate
     * @return "true" if it's a wildcard expression, otherwise "false"
     */
    boolean isAll(String value) {
        return "*".equals(value);
    }

    /**
     * Helpful parseInt method that ensures the value is within the bounds of the Dimension, and handles the
     * potential NumberFormatException from Integer.parseInt().
     * @param value String value to parse to int
     * @param dimension Dimension to consider for lower and upper bounds
     * @return int value parsed from String
     * @throws IllegalArgumentException int value was out-of-bounds or malformed
     */
    int parseInt(String value, Dimension dimension) throws IllegalArgumentException {
        try {
            final int intValue = Integer.parseInt(value);
            if (intValue < dimension.getSmallest() || intValue > dimension.getLargest()) {
                throw new IllegalArgumentException(dimension + " value of " + intValue + " is out of bounds (" +
                        dimension.getSmallest() + "-" + dimension.getLargest() + ")");
            }
            return intValue;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Malformed " + dimension + " value: " + value);
        }
    }

    /**
     * Factory used to obtain the appropriate Parser for a given cron expression.
     */
    public static class Factory {
        static final CommaParser commaParser = new CommaParser();
        static final DashParser dashParser = new DashParser();
        static final SlashParser slashParser = new SlashParser();
        static final List<Parser> parsers = new ArrayList<>(Arrays.asList(commaParser, dashParser, slashParser));

        static final DefaultParser defaultParser = new DefaultParser();

        /**
         * Returns the appropriate Parser for a given cron expression
         * @param expression Cron expression to evaluate
         * @return Parser which should be utilised
         * @throws IllegalArgumentException Expression provided was invalid and matched multiple parsers
         */
        public static Parser getParser(String expression) throws IllegalArgumentException {
            final List<Parser> parserMatches = parsers.stream()
                    .filter(p -> p.expressionApplies(expression)).collect(Collectors.toList());

            if (parserMatches.isEmpty()) {
                return defaultParser;
            }
            if (parserMatches.size() > 1) {
                throw new IllegalArgumentException("Expression " + expression + " is invalid.");
            }
            return parserMatches.get(0);
        }
    }
}
