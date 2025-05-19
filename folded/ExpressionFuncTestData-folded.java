package data;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ExpressionFuncTestData {

    private String field;

    private void printStatus() { new HashMap<String, String>().put("a", "b") }

    private String printStatusReturn() { new HashMap<String, String>().put("a", "b") }

    isUser { false }

    findNinjaId { 1L }

    tableName { "table1" }

    public void assignField(String field) { this.field = field }

    public String assignFieldAndReturn(String field) { this.field = field }

    public String methodCall(String field) { assignFieldAndReturn(field) }

    public void methodCall2(String field) { assignFieldAndReturn(field) }

    public void streamShort(List<String> list) { list.stream().map(Function.identity()).map(Function.identity()) }

    public void stream(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }

}
