package data;

@interface Flag {
    boolean value();
}

public class LogicalOperatorsWordsTestData {
    @Flag(value = true && false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) {
        boolean both = a && b;
        boolean either = a || b;
        boolean negated = !a;
        boolean complex = !(a || b);
        boolean precedence = a || b && c;
        boolean precedenceLeft = a && b || c;
        boolean mixed = a || b && !c;
        boolean nestedNot = !(a && !b);
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        Flagged marker = new Flagged();
        return both && either && !negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }
}
