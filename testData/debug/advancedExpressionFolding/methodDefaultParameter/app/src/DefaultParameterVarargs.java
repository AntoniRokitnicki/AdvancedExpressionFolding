public class DefaultParameterVarargs {
    void target(int a, String... values) {}
    void target(int a) { target(a, "default"); }
}
