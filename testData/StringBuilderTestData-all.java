package data;

public class StringBuilderTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>StringBuilder</fold> sb1 = <fold text='"["' expand='false'>new StringBuilder("[")</fold>;
        for <fold text='val (' expand='false'>(<fold text='val' expand='false'>int</fold> </fold>i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>[i];</fold>
        sb1<fold text=' += ' expand='false'>.append(</fold>arg<fold text='' expand='false'>)</fold>;
        if <fold text='' expand='false'>(</fold>i < args.length - 1<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                sb1<fold text=' += ' expand='false'>.append(</fold>","<fold text='' expand='false'>)</fold>;
            }</fold>
        }</fold>
                System.out.println(sb1<fold text=' + ' expand='false'>.append(</fold>"]"<fold text='' expand='false'>).toString()</fold>);

        <fold text='val' expand='false'>StringBuilder</fold> sb2 = <fold text='' expand='false'>new StringBuilder().append(</fold>"["<fold text='' expand='false'>)</fold>;
        for <fold text='val (' expand='false'>(<fold text='val' expand='false'>int</fold> </fold>i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>[i];</fold>
        sb2<fold text=' += ' expand='false'>.append(</fold>arg<fold text='' expand='false'>)</fold>;
        if <fold text='' expand='false'>(</fold>i < args.length - 1<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                sb2<fold text=' += ' expand='false'>.append(</fold>","<fold text='' expand='false'>)</fold>;
            }</fold>
        }</fold>
                System.out.println(sb2<fold text=' + ' expand='false'>.append(</fold>"]"<fold text='' expand='false'>).toString()</fold>);

        <fold text='val' expand='false'>StringBuilder</fold> sb3 = <fold text='""' expand='false'>new StringBuilder()</fold>;
        for <fold text='val (' expand='false'>(<fold text='val' expand='false'>int</fold> </fold>i = 0; i < args.length; i++) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> arg<fold text=' : ' expand='false'> = </fold>args<fold text=') {
' expand='false'>[i];</fold>
        sb3<fold text=' += ' expand='false'>.append(</fold>arg<fold text='' expand='false'>)</fold>;
        if <fold text='' expand='false'>(</fold>i < args.length - 1<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                sb3<fold text=' += ' expand='false'>.append(</fold>","<fold text=' + ' expand='false'>).append(</fold>" "<fold text='' expand='false'>)</fold>;
            }</fold>
        }</fold>
                System.out.println(sb3<fold text='' expand='false'>.toString()</fold>);
    }</fold>
}
