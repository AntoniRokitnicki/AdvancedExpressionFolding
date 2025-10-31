package data;

@interface Flag {
    boolean value();
}

public class LogicalOperatorsWordsTestData {
    @Flag(value = true && false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) {
        boolean both = a AND b;
        boolean either = a OR b;
        boolean negated = NOT a;
        boolean complex = NOT (a OR b);
        boolean precedence = a OR (b AND c);
        boolean precedenceLeft = (a AND b) OR c;
        boolean mixed = a OR (b AND NOT c);
        boolean nestedNot = NOT (a AND NOT b);
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        Flagged marker = new Flagged();
        return both && either && NOT negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }
}
