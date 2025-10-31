package data;

public class LogicalOperatorsWordsTestData {
    public boolean demo(boolean a, boolean b, boolean c, boolean d) {
        boolean both = a AND b;
        boolean either = a OR b;
        boolean negated = NOT a;
        boolean complex = NOT (a OR b);
        boolean precedence = a OR b AND c;
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        return both && either && NOT negated && complex && precedence && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && d;
    }
}
