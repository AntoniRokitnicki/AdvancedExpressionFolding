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
        log.debug("Debug message with 1 parameter - Name: [0:"%s", "]{[0:"%s", "]}name[0:")"]{[0:")"]};

        log.info(MY_MARKER, "Info message with 2 parameters - Name: [0:"%s, Age: %d", "]{[0:"%s, Age: %d", "]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]};

        log.info("Info message with 2 parameters - Name: [0:"%s, Age: %d    ", "]{[0:"%s, Age: %d    ", "]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]};
        log.info("Info message with 2 parameters - Name: [0:"%s, Age: %d", "]{[0:"%s, Age: %d", "]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]};


        log.debug("Debug message with 1 parameter - Name: " + name);
        log.trace("Trace message - Name: [0:"%s, log:%s    $", "]{[0:"%s, log:%s    $", "]}data.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:", "]{[0:", "]}logPrintfStyle(data)[0:")"]{[0:")"]};
        log.warn("Warning message with three parameters - Name: [0:"%s, Age: %s, City: %s", "]{[0:"%s, Age: %s, City: %s", "]}name[0:", "]{[0:", "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:", "]{[0:", "]}city[0:")"]{[0:")"]};

        log.error("Missing 1 parameter - 1: [0:"%s, 2: %d, 3: %s, empty: %s", "]{[0:"%s, 2: %d, 3: %s, empty: %s", "]}name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]};
        log.error("Missing 2 parameters - 1: [0:"%s, 2: %d, empty: %s, empty: %s", "]{[0:"%s, 2: %d, empty: %s, empty: %s", "]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]};
        log.error("Missing 3 parameters - 1: [0:"%s, empty: %s, empty: %s, empty: %s", "]{[0:"%s, empty: %s, empty: %s, empty: %s", "]}name[0:")"]{[0:")"]};
        log.error("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s");

        try {
            log.warn("Warning message with 3 parameters and formatting - 1: [0:"%s, 2: %s, 3: %s",
                    "]{[0:"%s, 2: %s, 3: %s",
                    "]}name[0:",

                    "]{[0:",

                    "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:",

                    "]{[0:",

                    "]}city[0:"
            "]{[0:"
            "]});

            log.warn("Warning message with 3 parameters and formatting - 1: [0:"%s, 2: %s, 3: %s",
                    "]{[0:"%s, 2: %s, 3: %s",
                    "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:",
                    "]{[0:",
                    "]}name[0:",
                    "]{[0:",
                    "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:"
            "]{[0:"
            "]});
        } catch (Exception e) {
            log.error("error1 [0:"%s", "]{[0:"%s", "]}e[0:","]{[0:","]} e.[0:"getMessage()"]{[0:"getMessage()"]{[0:"getMessage()"]}}, e);
            log.error("error2 [0:"%s", "]{[0:"%s", "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:","]{[0:","]} data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}, data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}});
        }

        // 1. String.format()
        String formatted = String.format("Hello, [0:"%s! Your age is %d", "]{[0:"%s! Your age is %d", "]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]};
        log.info("String.format example: [0:"{}", "]{[0:"{}", "]}formatted[0:")"]{[0:")"]};

        // 2. System.out.printf()
        System.out.printf("User: [0:"%s, Age: %d, City: %s%n", "]{[0:"%s, Age: %d, City: %s%n", "]}name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]};

        // 3. System.err.printf()
        System.err.printf("Error scenario: User [0:"%s not found in %s and ignore new-line break%n", "]{[0:"%s not found in %s and ignore new-line break%n", "]}name[0:", "]{[0:", "]}city[0:","]{[0:","]} [0:""ignored""]{[0:""ignored""]});

        // 4. Formatter class
        Formatter formatter = new Formatter();
        formatter.format("User details - Name: [0:"%s, Age: %d, City: %s", "]{[0:"%s, Age: %d, City: %s", "]}name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]};
        log.info("Formatter example: [0:"{}", "]{[0:"{}", "]}formatter.toString()[0:")"]{[0:")"]};

        // 5. PrintWriter with printf
        try {
            PrintWriter writer = new PrintWriter(new File("log.txt"));
            writer.printf("Log entry: User [0:"%s, Age %d, accessed from %s", "]{[0:"%s, Age %d, accessed from %s", "]}name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]};
            writer.close();
        } catch (FileNotFoundException e) {
            log.error("Failed to write to log file: [0:"%s", "]{[0:"%s", "]}e.[0:"getMessage()"]{[0:"getMessage()"]{[0:"getMessage()"]}}[0:")"]{[0:")"]};
        }

        // 6. String with formatted
        System.out.println("Debug message with 1 parameter - Name: [0:"%s".formatted("]{[0:"%s".formatted("]}name[0:")"]{[0:")"]});
        System.out.println("Trace message - Name: [0:"%s, log:%s    $".formatted("]{[0:"%s, log:%s    $".formatted("]}data.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:", "]{[0:", "]}logPrintfStyle(data)[0:")"]{[0:")"]});
        System.out.println("Warning message with three parameters - Name: [0:"%s, Age: %s, City: %s".formatted("]{[0:"%s, Age: %s, City: %s".formatted("]}name[0:", "]{[0:", "]}data.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}.[0:"getName()"]{[0:"getName()"]{[0:"getName()"]}}[0:", "]{[0:", "]}city[0:")"]{[0:")"]});

        System.out.println("Missing 1 parameter - 1: [0:"%s, 2: %d, 3: %s, empty: %s".formatted("]{[0:"%s, 2: %d, 3: %s, empty: %s".formatted("]}name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]});
        System.out.println("Missing 2 parameters - 1: [0:"%s, 2: %d, empty: %s, empty: %s".formatted("]{[0:"%s, 2: %d, empty: %s, empty: %s".formatted("]}name[0:", "]{[0:", "]}age[0:")"]{[0:")"]});
        System.out.println("Missing 3 parameters - 1: [0:"%s, empty: %s, empty: %s, empty: %s".formatted("]{[0:"%s, empty: %s, empty: %s, empty: %s".formatted("]}name[0:")"]{[0:")"]});
        System.out.println("Missing all parameters - - empty: %s, empty: %s, empty: %s, empty: %s".formatted());
        System.out.println("Additional 1 parameter - Name: [0:"%s".formatted("]{[0:"%s".formatted("]}name[0:","]{[0:","]} data));
        System.out.println("Additional 2 parameters - Name: [0:"%s".formatted("]{[0:"%s".formatted("]}name[0:","]{[0:","]} data, logPrintfStyle(data)));

        // 7. Text Block examples (Java 15+)
        log.error([0:""""
                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s
                """"]{[0:"%s, 2: %d, 3: %s, empty: %s
                """, "]{[0:"%s, 2: %d, 3: %s, empty: %s
                """, "]}}, name[0:", "]{[0:", "]}age[0:", "]{[0:", "]}city[0:")"]{[0:")"]};
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
