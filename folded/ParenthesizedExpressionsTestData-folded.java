package data;

@SuppressWarnings("UnusedReturnValue")
public class ParenthesizedExpressionsTestData {
    int keepTernary(int h1, int h2) {
        return 1 - (h1 >= h2 ? h2 / h1 : h1 / h2);
    }

    int keepMultiplication(int a, int b, int c) {
        return (a + b) * c;
    }

    boolean keepComparisonBranch(int value, boolean flag) {
        return value == (flag ? 1 : 0);
    }

    boolean keepLogicalWithComparison(int a, int b, boolean tail) {
        return (a > b) && tail;
    }

    int dropMatchingAddition(int a, int b, int c) {
        return a + b + c;
    }

    int dropMatchingMultiplication(int a, int b, int c) {
        return a * b * c;
    }

    int dropSimpleCall() {
        return (foo());
    }

    private int foo() {
        return 42;
    }
}
