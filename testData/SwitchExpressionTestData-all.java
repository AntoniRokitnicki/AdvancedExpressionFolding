package data;

public class SwitchExpressionTestData {
    int map(int value) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='when' expand='false'>switch</fold> <fold text='' expand='false'>(</fold>value<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            case 0 -> 0;
            case 1, 2 -> value + 10;
            <fold text='else' expand='false'>default</fold> -> -1;
        }</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
    </fold>}</fold>

    String describe(String text) <fold text='{...}' expand='true'>{
        return <fold text='when' expand='false'>switch</fold> <fold text='' expand='false'>(</fold>text<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            case "x" -> "ex";
            case "y" -> "why";
            <fold text='else' expand='false'>default</fold> -> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> prefix = text<fold text='[' expand='false'>.substring(0</fold><fold text=':' expand='false'>, </fold>1<fold text=']' expand='false'>)</fold>;
                yield prefix + text;
            }</fold>
        }</fold>;
    }</fold>
}
