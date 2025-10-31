package data;

@interface FlagLower <fold text='{...}' expand='true'>{
    boolean value();
}</fold>

public class LogicalOperatorsWordsLowercaseTestData <fold text='{...}' expand='true'>{
    @FlagLower(value = true && false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) <fold text='{...}' expand='true'>{
        boolean both = a<fold text=' and ' expand='false'> && </fold>b;
        boolean either = a<fold text=' or ' expand='false'> || </fold>b;
        boolean negated = <fold text='not ' expand='false'>!</fold>a;
        boolean complex = <fold text='not ' expand='false'>!</fold>(a<fold text=' or ' expand='false'> || </fold>b);
        boolean precedence = a<fold text=' or ' expand='false'> || <fold text='(b and c)' expand='false'></fold>b && c</fold>;
        boolean precedenceLeft = <fold text='(a and b)' expand='false'>a && b</fold><fold text=' or ' expand='false'> || </fold>c;
        boolean mixed = a<fold text=' or ' expand='false'> || </fold><fold text='(b and not c)' expand='false'>b && !c</fold>;
        boolean nestedNot = <fold text='not ' expand='false'>!</fold>(a<fold text=' and ' expand='false'> && </fold><fold text='not ' expand='false'>!</fold>b);
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        Flagged marker = new Flagged();
        return both && either && <fold text='not ' expand='false'>!</fold>negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }</fold>
}</fold>
