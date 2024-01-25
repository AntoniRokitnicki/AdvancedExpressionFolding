<fold text='@Getter @Setter p' expand='false'>p</fold>ublic class GetterSetterTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        GetterSetterTestData d = new GetterSetterTestData();
        d.setParent(d);
        d.setName("Hello");
        d.getParent().setName("Pum!");
        System.out.println(d.getParent().getName());
    }</fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private GetterSetterTestData parent;</fold><fold text='' expand='true'>
    </fold><fold text='' expand='true'>private String name;</fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private void setParent(GetterSetterTestData parent)<fold text=' { ' expand='false'> {
        </fold>this.parent = parent;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>

    <fold text='' expand='true'></fold>private GetterSetterTestData getParent()<fold text=' { ' expand='false'> {
        </fold>return parent;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private String getName()<fold text=' { ' expand='false'> {
        </fold>return name;<fold text=' }' expand='false'>
    }</fold></fold><fold text='' expand='true'>

    </fold><fold text='' expand='true'>private void setName(String name)<fold text=' { ' expand='false'> {
        </fold>this.name = name;<fold text=' }' expand='false'>
    }</fold></fold>
}
