package data;

import java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public [0:"final"] class FinalData {
        private final int finalField = 10;

        public [5:"final"] void finalMethod() {[1:"\n            "][3:"final"] int localFinalVariable = 5;[1:"\n        "]}

        public void methodWithFinalParam([11:"final"] int param) {
        }

        public void anotherMethod() {
            [13:"final"] int anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {[15:"\n            "][17:"final"] int constructorFinalVariable = 30;[15:"\n        "]}
    }

    public static class StaticData {
        private static int staticField = 100;

        public static void staticMethod() {
        }

        static {
            staticField = 200;
        }

        public StaticData() {[23:"\n            "]staticMethod()[23:";"][23:"\n        "]}
    }

    [24:"p"]ublic class GetterData {
        private int field;[24:"\n\n        "][24:"public int getField() {\n            return field;\n        }"][24:"\n\n        "][24:"public void setField(int field) {\n            this.field = field;\n        }"]

        public void printField() {[31:"\n            "][32:"int"] value = [33:"getField()"];[31:"\n        "]}
    }

    public abstract class AbstractData {
        public abstract void abstractMethod();

        public void concreteMethod() {
        }
    }

    public interface InterfaceData {
        void interfaceMethod();
    }

    public class InterfaceImplementation implements InterfaceDat[39:"a"] {
        [41:"@Override"][41:"\n        "]public void interfaceMethod() [40:"{"]
        }
    }

    [42:"p"]ublic enum EnumData {
        ENUM_CONSTANT_1 {
            [43:"@Override"][43:"\n            "]public void abstractMethod() {
            }

            public void interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            [44:"@Override"][44:"\n            "]public void abstractMethod() {
            }

            public void interfaceMethod() {
            }
        };

        private int value;

        private EnumData() {[45:"\n            "]this.value = 0[45:";"][45:"\n        "]}[42:"\n\n        "][42:"public void setValue(int value) {\n            this.value = value;\n        }"][42:"\n\n        "][42:"public int getValue() {\n            return value;\n        }"]

        public abstract void abstractMethod();

        public void concreteMethod() {
        }

        public interface InterfaceData {
            void interfaceMethod();
        }
    }
    public class SynchronizedData {
        private int counter;

        public synchronized void increment() {[52:"\n            "]counter++[52:";"][52:"\n        "]}

        public void incrementWithBlock() {[53:"\n            "]synchronized (this) {
                counter++;
            }[53:"\n        "]}
    }

    [55:"p"]ublic class TransientVolatileData implements java.io.Serializabl[54:"e"] {
        private transient int transientField;
        private volatile boolean volatileField;[55:"\n\n        "][55:"public TransientVolatileData(int transientField, boolean volatileField) {\n            this.transientField = transientField;\n            this.volatileField = volatileField;\n        }"][55:"\n\n        "][55:"public int getTransientField() {\n            return transientField;\n        }"][55:"\n\n        "][55:"public void setTransientField(int transientField) {\n            this.transientField = transientField;\n        }"][55:"\n\n        "][55:"public boolean isVolatileField() {\n            return volatileField;\n        }"][55:"\n\n        "][55:"public void setVolatileField(boolean volatileField) {\n            this.volatileField = volatileField;\n        }"]
    }

    public class NativeData {
        public native void nativeMethod();
    }

    [73:"p"]ublic class InterfaceUsage implements Comparabl[72:"e"]<InterfaceUsage> {
        private int value;[73:"\n\n        "][73:"public InterfaceUsage(int value) {\n            this.value = value;\n        }"]

        [80:"@Override"][80:"\n        "]public int compareTo(InterfaceUsage other) {[79:"\n            "][79:"return"][79:" "]Integer.compare(this.value, other.value)[79:";"][79:"\n        "]}
    }

    public class AnonymousClassUsage {
        public Runnable getRunnable() {
            return new Runnable() {
                [84:"@Override"][84:"\n                "]public void run() {[81:"\n                    "][82:"int"] x = 5;[81:"\n                "]}
            };
        }
    }

    public class LocalClassUsage {
        public void useLocalClass() {
            [90:"c"]lass LocalClass {
                private int localValue;[90:"\n\n                "][90:"public LocalClass(int localValue) {\n                    this.localValue = localValue;\n                }"][90:"\n\n                "][90:"public int getLocalValue() {\n                    return localValue;\n                }"]
            }

            [91:"LocalClass"] localInstance = new LocalClass(10);
            [92:"int"] value = localInstance.[93:"getLocalValue()"];
        }
    }

    public class VarArgsUsage {
        public int sum(int... numbers) {
            [104:"int"] sum = 0;
            for [105:"("][108:"int"] number : numbers[105:")"] {
                sum += number;
            }
            return sum;
        }
    }

    public class DiamondOperatorUsage {
        public void useDiamondOperator() {
            [109:"java.util.List<String>"] list = [110:"new java.util.ArrayList<>()"];
            list[111:".add("]"Example"[111:")"];
        }
    }

    public class TryWithResourcesUsage {
        public void readFile(String filePath) throws java.io.IOException {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                [115:"String"] line = reader.readLine();
            }
        }
    }

    public class EnhancedForLoopUsage {
        public void iterateList(java.util.List<String> list) {[117:"\n            "]for [118:"("][119:"String"] item : list[118:")"] {
                [120:"int"] length = item.length();
            }[117:"\n        "]}
    }

    public class LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {[125:"\n            "][125:"return"][125:" "](a, b) -> a + b[125:";"][125:"\n        "]}
    }

    public class StreamAPIUsage {
        public int sumList(java.util.List<Integer> list) {[126:"\n            "][126:"return"][126:" "]list[127:".stream()."]mapToInt(Integer::intValue).sum()[126:";"][126:"\n        "]}
    }

    public class EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            switch [129:"("]day[129:")"] {
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
        public void printList(java.util.List<String> list) {[131:"\n            "]list.forEach(item -> {
                [132:"int"] length = item.length();
            })[131:";"][131:"\n        "]}
    }

    public class OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {[138:"\n            "][138:"return"][138:" "]optional[139:".orElse("]"Default Value"[139:")"][138:";"][138:"\n        "]}
    }

    public class MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {[141:"\n            "][141:"return"][141:" "]String::length[141:";"][141:"\n        "]}
    }

    public class StaticImportUsage {
        public void useStaticImport() {[142:"\n            "][143:"int"] max = java.lang.Math.max(5, 10);[142:"\n        "]}
    }

    public class DefaultMethodInInterface implements DefaultMethodInterfac[147:"e"] {
        [149:"@Override"][149:"\n        "]public void abstractMethod() [148:"{"]
        }
    }

    public interface DefaultMethodInterface {
        void abstractMethod();

        default void defaultMethod() {[150:"\n            "][151:"int"] defaultValue = 0;[150:"\n        "]}
    }

    public class NestedClassUsage {
        [155:"p"]ublic class InnerClass {
            private int value;[155:"\n\n            "][155:"public InnerClass(int value) {\n                this.value = value;\n            }"][155:"\n\n            "][155:"public int getValue() {\n                return value;\n            }"]
        }
    }

    [162:"p"]ublic class BuilderPatternUsage {
        private final int field1;
        private final String field2;

        private BuilderPatternUsage(Builder builder) {
            this.field1 = builder.[163:"field1"];
            this.field2 = builder.[164:"field2"];
        }

        public class Builder {
            private int field1;
            private String field2;

            public Builder setField1(int field1) {
                this.field1 = [167:"field1"];
                return this;
            }

            public Builder setField2(String field2) {
                this.field2 = [169:"field2"];
                return this;
            }

            public BuilderPatternUsage build() {[171:"\n                "][171:"return"][171:" "]new BuilderPatternUsage(this)[171:";"][171:"\n            "]}
        }
    }

    [172:"p"]ublic class CopyConstructorUsage {
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            this.field1 = other.[173:"field1"];
            this.field2 = other.[174:"field2"];
        }[172:"\n\n        "][172:"public CopyConstructorUsage(int field1, String field2) {\n            this.field1 = field1;\n            this.field2 = field2;\n        }"]
    }

    public class FinalizerUsage {
        [182:"@Override"][182:"\n        "]protected void finalize() throws Throwable {[181:"\n            "]try {
                // Finalization logic
            } finally {
                super.finalize();
            }[181:"\n        "]}
    }

    public class VarUsage {
        public void useVar() {
            [183:"var"] number = 10;
            [184:"var"] text = "Hello";
        }
    }

    public class TypeInferenceUsage {
        public void useTypeInference() {[187:"\n            "][188:"java.util.Map<String, Integer>"] map = new java.util.HashMap<>();[187:"\n        "]}
    }

    public class ResourceBundleUsage {
        public String getMessage(String key) {
            [192:"java.util.ResourceBundle"] bundle = java.util.ResourceBundle.getBundle("messages");
            return bundle.getString(key);
        }
    }

    public class PatternMatchingInstanceof {
        public void checkObject(Object obj) {[194:"\n            "]if [195:"("]obj instanceof String str[195:")"] {
                [198:"int"] length = str.length();
            }[194:"\n        "]}
    }

    public class SealedClassUsage {
        public abstract sealed class Shape permits Circle, Rectangle {
        }

        [200:"p"]ublic [201:"final"] class Circle extends Shap[199:"e"] {
            private double radius;[200:"\n\n            "][200:"public Circle(double radius) {\n                this.radius = radius;\n            }"][200:"\n\n            "][200:"public double getRadius() {\n                return radius;\n            }"]
        }

        [209:"p"]ublic [210:"final"] class Rectangle extends Shap[208:"e"] {
            private double length;
            private double width;[209:"\n\n            "][209:"public Rectangle(double length, double width) {\n                this.length = length;\n                this.width = width;\n            }"][209:"\n\n            "][209:"public double getLength() {\n                return length;\n            }"][209:"\n\n            "][209:"public double getWidth() {\n                return width;\n            }"]
        }
    }

    class NullUsage {
        public class Data {

            public void methodWithNullParam(String input) {[217:"\n                "]input = null[217:";"][217:"\n            "]}

            [218:"public"][218:" "][218:"String"][218:" "]methodReturningNull[218:"()"] {[218:"\n                "][218:"return"][218:" "]null[218:";"][218:"\n            "]}

            public void methodWithNullField() {[219:"\n                "][220:"String"] field = null;[219:"\n            "]}

            public void methodWithNullCheck(String input) {[224:"\n                "]if [225:"("]input == null[225:")"] {
                    return;
                }[224:"\n            "]}

            public String methodWithNullTernary(String input) {[228:"\n                "][228:"return"][228:" "][229:"input != null ? "]input[229:" : "]null[228:";"][228:"\n            "]}

            public void methodWithNullInArray() {
                [231:"String[]"] array = new String[10];
                array[0] = null;
            }

            public void methodWithNullInCollection() {
                [233:"java.util.List<String>"] list = [234:"new java.util.ArrayList<>()"];
                list[235:".add("]null[235:")"];
            }

            public void methodWithNullInMap() {
                [239:"java.util.Map<String, String>"] map = new java.util.HashMap<>();
                map[240:".put("]"key"[240:", "]null[240:")"];
            }

            public void methodWithNullInStream() {
                [243:"java.util.List<String>"] list = [244:"java.util.Arrays.asList("]null, [245:"\"value\""][244:")"];
                [246:"long"] count = list[247:".stream()."]filter(java.util.Objects::isNull).count();
            }

            public void methodWithNullInOptional() {[253:"\n                "][254:"java.util.Optional<String>"] optional = java.util.[255:"Optional.ofNullable("]null[255:")"];[253:"\n            "]}

            public void methodWithNullInSupplier() {[261:"\n                "][262:"java.util.function.Supplier<String>"] supplier = () -> null;[261:"\n            "]}

            public void methodWithNullInLambda() {[266:"\n                "][267:"java.util.function.Function<String, String>"] function = input -> null;[266:"\n            "]}

            public void methodWithNullInMethodReference() {
                [271:"java.util.function.Function<Object, String>"] function = Object::toString;
                [272:"String"] result = function.apply(null);
            }

            public void methodWithNullException() {
                try {
                    [275:"String"] value = null;
                    value.length();
                } catch [276:"("]NullPointerException e[276:")"] {
                    e.printStackTrace();
                }
            }

            public void methodWithNullInstanceof() {
                [279:"Object"] obj = null;
                [280:"boolean"] isString = obj instanceof String;
            }

            public void methodWithNullDefaultValue() {
                [283:"String"] value = null;
                [284:"String"] result = value == null ? "default" : value;
            }

            public void methodWithNullAssert() {
                [287:"String"] value = null;
                assert value != null : "Value cannot be null";
            }

            public void methodWithNullSynchronize() {
                [289:"String"] value = null;
                synchronized (this) {
                    if [290:"("]value == null[290:")"] {
                        return;
                    }
                }
            }

        }
    }

    class SingletonUsage {
        void main() {
            [293:"var"] s = Singleton.INSTANCE;
            [294:"System.out."]println(Singleton.INSTANCE.[295:"isOk()"]);
            [297:"System.out."]println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.[298:"getInstance()"])));

            [300:"var"] s2 = Singleton.INSTANCE.OTHER_NAME;
            [301:"System.out."]println(Singleton.INSTANCE.OTHER_NAME.[302:"isOk()"]);
            [304:"System.out."]println(Singleton.INSTANCE.OTHER_NAME.main(Singleton.INSTANCE.OTHER_NAME.main(Singleton.[305:"getInstance()"])));
        }

        static class Singleton {
            static Singleton INSTANCE =[321:" "]new[321:" "][321:"Singleton"][321:"()"];
            Singleton OTHER_NAME =[322:" "]new[322:" "][322:"Singleton"][322:"()"];
            [323:"b"]oolean ok;

            Singleton main(Singleton s) {[324:"\n                "][324:"return"][324:" "]this[324:";"][324:"\n            "]}[323:"\n\n            "][323:"public boolean isOk() {\n                return ok;\n            }"]

            public static Singleton getInstance() {[326:"\n                "][326:"return"][326:" "]INSTANCE[326:";"][326:"\n            "]}
        }
    }
}
