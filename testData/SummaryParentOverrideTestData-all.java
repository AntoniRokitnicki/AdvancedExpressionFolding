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

    class ParentClass extends GrandparentClas<fold text='sBlaBlaBla' expand='true'>s</fold> <fold text='{...}' expand='true'>{
        @Override
        public void grandparentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Grandparent Method in Parent");<fold text=' }' expand='false'>
        }</fold>

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

    public class TestDataClass extends ParentClas<fold text='sBlaBlaBla' expand='true'>s</fold> implements FirstInterfac<fold text='eBlaBlaBla' expand='true'>e</fold>, SecondInterfac<fold text='eBlaBlaBla' expand='true'>e</fold> <fold text='{...}' expand='true'>{

        @Override
        public void interfaceMethodOne()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Implementation of Interface Method One");<fold text=' }' expand='false'>
        }</fold>

        @Override
        public void interfaceMethodTwo()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Implementation of Interface Method Two");<fold text=' }' expand='false'>
        }</fold>

        @Override
        public void sharedMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Shared Method");<fold text=' }' expand='false'>
        }</fold>

        @Override
        public void grandparentMethod()<fold text=' { ' expand='false'> {
            </fold>System.out.println("Overridden Grandparent Method in TestDataClass");<fold text=' }' expand='false'>
        }</fold>

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