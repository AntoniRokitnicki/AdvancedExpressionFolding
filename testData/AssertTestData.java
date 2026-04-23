package data;

public class AssertTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='assert' expand='false'>if</fold> <fold text='' expand='false'>(</fold>args.length <fold text='!=' expand='false'>==</fold> 0) <fold text='{...}' expand='true'>{
            throw new IllegalArgumentException();<fold text='' expand='false'>
        }</fold></fold>
        <fold text='assert' expand='false'>if</fold> <fold text='' expand='false'>(</fold>args.length <fold text='!=' expand='false'>==</fold> 1) <fold text='{...}' expand='true'>{
            throw new IllegalArgumentException("...");<fold text='' expand='false'>
        }</fold></fold>
        <fold text='assert' expand='false'>if</fold> <fold text='' expand='false'>(</fold>args.length <fold text='!=' expand='false'>==</fold> 2<fold text=' : ' expand='false'>)
            throw </fold>new IllegalArgumentException("...");
        String[] strings = new String[0];
        <fold text='assert' expand='false'>if</fold> <fold text='' expand='false'>(</fold>strings.length <fold text='!=' expand='false'>==</fold> 0) <fold text='{...}' expand='true'>{
            throw new RuntimeException("error");<fold text='' expand='false'>
        }</fold></fold>
    }</fold>
}
