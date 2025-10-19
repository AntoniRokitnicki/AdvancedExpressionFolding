public class PersonaCoreExample {
    void baseline(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("value");
        }
        for (int i = 0; i < value.length(); i++) {
            System.out.println(value.charAt(i));
        }
    }
}
