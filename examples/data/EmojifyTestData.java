package data;

import java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public final class FinalData {
        private final int finalField = 10;

        public final void finalMethod() {
            final int localFinalVariable = 5;
        }

        public void methodWithFinalParam(final int param) {
        }

        public void anotherMethod() {
            final int anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {
            final int constructorFinalVariable = 30;
        }
    }

    public static class StaticData {
        private static int staticField = 100;

        public static void staticMethod() {
        }

        static {
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

        public void setField(int field) {
            this.field = field;
        }

        public void printField() {
            int value = getField();
        }
    }

    public abstract class AbstractData {
        public abstract void abstractMethod();

        public void concreteMethod() {
        }
    }

    public interface InterfaceData {
        void interfaceMethod();
    }

    public class InterfaceImplementation implements InterfaceData {
        @Override
        public void interfaceMethod() {
        }
    }

    public enum EnumData {
        ENUM_CONSTANT_1 {
            @Override
            public void abstractMethod() {
            }

            public void interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            @Override
            public void abstractMethod() {
            }

            public void interfaceMethod() {
            }
        };

        private int value;

        private EnumData() {
            this.value = 0;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public abstract void abstractMethod();

        public void concreteMethod() {
        }

        public interface InterfaceData {
            void interfaceMethod();
        }
    }
    public class SynchronizedData {
        private int counter;

        public synchronized void increment() {
            counter++;
        }

        public void incrementWithBlock() {
            synchronized (this) {
                counter++;
            }
        }
    }

    public class TransientVolatileData implements java.io.Serializable {
        private transient int transientField;
        private volatile boolean volatileField;

        public TransientVolatileData(int transientField, boolean volatileField) {
            this.transientField = transientField;
            this.volatileField = volatileField;
        }

        public int getTransientField() {
            return transientField;
        }

        public void setTransientField(int transientField) {
            this.transientField = transientField;
        }

        public boolean isVolatileField() {
            return volatileField;
        }

        public void setVolatileField(boolean volatileField) {
            this.volatileField = volatileField;
        }
    }

    public class NativeData {
        public native void nativeMethod();
    }

    public class InterfaceUsage implements Comparable<InterfaceUsage> {
        private int value;

        public InterfaceUsage(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(InterfaceUsage other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public class AnonymousClassUsage {
        public Runnable getRunnable() {
            return new Runnable() {
                @Override
                public void run() {
                    int x = 5;
                }
            };
        }
    }

    public class LocalClassUsage {
        public void useLocalClass() {
            class LocalClass {
                private int localValue;

                public LocalClass(int localValue) {
                    this.localValue = localValue;
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
        public void useDiamondOperator() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }
    }

    public class TryWithResourcesUsage {
        public void readFile(String filePath) throws java.io.IOException {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line = reader.readLine();
            }
        }
    }

    public class EnhancedForLoopUsage {
        public void iterateList(java.util.List<String> list) {
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
        public void printList(java.util.List<String> list) {
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
        public void useStaticImport() {
            int max = java.lang.Math.max(5, 10);
        }
    }

    public class DefaultMethodInInterface implements DefaultMethodInterface {
        @Override
        public void abstractMethod() {
        }
    }

    public interface DefaultMethodInterface {
        void abstractMethod();

        default void defaultMethod() {
            int defaultValue = 0;
        }
    }

    public class NestedClassUsage {
        public class InnerClass {
            private int value;

            public InnerClass(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
    }

    public class BuilderPatternUsage {
        private final int field1;
        private final String field2;

        private BuilderPatternUsage(Builder builder) {
            this.field1 = builder.field1;
            this.field2 = builder.field2;
        }

        public class Builder {
            private int field1;
            private String field2;

            public Builder setField1(int field1) {
                this.field1 = field1;
                return this;
            }

            public Builder setField2(String field2) {
                this.field2 = field2;
                return this;
            }

            public BuilderPatternUsage build() {
                return new BuilderPatternUsage(this);
            }
        }
    }

    public class CopyConstructorUsage {
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            this.field1 = other.field1;
            this.field2 = other.field2;
        }

        public CopyConstructorUsage(int field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    public class FinalizerUsage {
        protected void performCleanup() {
            try {
                // Cleanup logic
            } finally {
                completeCleanup();
            }
        }

        protected void completeCleanup() {
            // Base cleanup logic
        }
    }

    public class VarUsage {
        public void useVar() {
            var number = 10;
            var text = "Hello";
        }
    }

    public class TypeInferenceUsage {
        public void useTypeInference() {
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
        public void checkObject(Object obj) {
            if (obj instanceof String str) {
                int length = str.length();
            }
        }
    }

    public class SealedClassUsage {
        public abstract sealed class Shape permits Circle, Rectangle {
        }

        public final class Circle extends Shape {
            private double radius;

            public Circle(double radius) {
                this.radius = radius;
            }

            public double getRadius() {
                return radius;
            }
        }

        public final class Rectangle extends Shape {
            private double length;
            private double width;

            public Rectangle(double length, double width) {
                this.length = length;
                this.width = width;
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

            public void methodWithNullParam(String input) {
                input = null;
            }

            public String methodReturningNull() {
                return null;
            }

            public void methodWithNullField() {
                String field = null;
            }

            public void methodWithNullCheck(String input) {
                if (input == null) {
                    return;
                }
            }

            public String methodWithNullTernary(String input) {
                return input != null ? input : null;
            }

            public void methodWithNullInArray() {
                String[] array = new String[10];
                array[0] = null;
            }

            public void methodWithNullInCollection() {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add(null);
            }

            public void methodWithNullInMap() {
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", null);
            }

            public void methodWithNullInStream() {
                java.util.List<String> list = java.util.Arrays.asList(null, "value");
                long count = list.stream().filter(java.util.Objects::isNull).count();
            }

            public void methodWithNullInOptional() {
                java.util.Optional<String> optional = java.util.Optional.ofNullable(null);
            }

            public void methodWithNullInSupplier() {
                java.util.function.Supplier<String> supplier = () -> null;
            }

            public void methodWithNullInLambda() {
                java.util.function.Function<String, String> function = input -> null;
            }

            public void methodWithNullInMethodReference() {
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply(null);
            }

            public void methodWithNullException() {
                try {
                    String value = null;
                    value.length();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            public void methodWithNullInstanceof() {
                Object obj = null;
                boolean isString = obj instanceof String;
            }

            public void methodWithNullDefaultValue() {
                String value = null;
                String result = value == null ? "default" : value;
            }

            public void methodWithNullAssert() {
                String value = null;
                assert value != null : "Value cannot be null";
            }

            public void methodWithNullSynchronize() {
                String value = null;
                synchronized (this) {
                    if (value == null) {
                        return;
                    }
                }
            }

        }
    }

    class SingletonUsage {
        void main() {
            var s = Singleton.INSTANCE;
            System.out.println(Singleton.INSTANCE.isOk());
            System.out.println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.getInstance())));

            var s2 = Singleton.INSTANCE.OTHER_NAME;
            System.out.println(Singleton.INSTANCE.OTHER_NAME.isOk());
            System.out.println(Singleton.INSTANCE.OTHER_NAME.main(Singleton.INSTANCE.OTHER_NAME.main(Singleton.getInstance())));
        }

        static class Singleton {
            static Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            boolean ok;

            Singleton main(Singleton s) {
                return this;
            }

            public boolean isOk() {
                return ok;
            }

            public static Singleton getInstance() {
                return INSTANCE;
            }
        }
    }
}
