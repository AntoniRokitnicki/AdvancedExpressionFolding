package data;

public class CharAtTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>String</fold> str = "hello";
        <fold text='' expand='false'>System.out.</fold>println(
                str<fold text='[' expand='false'>.charAt(</fold>0<fold text=']' expand='false'>)</fold>
        );
        <fold text='var' expand='false'>int</fold> i = 2;
        <fold text='' expand='false'>System.out.</fold>println(
                str<fold text='[' expand='false'>.charAt(</fold>i<fold text=']' expand='false'>)</fold>
        );
        <fold text='' expand='false'>System.out.</fold>println(
                str<fold text='[' expand='false'>.charAt(</fold>str.length() - 1<fold text=']' expand='false'>)</fold>
        );
    }</fold>
}
