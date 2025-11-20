package data;

class Subject <fold text='{...}' expand='true'>{
    String method()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"value"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>
}</fold>

public class StringConcatenationWhitespaceTestData <fold text='{...}' expand='true'>{
    String format(Subject subject)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"Abc:<fold text='${' expand='false'>" + </fold>subject.method()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>
}</fold>
