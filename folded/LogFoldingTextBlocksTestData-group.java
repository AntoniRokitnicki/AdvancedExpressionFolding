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
        log.debug("Debug message with 1 parameter - Name: [0:"%s\", "][50:"%s\", "]name[0:")"][50:")"];

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [1:"%s, Age: %d\", "][50:"%s, Age: %d\", "]name[1:", "][50:", "]age[1:")"][50:")"];

        log.info("Info message with 2 parameters - Name: [2:"%s, Age: %d    \", "][50:"%s, Age: %d    \", "]name[2:", "][50:", "]age[2:")"][50:")"];
        log.info("Info message with 2 parameters - Name: [3:"%s, Age: %d\", "][50:"%s, Age: %d\", "]name[3:", "][50:", "]age[3:")"][50:")"];


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: [4:"%s, log:%s    $\", "][50:"%s, log:%s    $\", "]data.[5:"getName()"][51:"getName()"][52:"getName()"][5:", "][51:", "]logPrintfStyle(data)[5:")"][51:")"];
        log.warn("Warning message with three parameters - Name: [6:"%s, Age: %s, City: %s\", "][52:"%s, Age: %s, City: %s\", "]name[6:", "][52:", "]data.[8:"getData()"][54:"getData()"][56:"getData()"].[7:"getName()"][53:"getName()"][55:"getName()"][8:", "][54:", "]city[8:")"][54:")"];

        log.error("Missing 1 parameter - 1: [9:"%s, 2: %d, 3: %s, empty: %s\", "][56:"%s, 2: %d, 3: %s, empty: %s\", "]name[9:", "][56:", "]age[9:", "][56:", "]city[9:")"][56:")"];
        log.error("Missing 2 parameters - 1: [10:"%s, 2: %d, empty: %s, empty: %s\", "][56:"%s, 2: %d, empty: %s, empty: %s\", "]name[10:", "][56:", "]age[10:")"][56:")"];
        log.error("Missing 3 parameters - 1: [11:"%s, empty: %s, empty: %s, empty: %s\", "][56:"%s, empty: %s, empty: %s, empty: %s\", "]name[11:")"][56:")"];
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [12:"%s, 2: %s, 3: %s\",\n                    "][56:"%s, 2: %s, 3: %s\",\n                    "]name[12:",\n\n                    "][56:",\n\n                    "]data.[14:"getData()"][58:"getData()"][60:"getData()"].[13:"getName()"][57:"getName()"][59:"getName()"][14:",\n\n                    "][58:",\n\n                    "]city[14:"\n            "][58:"\n            "]);

            log.warn("Warning message with 3 parameters and formatting - 1: [15:"%s, 2: %s, 3: %s\",\n                    "][60:"%s, 2: %s, 3: %s\",\n                    "]data.[17:"getData()"][62:"getData()"][66:"getData()"].[16:"getName()"][61:"getName()"][65:"getName()"][17:",\n                    "][62:",\n                    "]name[17:",\n                    "][62:",\n                    "]data.[19:"getData()"][64:"getData()"][68:"getData()"].[18:"getName()"][63:"getName()"][67:"getName()"][19:"\n            "][64:"\n            "]);
        } catch (Exception e) {
            log.error("error1 [20:"%s\", "][68:"%s\", "]e[20:","][68:","] e.[21:"getMessage()"][69:"getMessage()"][70:"getMessage()"], e);
            log.error("error2 [22:"%s\", "][70:"%s\", "]data.[24:"getData()"][72:"getData()"][78:"getData()"].[23:"getName()"][71:"getName()"][77:"getName()"][24:","][72:","] data.[26:"getData()"][74:"getData()"][80:"getData()"].[25:"getName()"][73:"getName()"][79:"getName()"], data.[28:"getData()"][76:"getData()"][82:"getData()"].[27:"getName()"][75:"getName()"][81:"getName()"]);
        }

        // 1. String.format()
        String formatted = String.format("Hello, [29:"%s! Your age is %d\", "][82:"%s! Your age is %d\", "]name[29:", "][82:", "]age[29:")"][82:")"];
        log.info("String.format example: [30:"{}\", "][82:"{}\", "]formatted[30:")"][82:")"];

        // 2. System.out.printf()
        System.out.printf("User: [31:"%s, Age: %d, City: %s%n\", "][82:"%s, Age: %d, City: %s%n\", "]name[31:", "][82:", "]age[31:", "][82:", "]city[31:")"][82:")"];

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [32:"%s not found in %s and ignore new-line break%n\", "][82:"%s not found in %s and ignore new-line break%n\", "]name[32:", "][82:", "]city[32:","][82:","] [33:"\"ignored\""][83:"\"ignored\""]);

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: [34:"%s, Age: %d, City: %s\", "][83:"%s, Age: %d, City: %s\", "]name[34:", "][83:", "]age[34:", "][83:", "]city[34:")"][83:")"];
        log.info("Formatter example: [35:"{}\", "][83:"{}\", "]formatter.toString()[35:")"][83:")"];

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [36:"%s, Age %d, accessed from %s\", "][83:"%s, Age %d, accessed from %s\", "]name[36:", "][83:", "]age[36:", "][83:", "]city[36:")"][83:")"];
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: [37:"%s\", "][83:"%s\", "]e.[38:"getMessage()"][84:"getMessage()"][85:"getMessage()"][38:")"][84:")"];
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: [39:"%s\".formatted("][85:"%s\".formatted("]name[39:")"][85:")"]);
        System.out.println("Trace message - Name: [40:"%s, log:%s    $\".formatted("][85:"%s, log:%s    $\".formatted("]data.[41:"getName()"][86:"getName()"][87:"getName()"][41:", "][86:", "]logPrintfStyle(data)[41:")"][86:")"]);
        System.out.println("Warning message with three parameters - Name: [42:"%s, Age: %s, City: %s\".formatted("][87:"%s, Age: %s, City: %s\".formatted("]name[42:", "][87:", "]data.[44:"getData()"][89:"getData()"][91:"getData()"].[43:"getName()"][88:"getName()"][90:"getName()"][44:", "][89:", "]city[44:")"][89:")"]);

        System.out.println("Missing 1 parameter - 1: [45:"%s, 2: %d, 3: %s, empty: %s\".formatted("][91:"%s, 2: %d, 3: %s, empty: %s\".formatted("]name[45:", "][91:", "]age[45:", "][91:", "]city[45:")"][91:")"]);
        System.out.println("Missing 2 parameters - 1: [46:"%s, 2: %d, empty: %s, empty: %s\".formatted("][91:"%s, 2: %d, empty: %s, empty: %s\".formatted("]name[46:", "][91:", "]age[46:")"][91:")"]);
        System.out.println("Missing 3 parameters - 1: [47:"%s, empty: %s, empty: %s, empty: %s\".formatted("][91:"%s, empty: %s, empty: %s, empty: %s\".formatted("]name[47:")"][91:")"]);
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: [48:"%s\".formatted("][91:"%s\".formatted("]name[48:","][91:","] data));
        System.out.println("Additional 2 parameters - Name: [49:"%s\".formatted("][91:"%s\".formatted("]name[49:","][91:","] data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error([92:"\"\"\"\n                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s\n                \"\"\""]   \"\"\", "][91:"%s, 2: %d, 3: %s, empty: %s\n                \"\"\", "]name[50:", "][91:", "]age[50:", "][91:", "]city[50:")"][91:")"];
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
