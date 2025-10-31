package data;

@interface Flag <fold text='{...}' expand='true'>{
    boolean value();
}</fold>

public class LogicalOperatorsWordsTestData <fold text='{...}' expand='true'>{
    @Flag(value = true && false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) <fold text='{...}' expand='true'>{
        boolean both = a<fold text=' AND ' expand='false'> && </fold>b;
        boolean either = a<fold text=' OR ' expand='false'> || </fold>b;
        boolean negated = <fold text='NOT ' expand='false'>!</fold>a;
        boolean complex = <fold text='NOT ' expand='false'>!</fold>(a<fold text=' OR ' expand='false'> || </fold>b);
        boolean precedence = a<fold text=' OR ' expand='false'> || <fold text='(b AND c)' expand='false'></fold>b && c</fold>;
        boolean precedenceLeft = <fold text='(a AND b)' expand='false'>a && b</fold><fold text=' OR ' expand='false'> || </fold>c;
        boolean mixed = a<fold text=' OR ' expand='false'> || </fold><fold text='(b AND NOT c)' expand='false'>b && !c</fold>;
        boolean nestedNot = <fold text='NOT ' expand='false'>!</fold>(a<fold text=' AND ' expand='false'> && </fold><fold text='NOT ' expand='false'>!</fold>b);
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        Flagged marker = new Flagged();
        return both && either && <fold text='NOT ' expand='false'>!</fold>negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }</fold>
}</fold>
