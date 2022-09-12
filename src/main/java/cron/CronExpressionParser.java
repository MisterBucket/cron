package cron;

import cron.dimensions.DayOfMonth;
import cron.dimensions.DayOfWeek;
import cron.dimensions.Dimension;
import cron.dimensions.Hour;
import cron.dimensions.Minute;
import cron.dimensions.Month;
import cron.parsers.Parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Application used to parse a cron expression.
 */
public class CronExpressionParser {
    static final Minute minute = new Minute();
    static final Hour hour = new Hour();
    static final DayOfMonth dayOfMonth = new DayOfMonth();
    static final Month month = new Month();
    static final DayOfWeek dayOfWeek = new DayOfWeek();

    public void printUsage() {
        System.out.println("Usage:   ./gradlew run --args=\"[minute] [hour] [day of month] [month] [day of week] [command]\"");
        System.out.println("Example: ./gradlew run --args=\"*/15 0 1,15 * 1-5 /usr/bin/find\"");
    }

    /**
     * Given the five cron items (minute, hour, day of month, month, and day of week), parse and return the numeric
     * cron values by dimension.
     * @param expressions String array of the cron expression items (Must contain 5 elements)
     * @return Map of cron values keyed by Dimension
     */
    public Map<Dimension, int[]> parseExpression(String[] expressions) {
        if (expressions.length != 5) {
            throw new IllegalArgumentException("5 cron expressions must be provided.");
        }
        final Map<Dimension,int[]> results = new HashMap<>(5);
        results.put(minute, Parser.Factory.getParser(expressions[0]).parse(expressions[0], minute));
        results.put(hour, Parser.Factory.getParser(expressions[1]).parse(expressions[1], hour));
        results.put(dayOfMonth, Parser.Factory.getParser(expressions[2]).parse(expressions[2], dayOfMonth));
        results.put(month, Parser.Factory.getParser(expressions[3]).parse(expressions[3], month));
        results.put(dayOfWeek, Parser.Factory.getParser(expressions[4]).parse(expressions[4], dayOfWeek));
        return results;
    }

    /**
     * Converts an int Array to a String for pretty printing
     * @param values int Array
     * @return String of the ints nicely formatted for printing
     */
    public String convertIntArrayToString(int[] values) {
        final StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            resultBuilder.append(values[i]);
            if (i < values.length - 1) {
                resultBuilder.append(' ');
            }
        }
        return resultBuilder.toString();
    }

    /**
     * Pretty print a single line with the item name on left and values on right.
     * @param name Line item's name
     * @param value Line item's value
     */
    public void prettyPrintResultLine(String name, String value) {
        final StringBuilder lineBuilder = new StringBuilder(name);
        for (int i = name.length(); i <= 14; i++) {
            lineBuilder.append(' ');
        }
        lineBuilder.append(value);
        System.out.println(lineBuilder);
    }

    /**
     * Pretty print the Map of cron values keyed by Dimension
     * @param results Map of cron values keyed by Dimension
     * @param command Command to be run by this cron
     */
    public void prettyPrintResults(Map<Dimension, int[]> results, String command) {
        prettyPrintResultLine(minute.toString(), convertIntArrayToString(results.get(minute)));
        prettyPrintResultLine(hour.toString(), convertIntArrayToString(results.get(hour)));
        prettyPrintResultLine(dayOfMonth.toString(), convertIntArrayToString(results.get(dayOfMonth)));
        prettyPrintResultLine(month.toString(), convertIntArrayToString(results.get(month)));
        prettyPrintResultLine(dayOfWeek.toString(), convertIntArrayToString(results.get(dayOfWeek)));
        prettyPrintResultLine("command", command);
    }

    public static void main(String[] args) {
        final CronExpressionParser app = new CronExpressionParser();
        if (args.length != 6) {
            app.printUsage();
            System.exit(0);
        }
        final Map<Dimension, int[]> results;
        try {
            results = app.parseExpression(Arrays.copyOfRange(args, 0, 5));
            app.prettyPrintResults(results, args[5]);
        } catch(IllegalArgumentException ex) {
            System.out.println("I am sorry, I could not parse your cron expression for this reason:");
            System.out.println(ex.getMessage());
        }
    }
}
