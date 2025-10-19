package data;

public class SummaryParentOverrideTestData {

    class GrandparentClass <fold text='{...}' expand='true'>{
        public void grandparentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Grandparent Method"' expand='true'>"Grandparent Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void sharedMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Shared method from Grandparent"' expand='true'>"Shared method from Grandparent"</fold>)<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>
    }</fold>

    class ParentClass extends GrandparentClas<fold text='s(1-grandparentMethod)' expand='true'>s</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void grandparentMethod() <fold text='{ // overrides from GrandparentClass' expand='true'><fold text='{...}' expand='true'>{</fold>
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Grandparent Method in Parent"' expand='true'>"Overridden Grandparent Method in Parent"</fold>);
            <fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Grandparent Method in Parent"' expand='true'>"Overridden Grandparent Method in Parent"</fold>);
        }</fold>

        public void parentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'></fold>System.out.</fold>println(<fold text='"Parent Method"' expand='true'>"Parent Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    interface FirstInterface <fold text='{...}' expand='true'>{
        void interfaceMethodOne();
        void sharedMethod();
    }</fold>

    interface SecondInterface <fold text='{...}' expand='true'>{
        void interfaceMethodTwo();
    }</fold>

    interface WithoutMethodInterface <fold text='{...}' expand='true'>{

    }</fold>

    public class TestDataClass extends ParentClas<fold text='s(1-grandparentMethod)' expand='true'>s</fold> implements FirstInterfac<fold text='e(2-interfaceMethodOne, sharedMethod)' expand='true'>e</fold>, SecondInterfac<fold text='e(1-interfaceMethodTwo)' expand='true'>e</fold>, WithoutMethodInterfac<fold text='e(nothing overridden)' expand='true'>e</fold> <fold text='{...}' expand='true'>{

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void interfaceMethodOne()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Implementation of Interface Method One"' expand='true'>"Implementation of Interface Method One"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold><fold text='} // overrides from FirstInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void interfaceMethodTwo()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Implementation of Interface Method Two"' expand='true'>"Implementation of Interface Method Two"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold><fold text='} // overrides from SecondInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void sharedMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Shared Method"' expand='true'>"Overridden Shared Method"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold><fold text='} // overrides from FirstInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void grandparentMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Overridden Grandparent Method in TestDataClass"' expand='true'>"Overridden Grandparent Method in TestDataClass"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        <fold text='} // overrides from ParentClass' expand='true'></fold>}</fold></fold>

        public void additionalMethodOne()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Additional method one"' expand='true'>"Additional method one"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void additionalMethodTwo()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Additional method two"' expand='true'>"Additional method two"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void additionalMethodThree()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>System.out.</fold>println(<fold text='"Additional method three"' expand='true'>"Additional method three"</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}