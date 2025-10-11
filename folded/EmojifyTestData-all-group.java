package data;

import java.time.DayOfWeek;

@SuppressWarnings("ALL")
public class EmojifyTestData {

    public [0:"final"] class FinalData {
        private final int finalField = 10;

        public [5:"final"][8:"final"] void finalMethod() {[1:"\n            "][3:"final"][7:"final"][10:"final"]inal int"][4:"final int"][6:"final int"][9:"final int"] localFinalVariable = 5;[1:"\n        "]}

        public void methodWithFinalParam([11:"final"][12:"final"] int param) {
        }

        public void anotherMethod() {
            [13:"final"][14:"final"] int anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {[15:"\n            "][17:"final"][20:"final"][22:"final"]final int"][18:"final int"][19:"final int"][21:"final int"] constructorFinalVariable = 30;[15:"\n        "]}
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
        private int field;[24:"\n\n        "][24:"public int getField() {\n            return field;\n        }"]5:" "]field[25:";"][25:"\n        "]}[24:"\n\n        "][24:"public void setField(int field) {\n            this.field = field;\n        }"]ld"][28:"field"][29:"field"][30:"field"][26:";"][26:"\n        "]}

        public void printField() {[31:"\n            "][32:"int"][34:"int"][35:"int"][37:"int"] value = [33:"getField()"][36:"getField()"][38:"getField()"];[31:"\n        "]}
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

        private EnumData() {[45:"\n            "]this.value = 0[45:";"][45:"\n        "]}[42:"\n\n        "][42:"public void setValue(int value) {\n            this.value = value;\n        }"]ue"][48:"value"][49:"value"][50:"value"][46:";"][46:"\n        "]}[42:"\n\n        "][42:"public int getValue() {\n            return value;\n        }"]1:" "]value[51:";"][51:"\n        "]}

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
        private volatile boolean volatileField;[55:"\n\n        "][55:"public TransientVolatileData(int transientField, boolean volatileField) {\n            this.transientField = transientField;\n            this.volatileField = volatileField;\n        }"]d = [57:"volatileField"][59:"volatileField"];
        }[55:"\n\n        "][55:"public int getTransientField() {\n            return transientField;\n        }"]nsientField[60:";"][60:"\n        "]}[55:"\n\n        "][55:"public void setTransientField(int transientField) {\n            this.transientField = transientField;\n        }"]ld"][63:"transientField"][64:"transientField"][65:"transientField"][61:";"][61:"\n        "]}[55:"\n\n        "][55:"public boolean isVolatileField() {\n            return volatileField;\n        }"]latileField[66:";"][66:"\n        "]}[55:"\n\n        "][55:"public void setVolatileField(boolean volatileField) {\n            this.volatileField = volatileField;\n        }"]ld"][69:"volatileField"][70:"volatileField"][71:"volatileField"][67:";"][67:"\n        "]}
    }

    public class NativeData {
        public native void nativeMethod();
    }

    [73:"p"]ublic class InterfaceUsage implements Comparabl[72:"e"]<InterfaceUsage> {
        private int value;[73:"\n\n        "][73:"public InterfaceUsage(int value) {\n            this.value = value;\n        }"]ue"][76:"value"][77:"value"][78:"value"][74:";"][74:"\n        "]}

        [80:"@Override"][80:"\n        "]public int compareTo(InterfaceUsage other) {[79:"\n            "][79:"return"][79:" "]Integer.compare(this.value, other.value)[79:";"][79:"\n        "]}
    }

    public class AnonymousClassUsage {
        public Runnable getRunnable() {
            return new Runnable() {
                [84:"@Override"][87:"@Override"][84:"\n                "][87:"\n                "]public void run() {[81:"\n                    "][85:"\n                    "][82:"int"][83:"int"][85:"int"][86:"int"][87:"int"][88:"int"][89:"int"] x = 5;[81:"\n                "][85:"\n                "]}
            };
        }
    }

    public class LocalClassUsage {
        public void useLocalClass() {
            [90:"c"][94:"c"]lass LocalClass {
                private int localValue;[90:"\n\n                "][94:"\n\n                "][90:"public LocalClass(int localValue) {\n                    this.localValue = localValue;\n                }"][94:"public LocalClass(int localValue) {\n                    this.localValue = localValue;\n                }"]"localValue"][98:"localValue"][99:"localValue"][95:";"][95:"\n                "]}[90:"\n\n                "][94:"\n\n                "][90:"public int getLocalValue() {\n                    return localValue;\n                }"][94:"public int getLocalValue() {\n                    return localValue;\n                }"]lValue[100:";"][100:"\n                "]}
            }

            [91:"LocalClass"][101:"LocalClass"] localInstance = new LocalClass(10);
            [92:"int"][102:"int"] value = localInstance.[93:"getLocalValue()"][103:"getLocalValue()"];
        }
    }

    public class VarArgsUsage {
        public int sum(int... numbers) {
            [104:"int"][106:"int"] sum = 0;
            for [105:"("][107:"("][108:"int"] number : numbers[105:")"][107:")"] {
                sum += number;
            }
            return sum;
        }
    }

    public class DiamondOperatorUsage {
        public void useDiamondOperator() {
            [109:"java.util.List<String>"][112:"java.util.List<String>"] list = [110:"new java.util.ArrayList<>()"][113:"new java.util.ArrayList<>()"];
            list[111:".add("][114:".add("]"Example"[111:")"][114:")"];
        }
    }

    public class TryWithResourcesUsage {
        public void readFile(String filePath) throws java.io.IOException {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                [115:"String"][116:"String"] line = reader.readLine();
            }
        }
    }

    public class EnhancedForLoopUsage {
        public void iterateList(java.util.List<String> list) {[117:"\n            "]for [118:"("][121:"("][122:"("][119:"String"][123:"String"] item : list[118:")"][121:")"][122:")"] {
                [120:"int"][124:"int"] length = item.length();
            }[117:"\n        "]}
    }

    public class LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {[125:"\n            "][125:"return"][125:" "](a, b) -> a + b[125:";"][125:"\n        "]}
    }

    public class StreamAPIUsage {
        public int sumList(java.util.List<Integer> list) {[126:"\n            "][126:"return"][126:" "]list[127:".stream()."][128:".stream()."]mapToInt(Integer::intValue).sum()[126:";"][126:"\n        "]}
    }

    public class EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            switch [129:"("][130:"("]day[129:")"][130:")"] {
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
                [132:"int"][133:"int"][134:"int"][135:"int"][136:"int"][137:"int"] length = item.length();
            })[131:";"][131:"\n        "]}
    }

    public class OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {[138:"\n            "][138:"return"][138:" "]optional[139:".orElse("][140:".orElse("]"Default Value"[139:")"][140:")"][138:";"][138:"\n        "]}
    }

    public class MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {[141:"\n            "][141:"return"][141:" "]String::length[141:";"][141:"\n        "]}
    }

    public class StaticImportUsage {
        public void useStaticImport() {[142:"\n            "][143:"int"][144:"int"][145:"int"][146:"int"] max = java.lang.Math.max(5, 10);[142:"\n        "]}
    }

    public class DefaultMethodInInterface implements DefaultMethodInterfac[147:"e"] {
        [149:"@Override"][149:"\n        "]public void abstractMethod() [148:"{"]
        }
    }

    public interface DefaultMethodInterface {
        void abstractMethod();

        default void defaultMethod() {[150:"\n            "][151:"int"][152:"int"][153:"int"][154:"int"] defaultValue = 0;[150:"\n        "]}
    }

    public class NestedClassUsage {
        [155:"p"]ublic class InnerClass {
            private int value;[155:"\n\n            "][155:"public InnerClass(int value) {\n                this.value = value;\n            }"]"][158:"value"][159:"value"][160:"value"][156:";"][156:"\n            "]}[155:"\n\n            "][155:"public int getValue() {\n                return value;\n            }"]:" "]value[161:";"][161:"\n            "]}
        }
    }

    [162:"p"]ublic class BuilderPatternUsage {
        private final int field1;
        private final String field2;

        private BuilderPatternUsage(Builder builder) {
            this.field1 = builder.[163:"field1"][165:"field1"];
            this.field2 = builder.[164:"field2"][166:"field2"];
        }

        public class Builder {
            private int field1;
            private String field2;

            public Builder setField1(int field1) {
                this.field1 = [167:"field1"][168:"field1"];
                return this;
            }

            public Builder setField2(String field2) {
                this.field2 = [169:"field2"][170:"field2"];
                return this;
            }

            public BuilderPatternUsage build() {[171:"\n                "][171:"return"][171:" "]new BuilderPatternUsage(this)[171:";"][171:"\n            "]}
        }
    }

    [172:"p"]ublic class CopyConstructorUsage {
        private int field1;
        private String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            this.field1 = other.[173:"field1"][175:"field1"];
            this.field2 = other.[174:"field2"][176:"field2"];
        }[172:"\n\n        "][172:"public CopyConstructorUsage(int field1, String field2) {\n            this.field1 = field1;\n            this.field2 = field2;\n        }"]d2 = [178:"field2"][180:"field2"];
        }
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
            [183:"var"][185:"var"] number = 10;
            [184:"var"][186:"var"] text = "Hello";
        }
    }

    public class TypeInferenceUsage {
        public void useTypeInference() {[187:"\n            "][188:"java.util.Map<String, Integer>"][189:"java.util.Map<String, Integer>"][190:"java.util.Map<String, Integer>"][191:"java.util.Map<String, Integer>"] map = new java.util.HashMap<>();[187:"\n        "]}
    }

    public class ResourceBundleUsage {
        public String getMessage(String key) {
            [192:"java.util.ResourceBundle"][193:"java.util.ResourceBundle"] bundle = java.util.ResourceBundle.getBundle("messages");
            return bundle.getString(key);
        }
    }

    public class PatternMatchingInstanceof {
        public void checkObject(Object obj) {[194:"\n            "]if [195:"("][196:"("][197:"("]obj instanceof String str[195:")"][196:")"][197:")"] {
                [198:"int"] length = str.length();
            }[194:"\n        "]}
    }

    public class SealedClassUsage {
        public abstract sealed class Shape permits Circle, Rectangle {
        }

        [200:"p"]ublic [201:"final"] class Circle extends Shap[199:"e"] {
            private double radius;[200:"\n\n            "][200:"public Circle(double radius) {\n                this.radius = radius;\n            }"]"][204:"radius"][205:"radius"][206:"radius"][202:";"][202:"\n            "]}[200:"\n\n            "][200:"public double getRadius() {\n                return radius;\n            }"]" "]radius[207:";"][207:"\n            "]}
        }

        [209:"p"]ublic [210:"final"] class Rectangle extends Shap[208:"e"] {
            private double length;
            private double width;[209:"\n\n            "][209:"public Rectangle(double length, double width) {\n                this.length = length;\n                this.width = width;\n            }"]= [212:"width"][214:"width"];
            }[209:"\n\n            "][209:"public double getLength() {\n                return length;\n            }"]" "]length[215:";"][215:"\n            "]}[209:"\n\n            "][209:"public double getWidth() {\n                return width;\n            }"]:" "]width[216:";"][216:"\n            "]}
        }
    }

    class NullUsage {
        public class Data {

            public void methodWithNullParam(String input) {[217:"\n                "]input = null[217:";"][217:"\n            "]}

            [218:"public"][218:" "][218:"String"][218:" "]methodReturningNull[218:"()"] {[218:"\n                "][218:"return"][218:" "]null[218:";"][218:"\n            "]}

            public void methodWithNullField() {[219:"\n                "][220:"String"][221:"String"][222:"String"][223:"String"] field = null;[219:"\n            "]}

            public void methodWithNullCheck(String input) {[224:"\n                "]if [225:"("][226:"("][227:"("]input == null[225:")"][226:")"][227:")"] {
                    return;
                }[224:"\n            "]}

            public String methodWithNullTernary(String input) {[228:"\n                "][228:"return"][228:" "][229:"input != null ? "][230:"input != null ? "]input[229:" : "][230:" : "]null[228:";"][228:"\n            "]}

            public void methodWithNullInArray() {
                [231:"String[]"][232:"String[]"] array = new String[10];
                array[0] = null;
            }

            public void methodWithNullInCollection() {
                [233:"java.util.List<String>"][236:"java.util.List<String>"] list = [234:"new java.util.ArrayList<>()"][237:"new java.util.ArrayList<>()"];
                list[235:".add("][238:".add("]null[235:")"][238:")"];
            }

            public void methodWithNullInMap() {
                [239:"java.util.Map<String, String>"][241:"java.util.Map<String, String>"] map = new java.util.HashMap<>();
                map[240:".put("][242:".put("]"key"[240:", "][242:", "]null[240:")"][242:")"];
            }

            public void methodWithNullInStream() {
                [243:"java.util.List<String>"][248:"java.util.List<String>"] list = [244:"java.util.Arrays.asList("][249:"java.util.Arrays.asList("]null, [245:"\"value\""][250:"\"value\""][244:")"][249:")"];
                [246:"long"][251:"long"] count = list[247:".stream()."][252:".stream()."]filter(java.util.Objects::isNull).count();
            }

            public void methodWithNullInOptional() {[253:"\n                "][254:"java.util.Optional<String>"][256:"java.util.Optional<String>"][257:"java.util.Optional<String>"][259:"java.util.Optional<String>"] optional = java.util.[255:"Optional.ofNullable("][258:"Optional.ofNullable("][260:"Optional.ofNullable("]null[255:")"][258:")"][260:")"];[253:"\n            "]}

            public void methodWithNullInSupplier() {[261:"\n                "][262:"java.util.function.Supplier<String>"][263:"java.util.function.Supplier<String>"][264:"java.util.function.Supplier<String>"][265:"java.util.function.Supplier<String>"] supplier = () -> null;[261:"\n            "]}

            public void methodWithNullInLambda() {[266:"\n                "][267:"java.util.function.Function<String, String>"][268:"java.util.function.Function<String, String>"][269:"java.util.function.Function<String, String>"][270:"java.util.function.Function<String, String>"] function = input -> null;[266:"\n            "]}

            public void methodWithNullInMethodReference() {
                [271:"java.util.function.Function<Object, String>"][273:"java.util.function.Function<Object, String>"] function = Object::toString;
                [272:"String"][274:"String"] result = function.apply(null);
            }

            public void methodWithNullException() {
                try {
                    [275:"String"][277:"String"] value = null;
                    value.length();
                } catch [276:"("][278:"("]NullPointerException e[276:")"][278:")"] {
                    e.printStackTrace();
                }
            }

            public void methodWithNullInstanceof() {
                [279:"Object"][281:"Object"] obj = null;
                [280:"boolean"][282:"boolean"] isString = obj instanceof String;
            }

            public void methodWithNullDefaultValue() {
                [283:"String"][285:"String"] value = null;
                [284:"String"][286:"String"] result = value == null ? "default" : value;
            }

            public void methodWithNullAssert() {
                [287:"String"][288:"String"] value = null;
                assert value != null : "Value cannot be null";
            }

            public void methodWithNullSynchronize() {
                [289:"String"][291:"String"] value = null;
                synchronized (this) {
                    if [290:"("][292:"("]value == null[290:")"][292:")"] {
                        return;
                    }
                }
            }

        }
    }

    class SingletonUsage {
        void main() {
            [293:"var"][307:"var"] s = Singleton.INSTANCE;
            [294:"System.out."][308:"System.out."]println(Singleton.INSTANCE.[295:"isOk()"][296:"isOk()"][309:"isOk()"][310:"isOk()"]);
            [297:"System.out."][311:"System.out."]println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.[298:"getInstance()"][299:"getInstance()"][312:"getInstance()"][313:"getInstance()"])));

            [300:"var"][314:"var"] s2 = Singleton.INSTANCE.OTHER_NAME;
            [301:"System.out."][315:"System.out."]println(Singleton.INSTANCE.OTHER_NAME.[302:"isOk()"][303:"isOk()"][316:"isOk()"][317:"isOk()"]);
            [304:"System.out."][318:"System.out."]println(Singleton.INSTANCE.OTHER_NAME.main(Singleton.INSTANCE.OTHER_NAME.main(Singleton.[305:"getInstance()"][306:"getInstance()"][319:"getInstance()"][320:"getInstance()"])));
        }

        static class Singleton {
            static Singleton INSTANCE =[321:" "]new[321:" "][321:"Singleton"][321:"()"];
            Singleton OTHER_NAME =[322:" "]new[322:" "][322:"Singleton"][322:"()"];
            [323:"b"]oolean ok;

            Singleton main(Singleton s) {[324:"\n                "][324:"return"][324:" "]this[324:";"][324:"\n            "]}[323:"\n\n            "][323:"public boolean isOk() {\n                return ok;\n            }"]325:" "]ok[325:";"][325:"\n            "]}

            public static Singleton getInstance() {[326:"\n                "][326:"return"][326:" "]INSTANCE[326:";"][326:"\n            "]}
        }
    }
}
