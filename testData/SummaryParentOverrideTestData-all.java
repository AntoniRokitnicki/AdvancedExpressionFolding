<fold text='📦' expand='false'>package</fold> data;

public <fold text='🏛️' expand='false'>class</fold> SummaryParentOverrideTestData {

    <fold text='🏛️' expand='false'>class</fold> GrandparentClass <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> grandparentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Grandparent Method"' expand='false'>"Grandparent Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> sharedMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Shared method from Grandparent"' expand='false'>"Shared method from Grandparent"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> ParentClass extends GrandparentClass <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> grandparentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Grandparent Method in Parent"' expand='false'>"Overridden Grandparent Method in Parent"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> parentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Parent Method"' expand='false'>"Parent Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> FirstInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> interfaceMethodOne();
        <fold text='💀' expand='false'>void</fold> sharedMethod();
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> SecondInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> interfaceMethodTwo();
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TestDataClass extends ParentClass implements FirstInterface, SecondInterface <fold text='{...}' expand='true'>{

        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> interfaceMethodOne()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Implementation of Interface Method One"' expand='false'>"Implementation of Interface Method One"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> interfaceMethodTwo()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Implementation of Interface Method Two"' expand='false'>"Implementation of Interface Method Two"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> sharedMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Shared Method"' expand='false'>"Overridden Shared Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> grandparentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Grandparent Method in TestDataClass"' expand='false'>"Overridden Grandparent Method in TestDataClass"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> additionalMethodOne()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Additional method one"' expand='false'>"Additional method one"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> additionalMethodTwo()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'></fold>System.out.</fold>println(<fold text='"Additional method two"' expand='false'>"Additional method two"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> additionalMethodThree()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Additional method three"' expand='false'>"Additional method three"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}