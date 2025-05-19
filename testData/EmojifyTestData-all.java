package data;

import java.time.DayOfWeek;

<fold text='' expand='true'>@SuppressWarnings("ALL")</fold><fold text='' expand='true'>
</fold>public class EmojifyTestData {

    public <fold text='' expand='false'>final</fold> class FinalData <fold text='{...}' expand='true'>{
        private final int finalField = 10;

        public <fold text='' expand='false'>final</fold> void finalMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'><fold text='val' expand='false'>final</fold> int</fold> localFinalVariable = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void methodWithFinalParam(<fold text='' expand='false'>final</fold> int param) <fold text='{}' expand='true'>{
        }</fold>

        public void anotherMethod() <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>final</fold> int anotherFinalVariable;
            anotherFinalVariable = 20;
        }</fold>

        public FinalData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'><fold text='val' expand='false'>final</fold> int</fold> constructorFinalVariable = 30;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public static class StaticData <fold text='{...}' expand='true'>{
        private static int staticField = 100;

        public static void staticMethod() <fold text='{}' expand='true'>{
        }</fold>

        static <fold text='{...}' expand='true'>{
            staticField = 200;
        }</fold>

        public StaticData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>staticMethod()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic class GetterData <fold text='{...}' expand='true'>{
        private int field;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public int getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>field<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setField(int field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.field = <fold text='<<' expand='false'>field</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public void printField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>int</fold> value = <fold text='field' expand='false'>getField()</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public abstract class AbstractData <fold text='{...}' expand='true'>{
        public abstract void abstractMethod();

        public void concreteMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public interface InterfaceData <fold text='{...}' expand='true'>{
        void interfaceMethod();
    }</fold>

    public class InterfaceImplementation implements InterfaceDat<fold text='a(1-interfaceMethod)' expand='true'>a</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void interfaceMethod() <fold text='{ // overrides from InterfaceData' expand='true'><fold text='{}' expand='true'>{</fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic enum EnumData <fold text='{...}' expand='true'>{
        ENUM_CONSTANT_1 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
            </fold>public void abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public void interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>,
        ENUM_CONSTANT_2 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override<fold text='' expand='true'></fold>
            </fold>public void abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public void interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        private int value;

        private EnumData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.value = 0<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public void setValue(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public int getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>value<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public abstract void abstractMethod();

        public void concreteMethod() <fold text='{}' expand='true'>{
        }</fold>

        public interface InterfaceData <fold text='{...}' expand='true'>{
            void interfaceMethod();
        }</fold>
    }</fold>
    public class SynchronizedData <fold text='{...}' expand='true'>{
        private int counter;

        public synchronized void increment()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>counter++<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public void incrementWithBlock() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>synchronized (this) <fold text='{...}' expand='true'>{
                counter++;
            }<fold text=' ' expand='true'></fold>
        </fold>}</fold>
    }</fold>

    <fold text='@AllArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic class TransientVolatileData implements java.io.Serializabl<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        private transient int transientField;
        private volatile boolean volatileField;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public TransientVolatileData(int transientField, boolean volatileField) <fold text='{...}' expand='true'>{
            this.transientField = <fold text='<<' expand='false'>transientField</fold>;
            this.volatileField = <fold text='<<' expand='false'>volatileField</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public int getTransientField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>transientField<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setTransientField(int transientField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.transientField = <fold text='<<' expand='false'>transientField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public boolean isVolatileField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>volatileField<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public void setVolatileField(boolean volatileField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.volatileField = <fold text='<<' expand='false'>volatileField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public class NativeData <fold text='{...}' expand='true'>{
        public native void nativeMethod();
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic class InterfaceUsage implements Comparabl<fold text='e(0-)' expand='true'>e</fold><InterfaceUsage> <fold text='{...}' expand='true'>{
        private int value;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public InterfaceUsage(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public int compareTo(InterfaceUsage other)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Integer.compare(this.value, other.value)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class AnonymousClassUsage <fold text='{...}' expand='true'>{
        public Runnable getRunnable() <fold text='{...}' expand='true'>{
            return <fold text='run() → { ' expand='false'>new Runnable() {
                <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
                </fold>public void run() {<fold text=' ' expand='true'>
                    </fold></fold><fold text='val' expand='false'>int</fold> x = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}
            }</fold>;
        }</fold>
    }</fold>

    public class LocalClassUsage <fold text='{...}' expand='true'>{
        public void useLocalClass() <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor @Getter c' expand='false'>c</fold>lass LocalClass <fold text='{...}' expand='true'>{
                private int localValue;<fold text='' expand='false'>

                </fold><fold text='' expand='false'>public LocalClass(int localValue)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold>this.localValue = <fold text='<<' expand='false'>localValue</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold><fold text='' expand='false'></fold>

                </fold><fold text='' expand='false'>public int getLocalValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>localValue<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
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
        public void useDiamondOperator() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[]' expand='false'>new java.util.ArrayList<>()</fold>;
            list<fold text=' += ' expand='false'>.add(</fold>"Example"<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    public class TryWithResourcesUsage <fold text='{...}' expand='true'>{
        public void readFile(String filePath) throws java.io.IOException <fold text='{...}' expand='true'>{
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) <fold text='{...}' expand='true'>{
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
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>(a, b) -> a + b<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class StreamAPIUsage <fold text='{...}' expand='true'>{
        public int sumList(java.util.List<Integer> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>list<fold text='.' expand='false'>.stream().</fold>mapToInt(Integer::intValue).sum()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
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
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>optional<fold text=' ?: ' expand='false'>.orElse(</fold>"Default Value"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class MethodReferenceUsage <fold text='{...}' expand='true'>{
        public java.util.function.Function<String, Integer> getStringLengthFunction()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>String::length<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class StaticImportUsage <fold text='{...}' expand='true'>{
        public void useStaticImport()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='val' expand='false'></fold>int</fold> max = <fold text='max(' expand='false'>java.lang.Math.max(</fold>5, 10<fold text=')' expand='false'>)</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class DefaultMethodInInterface implements DefaultMethodInterfac<fold text='e(1-abstractMethod)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>public void abstractMethod() <fold text='{ // overrides from DefaultMethodInterface' expand='true'><fold text='{}' expand='true'>{</fold>
        }</fold>
    }</fold>

    public interface DefaultMethodInterface <fold text='{...}' expand='true'>{
        void abstractMethod();

        default void defaultMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>int</fold> defaultValue = 0;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public class NestedClassUsage <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic class InnerClass <fold text='{...}' expand='true'>{
            private int value;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public InnerClass(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public int getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>value<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Builder(Builder) p' expand='false'>p</fold>ublic class BuilderPatternUsage <fold text='{...}' expand='true'>{
        private final int field1;
        private final String field2;

        private BuilderPatternUsage(Builder builder) <fold text='{...}' expand='true'>{
            this.field1 = builder.<fold text='<<' expand='true'>field1</fold>;
            this.field2 = builder.<fold text='<<' expand='true'>field2</fold>;
        }</fold>

        public class Builder <fold text='{...}' expand='true'>{
            private int field1;
            private String field2;

            public Builder setField1(int field1) <fold text='{...}' expand='true'>{
                this.field1 = <fold text='<<' expand='false'>field1</fold>;
                return this;
            }</fold>

            public Builder setField2(String field2) <fold text='{...}' expand='true'>{
                this.field2 = <fold text='<<' expand='false'>field2</fold>;
                return this;
            }</fold>

            public BuilderPatternUsage build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new BuilderPatternUsage(this)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic class CopyConstructorUsage <fold text='{...}' expand='true'>{
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) <fold text='{...}' expand='true'>{
            this.field1 = other.<fold text='<<' expand='true'>field1</fold>;
            this.field2 = other.<fold text='<<' expand='true'>field2</fold>;
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public CopyConstructorUsage(int field1, String field2) <fold text='{...}' expand='true'>{
            this.field1 = <fold text='<<' expand='false'>field1</fold>;
            this.field2 = <fold text='<<' expand='false'>field2</fold>;
        }</fold></fold>
    }</fold>

    public class FinalizerUsage <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold><fold text='' expand='true'>
        </fold>protected void finalize() throws Throwable <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>try {
                // Finalization logic
            } finally <fold text='{...}' expand='true'>{
                super.finalize();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class VarUsage <fold text='{...}' expand='true'>{
        public void useVar() <fold text='{...}' expand='true'>{
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
                <fold text='val' expand='false'>int</fold> length = str.length();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public class SealedClassUsage <fold text='{...}' expand='true'>{
        public abstract sealed class Shape permits Circle, Rectangle <fold text='{...}' expand='true'>{
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='' expand='false'>final</fold> class Circle extends Shap<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
            private double radius;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public Circle(double radius)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.radius = <fold text='<<' expand='false'>radius</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public double getRadius()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>radius<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='' expand='false'>final</fold> class Rectangle extends Shap<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
            private double length;
            private double width;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public Rectangle(double length, double width) <fold text='{...}' expand='true'>{
                this.length = <fold text='<<' expand='false'>length</fold>;
                this.width = <fold text='<<' expand='false'>width</fold>;
            }</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public double getLength()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>length<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            <fold text='' expand='false'></fold>public double getWidth()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>width<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    class NullUsage <fold text='{...}' expand='true'>{
        public class Data <fold text='{...}' expand='true'>{

            public void methodWithNullParam(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>input = null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>methodReturningNull<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='val' expand='false'>String</fold> field = null;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullCheck(String input) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>if <fold text='' expand='false'>(</fold>input == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    return;
                }</fold><fold text=' ' expand='true'>
            </fold>}</fold>

            public String methodWithNullTernary(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>input != null ? </fold>input<fold text=' ?: ' expand='false'> : </fold>null<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
            </fold>}</fold>

            public void methodWithNullInArray() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String[]</fold> array = new String[10];
                array[0] = null;
            }</fold>

            public void methodWithNullInCollection() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[]' expand='false'>new java.util.ArrayList<>()</fold>;
                list<fold text=' += ' expand='false'>.add(</fold>null<fold text='' expand='false'>)</fold>;
            }</fold>

            public void methodWithNullInMap() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.Map<String, String></fold> map = new java.util.HashMap<>();
                map<fold text='[' expand='false'>.put(</fold>"key"<fold text='] = ' expand='false'>, </fold>null<fold text='' expand='false'>)</fold>;
            }</fold>

            public void methodWithNullInStream() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[' expand='false'>java.util.Arrays.asList(</fold>null, <fold text='"value"' expand='false'>"value"<fold text=']' expand='false'></fold>)</fold>;
                <fold text='val' expand='false'>long</fold> count = list<fold text='.' expand='false'>.stream().</fold>filter(java.util.Objects::isNull).count();
            }</fold>

            public void methodWithNullInOptional() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'>java.util.Optional<String></fold> optional = java.util.<fold text='' expand='false'>Optional.ofNullable(</fold>null<fold text='' expand='false'>)</fold>;<fold text=' ' expand='true'>
            </fold>}</fold>

            public void methodWithNullInSupplier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='val' expand='false'>java.util.function.Supplier<String></fold> supplier = () -> null;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public void methodWithNullInLambda() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'>java.util.function.Function<String, String></fold> function = input -> null;<fold text=' ' expand='true'>
            </fold>}</fold>

            public void methodWithNullInMethodReference() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>java.util.function.Function<Object, String></fold> function = Object::toString;
                <fold text='val' expand='false'>String</fold> result = function.apply(null);
            }</fold>

            public void methodWithNullException() <fold text='{...}' expand='true'>{
                try <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>String</fold> value = null;
                    value.length();
                }</fold> catch <fold text='' expand='false'>(</fold>NullPointerException e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                }</fold>
            }</fold>

            public void methodWithNullInstanceof() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Object</fold> obj = null;
                <fold text='val' expand='false'>boolean</fold> isString = obj instanceof String;
            }</fold>

            public void methodWithNullDefaultValue() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = null;
                <fold text='val' expand='false'>String</fold> result = value == null ? "default" : value;
            }</fold>

            public void methodWithNullAssert() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = null;
                assert value != null : "Value cannot be null";
            }</fold>

            public void methodWithNullSynchronize() <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> value = null;
                synchronized (this) <fold text='{...}' expand='true'>{
                    if <fold text='' expand='false'>(</fold>value == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        return;
                    }</fold>
                }</fold>
            }</fold>

        }</fold>
    }</fold>

    class SingletonUsage <fold text='{...}' expand='true'>{
        void main() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>var</fold> s = Singleton.INSTANCE;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.INSTANCE.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));

            <fold text='val' expand='false'>var</fold> s2 = Singleton.INSTANCE.OTHER_NAME;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.INSTANCE.OTHER_NAME.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.INSTANCE.OTHER_NAME.main(Singleton.INSTANCE.OTHER_NAME.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));
        }</fold>

        static class Singleton <fold text='{...}' expand='true'>{
            static Singleton INSTANCE =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
            Singleton OTHER_NAME =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
            <fold text='@Getter b' expand='false'>b</fold>oolean ok;

            Singleton main(Singleton s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>this<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public static Singleton getInstance()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>INSTANCE<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>
}
