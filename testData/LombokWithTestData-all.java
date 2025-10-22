package data;

<fold text='@Getter @With p' expand='false'>p</fold>ublic class LombokWithTestData {
    LombokWithTestData data;
    boolean ok;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public LombokWithTestData getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public LombokWithTestData withData(LombokWithTestData data) <fold text='{...}' expand='true'>{
        this.data = <fold text='<<' expand='false'>data</fold>;
        return this;
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public LombokWithTestData withOk(boolean ok) <fold text='{...}' expand='true'>{
        this.ok = <fold text='<<' expand='false'>ok</fold>;
        return this;
    }</fold></fold>

    public class PartialWith <fold text='{...}' expand='true'>{
        <fold text='@With L' expand='false'>L</fold>ombokWithTestData data;
        boolean ok;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public PartialWith withData(LombokWithTestData data) <fold text='{...}' expand='true'>{
            this.data = <fold text='<<' expand='false'>data</fold>;
            return this;
        }</fold></fold>
    }</fold>
}
