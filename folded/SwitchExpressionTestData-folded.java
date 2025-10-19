package data;

public class SwitchExpressionTestData {
    int map(int value) {
        return when value {
            case 0 -> 0;
            case 1, 2 -> value + 10;
            else -> -1;
        };
    }

    String describe(String text) {
        return when text {
            case "x" -> "ex";
            case "y" -> "why";
            else -> {
                String prefix = text.substring(0, 1);
                yield prefix + text;
            }
        };
    }
}
