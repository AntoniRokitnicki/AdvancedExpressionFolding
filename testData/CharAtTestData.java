package data;

public class CharAtTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        String str = "hello";
        System.out.println(str<fold text='[' expand='false'>.charAt(</fold>0<fold text=']' expand='false'>)</fold>);
        int i = 2;
        System.out.println(str<fold text='[' expand='false'>.charAt(</fold>i<fold text=']' expand='false'>)</fold>);
        System.out.println(
                str<fold text='[' expand='false'>.charAt(</fold>str.length() - 1<fold text=']' expand='false'>)</fold>
        );
    }</fold>
}
