package data;

public class LogicalOperatorsWordsTestData {
    public boolean demo(boolean a, boolean b, boolean c, boolean d) {
        boolean both = a && b;
        boolean either = a || b;
        boolean negated = !a;
        boolean complex = !(a || b);
        boolean precedence = a || b && c;
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        return both && either && !negated && complex && precedence && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && d;
    }
}
