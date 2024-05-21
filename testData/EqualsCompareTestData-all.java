package data;

public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>EqualsCompareTestData</fold> a = new EqualsCompareTestData();
        <fold text='val' expand='false'>EqualsCompareTestData</fold> b = new EqualsCompareTestData();
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≡ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(<fold text='' expand='false'>!</fold>a<fold text=' ≢ ' expand='false'>.equals(</fold>b<fold text='' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≡ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) == 0</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≢ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) != 0</fold>);

        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' > ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) > 0</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' > ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) == 1</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≥ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) > -1</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≥ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) >= 0</fold>); // Should be a >= b

        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' < ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) < 0</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' < ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) == -1</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≤ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) < 1</fold>);
        <fold text='' expand='false'>System.out.</fold>println(a<fold text=' ≤ ' expand='false'>.compareTo(</fold>b<fold text='' expand='false'>) <= 0</fold>); // Should be a <= b
    }</fold>

    @Override
    public int compareTo(EqualsCompareTestData o)<fold text=' { ' expand='false'> {
        </fold>return 0;<fold text=' }' expand='false'>
    }</fold>
}
