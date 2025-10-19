package data;

public class SwitchStatementTestData {
    String describe(int value) {
        String result;
        when value {
            case 0 ->
                result = "zero";
            case 1, 2 ->
                result = "small";
            case 3 ->
                return "three";
            else ->
                result = "other";
        }
        return result;
    }

    void show(int number) {
        when number {
            case 10 -> {
                System.out.println("ten");
            }
            else ->
                System.out.println("other");
        }
    }
}
