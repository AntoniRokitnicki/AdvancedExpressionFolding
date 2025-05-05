<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.HashMap;
<fold text='🚢' expand='false'>import</fold> java.util.List;
<fold text='🚢' expand='false'>import</fold> java.util.function.Function;</fold>

public <fold text='🏛️' expand='false'>class</fold> ExpressionFuncTestData {

    <fold text='🚫' expand='false'>private</fold> String field;

    <fold text='🚫' expand='false'>private</fold> <fold text='💀' expand='false'>void</fold> printStatus()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> String printStatusReturn()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public <fold text='🔘' expand='false'>boolean</fold> isUser()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='❌' expand='false'>false</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public <fold text='📏' expand='false'>long</fold> findNinjaId()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>1L<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public String tableName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>"table1"<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public <fold text='💀' expand='false'>void</fold> assignField(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.field = <fold text='<<' expand='false'>field</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public String assignFieldAndReturn(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.field = <fold text='<<' expand='false'>field</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public String methodCall(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>assignFieldAndReturn(field)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public <fold text='💀' expand='false'>void</fold> methodCall2(String field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>assignFieldAndReturn(field)<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
    </fold>}</fold>

    public <fold text='💀' expand='false'>void</fold> streamShort(List<String> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>list<fold text='.' expand='false'>.stream().</fold>map(Function.identity()).map(Function.identity())<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    public <fold text='💀' expand='false'>void</fold> stream(List<String> list) <fold text='{...}' expand='true'>{
        list<fold text='.' expand='false'>.stream().</fold>map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity()).map(Function.identity());
    }</fold>

}
