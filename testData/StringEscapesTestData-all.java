package data;

public class StringEscapesTestData {
    public void sample() <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>String</fold> lf = <fold text='"a⏎b"' expand='false'>"a\nb"</fold>;
        <fold text='val' expand='false'>String</fold> crlf = <fold text='"a⏎b"' expand='false'>"a\r\nb"</fold>;
        <fold text='val' expand='false'>String</fold> cr = <fold text='"a␍b"' expand='false'>"a\rb"</fold>;
        <fold text='val' expand='false'>String</fold> tab = <fold text='"a	b"' expand='false'>"a\tb"</fold>;
        <fold text='val' expand='false'>String</fold> space = "a b";
    }</fold>
}
