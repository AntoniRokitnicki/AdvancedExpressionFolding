package data;

public class StringEscapesTestData {
    public void sample() <fold text='{...}' expand='true'>{
        String lf = <fold text='"a⏎b"' expand='false'>"a\nb"</fold>;
        String crlf = <fold text='"a⏎b"' expand='false'>"a\r\nb"</fold>;
        String cr = <fold text='"a␍b"' expand='false'>"a\rb"</fold>;
        String tab = <fold text='"a	b"' expand='false'>"a\tb"</fold>;
        String space = "a b";
    }</fold>
}
