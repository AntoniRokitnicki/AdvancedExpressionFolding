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

    private void printStatus() {[1:"\n        "]new HashMap<String, String>()[2:".put("][3:".put("][4:".put("][5:".put("]"a"[2:", "][3:", "][4:", "][5:", "]"b"[2:")"][3:")"][4:")"][5:")"][1:";"][1:"\n    "]}

    private String printStatusReturn() {[6:"\n        "][6:"return"][6:" "]new HashMap<String, String>()[7:".put("][8:".put("]"a"[7:", "][8:", "]"b"[7:")"][8:")"][6:";"][6:"\n    "]}

    [9:"public"][9:" "][9:"boolean"][9:" "]isUser[9:"()"] {[9:"\n        "][9:"return"][9:" "]false[9:";"][9:"\n    "]}

    [10:"public"][10:" "][10:"String"][10:" "]tableName[10:"()"] {[10:"\n        "][10:"return"][10:" "]"table1"[10:";"][10:"\n    "]}

    [11:"public"][11:" "][11:"String"][11:" "]columnName(String column) {[11:"\n        "][11:"return"][11:" "]"column1"[11:";"][11:"\n    "]}

    public void assignField(String field) {[12:"\n        "]this.field = [13:"field"][14:"field"][15:"field"][16:"field"][12:";"][12:"\n    "]}

    public String assignFieldAndReturn(String field) {[17:"\n        "][17:"return"][17:" "]this.field = [18:"field"][19:"field"][17:";"][17:"\n    "]}

    public String methodCall(String field) {[20:"\n        "][20:"return"][20:" "]assignFieldAndReturn(field)[20:";"][20:"\n    "]}

    public void methodCall2(String field) {[21:"\n        "]assignFieldAndReturn(field)[21:";"][21:"\n    "]}

    public void streamShort(List<String> list) {[22:"\n        "]list[23:".stream()."][24:".stream()."][25:".stream()."][26:".stream()."][27:".stream()."][28:".stream()."]map(Function.identity()).map(Function.identity())[22:";"][22:"\n    "]}

    public void stream(List<String> list) {
        list[29:".stream()."][30:".stream()."][31:".stream()."][32:".stream()."]map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }


    class Child extends Paren[33:"t"] {
        [36:"@Override"][36:"\n        "][35:"public"][35:" "][35:"long"][35:" "]getId[35:"()"] {[35:"\n            "][35:"return"][35:" "]0L[35:";"][35:"\n        "][34:"}"]
    }
    class Child2 extends Paren[37:"t"] {
        @ExampleAnnotation
        [38:"public"][38:" "][38:"long"][38:" "]getId[38:"()"] {[38:"\n            "][38:"return"][38:" "]2L[38:";"][38:"\n        "]}
    }
    class Child3 extends Paren[39:"t"] {
        [42:"@Override"][42:"\n        "]@ExampleAnnotation
        [41:"public"][41:" "][41:"long"][41:" "]getId[41:"()"] {[41:"\n            "][41:"return"][41:" "]3L[41:";"][41:"\n        "][40:"}"]
    }
    class Parent {
        [44:"@SuppressWarnings(\"ignored\")"][44:"\n        "][43:"public"][43:" "][43:"long"][43:" "]getId[43:"()"] {[43:"\n            "][43:"return"][43:" "]1L[43:";"][43:"\n        "]}
    }
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExampleAnnotation {}
}
