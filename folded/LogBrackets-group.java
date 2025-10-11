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
        log.debug("Debug message with 1 parameter - Name: [0:"%s\", "][49:"%s\", "]name[0:")"][49:")"];

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [1:"%s, Age: %d\", "][49:"%s, Age: %d\", "]name[1:", "][49:", "]age[1:")"][49:")"];

        log.info("Info message with 2 parameters - Name: [2:"%s, Age: %d    \", "][49:"%s, Age: %d    \", "]name[2:", "][49:", "]age[2:")"][49:")"];
        log.info("Info message with 2 parameters - Name: [3:"%s, Age: %d\", "][49:"%s, Age: %d\", "]name[3:", "][49:", "]age[3:")"][49:")"];


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: [4:"%s, log:%s    $\", "][49:"%s, log:%s    $\", "]data.[5:"getName()"][50:"getName()"][51:"getName()"][5:", "][50:", "]logPrintfStyle(data)[5:")"][50:")"];
        log.warn("Warning message with three parameters - Name: [6:"%s, Age: %s, City: %s\", "][51:"%s, Age: %s, City: %s\", "]name[6:", "][51:", "]data.[8:"getData()"][53:"getData()"][55:"getData()"].[7:"getName()"][52:"getName()"][54:"getName()"][8:", "][53:", "]city[8:")"][53:")"];

        log.error("Missing 1 parameter - 1: [9:"%s, 2: %d, 3: %s, empty: %s\", "][55:"%s, 2: %d, 3: %s, empty: %s\", "]name[9:", "][55:", "]age[9:", "][55:", "]city[9:")"][55:")"];
        log.error("Missing 2 parameters - 1: [10:"%s, 2: %d, empty: %s, empty: %s\", "][55:"%s, 2: %d, empty: %s, empty: %s\", "]name[10:", "][55:", "]age[10:")"][55:")"];
        log.error("Missing 3 parameters - 1: [11:"%s, empty: %s, empty: %s, empty: %s\", "][55:"%s, empty: %s, empty: %s, empty: %s\", "]name[11:")"][55:")"];
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [12:"%s, 2: %s, 3: %s\",\n                    "][55:"%s, 2: %s, 3: %s\",\n                    "]name[12:",\n\n                    "][55:",\n\n                    "]data.[14:"getData()"][57:"getData()"][59:"getData()"].[13:"getName()"][56:"getName()"][58:"getName()"][14:",\n\n                    "][57:",\n\n                    "]city[14:"\n            "][57:"\n            "]);

            log.warn("Warning message with 3 parameters and formatting - 1: [15:"%s, 2: %s, 3: %s\",\n                    "][59:"%s, 2: %s, 3: %s\",\n                    "]data.[17:"getData()"][61:"getData()"][65:"getData()"].[16:"getName()"][60:"getName()"][64:"getName()"][17:",\n                    "][61:",\n                    "]name[17:",\n                    "][61:",\n                    "]data.[19:"getData()"][63:"getData()"][67:"getData()"].[18:"getName()"][62:"getName()"][66:"getName()"][19:"\n            "][63:"\n            "]);
        } catch (Exception e) {
            log.error("error1 [20:"%s\", "][67:"%s\", "]e[20:","][67:","] e.[21:"getMessage()"][68:"getMessage()"][69:"getMessage()"], e);
            log.error("error2 [22:"%s\", "][69:"%s\", "]data.[24:"getData()"][71:"getData()"][77:"getData()"].[23:"getName()"][70:"getName()"][76:"getName()"][24:","][71:","] data.[26:"getData()"][73:"getData()"][79:"getData()"].[25:"getName()"][72:"getName()"][78:"getName()"], data.[28:"getData()"][75:"getData()"][81:"getData()"].[27:"getName()"][74:"getName()"][80:"getName()"]);
        }

        // 1. String.format()
        String formatted = String.format("Hello, [29:"%s! Your age is %d\", "][81:"%s! Your age is %d\", "]name[29:", "][81:", "]age[29:")"][81:")"];
        log.info("String.format example: [30:"{}\", "][81:"{}\", "]formatted[30:")"][81:")"];

        // 2. System.out.printf()
        System.out.printf("User: [31:"%s, Age: %d, City: %s%n\", "][81:"%s, Age: %d, City: %s%n\", "]name[31:", "][81:", "]age[31:", "][81:", "]city[31:")"][81:")"];

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [32:"%s not found in %s and ignore new-line break%n\", "][81:"%s not found in %s and ignore new-line break%n\", "]name[32:", "][81:", "]city[32:","][81:","] [33:"\"ignored\""][82:"\"ignored\""]);

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: [34:"%s, Age: %d, City: %s\", "][82:"%s, Age: %d, City: %s\", "]name[34:", "][82:", "]age[34:", "][82:", "]city[34:")"][82:")"];
        log.info("Formatter example: [35:"{}\", "][82:"{}\", "]formatter.toString()[35:")"][82:")"];

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [36:"%s, Age %d, accessed from %s\", "][82:"%s, Age %d, accessed from %s\", "]name[36:", "][82:", "]age[36:", "][82:", "]city[36:")"][82:")"];
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: [37:"%s\", "][82:"%s\", "]e.[38:"getMessage()"][83:"getMessage()"][84:"getMessage()"][38:")"][83:")"];
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: [39:"%s\".formatted("][84:"%s\".formatted("]name[39:")"][84:")"]);
        System.out.println("Trace message - Name: [40:"%s, log:%s    $\".formatted("][84:"%s, log:%s    $\".formatted("]data.[41:"getName()"][85:"getName()"][86:"getName()"][41:", "][85:", "]logPrintfStyle(data)[41:")"][85:")"]);
        System.out.println("Warning message with three parameters - Name: [42:"%s, Age: %s, City: %s\".formatted("][86:"%s, Age: %s, City: %s\".formatted("]name[42:", "][86:", "]data.[44:"getData()"][88:"getData()"][90:"getData()"].[43:"getName()"][87:"getName()"][89:"getName()"][44:", "][88:", "]city[44:")"][88:")"]);

        System.out.println("Missing 1 parameter - 1: [45:"%s, 2: %d, 3: %s, empty: %s\".formatted("][90:"%s, 2: %d, 3: %s, empty: %s\".formatted("]name[45:", "][90:", "]age[45:", "][90:", "]city[45:")"][90:")"]);
        System.out.println("Missing 2 parameters - 1: [46:"%s, 2: %d, empty: %s, empty: %s\".formatted("][90:"%s, 2: %d, empty: %s, empty: %s\".formatted("]name[46:", "][90:", "]age[46:")"][90:")"]);
        System.out.println("Missing 3 parameters - 1: [47:"%s, empty: %s, empty: %s, empty: %s\".formatted("][90:"%s, empty: %s, empty: %s, empty: %s\".formatted("]name[47:")"][90:")"]);
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: [48:"%s\".formatted("][90:"%s\".formatted("]name[48:","][90:","] data));
        System.out.println("Additional 2 parameters - Name: [49:"%s\".formatted("][90:"%s\".formatted("]name[49:","][90:","] data, logPrintfStyle(data)));

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
