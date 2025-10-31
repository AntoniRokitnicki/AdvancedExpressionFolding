package data;

@interface FlagLower {
    boolean value();
}

public class LogicalOperatorsWordsLowercaseTestData {
    @FlagLower(value = true and false)
    private static class Flagged {}

    public boolean demo(boolean a, boolean b, boolean c, boolean d) {
        boolean both = a and b;
        boolean either = a or b;
        boolean negated = not a;
        boolean complex = not (a or b);
        boolean precedence = a or (b and c);
        boolean precedenceLeft = (a and b) or c;
        boolean mixed = a or (b and not c);
        boolean nestedNot = not (a and not b);
        int bitwiseOr = (a ? 1 : 0) | (b ? 1 : 0);
        int bitwiseAnd = (a ? 1 : 0) & (b ? 1 : 0);
        boolean insideString = "a && b".isEmpty();
        boolean insideComment = true; // && || !
        Flagged marker = new Flagged();
        return both && either && not negated && complex && precedence && precedenceLeft && mixed && nestedNot && bitwiseOr == 0 && bitwiseAnd == 0 && insideString && insideComment && marker != null && d;
    }
}
