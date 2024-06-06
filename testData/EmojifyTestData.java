<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public <fold text='🔒' expand='false'>final</fold> class FinalData <fold text='{...}' expand='true'>{
        private <fold text='🔒' expand='false'>final</fold> int finalField = 10;

        public <fold text='🔒' expand='false'>final</fold> <fold text='💀' expand='false'>void</fold> finalMethod()<fold text=' { ' expand='false'> {
            </fold><fold text='🔒' expand='false'>final</fold> int localFinalVariable = 5;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> methodWithFinalParam(<fold text='🔒' expand='false'>final</fold> int param) <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='💀' expand='false'>void</fold> anotherMethod() <fold text='{...}' expand='true'>{
            <fold text='🔒' expand='false'>final</fold> int anotherFinalVariable;
            anotherFinalVariable = 20;
        }</fold>

        public FinalData()<fold text=' { ' expand='false'> {
            </fold><fold text='🔒' expand='false'>final</fold> int constructorFinalVariable = 30;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> class StaticData <fold text='{...}' expand='true'>{
        private <fold text='⚡' expand='false'>static</fold> int staticField = 100;

        public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> staticMethod() <fold text='{}' expand='true'>{
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='{...}' expand='true'>{
            staticField = 200;
        }</fold>

        public StaticData()<fold text=' { ' expand='false'> {
            </fold>staticMethod();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class GetterData <fold text='{...}' expand='true'>{
        private int field;

        public int getField()<fold text=' { ' expand='false'> {
            </fold>return field;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setField(int field)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.field = field;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> printField()<fold text=' { ' expand='false'> {
            </fold>int value = getField();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public abstract class AbstractData <fold text='{...}' expand='true'>{
        public abstract <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public interface InterfaceData <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> interfaceMethod();
    }</fold>

    public class InterfaceImplementation implements InterfaceData <fold text='{...}' expand='true'>{
        @Override
        public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public enum EnumData <fold text='{...}' expand='true'>{
        ENUM_CONSTANT_1 <fold text='{...}' expand='true'>{
            @Override
            public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>,
        ENUM_CONSTANT_2 <fold text='{...}' expand='true'>{
            @Override
            public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        private int value;

        private EnumData()<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.value = 0;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setValue(int value)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
        }</fold>

        public int getValue()<fold text=' { ' expand='false'> {
            </fold>return value;<fold text=' }' expand='false'>
        }</fold>

        public abstract <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>

        public interface InterfaceData <fold text='{...}' expand='true'>{
            <fold text='💀' expand='false'>void</fold> interfaceMethod();
        }</fold>
    }</fold>
    public class SynchronizedData <fold text='{...}' expand='true'>{
        private int counter;

        public synchronized <fold text='💀' expand='false'>void</fold> increment()<fold text=' { ' expand='false'> {
            </fold>counter++;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> incrementWithBlock() <fold text='{...}' expand='true'>{
            synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                counter++;
            }</fold>
        }</fold>
    }</fold>

    public class TransientVolatileData implements java.io.Serializable <fold text='{...}' expand='true'>{
        private transient int transientField;
        private volatile boolean volatileField;

        public TransientVolatileData(int transientField, boolean volatileField) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.transientField = transientField;
            <fold text='📍' expand='false'>this</fold>.volatileField = volatileField;
        }</fold>

        public int getTransientField()<fold text=' { ' expand='false'> {
            </fold>return transientField;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setTransientField(int transientField)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.transientField = transientField;<fold text=' }' expand='false'>
        }</fold>

        public boolean isVolatileField()<fold text=' { ' expand='false'> {
            </fold>return volatileField;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setVolatileField(boolean volatileField)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.volatileField = volatileField;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public strictfp class StrictfpData <fold text='{...}' expand='true'>{
        public strictfp double calculate(double a, double b)<fold text=' { ' expand='false'> {
            </fold>return a / b;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class NativeData <fold text='{...}' expand='true'>{
        public native <fold text='💀' expand='false'>void</fold> nativeMethod();
    }</fold>

    public class InterfaceUsage implements Comparable<InterfaceUsage> <fold text='{...}' expand='true'>{
        private int value;

        public InterfaceUsage(int value)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public int compareTo(InterfaceUsage other)<fold text=' { ' expand='false'> {
            </fold>return Integer.compare(<fold text='📍' expand='false'>this</fold>.value, other.value);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class AnonymousClassUsage <fold text='{...}' expand='true'>{
        public Runnable getRunnable() <fold text='{...}' expand='true'>{
            return <fold text='run() → { ' expand='false'>new Runnable() {
                @Override
                public <fold text='💀' expand='false'>void</fold> run() {
                    </fold>int x = 5;<fold text=' }' expand='false'>
                }
            }</fold>;
        }</fold>
    }</fold>

    public class LocalClassUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useLocalClass() <fold text='{...}' expand='true'>{
            class LocalClass <fold text='{...}' expand='true'>{
                private int localValue;

                public LocalClass(int localValue)<fold text=' { ' expand='false'> {
                    <fold text='📍' expand='false'></fold>this</fold>.localValue = localValue;<fold text=' }' expand='false'>
                }</fold>

                public int getLocalValue()<fold text=' { ' expand='false'> {
                    </fold>return localValue;<fold text=' }' expand='false'>
                }</fold>
            }</fold>

            LocalClass localInstance = new LocalClass(10);
            int value = localInstance.getLocalValue();
        }</fold>
    }</fold>

    public class VarArgsUsage <fold text='{...}' expand='true'>{
        public int sum(int... numbers) <fold text='{...}' expand='true'>{
            int sum = 0;
            for (int number : numbers) <fold text='{...}' expand='true'>{
                sum += number;
            }</fold>
            return sum;
        }</fold>
    }</fold>

    public class DiamondOperatorUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useDiamondOperator() <fold text='{...}' expand='true'>{
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }</fold>
    }</fold>

    public class TryWithResourcesUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> readFile(String filePath) <fold text='🪃' expand='false'>throws</fold> java.io.IOException <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) <fold text='{...}' expand='true'>{
                String line = reader.readLine();
            }</fold>
        }</fold>
    }</fold>

    public class EnhancedForLoopUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> iterateList(java.util.List<String> list) <fold text='{...}' expand='true'>{
            for (String item : list) <fold text='{...}' expand='true'>{
                int length = item.length();
            }</fold>
        }</fold>
    }</fold>

    public class LambdaExpressionUsage <fold text='{...}' expand='true'>{
        public java.util.function.IntBinaryOperator getAdder()<fold text=' { ' expand='false'> {
            </fold>return (a, b) -> a + b;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class StreamAPIUsage <fold text='{...}' expand='true'>{
        public int sumList(java.util.List<Integer> list)<fold text=' { ' expand='false'> {
            </fold>return list.stream().mapToInt(Integer::intValue).sum();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class EnumSwitchUsage <fold text='{...}' expand='true'>{
        public String getDayType(DayOfWeek day) <fold text='{...}' expand='true'>{
            switch (day) <fold text='{...}' expand='true'>{
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
            }</fold>
        }</fold>
    }</fold>

    public class ForEachMethodUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> printList(java.util.List<String> list) <fold text='{...}' expand='true'>{
            list.forEach(item -> <fold text='{...}' expand='true'>{
                int length = item.length();
            }</fold>);
        }</fold>
    }</fold>

    public class OptionalUsage <fold text='{...}' expand='true'>{
        public String getValueOrDefault(java.util.Optional<String> optional) <fold text='{...}' expand='true'>{
            return optional.orElse("Default Value");
        }</fold>
    }</fold>

    public class MethodReferenceUsage <fold text='{...}' expand='true'>{
        public java.util.function.Function<String, Integer> getStringLengthFunction()<fold text=' { ' expand='false'> {
            </fold>return String::length;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class StaticImportUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useStaticImport()<fold text=' { ' expand='false'> {
            </fold>int max = <fold text='max(' expand='false'>java.lang.Math.max(</fold>5, 10<fold text=')' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class DefaultMethodInInterface implements DefaultMethodInterface <fold text='{...}' expand='true'>{
        @Override
        public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public interface DefaultMethodInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> abstractMethod();

        default <fold text='💀' expand='false'>void</fold> defaultMethod()<fold text=' { ' expand='false'> {
            </fold>int defaultValue = 0;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class NestedClassUsage <fold text='{...}' expand='true'>{
        public class InnerClass <fold text='{...}' expand='true'>{
            private int value;

            public InnerClass(int value)<fold text=' { ' expand='false'> {
                </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
            }</fold>

            public int getValue()<fold text=' { ' expand='false'> {
                </fold>return value;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class BuilderPatternUsage <fold text='{...}' expand='true'>{
        private <fold text='🔒' expand='false'>final</fold> int field1;
        private <fold text='🔒' expand='false'>final</fold> String field2;

        private BuilderPatternUsage(Builder builder) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = builder.field1;
            <fold text='📍' expand='false'>this</fold>.field2 = builder.field2;
        }</fold>

        public class Builder <fold text='{...}' expand='true'>{
            private int field1;
            private String field2;

            public Builder setField1(int field1) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = field1;
                return <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public Builder setField2(String field2) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field2 = field2;
                return <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public BuilderPatternUsage build()<fold text=' { ' expand='false'> {
                </fold>return new BuilderPatternUsage(<fold text='📍' expand='false'>this</fold>);<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public class CopyConstructorUsage <fold text='{...}' expand='true'>{
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = other.field1;
            <fold text='📍' expand='false'>this</fold>.field2 = other.field2;
        }</fold>

        public CopyConstructorUsage(int field1, String field2) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = field1;
            <fold text='📍' expand='false'>this</fold>.field2 = field2;
        }</fold>
    }</fold>

    public class FinalizerUsage <fold text='{...}' expand='true'>{
        @Override
        <fold text='🛡️' expand='false'>protected</fold> <fold text='💀' expand='false'>void</fold> finalize() <fold text='🪃' expand='false'>throws</fold> Throwable <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> {
                // Finalization logic
            } finally <fold text='{...}' expand='true'>{
                super.finalize();
            }</fold>
        }</fold>
    }</fold>

    public class VarUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useVar() <fold text='{...}' expand='true'>{
            var number = 10;
            var text = "Hello";
        }</fold>
    }</fold>

    public class TypeInferenceUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useTypeInference()<fold text=' { ' expand='false'> {
            </fold>java.util.Map<String, Integer> map = new java.util.HashMap<>();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class ResourceBundleUsage <fold text='{...}' expand='true'>{
        public String getMessage(String key) <fold text='{...}' expand='true'>{
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            return bundle.getString(key);
        }</fold>
    }</fold>

    public class PatternMatchingInstanceof <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> checkObject(Object obj) <fold text='{...}' expand='true'>{
            if (obj instanceof String str) <fold text='{...}' expand='true'>{
                int length = str.length();
            }</fold>
        }</fold>
    }</fold>

    public class SealedClassUsage <fold text='{...}' expand='true'>{
        public abstract sealed class Shape permits Circle, Rectangle <fold text='{...}' expand='true'>{
        }</fold>

        public <fold text='🔒' expand='false'>final</fold> class Circle extends Shape <fold text='{...}' expand='true'>{
            private double radius;

            public Circle(double radius)<fold text=' { ' expand='false'> {
                </fold><fold text='📍' expand='false'>this</fold>.radius = radius;<fold text=' }' expand='false'>
            }</fold>

            public double getRadius()<fold text=' { ' expand='false'> {
                </fold>return radius;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        public <fold text='🔒' expand='false'>final</fold> class Rectangle extends Shape <fold text='{...}' expand='true'>{
            private double length;
            private double width;

            public Rectangle(double length, double width) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.length = length;
                <fold text='📍' expand='false'>this</fold>.width = width;
            }</fold>

            public double getLength()<fold text=' { ' expand='false'> {
                </fold>return length;<fold text=' }' expand='false'>
            }</fold>

            public double getWidth()<fold text=' { ' expand='false'> {
                </fold>return width;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    class NullUsage <fold text='{...}' expand='true'>{
        public class Data <fold text='{...}' expand='true'>{

            public <fold text='💀' expand='false'>void</fold> methodWithNullParam(String input)<fold text=' { ' expand='false'> {
                </fold>input = <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public String methodReturningNull()<fold text=' { ' expand='false'> {
                </fold>return <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullField()<fold text=' { ' expand='false'> {
                </fold>String field = <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullCheck(String input) <fold text='{...}' expand='true'>{
                if (input == <fold text='🕳️' expand='false'>null</fold>) <fold text='{...}' expand='true'>{
                    return;
                }</fold>
            }</fold>

            public String methodWithNullTernary(String input)<fold text=' { ' expand='false'> {
                </fold>return input != <fold text='🕳️' expand='false'>null</fold> ? input : <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInArray() <fold text='{...}' expand='true'>{
                String[] array = new String[10];
                array[0] = <fold text='🕳️' expand='false'>null</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInCollection() <fold text='{...}' expand='true'>{
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMap() <fold text='{...}' expand='true'>{
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", <fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInStream() <fold text='{...}' expand='true'>{
                java.util.List<String> list = java.util.Arrays.asList(<fold text='🕳️' expand='false'>null</fold>, "value");
                long count = list.stream().filter(java.util.Objects::isNull).count();
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInOptional() <fold text='{...}' expand='true'>{
                java.util.Optional<String> optional = java.util.Optional.ofNullable(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInSupplier()<fold text=' { ' expand='false'> {
                </fold>java.util.function.Supplier<String> supplier = () -> <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInLambda() <fold text='{...}' expand='true'>{
                java.util.function.Function<String, String> function = input -> <fold text='🕳️' expand='false'>null</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMethodReference() <fold text='{...}' expand='true'>{
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullException() <fold text='{...}' expand='true'>{
                <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
                    String value = <fold text='🕳️' expand='false'>null</fold>;
                    value.length();
                }</fold> <fold text='🎣' expand='false'>catch</fold> (NullPointerException e) <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                }</fold>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInstanceof() <fold text='{...}' expand='true'>{
                Object obj = <fold text='🕳️' expand='false'>null</fold>;
                boolean isString = obj instanceof String;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullDefaultValue() <fold text='{...}' expand='true'>{
                String value = <fold text='🕳️' expand='false'>null</fold>;
                String result = value == <fold text='🕳️' expand='false'>null</fold> ? "default" : value;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullAssert() <fold text='{...}' expand='true'>{
                String value = <fold text='🕳️' expand='false'>null</fold>;
                assert value != <fold text='🕳️' expand='false'>null</fold> : "Value cannot be null";
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullSynchronize() <fold text='{...}' expand='true'>{
                String value = <fold text='🕳️' expand='false'>null</fold>;
                synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                    if (value == <fold text='🕳️' expand='false'>null</fold>) <fold text='{...}' expand='true'>{
                        return;
                    }</fold>
                }</fold>
            }</fold>

        }</fold>
    }</fold>

    class SingletonUsage <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> main() <fold text='{...}' expand='true'>{
            var s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.isOk());
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.getInstance())));

            var s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME;
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.isOk());
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.getInstance())));
        }</fold>

        <fold text='⚡' expand='false'>static</fold> class Singleton <fold text='{...}' expand='true'>{
            <fold text='⚡' expand='false'>static</fold> Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            boolean ok;

            Singleton main(Singleton s)<fold text=' { ' expand='false'> {
                </fold>return <fold text='📍' expand='false'>this</fold>;<fold text=' }' expand='false'>
            }</fold>

            public boolean isOk()<fold text=' { ' expand='false'> {
                </fold>return ok;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='⚡' expand='false'>static</fold> Singleton getInstance()<fold text=' { ' expand='false'> {
                </fold>return INSTANCE;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>
}
