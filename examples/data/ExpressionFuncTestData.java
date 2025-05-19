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

    public long findNinjaId() {
        return 1L;
    }

    private void printStatus() {
        new HashMap<String, String>().put("a", "b");
    }

    private String printStatusReturn() {
        return new HashMap<String, String>().put("a", "b");
    }

    public boolean isUser() {
        return false;
    }

    public String tableName() {
        return "table1";
    }

    public String columnName(String column) {
        return "column1";
    }

    public void assignField(String field) {
        this.field = field;
    }

    public String assignFieldAndReturn(String field) {
        return this.field = field;
    }

    public String methodCall(String field) {
        return assignFieldAndReturn(field);
    }

    public void methodCall2(String field) {
        assignFieldAndReturn(field);
    }

    public void streamShort(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity());
    }

    public void stream(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }


    class Child extends Parent {
        @Override
        public long getId() {
            return 0L;
        }
    }
    class Child2 extends Parent {
        @ExampleAnnotation
        public long getId() {
            return 2L;
        }
    }
    class Child3 extends Parent {
        @Override
        @ExampleAnnotation
        public long getId() {
            return 3L;
        }
    }
    class Parent {
        @SuppressWarnings("ignored")
        public long getId() {
            return 1L;
        }
    }
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExampleAnnotation {}
}
