package data;

import java.util.List;
import java.util.function.Function;

public class ExpressionFuncTestData {

    private String field;

    public boolean isUser() {
        return false;
    }

    public long findNinjaId() {
        return 1L;
    }

    public String tableName() {
        return "table1";
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

}