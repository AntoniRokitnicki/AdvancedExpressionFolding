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
[0:"p"]ublic class LogBrackets {[0:"\n\n    "][0:"private static final Logger log = LoggerFactory.getLogger(LogBrackets.class);"]
    private [2:"static final "][2:"Marker"] MY_MARKER = MarkerFactory.getMarker("MY_MARKER");

    public Data logPrintfStyle(Data data) {
        [3:"String"] name = "John";
        [4:"int"] age = 30;
        [5:"String"] city = "New York";
        log.debug("Debug message with 1 parameter - Name: [6:"%s\", "]name[6:")"];

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [7:"%s, Age: %d\", "]name[7:", "]age[7:")"];

        log.info("Info message with 2 parameters - Name: [8:"%s, Age: %d    \", "]name[8:", "]age[8:")"];
        log.info("Info message with 2 parameters - Name: [9:"%s, Age: %d\", "]name[9:", "]age[9:")"];


        log.debug("Debug message with 1 parameter - Name: [10:"\" + "]name[10:")"];
        log.trace("Trace message - Name: [11:"%s, log:%s    $\", "]data.[12:"getName()"][12:", "]logPrintfStyle(data)[12:")"];
        log.warn("Warning message with three parameters - Name: [13:"%s, Age: %s, City: %s\", "]name[13:", "]data.[15:"getData()"].[14:"getName()"][15:", "]city[15:")"];

        log.error("Missing 1 parameter - 1: [16:"%s, 2: %d, 3: %s, empty: %s\", "]name[16:", "]age[16:", "]city[16:")"];
        log.error("Missing 2 parameters - 1: [17:"%s, 2: %d, empty: %s, empty: %s\", "]name[17:", "]age[17:")"];
        log.error("Missing 3 parameters - 1: [18:"%s, empty: %s, empty: %s, empty: %s\", "]name[18:")"];
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [19:"%s, 2: %s, 3: %s\",\n                    "]name[19:",\n\n                    "]data.[21:"getData()"].[20:"getName()"][21:",\n\n                    "]city[21:"\n            "]);

            log.warn("Warning message with 3 parameters and formatting - 1: [22:"%s, 2: %s, 3: %s\",\n                    "]data.[24:"getData()"].[23:"getName()"][24:",\n                    "]name[24:",\n                    "]data.[26:"getData()"].[25:"getName()"][26:"\n            "]);
        } catch [27:"("]Exception e[27:")"] {
            log.error("error1 [28:"%s\", "]e[28:","] e.[29:"getMessage()"], e);
            log.error("error2 [30:"%s\", "]data.[32:"getData()"].[31:"getName()"][32:","] data.[34:"getData()"].[33:"getName()"], data.[36:"getData()"].[35:"getName()"]);
        }

        // 1. String.format()
        [37:"String"] formatted = String.format("Hello, [38:"%s! Your age is %d\", "]name[38:", "]age[38:")"];
        log.info("String.format example: [39:"{}\", "]formatted[39:")"];

        // 2. System.out.printf()
        System.out.printf("User: [40:"%s, Age: %d, City: %s%n\", "]name[40:", "]age[40:", "]city[40:")"];

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [41:"%s not found in %s and ignore new-line break%n\", "]name[41:", "]city[41:","] [42:"\"ignored\""]);

        // 4. Formatter class
        [43:"Formatter"] formatter = new Formatter();
        formatter.format("User details - Name: [44:"%s, Age: %d, City: %s\", "]name[44:", "]age[44:", "]city[44:")"];
        log.info("Formatter example: [45:"{}\", "]formatter.toString()[45:")"];

        // 5. PrintWriter with printf
        try {
            [46:"PrintWriter"] writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [47:"%s, Age %d, accessed from %s\", "]name[47:", "]age[47:", "]city[47:")"];
            writer.close();
        } catch [48:"("]FileNotFoundException e[48:")"] {
            log.error("Failed to write to log file: [49:"%s\", "]e.[50:"getMessage()"][50:")"];
        }

        // 6. String with formatted
        [51:"System.out."]println("Debug message with 1 parameter - Name: [51:"%s\".formatted("]name[51:")"]);
        [53:"System.out."]println("Trace message - Name: [53:"%s, log:%s    $\".formatted("]data.[54:"getName()"][54:", "]logPrintfStyle(data)[54:")"]);
        [57:"System.out."]println("Warning message with three parameters - Name: [57:"%s, Age: %s, City: %s\".formatted("]name[57:", "]data.[59:"getData()"].[58:"getName()"][59:", "]city[59:")"]);

        [63:"System.out."]println("Missing 1 parameter - 1: [63:"%s, 2: %d, 3: %s, empty: %s\".formatted("]name[63:", "]age[63:", "]city[63:")"]);
        [65:"System.out."]println("Missing 2 parameters - 1: [65:"%s, 2: %d, empty: %s, empty: %s\".formatted("]name[65:", "]age[65:")"]);
        [67:"System.out."]println("Missing 3 parameters - 1: [67:"%s, empty: %s, empty: %s, empty: %s\".formatted("]name[67:")"]);
        [69:"System.out."]println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        [70:"System.out."]println("Additional 1 parameter - Name: [70:"%s\".formatted("]name[70:","] data));
        [72:"System.out."]println("Additional 2 parameters - Name: [72:"%s\".formatted("]name[72:","] data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error([137:"\"\"\"\n                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s\n                \"\"\""], name[74:", "]age[74:", "]city[74:")"];
        return data;
    }

    [138:"p"]ublic static class Data {
        private Data data;
        private String name;[138:"\n\n        "][138:"public String getName() {\n            return name;\n        }"][138:"\n\n        "][138:"public Data getData() {\n            return data;\n        }"]
    }

}
