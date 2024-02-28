package data;

import <fold text='...' expand='false'>org.slf4j.Logger;
import org.slf4j.LoggerFactory;</fold>

@SuppressWarnings("ALL")
public class LogBrackets {

    private static final Logger log = LoggerFactory.getLogger(LogBrackets.class);

    public Data log(Data data) <fold text='{...}' expand='true'>{
        String name = "John";
        int age = 30;
        String city = "New York";

        log.info("Info message with two parameters - Name: <fold text='$' expand='false'>{}, Age: {}    ", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='    ")' expand='false'>)</fold>;
        log.info("Info message with two parameters - Name: <fold text='$' expand='false'>{}, Age: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;

        log.debug("Debug message with one parameter - Name: <fold text='$' expand='false'>{}", </fold>name<fold text='")' expand='false'>)</fold>;
        log.debug("Debug message with one parameter - Name: $name");
        log.trace("Trace message with three parameters - Name: <fold text='$' expand='false'>{}, log:{}    $", </fold>data.<fold text='name' expand='false'>getName()</fold><fold text=', log:$' expand='false'>, </fold>log(data)<fold text='    $")' expand='false'>)</fold>;
        log.warn("Warning message with three parameters - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text=', City: $' expand='false'>, </fold>city<fold text='")' expand='false'>)</fold>;


        log.warn("Warning message with three parameters - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}",
                </fold>name<fold text=', Age: $' expand='false'>,

                </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text=', City: $' expand='false'>,

                </fold>city<fold text='"' expand='false'>
        </fold>);

        log.error("Missing last parameter - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}, Salary: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text=', City: $' expand='false'>, </fold>city<fold text=', Salary: {}")' expand='false'>)</fold>;
        log.error("Missing last parameter - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}, Salary: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text=', City: {}, Salary: {}")' expand='false'>)</fold>;
        log.error("Missing last parameter - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}, Salary: {}", </fold>name<fold text=', Age: {}, City: {}, Salary: {}")' expand='false'>)</fold>;
        log.error("Missing last parameter - Name: {}, Age: {}, City: {}, Salary: {}");

        return data;
    }</fold>

    public static class Data <fold text='{...}' expand='true'>{
        private Data data;
        private String name;

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        public Data getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
