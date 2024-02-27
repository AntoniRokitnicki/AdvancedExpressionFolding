package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ALL")
public class LogBraces {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBraces.class);

    public Data log(Data data) {
        String name = "John";
        int age = 30;
        String city = "New York";

        LOGGER.info("Info message with two parameters - Name: {}, Age: {}    ", name, age);
        LOGGER.info("Info message with two parameters - Name: {}, Age: {}", name, age);

        LOGGER.debug("Debug message with one parameter - Name: {}", name);
        LOGGER.debug("Debug message with one parameter - Name: $name");
        LOGGER.trace("Trace message with three parameters - Name: {}, log:{}    $", data.getName(), log(data));
        LOGGER.warn("Warning message with three parameters - Name: {}, Age: {}, City: {}", name, data.getData().getName(), city);


        LOGGER.warn("Warning message with three parameters - Name: {}, Age: {}, City: {}",
                name,

                data.getData()
                        .getName(),

                city
        );

        LOGGER.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}", name, age, city);
        LOGGER.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}", name, age);
        LOGGER.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}", name);
        LOGGER.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}");

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
