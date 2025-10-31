package data;

public class LogicalOperatorsWordsTestData {
    public boolean demo(boolean a, boolean b, boolean c, boolean d) <fold text='{...}' expand='true'>{
        boolean both = a<fold text=' AND ' expand='false'> && </fold>b;
        boolean either = a<fold text=' OR ' expand='false'> || </fold>b;
        boolean negated = <fold text='NOT ' expand='false'>!</fold>a;
        boolean complex = <fold text='NOT ' expand='false'>!</fold>(a<fold text=' OR ' expand='false'> || </fold>b);
        boolean precedence = a<fold text=' OR ' expand='false'> || </fold>b<fold text=' AND ' expand='false'> && </fold>c;
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        return both && either && <fold text='NOT ' expand='false'>!</fold>negated && complex && precedence && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && d;
    }</fold>
}
