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

    [0:"public"][0:" "][0:"long"][0:" "]findNinjaId[0:"()"] {[0:"\n        "][0:"return"][0:" "]1L[0:";"][0:"\n    "]}

    private void printStatus() {[1:"\n        "]new HashMap<String, String>().put("a", "b")[1:";"][1:"\n    "]}

    private String printStatusReturn() {[2:"\n        "][2:"return"][2:" "]new HashMap<String, String>().put("a", "b")[2:";"][2:"\n    "]}

    [3:"public"][3:" "][3:"boolean"][3:" "]isUser[3:"()"] {[3:"\n        "][3:"return"][3:" "]false[3:";"][3:"\n    "]}

    [4:"public"][4:" "][4:"String"][4:" "]tableName[4:"()"] {[4:"\n        "][4:"return"][4:" "]"table1"[4:";"][4:"\n    "]}

    [5:"public"][5:" "][5:"String"][5:" "]columnName(String column) {[5:"\n        "][5:"return"][5:" "]"column1"[5:";"][5:"\n    "]}

    public void assignField(String field) {[6:"\n        "]this.field = field[6:";"][6:"\n    "]}

    public String assignFieldAndReturn(String field) {[7:"\n        "][7:"return"][7:" "]this.field = field[7:";"][7:"\n    "]}

    public String methodCall(String field) {[8:"\n        "][8:"return"][8:" "]assignFieldAndReturn(field)[8:";"][8:"\n    "]}

    public void methodCall2(String field) {[9:"\n        "]assignFieldAndReturn(field)[9:";"][9:"\n    "]}

    public void streamShort(List<String> list) {[10:"\n        "]list.stream().map(Function.identity()).map(Function.identity())[10:";"][10:"\n    "]}

    public void stream(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }


    class Child extends Parent {
        @Override
        [11:"public"][11:" "][11:"long"][11:" "]getId[11:"()"] {[11:"\n            "][11:"return"][11:" "]0L[11:";"][11:"\n        "]}
    }
    class Child2 extends Parent {
        @ExampleAnnotation
        [12:"public"][12:" "][12:"long"][12:" "]getId[12:"()"] {[12:"\n            "][12:"return"][12:" "]2L[12:";"][12:"\n        "]}
    }
    class Child3 extends Parent {
        @Override
        @ExampleAnnotation
        [13:"public"][13:" "][13:"long"][13:" "]getId[13:"()"] {[13:"\n            "][13:"return"][13:" "]3L[13:";"][13:"\n        "]}
    }
    class Parent {
        @SuppressWarnings("ignored")
        [14:"public"][14:" "][14:"long"][14:" "]getId[14:"()"] {[14:"\n            "][14:"return"][14:" "]1L[14:";"][14:"\n        "]}
    }
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExampleAnnotation {}
}
