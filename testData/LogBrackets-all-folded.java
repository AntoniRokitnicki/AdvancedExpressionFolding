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
        val name = "John";
        val age = 30;
        val city = "New York";
        log.info(MY_MARKER, "Info message with two parameters - Name: $name, Age: $age");

        log.info("Info message with two parameters - Name: $name, Age: $age    ");
        log.info("Info message with two parameters - Name: $name, Age: $age");

        log.debug("Debug message with one parameter - Name: $name");
        log.debug("Debug message with one parameter - Name: $name");
        log.trace("Trace message with three parameters - Name: $data.name, log:$log(data)    $");
        log.warn("Warning message with three parameters - Name: $name, Age: $data.data.name, City: $city");


        log.warn("Warning message with three parameters - Name: $name, Age: $data.data.name, City: $city");

        log.error("Missing last parameter - Name: $name, Age: $age, City: $city, Salary: {}");
        log.error("Missing last parameter - Name: $name, Age: $age, City: {}, Salary: {}");
        log.error("Missing last parameter - Name: $name, Age: {}, City: {}, Salary: {}");
        log.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}");

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
