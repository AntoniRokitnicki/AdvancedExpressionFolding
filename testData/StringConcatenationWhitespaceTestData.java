package data;

class Subject <fold text='{...}' expand='true'>{
    String method()<fold text=' { ' expand='false'> {
        </fold>return "value";<fold text=' }' expand='false'>
    }</fold>
}</fold>

public class StringConcatenationWhitespaceTestData <fold text='{...}' expand='true'>{
    String format(Subject subject)<fold text=' { ' expand='false'> {
        </fold>return "Abc:<fold text='${' expand='false'>" + </fold>subject.method()<fold text='}";' expand='false'>;</fold><fold text=' }' expand='false'>
    }</fold>
}</fold>
