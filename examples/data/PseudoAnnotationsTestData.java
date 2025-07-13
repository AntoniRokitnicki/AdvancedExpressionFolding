package data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class PseudoAnnotationsTestData {

    public static void staticMethod(int x) {}

    public void instanceMethod(String s) {}

    public String getValue() { return ""; }

    public void doSomething() {}

    public void primitiveParams(boolean b, char c, byte by, short s, int i, long l, float f, double d) {}

    public void dateParams(java.util.Date date, LocalDate ld, java.time.LocalDateTime ldt, ZonedDateTime zdt) {}

    public void referenceParams(String str, Object obj) {}

    public void arrayParams(int[] arr, String... varargs) {}

    public void mixedParams(int x, String s, boolean[] arr, Object... objs) {}

    public PseudoAnnotationsTestData() {}

    public PseudoAnnotationsTestData(int param) {}

    public PseudoAnnotationsTestData(String name, int value) {}

    public static void main(String[] args) {}

    public static class NestedStatic {
        public void nestedMethod(int x) {}
        public NestedStatic(String param) {}
    }

    public class NestedInstance {
        public void nestedInstanceMethod(double d) {}
        public NestedInstance() {}
        public NestedInstance(boolean flag) {}

        public class DeeplyNested {
            public void deepMethod(char c) {}
        }
    }

    public static class WithExistingMain {
        public static void main(String[] args) {}
        public void newMainMethod() {}
    }

    public static class NoDefaultConstructor {
        public NoDefaultConstructor(int required) {}
        public void testMethod(String s) {}
    }

    public static class MultipleConstructors {
        public MultipleConstructors() {}
        public MultipleConstructors(int x) {}
        public MultipleConstructors(String s, double d) {}
        public void methodToTest(boolean flag) {}
    }

    public static class VoidReturnTest {
        public void voidMethod() {}
        public static void staticVoidMethod() {}
    }

    public static class NonVoidReturnTest {
        public int intMethod() { return 0; }
        public String stringMethod() { return ""; }
        public Object objectMethod() { return null; }
        public static double staticDoubleMethod() { return 0.0; }
    }

    public static class VarargsTest {
        public void stringVarargs(String... args) {}
        public void intVarargs(int... numbers) {}
        public void mixedVarargs(String prefix, int... numbers) {}
        public static void staticVarargs(Object... objects) {}
    }

    public static class ArrayTest {
        public void intArray(int[] arr) {}
        public void stringArray(String[] arr) {}
        public void twoDimensionalArray(int[][] matrix) {}
        public void mixedArrays(int[] ints, String[] strings) {}
    }

    public static class EmptyParamsTest {
        public void noParams() {}
        public static void staticNoParams() {}
    }

}
