package data;

public class VarTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>String</fold> string = "Hello, world";
        System.out.println();
        <fold text='var' expand='false'>int</fold> count = 0;
        for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> arg : args<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                System.out.println(arg);
        count++;
        }</fold>
        for <fold text='var (' expand='false'>(<fold text='var' expand='false'>int</fold> </fold>i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>[i];</fold>
        System.out.println(arg);
        i++;
        }</fold>
    }</fold>
}
