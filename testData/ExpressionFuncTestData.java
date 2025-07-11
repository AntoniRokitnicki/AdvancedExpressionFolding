package data;

import <fold text='...' expand='false'>java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;</fold>

public class ExpressionFuncTestData {

    private String field;

    <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>long</fold><fold text='' expand='true'> </fold>findNinjaId<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>1L<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private void printStatus()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>new HashMap<String, String>().put("a", "b")<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private String printStatusReturn()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashMap<String, String>().put("a", "b")<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='true'>public</fold><fold text='' expand='true'> <fold text='' expand='true'></fold>boolean</fold><fold text='' expand='true'> </fold>isUser<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>false<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>tableName<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>"table1"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>columnName(String column)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>"column1"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public void assignField(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.field = field<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold>

    public String assignFieldAndReturn(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.field = field<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public String methodCall(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>assignFieldAndReturn(field)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public void methodCall2(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>assignFieldAndReturn(field)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public void streamShort(List<String> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>list.stream().map(Function.identity()).map(Function.identity())<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public void stream(List<String> list) <fold text='{...}' expand='true'>{
        list.stream().map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }</fold>


    class Child extends Parent <fold text='{...}' expand='true'>{
        @Override
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>long</fold><fold text='' expand='true'> </fold>getId<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>0L<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
    class Child2 extends Parent <fold text='{...}' expand='true'>{
        @ExampleAnnotation
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>long</fold><fold text='' expand='true'> </fold>getId<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>2L<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
    class Child3 extends Parent <fold text='{...}' expand='true'>{
        <fold text='@{...}' expand='true'>@Override
        @ExampleAnnotation</fold>
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>long</fold><fold text='' expand='true'> </fold>getId<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>3L<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
    class Parent <fold text='{...}' expand='true'>{
        @SuppressWarnings("ignored")
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>long</fold><fold text='' expand='true'> </fold>getId<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>1L<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
    <fold text='@{...}' expand='true'>@Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)</fold>
    public @interface ExampleAnnotation {}
}
