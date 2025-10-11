package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;

@SuppressWarnings("ALL")
public class LogFoldingTextBlocksTestData {

    private static final Logger log = LoggerFactory.getLogger(LogFoldingTextBlocksTestData.class);

    private static final Marker MY_MARKER = MarkerFactory.getMarker("MY_MARKER");

    public LogBrackets.Data logPrintfStyle(LogBrackets.Data data) {
        String name = "John";
        int age = 30;
        String city = "New York";
        log.debug("Debug message with 1 parameter - Name: $name");

        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");

        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");
        log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");

        log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");
        log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");
        log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");

            log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");
        } catch (Exception e) {
            log.error("error1 $e", e.message, e);
            log.error("error2 ${data.data.name}", data.data.name, data.data.name);
        }

        // 1. String.format()
        String formatted = String.format("Hello, $name! Your age is $age");
        log.info("String.format example: $formatted");

        // 2. System.out.printf()
        System.out.printf("User: $name, Age: $age, City: $city%n");

        // 3. System.err.printf()
        System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: $name, Age: $age, City: $city");
        log.info("Formatter example: ${formatter.toString()}");

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User $name, Age $age, accessed from $city");
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: ${e.message}");
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: $name".formatted());
        System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());
        System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());

        System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());
        System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());
        System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: $name".formatted( data));
        System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error("""
                Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s
                """);
        log.info("""
                Data summary:
                Root: ${data.name}
                Child: ${data.data.name}
                """);
        log.debug("""
                User summary:
                Name: $name
                Age: $age
                City: $city
                """);
        log.warn("""
                Nested data snapshot:
                Parent: ${data.name}
                Child: ${data.data.name}
                """);
        log.trace("""
                Formatter contents:
                $formatter
                """);
        return data;
    }

    public static class Data {
        private LogBrackets.Data data;
        private String name;

        public String getName() {
            return name;
        }

        public LogBrackets.Data getData() {
            return data;
        }
    }

}
