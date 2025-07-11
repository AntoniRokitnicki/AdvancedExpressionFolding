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

    findNinjaId { 1L }

    private void printStatus() { new HashMap<String, String>().put("a", "b") }

    private String printStatusReturn() { new HashMap<String, String>().put("a", "b") }

    isUser { false }

    tableName { "table1" }

    columnName(String column) { "column1" }

    public void assignField(String field) { this.field = field }

    public String assignFieldAndReturn(String field) { this.field = field }

    public String methodCall(String field) { assignFieldAndReturn(field) }

    public void methodCall2(String field) { assignFieldAndReturn(field) }

    public void streamShort(List<String> list) { list.stream().map(Function.identity()).map(Function.identity()) }

    public void stream(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }


    class Child extends Parent {
        @Override
        getId { 0L }
    }
    class Child2 extends Parent {
        @ExampleAnnotation
        getId { 2L }
    }
    class Child3 extends Parent {
        @Override
        @ExampleAnnotation
        getId { 3L }
    }
    class Parent {
        @SuppressWarnings("ignored")
        getId { 1L }
    }
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExampleAnnotation {}
}
