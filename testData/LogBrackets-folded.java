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
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");

        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");

        log.debug("Debug message with 1 parameter - Name: $name");
        log.debug("Debug message with 1 parameter - Name: $name");
        log.trace("Trace message - Name: $data.name, log:$log(data)    $");
        log.warn("Warning message with three parameters - Name: $name, Age: $data.data.name, City: $city");

        log.error("Missing 1 parameter - Name: $name, Age: $age, City: $city, Salary: {}");
        log.error("Missing 2 parameters - Name: $name, Age: $age, City: {}, Salary: {}");
        log.error("Missing 3 parameters - Name: $name, Age: {}, City: {}, Salary: {}");
        log.error("Missing all parameters - Name: {}, Age: {}, City: {}, Salary: {}");

        try {
            log.warn("Warning message with 3 parameters and formatting - Name: $name, Age: $data.data.name, City: $city");
        } catch (Exception e) {
            log.error("error1 $e.message"$e);
            log.error("error2 $log(data)"$e, e);
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
