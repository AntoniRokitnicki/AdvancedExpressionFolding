📦 data;

🚢 java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public 🔒 class FinalData {
        private 🔒 int finalField = 10;

        public 🔒 💀 finalMethod() {
            🔒 int localFinalVariable = 5;
        }

        public 💀 methodWithFinalParam(🔒 int param) {
        }

        public 💀 anotherMethod() {
            🔒 int anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {
            🔒 int constructorFinalVariable = 30;
        }
    }

    public ⚡ class StaticData {
        private ⚡ int staticField = 100;

        public ⚡ 💀 staticMethod() {
        }

        ⚡ {
            staticField = 200;
        }

        public StaticData() {
            staticMethod();
        }
    }

    public class GetterData {
        private int field;

        public int getField() {
            return field;
        }

        public 💀 setField(int field) {
            📍.field = field;
        }

        public 💀 printField() {
            int value = getField();
        }
    }

    public abstract class AbstractData {
        public abstract 💀 abstractMethod();

        public 💀 concreteMethod() {
        }
    }

    public interface InterfaceData {
        💀 interfaceMethod();
    }

    public class InterfaceImplementation implements InterfaceData {
        @Override
        public 💀 interfaceMethod() {
        }
    }

    public enum EnumData {
        ENUM_CONSTANT_1 {
            @Override
            public 💀 abstractMethod() {
            }

            public 💀 interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            @Override
            public 💀 abstractMethod() {
            }

            public 💀 interfaceMethod() {
            }
        };

        private int value;

        private EnumData() {
            📍.value = 0;
        }

        public 💀 setValue(int value) {
            📍.value = value;
        }

        public int getValue() {
            return value;
        }

        public abstract 💀 abstractMethod();

        public 💀 concreteMethod() {
        }

        public interface InterfaceData {
            💀 interfaceMethod();
        }
    }
    public class SynchronizedData {
        private int counter;

        public synchronized 💀 increment() {
            counter++;
        }

        public 💀 incrementWithBlock() {
            synchronized (📍) {
                counter++;
            }
        }
    }

    public class TransientVolatileData implements java.io.Serializable {
        private transient int transientField;
        private volatile boolean volatileField;

        public TransientVolatileData(int transientField, boolean volatileField) {
            📍.transientField = transientField;
            📍.volatileField = volatileField;
        }

        public int getTransientField() {
            return transientField;
        }

        public 💀 setTransientField(int transientField) {
            📍.transientField = transientField;
        }

        public boolean isVolatileField() {
            return volatileField;
        }

        public 💀 setVolatileField(boolean volatileField) {
            📍.volatileField = volatileField;
        }
    }

    public strictfp class StrictfpData {
        public strictfp double calculate(double a, double b) {
            return a / b;
        }
    }

    public class NativeData {
        public native 💀 nativeMethod();
    }

    public class InterfaceUsage implements Comparable<InterfaceUsage> {
        private int value;

        public InterfaceUsage(int value) {
            📍.value = value;
        }

        @Override
        public int compareTo(InterfaceUsage other) {
            return Integer.compare(📍.value, other.value);
        }
    }

    public class AnonymousClassUsage {
        public Runnable getRunnable() {
            return new Runnable() {
                @Override
                public 💀 run() {
                    int x = 5;
                }
            };
        }
    }

    public class LocalClassUsage {
        public 💀 useLocalClass() {
            class LocalClass {
                private int localValue;

                public LocalClass(int localValue) {
                    📍.localValue = localValue;
                }

                public int getLocalValue() {
                    return localValue;
                }
            }

            LocalClass localInstance = new LocalClass(10);
            int value = localInstance.getLocalValue();
        }
    }

    public class VarArgsUsage {
        public int sum(int... numbers) {
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }
            return sum;
        }
    }

    public class DiamondOperatorUsage {
        public 💀 useDiamondOperator() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }
    }

    public class TryWithResourcesUsage {
        public 💀 readFile(String filePath) 🪃 java.io.IOException {
            🤞 (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line = reader.readLine();
            }
        }
    }

    public class EnhancedForLoopUsage {
        public 💀 iterateList(java.util.List<String> list) {
            for (String item : list) {
                int length = item.length();
            }
        }
    }

    public class LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {
            return (a, b) -> a + b;
        }
    }

    public class StreamAPIUsage {
        public int sumList(java.util.List<Integer> list) {
            return list.stream().mapToInt(Integer::intValue).sum();
        }
    }

    public class EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            switch (day) {
                case MONDAY:
                case TUESDAY:
                case WEDNESDAY:
                case THURSDAY:
                case FRIDAY:
                    return "Weekday";
                case SATURDAY:
                case SUNDAY:
                    return "Weekend";
                default:
                    return "Unknown";
            }
        }
    }

    public class ForEachMethodUsage {
        public 💀 printList(java.util.List<String> list) {
            list.forEach(item -> {
                int length = item.length();
            });
        }
    }

    public class OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {
            return optional.orElse("Default Value");
        }
    }

    public class MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {
            return String::length;
        }
    }

    public class StaticImportUsage {
        public 💀 useStaticImport() {
            int max = max(5, 10);
        }
    }

    public class DefaultMethodInInterface implements DefaultMethodInterface {
        @Override
        public 💀 abstractMethod() {
        }
    }

    public interface DefaultMethodInterface {
        💀 abstractMethod();

        default 💀 defaultMethod() {
            int defaultValue = 0;
        }
    }

    public class NestedClassUsage {
        public class InnerClass {
            private int value;

            public InnerClass(int value) {
                📍.value = value;
            }

            public int getValue() {
                return value;
            }
        }
    }

    public class BuilderPatternUsage {
        private 🔒 int field1;
        private 🔒 String field2;

        private BuilderPatternUsage(Builder builder) {
            📍.field1 = builder.field1;
            📍.field2 = builder.field2;
        }

        public class Builder {
            private int field1;
            private String field2;

            public Builder setField1(int field1) {
                📍.field1 = field1;
                return 📍;
            }

            public Builder setField2(String field2) {
                📍.field2 = field2;
                return 📍;
            }

            public BuilderPatternUsage build() {
                return new BuilderPatternUsage(📍);
            }
        }
    }

    public class CopyConstructorUsage {
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            📍.field1 = other.field1;
            📍.field2 = other.field2;
        }

        public CopyConstructorUsage(int field1, String field2) {
            📍.field1 = field1;
            📍.field2 = field2;
        }
    }

    public class FinalizerUsage {
        @Override
        🛡️ 💀 finalize() 🪃 Throwable {
            🤞 {
                // Finalization logic
            } finally {
                super.finalize();
            }
        }
    }

    public class VarUsage {
        public 💀 useVar() {
            var number = 10;
            var text = "Hello";
        }
    }

    public class TypeInferenceUsage {
        public 💀 useTypeInference() {
            java.util.Map<String, Integer> map = new java.util.HashMap<>();
        }
    }

    public class ResourceBundleUsage {
        public String getMessage(String key) {
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            return bundle.getString(key);
        }
    }

    public class PatternMatchingInstanceof {
        public 💀 checkObject(Object obj) {
            if (obj instanceof String str) {
                int length = str.length();
            }
        }
    }

    public class SealedClassUsage {
        public abstract sealed class Shape permits Circle, Rectangle {
        }

        public 🔒 class Circle extends Shape {
            private double radius;

            public Circle(double radius) {
                📍.radius = radius;
            }

            public double getRadius() {
                return radius;
            }
        }

        public 🔒 class Rectangle extends Shape {
            private double length;
            private double width;

            public Rectangle(double length, double width) {
                📍.length = length;
                📍.width = width;
            }

            public double getLength() {
                return length;
            }

            public double getWidth() {
                return width;
            }
        }
    }

    class NullUsage {
        public class Data {

            public 💀 methodWithNullParam(String input) {
                input = 🕳️;
            }

            public String methodReturningNull() {
                return 🕳️;
            }

            public 💀 methodWithNullField() {
                String field = 🕳️;
            }

            public 💀 methodWithNullCheck(String input) {
                if (input == 🕳️) {
                    return;
                }
            }

            public String methodWithNullTernary(String input) {
                return input != 🕳️ ? input : 🕳️;
            }

            public 💀 methodWithNullInArray() {
                String[] array = new String[10];
                array[0] = 🕳️;
            }

            public 💀 methodWithNullInCollection() {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add(🕳️);
            }

            public 💀 methodWithNullInMap() {
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", 🕳️);
            }

            public 💀 methodWithNullInStream() {
                java.util.List<String> list = java.util.Arrays.asList(🕳️, "value");
                long count = list.stream().filter(java.util.Objects::isNull).count();
            }

            public 💀 methodWithNullInOptional() {
                java.util.Optional<String> optional = java.util.Optional.ofNullable(🕳️);
            }

            public 💀 methodWithNullInSupplier() {
                java.util.function.Supplier<String> supplier = () -> 🕳️;
            }

            public 💀 methodWithNullInLambda() {
                java.util.function.Function<String, String> function = input -> 🕳️;
            }

            public 💀 methodWithNullInMethodReference() {
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply(🕳️);
            }

            public 💀 methodWithNullException() {
                🤞 {
                    String value = 🕳️;
                    value.length();
                } 🎣 (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            public 💀 methodWithNullInstanceof() {
                Object obj = 🕳️;
                boolean isString = obj instanceof String;
            }

            public 💀 methodWithNullDefaultValue() {
                String value = 🕳️;
                String result = value == 🕳️ ? "default" : value;
            }

            public 💀 methodWithNullAssert() {
                String value = 🕳️;
                assert value != 🕳️ : "Value cannot be null";
            }

            public 💀 methodWithNullSynchronize() {
                String value = 🕳️;
                synchronized (📍) {
                    if (value == 🕳️) {
                        return;
                    }
                }
            }

        }
    }

    class SingletonUsage {
        💀 main() {
            var s = Singleton.🧍;
            System.out.println(Singleton.🧍.isOk());
            System.out.println(Singleton.🧍.main(Singleton.🧍.main(Singleton.getInstance())));

            var s2 = Singleton.🧍.OTHER_NAME;
            System.out.println(Singleton.🧍.OTHER_NAME.isOk());
            System.out.println(Singleton.🧍.OTHER_NAME.main(Singleton.🧍.OTHER_NAME.main(Singleton.getInstance())));
        }

        ⚡ class Singleton {
            ⚡ Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            boolean ok;

            Singleton main(Singleton s) {
                return 📍;
            }

            public boolean isOk() {
                return ok;
            }

            public ⚡ Singleton getInstance() {
                return INSTANCE;
            }
        }
    }
}
