package data;

public class SwitchExpressionTestData {
    int map(int value) {
        return switch (value) {
            case 0 -> 0;
            case 1, 2 -> value + 10;
            default -> -1;
        };
    }

    String describe(String text) {
        return switch (text) {
            case "x" -> "ex";
            case "y" -> "why";
            default -> {
                String prefix = text.substring(0, 1);
                yield prefix + text;
            }
        };
    }
}
