<fold text='📦' expand='false'>package</fold> data;

public <fold text='🏛️' expand='false'>class</fold> EqualsCompareTestData implements Comparabl<fold text='e(0-)' expand='true'>e</fold><EqualsCompareTestData> {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
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
    public <fold text='🔢' expand='false'>int</fold> compareTo(EqualsCompareTestData o)<fold text=' { ' expand='false'> {
        </fold><fold text='🔙' expand='false'>return</fold> 0;<fold text=' }' expand='false'>
    }</fold>
}
