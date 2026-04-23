package data;

public class AssertTestData {
    public static void main(String[] args) {
        assert args.length != 0 : new IllegalArgumentException();
        assert args.length != 1 : new IllegalArgumentException("...");
        assert args.length != 2 : new IllegalArgumentException("...");
        String[] strings = new String[0];
        assert strings.length != 0 : new RuntimeException("error");
    }
}
