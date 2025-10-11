package data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ExpressionFuncTestData {

    private String field;

    [0:"public"][0:" "][0:"long"][0:" "]findNinjaId[0:"()"] {[0:"
        "][0:"return"][0:" "]1L[0:";"][0:"
    "]}

    private void printStatus() {[0:"
        "]new HashMap<String, String>().put("a", "b")[0:";"][0:"
    "]}

    private String printStatusReturn() {[0:"
        "][0:"return"][0:" "]new HashMap<String, String>().put("a", "b")[0:";"][0:"
    "]}

    [0:"public"][0:" "][0:"boolean"][0:" "]isUser[0:"()"] {[0:"
        "][0:"return"][0:" "]false[0:";"][0:"
    "]}

    [0:"public"][0:" "][0:"String"][0:" "]tableName[0:"()"] {[0:"
        "][0:"return"][0:" "]"table1"[0:";"][0:"
    "]}

    [0:"public"][0:" "][0:"String"][0:" "]columnName(String column) {[0:"
        "][0:"return"][0:" "]"column1"[0:";"][0:"
    "]}

    public void assignField(String field) {[0:"
        "]this.field = field[0:";"][0:"
    "]}

    public String assignFieldAndReturn(String field) {[0:"
        "][0:"return"][0:" "]this.field = field[0:";"][0:"
    "]}

    public String methodCall(String field) {[0:"
        "][0:"return"][0:" "]assignFieldAndReturn(field)[0:";"][0:"
    "]}

    public void methodCall2(String field) {[0:"
        "]assignFieldAndReturn(field)[0:";"][0:"
    "]}

    public void streamShort(List<String> list) {[0:"
        "]list.stream().map(Function.identity()).map(Function.identity())[0:";"][0:"
    "]}

    public void stream(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }


    class Child extends Parent {
        @Override
        [0:"public"][0:" "][0:"long"][0:" "]getId[0:"()"] {[0:"
            "][0:"return"][0:" "]0L[0:";"][0:"
        "]}
    }
    class Child2 extends Parent {
        @ExampleAnnotation
        [0:"public"][0:" "][0:"long"][0:" "]getId[0:"()"] {[0:"
            "][0:"return"][0:" "]2L[0:";"][0:"
        "]}
    }
    class Child3 extends Parent {
        @Override
        @ExampleAnnotation
        [0:"public"][0:" "][0:"long"][0:" "]getId[0:"()"] {[0:"
            "][0:"return"][0:" "]3L[0:";"][0:"
        "]}
    }
    class Parent {
        @SuppressWarnings("ignored")
        [0:"public"][0:" "][0:"long"][0:" "]getId[0:"()"] {[0:"
            "][0:"return"][0:" "]1L[0:";"][0:"
        "]}
    }
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExampleAnnotation {}
}
