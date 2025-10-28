package data;

<fold text='@Getter @Setter p' expand='false'>p</fold>ublic class GetterSetterTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>GetterSetterTestData</fold> d = new GetterSetterTestData();
        d.<fold text='parent = ' expand='false'>setParent(</fold>d<fold text='' expand='false'>)</fold>;
        d.<fold text='name = ' expand='false'>setName(</fold>"Hello"<fold text='' expand='false'>)</fold>;
        d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name = ' expand='false'>setName(</fold>"Pum!"<fold text='' expand='false'>)</fold>;
        <fold text='' expand='false'>System.out.</fold>println(d.<fold text='parent' expand='false'>getParent()</fold>.<fold text='name' expand='false'>getName()</fold>);
        java.util.Locale locale = java.util.Locale.US;
        locale.<fold text='iso3Country' expand='false'>getISO3Country()</fold>;
        locale.<fold text='iso3Language' expand='false'>getISO3Language()</fold>;
        AcronymGetter obj = new AcronymGetter();
        obj.<fold text='urlValue' expand='false'>getURLValue()</fold>;
        obj.<fold text='htmlTag' expand='false'>getHTMLTag()</fold>;
    }</fold>

    private GetterSetterTestData parent;
    private String name;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>private void setParent(GetterSetterTestData parent)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.parent = <fold text='<<' expand='false'>parent</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>private GetterSetterTestData getParent()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>parent<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>private String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>private void setName(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.name = <fold text='<<' expand='false'>name</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    private static class AcronymGetter <fold text='{...}' expand='true'>{
        private String urlValue;
        private String htmlTag;

        private String getURLValue()<fold text=' { ' expand='false'> {
            </fold>return urlValue;<fold text=' }' expand='false'>
        }</fold>

        private String getHTMLTag()<fold text=' { ' expand='false'> {
            </fold>return htmlTag;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
