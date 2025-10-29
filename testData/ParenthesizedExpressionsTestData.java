package data;

@SuppressWarnings("UnusedReturnValue")
public class ParenthesizedExpressionsTestData {
    int keepTernary(int h1, int h2)<fold text=' { ' expand='false'> {
        </fold>return 1 - (h1 >= h2 ? h2 / h1 : h1 / h2);<fold text=' }' expand='false'>
    }</fold>

    int keepMultiplication(int a, int b, int c)<fold text=' { ' expand='false'> {
        </fold>return (a + b) * c;<fold text=' }' expand='false'>
    }</fold>

    boolean keepComparisonBranch(int value, boolean flag)<fold text=' { ' expand='false'> {
        </fold>return value == (flag ? 1 : 0);<fold text=' }' expand='false'>
    }</fold>

    boolean keepLogicalWithComparison(int a, int b, boolean tail)<fold text=' { ' expand='false'> {
        </fold>return (a > b) && tail;<fold text=' }' expand='false'>
    }</fold>

    int dropMatchingAddition(int a, int b, int c)<fold text=' { ' expand='false'> {
        </fold>return <fold text='' expand='false'>(</fold>a + b<fold text=' + ' expand='false'>) + </fold>c;<fold text=' }' expand='false'>
    }</fold>

    int dropMatchingMultiplication(int a, int b, int c)<fold text=' { ' expand='false'> {
        </fold>return a<fold text=' * ' expand='false'> * (</fold>b * c<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
    }</fold>

    int dropSimpleCall()<fold text=' { ' expand='false'> {
        </fold>return (foo());<fold text=' }' expand='false'>
    }</fold>

    private int foo()<fold text=' { ' expand='false'> {
        </fold>return 42;<fold text=' }' expand='false'>
    }</fold>
}
