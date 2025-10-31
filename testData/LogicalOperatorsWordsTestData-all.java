package data;

@interface Flag <fold text='{...}' expand='true'>{
    boolean value();
}</fold>

public class LogicalOperatorsWordsTestData <fold text='{...}' expand='true'>{
    @Flag(value = true && false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>boolean</fold> both = a<fold text=' AND ' expand='false'> && </fold>b;
        <fold text='val' expand='false'>boolean</fold> either = a<fold text=' OR ' expand='false'> || </fold>b;
        <fold text='val' expand='false'>boolean</fold> negated = <fold text='NOT ' expand='false'>!</fold>a;
        <fold text='val' expand='false'>boolean</fold> complex = <fold text='NOT (' expand='false'>!(</fold>a<fold text=' OR ' expand='false'> || </fold>b<fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>boolean</fold> precedence = a<fold text=' OR ' expand='false'> || </fold><fold text='(b AND c)' expand='false'>b && c</fold>;
        <fold text='val' expand='false'>boolean</fold> precedenceLeft = <fold text='(a AND b)' expand='false'>a && b</fold><fold text=' OR ' expand='false'> || </fold>c;
        <fold text='val' expand='false'>boolean</fold> mixed = a<fold text=' OR ' expand='false'> || </fold><fold text='(b AND NOT c)' expand='false'>b && !c</fold>;
        <fold text='val' expand='false'>boolean</fold> nestedNot = <fold text='NOT (' expand='false'>!(</fold>a<fold text=' AND ' expand='false'> && </fold><fold text='NOT ' expand='false'>!</fold>b<fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>int</fold> bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        <fold text='val' expand='false'>int</fold> bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        <fold text='val' expand='false'>boolean</fold> insideString = "a && b".<fold text='empty' expand='false'>isEmpty()</fold>;
        <fold text='val' expand='false'>boolean</fold> insideComment = true; // && || !
        <fold text='val' expand='false'>Flagged</fold> marker = new Flagged();
        return both && either && <fold text='NOT ' expand='false'>!</fold>negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }</fold>
}</fold>
