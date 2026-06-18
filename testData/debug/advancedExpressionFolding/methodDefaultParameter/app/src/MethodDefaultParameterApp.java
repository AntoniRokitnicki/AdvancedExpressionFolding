public class MethodDefaultParameterApp {
    public static void main(String[] args) {
        invokeWithDefault();
    }

    private static void invokeWithDefault() {
        target(21);
    }

    private static void target(int value, String... values) {
        // Intentionally empty; real debugger session will attach here.
    }

    private static void target(int value) {
        // Breakpoint! LogExpression("target invoked")
        target(value, "default");
    }
}
