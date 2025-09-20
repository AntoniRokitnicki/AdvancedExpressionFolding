package data;

import <fold text='...' expand='false'>org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;</fold>

@SuppressWarnings("ALL")
public class LogFoldingTextBlocksTestData {

    private static final Logger log = LoggerFactory.getLogger(LogFoldingTextBlocksTestData.class);

    private static final Marker MY_MARKER = MarkerFactory.getMarker("MY_MARKER");

    public LogBrackets.Data logPrintfStyle(LogBrackets.Data data) <fold text='{...}' expand='true'>{
        String name = "John";
        int age = 30;
        String city = "New York";
        log.debug("Debug message with 1 parameter - Name: <fold text='$' expand='false'>%s", </fold>name<fold text='")' expand='false'>)</fold>;

        log.info(MY_MARKER, "Info message with 2 parameters - Name: <fold text='$' expand='false'>%s, Age: %d", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;

        log.info("Info message with 2 parameters - Name: <fold text='$' expand='false'>%s, Age: %d    ", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='    ")' expand='false'>)</fold>;
        log.info("Info message with 2 parameters - Name: <fold text='$' expand='false'>%s, Age: %d", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: <fold text='${' expand='false'>%s, log:%s    $", </fold>data.<fold text='name' expand='false'>getName()</fold><fold text='}, log:${' expand='false'>, </fold>logPrintfStyle(data)<fold text='}    $")' expand='false'>)</fold>;
        log.warn("Warning message with three parameters - Name: <fold text='$' expand='false'>%s, Age: %s, City: %s", </fold>name<fold text=', Age: ${' expand='false'>, </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, City: $' expand='false'>, </fold>city<fold text='")' expand='false'>)</fold>;

        log.error("Missing 1 parameter - 1: <fold text='$' expand='false'>%s, 2: %d, 3: %s, empty: %s", </fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', 3: $' expand='false'>, </fold>city<fold text=', empty: %s")' expand='false'>)</fold>;
        log.error("Missing 2 parameters - 1: <fold text='$' expand='false'>%s, 2: %d, empty: %s, empty: %s", </fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', empty: %s, empty: %s")' expand='false'>)</fold>;
        log.error("Missing 3 parameters - 1: <fold text='$' expand='false'>%s, empty: %s, empty: %s, empty: %s", </fold>name<fold text=', empty: %s, empty: %s, empty: %s")' expand='false'>)</fold>;
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try <fold text='{...}' expand='true'>{
            log.warn("Warning message with 3 parameters and formatting - 1: <fold text='$' expand='false'>%s, 2: %s, 3: %s",
                    </fold>name<fold text=', 2: ${' expand='false'>,

                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, 3: $' expand='false'>,

                    </fold>city<fold text='"' expand='false'>
            </fold>);

            log.warn("Warning message with 3 parameters and formatting - 1: <fold text='${' expand='false'>%s, 2: %s, 3: %s",
                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, 2: $' expand='false'>,
                    </fold>name<fold text=', 3: ${' expand='false'>,
                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}"' expand='false'>
            </fold>);
        }</fold> catch (Exception e) <fold text='{...}' expand='true'>{
            log.error("error1 <fold text='$' expand='false'>%s", </fold>e<fold text='",' expand='false'>,</fold> e.<fold text='message' expand='false'>getMessage()</fold>, e);
            log.error("error2 <fold text='${' expand='false'>%s", </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}",' expand='false'>,</fold> data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold>, data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold>);
        }</fold>

        // 1. String.format()
        String formatted = String.format("Hello, <fold text='$' expand='false'>%s! Your age is %d", </fold>name<fold text='! Your age is $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;
        log.info("String.format example: <fold text='$' expand='false'>{}", </fold>formatted<fold text='")' expand='false'>)</fold>;

        // 2. System.out.printf()
        System.out.printf("User: <fold text='$' expand='false'>%s, Age: %d, City: %s%n", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text=', City: $' expand='false'>, </fold>city<fold text='%n")' expand='false'>)</fold>;

        // 3. System.err.printf()
        System.err.printf("Error scenario: User <fold text='$' expand='false'>%s not found in %s and ignore new-line break%n", </fold>name<fold text=' not found in $' expand='false'>, </fold>city<fold text=' and ignore new-line break%n",' expand='false'>,</fold> <fold text='"ignored"' expand='false'>"ignored"</fold>);

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: <fold text='$' expand='false'>%s, Age: %d, City: %s", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text=', City: $' expand='false'>, </fold>city<fold text='")' expand='false'>)</fold>;
        log.info("Formatter example: <fold text='${' expand='false'>{}", </fold>formatter.toString()<fold text='}")' expand='false'>)</fold>;

        // 5. PrintWriter with printf
        try <fold text='{...}' expand='true'>{
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User <fold text='$' expand='false'>%s, Age %d, accessed from %s", </fold>name<fold text=', Age $' expand='false'>, </fold>age<fold text=', accessed from $' expand='false'>, </fold>city<fold text='")' expand='false'>)</fold>;
            writer.close();
        }</fold> catch (FileNotFoundException e) <fold text='{...}' expand='true'>{
            log.error("Failed to write to log file: <fold text='${' expand='false'>%s", </fold>e.<fold text='message' expand='false'>getMessage()</fold><fold text='}")' expand='false'>)</fold>;
        }</fold>

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: <fold text='$' expand='false'>%s".formatted(</fold>name<fold text='".formatted()' expand='false'>)</fold>);
        System.out.println("Trace message - Name: <fold text='${' expand='false'>%s, log:%s    $".formatted(</fold>data.<fold text='name' expand='false'>getName()</fold><fold text='}, log:${' expand='false'>, </fold>logPrintfStyle(data)<fold text='}    $".formatted()' expand='false'>)</fold>);
        System.out.println("Warning message with three parameters - Name: <fold text='$' expand='false'>%s, Age: %s, City: %s".formatted(</fold>name<fold text=', Age: ${' expand='false'>, </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, City: $' expand='false'>, </fold>city<fold text='".formatted()' expand='false'>)</fold>);

        System.out.println("Missing 1 parameter - 1: <fold text='$' expand='false'>%s, 2: %d, 3: %s, empty: %s".formatted(</fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', 3: $' expand='false'>, </fold>city<fold text=', empty: %s".formatted()' expand='false'>)</fold>);
        System.out.println("Missing 2 parameters - 1: <fold text='$' expand='false'>%s, 2: %d, empty: %s, empty: %s".formatted(</fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', empty: %s, empty: %s".formatted()' expand='false'>)</fold>);
        System.out.println("Missing 3 parameters - 1: <fold text='$' expand='false'>%s, empty: %s, empty: %s, empty: %s".formatted(</fold>name<fold text=', empty: %s, empty: %s, empty: %s".formatted()' expand='false'>)</fold>);
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: <fold text='$' expand='false'>%s".formatted(</fold>name<fold text='".formatted(' expand='false'>,</fold> data));
        System.out.println("Additional 2 parameters - Name: <fold text='$' expand='false'>%s".formatted(</fold>name<fold text='".formatted(' expand='false'>,</fold> data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error("""
                Missing 1 parameter - 1: <fold text='$' expand='false'>%s, 2: %d, 3: %s, empty: %s
                """, </fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', 3: $' expand='false'>, </fold>city<fold text=', empty: %s
                """)' expand='false'>)</fold>;
        return data;
    }</fold>

    public static class Data <fold text='{...}' expand='true'>{
        private LogBrackets.Data data;
        private String name;

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        public LogBrackets.Data getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

}
