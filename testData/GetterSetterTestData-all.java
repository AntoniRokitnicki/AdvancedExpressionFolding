package data;

<fold text='@Getter @Setter p' expand='false'>p</fold>ublic class GetterSetterTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>GetterSetterTestData</fold> d = new GetterSetterTestData();
        d.<fold text='parent = ' expand='false'>setParent(</fold>d<fold text='' expand='false'>)</fold>;
        d.<fold text='name = ' expand='false'>setName(</fold>"Hello"<fold text='' expand='false'>)</fold>;
        d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name = ' expand='false'>setName(</fold>"Pum!"<fold text='' expand='false'>)</fold>;
        System.out.println(d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name' expand='false'>getName()</fold>);
    }</fold>

    private GetterSetterTestData parent;
    private String name;<fold text='' expand='true'>

    </fold><fold text='' expand='true'>private void setParent(GetterSetterTestData parent)<fold text=' { ' expand='false'> {
        </fold>this.parent = <fold text='<<' expand='false'>parent</fold>;<fold text=' }' expand='false'>
    }</fold><fold text='' expand='true'></fold>

    </fold><fold text='' expand='true'>private GetterSetterTestData getParent()<fold text=' { ' expand='false'> {
        </fold>return parent;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private String getName()<fold text=' { ' expand='false'> {
        </fold>return name;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private void setName(String name)<fold text=' { ' expand='false'> {
        </fold>this.name = <fold text='<<' expand='false'>name</fold>;<fold text=' }' expand='false'>
    }</fold></fold>
}
