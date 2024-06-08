<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.Arrays;
<fold text='🚢' expand='false'>import</fold> java.util.List;</fold>

public <fold text='🏛️' expand='false'>class</fold> SliceTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>List<String></fold> list = Arrays.asList(args);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(</fold>1<fold text=':]' expand='false'>, list.size())</fold>);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(</fold>1<fold text=':' expand='false'>, </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(</fold>1<fold text=':]' expand='false'>, list.size())</fold>);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(0</fold><fold text=':' expand='false'>, </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(</fold>1<fold text=':' expand='false'>, list.size() </fold>-<fold text='' expand='false'> </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(list<fold text='[' expand='false'>.subList(0</fold><fold text=':' expand='false'>, list.size() </fold>-<fold text='' expand='false'> </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='val' expand='false'>String</fold> f = args[0];
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(</fold>1<fold text=':]' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(</fold>1<fold text=':' expand='false'>, </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(</fold>1<fold text=':]' expand='false'>, f.length())</fold>);
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(0</fold><fold text=':' expand='false'>, </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(</fold>1<fold text=':' expand='false'>, f.length() </fold>-<fold text='' expand='false'> </fold>2<fold text=']' expand='false'>)</fold>);
        <fold text='' expand='false'>System.out.</fold>println(f<fold text='[' expand='false'>.substring(0</fold><fold text=':' expand='false'>, f.length() </fold>-<fold text='' expand='false'> </fold>2<fold text=']' expand='false'>)</fold>);
    }</fold>
}
