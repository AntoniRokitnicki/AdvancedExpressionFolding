package data;

public class SummaryParentOverrideTestData {

    class GrandparentClass <fold text='{...}' expand='true'>{
        public void grandparentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Grandparent Method");<fold text=' }' expand='false'>
        }</fold>

        public void sharedMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Shared method from Grandparent");<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class ParentClass extends GrandparentClas<fold text='s(1-grandparentMethod)' expand='true'>s</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        public void grandparentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Grandparent Method in Parent");<fold text=' }' expand='false'>
        <fold text='} // overrides from GrandparentClass' expand='true'>}</fold></fold>

        public void parentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Parent Method");<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    interface FirstInterface <fold text='{...}' expand='true'>{
        void interfaceMethodOne();
        void sharedMethod();
    }</fold>

    interface SecondInterface <fold text='{...}' expand='true'>{
        void interfaceMethodTwo();
    }</fold>

    public class TestDataClass extends ParentClas<fold text='s(1-grandparentMethod)' expand='true'>s</fold> implements FirstInterfac<fold text='e(2-interfaceMethodOne, sharedMethod)' expand='true'>e</fold>, SecondInterfac<fold text='e(1-interfaceMethodTwo)' expand='true'>e</fold> <fold text='{...}' expand='true'>{

        <fold text='' expand='true'>@Override</fold>
        public void interfaceMethodOne()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Implementation of Interface Method One");<fold text=' }' expand='false'>
        <fold text='} // overrides from FirstInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold>
        public void interfaceMethodTwo()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Implementation of Interface Method Two");<fold text=' }' expand='false'>
        <fold text='} // overrides from SecondInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold>
        public void sharedMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Shared Method");<fold text=' }' expand='false'>
        <fold text='} // overrides from FirstInterface' expand='true'>}</fold></fold>

        <fold text='' expand='true'>@Override</fold>
        public void grandparentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Grandparent Method in TestDataClass");<fold text=' }' expand='false'>
        <fold text='} // overrides from ParentClass' expand='true'>}</fold></fold>

        public void additionalMethodOne()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Additional method one");<fold text=' }' expand='false'>
        }</fold>

        public void additionalMethodTwo()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Additional method two");<fold text=' }' expand='false'>
        }</fold>

        public void additionalMethodThree()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Additional method three");<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}