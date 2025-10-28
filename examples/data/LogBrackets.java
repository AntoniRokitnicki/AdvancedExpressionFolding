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
public class LogBrackets {

    private static final Logger log = LoggerFactory.getLogger(LogBrackets.class);
    private static final Marker MY_MARKER = MarkerFactory.getMarker("MY_MARKER");

    public Data logPrintfStyle(Data data) {
        String name = "John";
        int age = 30;
        String city = "New York";
        log.debug("Debug message with 1 parameter - Name: %s", name);

        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
        log.info(MY_MARKER, "Marker missing parameters {} {}");

        log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);
        log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));
        log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);

        log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);
        log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);
        log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    name,

                    data.getData().getName(),

                    city
            );

            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    data.getData().getName(),
                    name,
                    data.getData().getName()
            );
        } catch (Exception e) {
            log.error("error1 %s", e, e.getMessage(), e);
            log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());
        }

        // 1. String.format()
        String formatted = String.format("Hello, %s! Your age is %d", name, age);
        log.info("String.format example: {}", formatted);

        // 2. System.out.printf()
        System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);

        // 3. System.err.printf()
        System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);
        log.info("Formatter example: {}", formatter.toString());

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: %s", e.getMessage());
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));
        System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));
        System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));

        System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));
        System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));
        System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));
        System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error("""
                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s
                """, name, age, city);
        return data;
    }

    public static class Data {
        private Data data;
        private String name;

        public String getName() {
            return name;
        }

        public Data getData() {
            return data;
        }
    }

}
