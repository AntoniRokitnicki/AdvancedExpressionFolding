package data;

import <fold text='...' expand='false'>org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;</fold>

@SuppressWarnings("ALL")
public class LogBrackets {

    private static final Logger log = LoggerFactory.getLogger(LogBrackets.class);
    private static final Marker MY_MARKER = MarkerFactory.getMarker("MY_MARKER");


    public Data log(Data data) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>String</fold> name = "John";
        <fold text='val' expand='false'>int</fold> age = 30;
        <fold text='val' expand='false'>String</fold> city = "New York";
        log.info(MY_MARKER, "Info message with 2 parameters - Name: <fold text='$' expand='false'>{}, Age: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;

        log.info("Info message with 2 parameters - Name: <fold text='$' expand='false'>{}, Age: {}    ", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='    ")' expand='false'>)</fold>;
        log.info("Info message with 2 parameters - Name: <fold text='$' expand='false'>{}, Age: {}", </fold>name<fold text=', Age: $' expand='false'>, </fold>age<fold text='")' expand='false'>)</fold>;

        log.debug("Debug message with 1 parameter - Name: <fold text='$' expand='false'>{}", </fold>name<fold text='")' expand='false'>)</fold>;
        log.debug("Debug message with 1 parameter - Name: $name");
        log.trace("Trace message - Name: <fold text='${' expand='false'>{}, log:{}    $", </fold>data.<fold text='name' expand='false'>getName()</fold><fold text='}, log:${' expand='false'>, </fold>log(data)<fold text='}    $")' expand='false'>)</fold>;
        log.warn("Warning message with three parameters - Name: <fold text='$' expand='false'>{}, Age: {}, City: {}", </fold>name<fold text=', Age: ${' expand='false'>, </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, City: $' expand='false'>, </fold>city<fold text='")' expand='false'>)</fold>;

        log.error("Missing 1 parameter - 1: <fold text='$' expand='false'>{}, 2: {}, 3: {}, empty: {}", </fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', 3: $' expand='false'>, </fold>city<fold text=', empty: {}")' expand='false'>)</fold>;
        log.error("Missing 2 parameters - 1: <fold text='$' expand='false'>{}, 2: {}, empty: {}, empty: {}", </fold>name<fold text=', 2: $' expand='false'>, </fold>age<fold text=', empty: {}, empty: {}")' expand='false'>)</fold>;
        log.error("Missing 3 parameters - 1: <fold text='$' expand='false'>{}, empty: {}, empty: {}, empty: {}", </fold>name<fold text=', empty: {}, empty: {}, empty: {}")' expand='false'>)</fold>;
        log.error("Missing all parameters - - empty: {}, empty: {}, empty: {}, empty: {}");

        try <fold text='{...}' expand='true'>{
            log.warn("Warning message with 3 parameters and formatting - 1: <fold text='$' expand='false'>{}, 2: {}, 3: {}",
                    </fold>name<fold text=', 2: ${' expand='false'>,

                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, 3: $' expand='false'>,

                    </fold>city<fold text='"' expand='false'>
            </fold>);

            log.warn("Warning message with 3 parameters and formatting - 1: <fold text='${' expand='false'>{}, 2: {}, 3: {}",
                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}, 2: $' expand='false'>,
                    </fold>name<fold text=', 3: ${' expand='false'>,
                    </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}"' expand='false'>
            </fold>);
        }</fold> catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            log.error("error1 <fold text='${' expand='false'>{}", </fold>e.<fold text='message' expand='false'>getMessage()</fold><fold text='}",' expand='false'>,</fold> e);
            log.error("error2 <fold text='${' expand='false'>{}", </fold>data.<fold text='data' expand='false'>getData()</fold>.<fold text='name' expand='false'>getName()</fold><fold text='}",' expand='false'>,</fold> data.getData().getName(), data.getData().getName());
        }</fold>

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
