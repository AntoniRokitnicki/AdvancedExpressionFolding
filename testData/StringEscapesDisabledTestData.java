package data;

public class StringEscapesDisabledTestData {
    public void sample() <fold text='{...}' expand='true'>{
        String lf = "a\nb";
        String crlf = "a\r\nb";
        String cr = "a\rb";
        String tab = <fold text='"a	b"' expand='false'>"a\tb"</fold>;
        String space = "a b";
    }</fold>
}
