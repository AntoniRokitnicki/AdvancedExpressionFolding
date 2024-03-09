package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@SuppressWarnings("ALL")
public class LogBrackets {

    private static final Logger log = LoggerFactory.getLogger(LogBrackets.class);
    private static final Marker MY_MARKER = MarkerFactory.getMarker("MY_MARKER");


    public Data log(Data data) {
        String name = "John";
        int age = 30;
        String city = "New York";
        log.info(MY_MARKER, "Info message with 2 parameters - Name: {}, Age: {}", name, age);

        log.info("Info message with 2 parameters - Name: {}, Age: {}    ", name, age);
        log.info("Info message with 2 parameters - Name: {}, Age: {}", name, age);

        log.debug("Debug message with 1 parameter - Name: {}", name);
        log.debug("Debug message with 1 parameter - Name: $name");
        log.trace("Trace message - Name: {}, log:{}    $", data.getName(), log(data));
        log.warn("Warning message with three parameters - Name: {}, Age: {}, City: {}", name, data.getData().getName(), city);

        log.error("Missing 1 parameter - Name: {}, Age: {}, City: {}, Salary: {}", name, age, city);
        log.error("Missing 2 parameters - Name: {}, Age: {}, City: {}, Salary: {}", name, age);
        log.error("Missing 3 parameters - Name: {}, Age: {}, City: {}, Salary: {}", name);
        log.error("Missing all parameters - Name: {}, Age: {}, City: {}, Salary: {}");

        try {
            log.warn("Warning message with 3 parameters and formatting - Name: {}, Age: {}, City: {}",
                    name,
    
                    data.getData().getName(),
    
                    city
            );
        } catch (Exception e) {
            log.error("error1 {}", e.getMessage(), e);
            log.error("error2 {}", log(data), e, e);
        }

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
