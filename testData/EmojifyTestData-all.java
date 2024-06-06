<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public <fold text='🔒' expand='false'>final</fold> class FinalData <fold text='{...}' expand='true'>{
        private <fold text='🔒' expand='false'>final</fold> int finalField = 10;

        public final void finalMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='🔒' expand='false'><fold text='val' expand='false'>final</fold> int</fold> localFinalVariable = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> methodWithFinalParam(<fold text='🔒' expand='false'>final</fold> int param) <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='💀' expand='false'>void</fold> anotherMethod() <fold text='{...}' expand='true'>{
            <fold text='🔒' expand='false'>final</fold> int anotherFinalVariable;
            anotherFinalVariable = 20;
        }</fold>

        public FinalData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='🔒' expand='false'><fold text='val' expand='false'>final</fold> int</fold> constructorFinalVariable = 30;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> class StaticData <fold text='{...}' expand='true'>{
        private <fold text='⚡' expand='false'>static</fold> int staticField = 100;

        public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> staticMethod() <fold text='{}' expand='true'>{
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='{...}' expand='true'>{
            staticField = 200;
        }</fold>

        public StaticData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>staticMethod()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class GetterData <fold text='{...}' expand='true'>{
        private int field;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public int getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>field<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setField(int field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.field = <fold text='<<' expand='false'>field</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public void printField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>int</fold> value = <fold text='field' expand='false'>getField()</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
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
        <fold text='' expand='true'>@Override</fold>
        public void interfaceMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic enum EnumData <fold text='{...}' expand='true'>{
        ENUM_CONSTANT_1 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override</fold>
            public void abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>,
        ENUM_CONSTANT_2 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override</fold>
            public void abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        private int value;

        private EnumData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = 0<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public void setValue(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public int getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>value<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public abstract <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>

        public interface InterfaceData <fold text='{...}' expand='true'>{
            <fold text='💀' expand='false'>void</fold> interfaceMethod();
        }</fold>
    }</fold>
    public class SynchronizedData <fold text='{...}' expand='true'>{
        private int counter;

        public synchronized void increment()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>counter++<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void incrementWithBlock() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                counter++;
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    <fold text='@AllArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic class TransientVolatileData implements java.io.Serializable <fold text='{...}' expand='true'>{
        private transient int transientField;
        private volatile boolean volatileField;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public TransientVolatileData(int transientField, boolean volatileField) <fold text='{...}' expand='true'>{
            this.transientField = <fold text='<<' expand='false'>transientField</fold>;
            this.volatileField = <fold text='<<' expand='false'>volatileField</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public int getTransientField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>transientField<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public void setTransientField(int transientField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.transientField = <fold text='<<' expand='false'>transientField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isVolatileField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>volatileField<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setVolatileField(boolean volatileField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='📍' expand='false'></fold>this</fold>.volatileField = <fold text='<<' expand='false'>volatileField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public strictfp class StrictfpData <fold text='{...}' expand='true'>{
        public strictfp double calculate(double a, double b)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>a / b<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class NativeData <fold text='{...}' expand='true'>{
        public native <fold text='💀' expand='false'>void</fold> nativeMethod();
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic class InterfaceUsage implements Comparable<InterfaceUsage> <fold text='{...}' expand='true'>{
        private int value;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public InterfaceUsage(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold>
        public int compareTo(InterfaceUsage other)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>Integer.compare(<fold text='📍' expand='false'>this</fold>.value, other.value)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class AnonymousClassUsage <fold text='{...}' expand='true'>{
        public Runnable getRunnable() <fold text='{...}' expand='true'>{
            return <fold text='run() → { ' expand='false'>new Runnable() {
                <fold text='' expand='true'>@Override</fold>
                public void run() {<fold text=' ' expand='true'>
                    </fold><fold text='val' expand='false'></fold>int</fold> x = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}
            }</fold>;
        }</fold>
    }</fold>

    public class LocalClassUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useLocalClass() <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor @Getter c' expand='false'>c</fold>lass LocalClass <fold text='{...}' expand='true'>{
                private int localValue;<fold text='' expand='false'>

                <fold text='' expand='false'></fold>public LocalClass(int localValue)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='📍' expand='false'>this</fold>.localValue = <fold text='<<' expand='false'>localValue</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold><fold text='' expand='false'>

                </fold><fold text='' expand='false'>public int getLocalValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>localValue<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>

            <fold text='val' expand='false'>LocalClass</fold> localInstance = new LocalClass(10);
            <fold text='val' expand='false'>int</fold> value = localInstance.<fold text='localValue' expand='false'>getLocalValue()</fold>;
        }</fold>
    }</fold>

    public class VarArgsUsage <fold text='{...}' expand='true'>{
        public int sum(int... numbers) <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> sum = 0;
            for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> number : numbers<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                sum += number;
            }</fold>
            return sum;
        }</fold>
    }</fold>

    public class DiamondOperatorUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useDiamondOperator() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[]' expand='false'>new java.util.ArrayList<>()</fold>;
            list<fold text=' += ' expand='false'>.add(</fold>"Example"<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    public class TryWithResourcesUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> readFile(String filePath) <fold text='🪃' expand='false'>throws</fold> java.io.IOException <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> line = reader.readLine();
            }</fold>
        }</fold>
    }</fold>

    public class EnhancedForLoopUsage <fold text='{...}' expand='true'>{
        public void iterateList(java.util.List<String> list) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> item : list<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>int</fold> length = item.length();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class LambdaExpressionUsage <fold text='{...}' expand='true'>{
        public java.util.function.IntBinaryOperator getAdder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>(a, b) -> a + b<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class StreamAPIUsage <fold text='{...}' expand='true'>{
        public int sumList(java.util.List<Integer> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>list<fold text='.' expand='false'>.stream().</fold>mapToInt(Integer::intValue).sum()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class EnumSwitchUsage <fold text='{...}' expand='true'>{
        public String getDayType(DayOfWeek day) <fold text='{...}' expand='true'>{
            switch <fold text='' expand='false'>(</fold>day<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
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
        public void printList(java.util.List<String> list) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>list.forEach(item -> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>int</fold> length = item.length();
            }</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class OptionalUsage <fold text='{...}' expand='true'>{
        public String getValueOrDefault(java.util.Optional<String> optional) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>optional<fold text=' ?: ' expand='false'>.orElse(</fold>"Default Value"<fold text='' expand='false'>)</fold><fold text=' ' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>
    }</fold>

    public class MethodReferenceUsage <fold text='{...}' expand='true'>{
        public java.util.function.Function<String, Integer> getStringLengthFunction()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>String::length<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class StaticImportUsage <fold text='{...}' expand='true'>{
        public void useStaticImport()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>int</fold> max = <fold text='max(' expand='false'>java.lang.Math.max(</fold>5, 10<fold text=')' expand='false'>)</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class DefaultMethodInInterface implements DefaultMethodInterface <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        public void abstractMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public interface DefaultMethodInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> abstractMethod();

        default void defaultMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='val' expand='false'></fold>int</fold> defaultValue = 0;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class NestedClassUsage <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic class InnerClass <fold text='{...}' expand='true'>{
            private int value;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public InnerClass(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='📍' expand='false'></fold>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public int getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>value<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Builder p' expand='false'>p</fold>ublic class BuilderPatternUsage <fold text='{...}' expand='true'>{
        private <fold text='🔒' expand='false'>final</fold> int field1;
        private <fold text='🔒' expand='false'>final</fold> String field2;

        private BuilderPatternUsage(Builder builder) <fold text='{...}' expand='true'>{
            this.field1 = builder.<fold text='<<' expand='true'>field1</fold>;
            this.field2 = builder.<fold text='<<' expand='true'>field2</fold>;
        }</fold>

        public class Builder <fold text='{...}' expand='true'>{
            private int field1;
            private String field2;

            public Builder setField1(int field1) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                return <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public Builder setField2(String field2) <fold text='{...}' expand='true'>{
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                return <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public BuilderPatternUsage build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>new BuilderPatternUsage(<fold text='📍' expand='false'>this</fold>)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic class CopyConstructorUsage <fold text='{...}' expand='true'>{
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) <fold text='{...}' expand='true'>{
            this.field1 = other.<fold text='<<' expand='true'>field1</fold>;
            this.field2 = other.<fold text='<<' expand='true'>field2</fold>;
        }<fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public CopyConstructorUsage(int field1, String field2) <fold text='{...}' expand='true'>{
            this.field1 = <fold text='<<' expand='false'>field1</fold>;
            this.field2 = <fold text='<<' expand='false'>field2</fold>;
        }</fold></fold>
    }</fold>

    public class FinalizerUsage <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        protected void finalize() throws Throwable <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='🤞' expand='false'>try</fold> {
                // Finalization logic
            } finally <fold text='{...}' expand='true'>{
                super.finalize();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class VarUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useVar() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>var</fold> number = 10;
            <fold text='val' expand='false'>var</fold> text = "Hello";
        }</fold>
    }</fold>

    public class TypeInferenceUsage <fold text='{...}' expand='true'>{
        public void useTypeInference()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>java.util.Map<String, Integer></fold> map = new java.util.HashMap<>();<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class ResourceBundleUsage <fold text='{...}' expand='true'>{
        public String getMessage(String key) <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>java.util.ResourceBundle</fold> bundle = java.util.ResourceBundle.getBundle("messages");
            return bundle.getString(key);
        }</fold>
    }</fold>

    public class PatternMatchingInstanceof <fold text='{...}' expand='true'>{
        public void checkObject(Object obj) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>if <fold text='' expand='false'>(</fold>obj instanceof String str<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                int length = str.length();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class SealedClassUsage <fold text='{...}' expand='true'>{
        public abstract sealed class Shape permits Circle, Rectangle <fold text='{...}' expand='true'>{
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='🔒' expand='false'>final</fold> class Circle extends Shape <fold text='{...}' expand='true'>{
            private double radius;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public Circle(double radius)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.radius = <fold text='<<' expand='false'>radius</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public double getRadius()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>radius<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='🔒' expand='false'>final</fold> class Rectangle extends Shape <fold text='{...}' expand='true'>{
            private double length;
            private double width;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public Rectangle(double length, double width) <fold text='{...}' expand='true'>{
                this.length = <fold text='<<' expand='false'>length</fold>;
                this.width = <fold text='<<' expand='false'>width</fold>;
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public double getLength()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>length<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public double getWidth()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>width<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    class NullUsage <fold text='{...}' expand='true'>{
        public class Data <fold text='{...}' expand='true'>{

            public void methodWithNullParam(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>input = <fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold>

            public String methodReturningNull()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='val' expand='false'>String</fold> field = <fold text='🕳️' expand='false'>null</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullCheck(String input) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>if <fold text='' expand='false'>(</fold>input == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    return;
                }</fold><fold text=' ' expand='true'>
            </fold>}</fold>

            public String methodWithNullTernary(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>input != null ? </fold>input<fold text=' ?: ' expand='false'> : </fold><fold text='🕳️' expand='false'>null</fold><fold text=' ' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInArray() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String[]</fold> array = new String[10];
                array[0] = <fold text='🕳️' expand='false'>null</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInCollection() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[]' expand='false'>new java.util.ArrayList<>()</fold>;
                list<fold text=' += ' expand='false'>.add(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMap() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.Map<String, String></fold> map = new java.util.HashMap<>();
                map<fold text='[' expand='false'>.put(</fold>"key"<fold text='] = ' expand='false'>, </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInStream() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[' expand='false'>java.util.Arrays.asList(</fold><fold text='🕳️' expand='false'>null</fold>, <fold text='"value"' expand='false'>"value"</fold><fold text=']' expand='false'>)</fold>;
                <fold text='val' expand='false'>long</fold> count = list<fold text='.' expand='false'>.stream().</fold>filter(java.util.Objects::isNull).count();
            }</fold>

            public void methodWithNullInOptional() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'>java.util.Optional<String></fold> optional = java.util.<fold text='' expand='false'>Optional.ofNullable(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;<fold text=' ' expand='true'>
            </fold>}</fold>

            public void methodWithNullInSupplier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='val' expand='false'>java.util.function.Supplier<String></fold> supplier = () -> <fold text='🕳️' expand='false'>null</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullInLambda() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'>java.util.function.Function<String, String></fold> function = input -> <fold text='🕳️' expand='false'>null</fold>;<fold text=' ' expand='true'>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMethodReference() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.function.Function<Object, String></fold> function = Object::toString;
                <fold text='val' expand='false'>String</fold> result = function.apply(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullException() <fold text='{...}' expand='true'>{
                <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                    value.length();
                }</fold> <fold text='🎣' expand='false'>catch</fold> <fold text='' expand='false'>(</fold>NullPointerException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                }</fold>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInstanceof() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Object</fold> obj = <fold text='🕳️' expand='false'>null</fold>;
                <fold text='val' expand='false'>boolean</fold> isString = obj instanceof String;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullDefaultValue() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                <fold text='val' expand='false'>String</fold> result = value == <fold text='🕳️' expand='false'>null</fold> ? "default" : value;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullAssert() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                assert value != <fold text='🕳️' expand='false'>null</fold> : "Value cannot be null";
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullSynchronize() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                    if <fold text='' expand='false'>(</fold>value == <fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        return;
                    }</fold>
                }</fold>
            }</fold>

        }</fold>
    }</fold>

    class SingletonUsage <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> main() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>var</fold> s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));

            <fold text='val' expand='false'>var</fold> s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));
        }</fold>

        <fold text='⚡' expand='false'>static</fold> class Singleton <fold text='{...}' expand='true'>{
            static Singleton INSTANCE =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
            Singleton OTHER_NAME =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;

            Singleton main(Singleton s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold><fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public static Singleton getInstance()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>INSTANCE<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>
}
