package christmas.view.output;

public class Printer {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void print(Object input) {
        System.out.println(input);
    }

    public void printFormat(String format, Object value) {
        System.out.printf(format + LINE_SEPARATOR, value);
    }

    public void printFormat(String format, Object... args) {
        System.out.printf(format + LINE_SEPARATOR, args);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public static void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
