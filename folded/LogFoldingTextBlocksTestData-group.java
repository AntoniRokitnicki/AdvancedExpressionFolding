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
        log.debug("Debug message with 1 parameter - Name: [0:"%s\", "]name[0:")"];

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [1:"%s, Age: %d\", "]name[1:", "]age[1:")"];

        log.info("Info message with 2 parameters - Name: [2:"%s, Age: %d    \", "]name[2:", "]age[2:")"];
        log.info("Info message with 2 parameters - Name: [3:"%s, Age: %d\", "]name[3:", "]age[3:")"];


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: [4:"%s, log:%s    $\", "]data.[5:"getName()"][5:", "]logPrintfStyle(data)[5:")"];
        log.warn("Warning message with three parameters - Name: [6:"%s, Age: %s, City: %s\", "]name[6:", "]data.[8:"getData()"].[7:"getName()"][8:", "]city[8:")"];

        log.error("Missing 1 parameter - 1: [9:"%s, 2: %d, 3: %s, empty: %s\", "]name[9:", "]age[9:", "]city[9:")"];
        log.error("Missing 2 parameters - 1: [10:"%s, 2: %d, empty: %s, empty: %s\", "]name[10:", "]age[10:")"];
        log.error("Missing 3 parameters - 1: [11:"%s, empty: %s, empty: %s, empty: %s\", "]name[11:")"];
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [12:"%s, 2: %s, 3: %s\",\n                    "]name[12:",\n\n                    "]data.[14:"getData()"].[13:"getName()"][14:",\n\n                    "]city[14:"\n            "]);

            log.warn("Warning message with 3 parameters and formatting - 1: [15:"%s, 2: %s, 3: %s\",\n                    "]data.[17:"getData()"].[16:"getName()"][17:",\n                    "]name[17:",\n                    "]data.[19:"getData()"].[18:"getName()"][19:"\n            "]);
        } catch (Exception e) {
            log.error("error1 [20:"%s\", "]e[20:","] e.[21:"getMessage()"], e);
            log.error("error2 [22:"%s\", "]data.[24:"getData()"].[23:"getName()"][24:","] data.[26:"getData()"].[25:"getName()"], data.[28:"getData()"].[27:"getName()"]);
        }

        // 1. String.format()
        String formatted = String.format("Hello, [29:"%s! Your age is %d\", "]name[29:", "]age[29:")"];
        log.info("String.format example: [30:"{}\", "]formatted[30:")"];

        // 2. System.out.printf()
        System.out.printf("User: [31:"%s, Age: %d, City: %s%n\", "]name[31:", "]age[31:", "]city[31:")"];

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [32:"%s not found in %s and ignore new-line break%n\", "]name[32:", "]city[32:","] [33:"\"ignored\""]);

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: [34:"%s, Age: %d, City: %s\", "]name[34:", "]age[34:", "]city[34:")"];
        log.info("Formatter example: [35:"{}\", "]formatter.toString()[35:")"];

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [36:"%s, Age %d, accessed from %s\", "]name[36:", "]age[36:", "]city[36:")"];
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: [37:"%s\", "]e.[38:"getMessage()"][38:")"];
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: [39:"%s\".formatted("]name[39:")"]);
        System.out.println("Trace message - Name: [40:"%s, log:%s    $\".formatted("]data.[41:"getName()"][41:", "]logPrintfStyle(data)[41:")"]);
        System.out.println("Warning message with three parameters - Name: [42:"%s, Age: %s, City: %s\".formatted("]name[42:", "]data.[44:"getData()"].[43:"getName()"][44:", "]city[44:")"]);

        System.out.println("Missing 1 parameter - 1: [45:"%s, 2: %d, 3: %s, empty: %s\".formatted("]name[45:", "]age[45:", "]city[45:")"]);
        System.out.println("Missing 2 parameters - 1: [46:"%s, 2: %d, empty: %s, empty: %s\".formatted("]name[46:", "]age[46:")"]);
        System.out.println("Missing 3 parameters - 1: [47:"%s, empty: %s, empty: %s, empty: %s\".formatted("]name[47:")"]);
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: [48:"%s\".formatted("]name[48:","] data));
        System.out.println("Additional 2 parameters - Name: [49:"%s\".formatted("]name[49:","] data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error([92:"\"\"\"\n                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s\n                \"\"\""], name[50:", "]age[50:", "]city[50:")"];
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
