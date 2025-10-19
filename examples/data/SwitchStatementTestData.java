package data;

public class SwitchStatementTestData {
    String describe(int value) {
        String result;
        switch (value) {
            case 0:
                result = "zero";
                break;
            case 1:
            case 2:
                result = "small";
                break;
            case 3:
                return "three";
            default:
                result = "other";
        }
        return result;
    }

    void show(int number) {
        switch (number) {
            case 10: {
                System.out.println("ten");
                break;
            }
            default:
                System.out.println("other");
        }
    }
}
