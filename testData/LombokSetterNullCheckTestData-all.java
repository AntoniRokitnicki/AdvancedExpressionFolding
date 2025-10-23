package data;

import java.util.Objects;

public class LombokSetterNullCheckTestData {
    private String withThrow;
    private String withRequireNonNull;

    public void setWithThrow(String withThrow) <fold text='{...}' expand='true'>{
        <fold text='assert' expand='false'>if</fold> <fold text='' expand='false'>(</fold>withThrow <fold text='!=' expand='false'>==</fold> null) <fold text='{...}' expand='true'>{
          <fold text=':' expand='false'> </fold> <fold text='' expand='false'>throw new NullPointerException(</fold>"withThrow"<fold text='' expand='false'>);
        }</fold></fold>
        this.withThrow = <fold text='<<' expand='false'>withThrow</fold>;
    }</fold>

    public void setWithRequireNonNull(String withRequireNonNull) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
        </fold>this.withRequireNonNull = Objects.requireNonNull(withRequireNonNull, "withRequireNonNull")<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
    </fold>}</fold>
}
