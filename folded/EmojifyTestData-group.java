[0:"package"] data;

[1:"import"] java.time.DayOfWeek;

@SuppressWarnings("ALL")
public [2:"class"] EmojifyTestData {

    public [3:"final"] [4:"class"] FinalData {
        [5:"private"] [6:"final"] [7:"int"] finalField = 10;

        public [8:"final"] [9:"void"] finalMethod() {
            [10:"final"] [11:"int"] localFinalVariable = 5;
        }

        public [16:"void"] methodWithFinalParam([17:"final"] [18:"int"] param) {
        }

        public [22:"void"] anotherMethod() {
            [23:"final"] [24:"int"] anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {
            [28:"final"] [29:"int"] constructorFinalVariable = 30;
        }
    }

    public [32:"static"] [33:"class"] StaticData {
        [34:"private"] [35:"static"] [36:"int"] staticField = 100;

        public [37:"static"] [38:"void"] staticMethod() {
        }

        [41:"static"] {
            staticField = 200;
        }

        public StaticData() {
            staticMethod();
        }
    }

    public [42:"class"] GetterData {
        [43:"private"] [44:"int"] field;

        public [45:"int"] getField() {
            [46:"return"] field;
        }

        public [49:"void"] setField([51:"int"] field) {
            [50:"this"].field = field;
        }

        public [55:"void"] printField() {
            [56:"int"] value = getField();
        }
    }

    public [59:"abstract"] [60:"class"] AbstractData {
        public [61:"abstract"] [62:"void"] abstractMethod();

        public [65:"void"] concreteMethod() {
        }
    }

    public [67:"interface"] InterfaceData {
        [68:"void"] interfaceMethod();
    }

    public [70:"class"] InterfaceImplementation implements InterfaceData {
        @Override
        public [71:"void"] interfaceMethod() {
        }
    }

    public [73:"enum"] EnumData {
        ENUM_CONSTANT_1 {
            @Override
            public [74:"void"] abstractMethod() {
            }

            public [76:"void"] interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            @Override
            public [78:"void"] abstractMethod() {
            }

            public [80:"void"] interfaceMethod() {
            }
        };

        [82:"private"] [83:"int"] value;

        [84:"private"] EnumData() {
            [85:"this"].value = 0;
        }

        public [88:"void"] setValue([90:"int"] value) {
            [89:"this"].value = value;
        }

        public [94:"int"] getValue() {
            [95:"return"] value;
        }

        public [98:"abstract"] [99:"void"] abstractMethod();

        public [102:"void"] concreteMethod() {
        }

        public [104:"interface"] InterfaceData {
            [105:"void"] interfaceMethod();
        }
    }
    public [107:"class"] SynchronizedData {
        [108:"private"] [109:"int"] counter;

        public synchronized [110:"void"] increment() {
            counter++;
        }

        public [112:"void"] incrementWithBlock() {
            synchronized ([113:"this"]) {
                counter++;
            }
        }
    }

    public [116:"class"] TransientVolatileData implements java.io.Serializable {
        [117:"private"] [118:"transient"] [119:"int"] transientField;
        [120:"private"] [121:"volatile"] [122:"boolean"] volatileField;

        public TransientVolatileData([125:"int"] transientField, [126:"boolean"] volatileField) {
            [123:"this"].transientField = transientField;
            [124:"this"].volatileField = volatileField;
        }

        public [131:"int"] getTransientField() {
            [132:"return"] transientField;
        }

        public [135:"void"] setTransientField([137:"int"] transientField) {
            [136:"this"].transientField = transientField;
        }

        public [141:"boolean"] isVolatileField() {
            [142:"return"] volatileField;
        }

        public [145:"void"] setVolatileField([147:"boolean"] volatileField) {
            [146:"this"].volatileField = volatileField;
        }
    }

    public [151:"class"] NativeData {
        public [152:"native"] [153:"void"] nativeMethod();
    }

    public [156:"class"] InterfaceUsage implements Comparable<InterfaceUsage> {
        [157:"private"] [158:"int"] value;

        public InterfaceUsage([160:"int"] value) {
            [159:"this"].value = value;
        }

        @Override
        public [163:"int"] compareTo(InterfaceUsage other) {
            [164:"return"] Integer.compare([165:"this"].value, other.value);
        }
    }

    public [169:"class"] AnonymousClassUsage {
        public Runnable getRunnable() {
            [170:"return"] new Runnable() {
                @Override
                public [171:"void"] run() {
                    [172:"int"] x = 5;
                }
            };
        }
    }

    public [178:"class"] LocalClassUsage {
        public [179:"void"] useLocalClass() {
            [180:"class"] LocalClass {
                [181:"private"] [182:"int"] localValue;

                public LocalClass([184:"int"] localValue) {
                    [183:"this"].localValue = localValue;
                }

                public [185:"int"] getLocalValue() {
                    [186:"return"] localValue;
                }
            }

            LocalClass localInstance = new LocalClass(10);
            [187:"int"] value = localInstance.getLocalValue();
        }
    }

    public [201:"class"] VarArgsUsage {
        public [202:"int"] sum([207:"int"]... numbers) {
            [203:"int"] sum = 0;
            [204:"for"] ([205:"int"] number : numbers) {
                sum += number;
            }
            [206:"return"] sum;
        }
    }

    public [214:"class"] DiamondOperatorUsage {
        public [215:"void"] useDiamondOperator() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }
    }

    public [217:"class"] TryWithResourcesUsage {
        public [218:"void"] readFile(String filePath) [221:"throws"] java.io.IOException {
            [219:"try"] (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line = reader.readLine();
            }
        }
    }

    public [223:"class"] EnhancedForLoopUsage {
        public [224:"void"] iterateList(java.util.List<String> list) {
            [225:"for"] (String item : list) {
                [226:"int"] length = item.length();
            }
        }
    }

    public [230:"class"] LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {
            [231:"return"] (a, b) -> a + b;
        }
    }

    public [233:"class"] StreamAPIUsage {
        public [234:"int"] sumList(java.util.List<Integer> list) {
            [235:"return"] list.stream().mapToInt(Integer::intValue).sum();
        }
    }

    public [238:"class"] EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            [239:"switch"] (day) {
                [240:"case"] MONDAY:
                [241:"case"] TUESDAY:
                [242:"case"] WEDNESDAY:
                [243:"case"] THURSDAY:
                [244:"case"] FRIDAY:
                    [245:"return"] "Weekday";
                [246:"case"] SATURDAY:
                [247:"case"] SUNDAY:
                    [248:"return"] "Weekend";
                default:
                    [249:"return"] "Unknown";
            }
        }
    }

    public [261:"class"] ForEachMethodUsage {
        public [262:"void"] printList(java.util.List<String> list) {
            list.forEach(item -> {
                [263:"int"] length = item.length();
            });
        }
    }

    public [268:"class"] OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {
            [269:"return"] optional.orElse("Default Value");
        }
    }

    public [271:"class"] MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {
            [272:"return"] String::length;
        }
    }

    public [274:"class"] StaticImportUsage {
        public [275:"void"] useStaticImport() {
            [276:"int"] max = java.lang.Math.max(5, 10);
        }
    }

    public [279:"class"] DefaultMethodInInterface implements DefaultMethodInterface {
        @Override
        public [280:"void"] abstractMethod() {
        }
    }

    public [282:"interface"] DefaultMethodInterface {
        [283:"void"] abstractMethod();

        default [285:"void"] defaultMethod() {
            [286:"int"] defaultValue = 0;
        }
    }

    public [289:"class"] NestedClassUsage {
        public [290:"class"] InnerClass {
            [291:"private"] [292:"int"] value;

            public InnerClass([294:"int"] value) {
                [293:"this"].value = value;
            }

            public [297:"int"] getValue() {
                [298:"return"] value;
            }
        }
    }

    public [301:"class"] BuilderPatternUsage {
        [302:"private"] [303:"final"] [304:"int"] field1;
        [305:"private"] [306:"final"] String field2;

        [307:"private"] BuilderPatternUsage(Builder builder) {
            [308:"this"].field1 = builder.field1;
            [309:"this"].field2 = builder.field2;
        }

        public [313:"class"] Builder {
            [314:"private"] [315:"int"] field1;
            [316:"private"] String field2;

            public Builder setField1([320:"int"] field1) {
                [317:"this"].field1 = field1;
                [318:"return"] [319:"this"];
            }

            public Builder setField2(String field2) {
                [325:"this"].field2 = field2;
                [326:"return"] [327:"this"];
            }

            public BuilderPatternUsage build() {
                [331:"return"] new BuilderPatternUsage([332:"this"]);
            }
        }
    }

    public [335:"class"] CopyConstructorUsage {
        [336:"private"] [337:"int"] field1;
        [338:"private"] String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            [339:"this"].field1 = other.field1;
            [340:"this"].field2 = other.field2;
        }

        public CopyConstructorUsage([345:"int"] field1, String field2) {
            [343:"this"].field1 = field1;
            [344:"this"].field2 = field2;
        }
    }

    public [349:"class"] FinalizerUsage {
        @Override
        [350:"protected"] [351:"void"] finalize() [357:"throws"] Throwable {
            [352:"try"] {
                // Finalization logic
            } [353:"finally"] {
                [354:"super"].finalize();
            }
        }
    }

    public [361:"class"] VarUsage {
        public [362:"void"] useVar() {
            var number = 10;
            var text = "Hello";
        }
    }

    public [364:"class"] TypeInferenceUsage {
        public [365:"void"] useTypeInference() {
            java.util.Map<String, Integer> map = new java.util.HashMap<>();
        }
    }

    public [367:"class"] ResourceBundleUsage {
        public String getMessage(String key) {
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            [368:"return"] bundle.getString(key);
        }
    }

    public [370:"class"] PatternMatchingInstanceof {
        public [371:"void"] checkObject(Object obj) {
            if (obj [373:"instanceof"] String str) {
                [374:"int"] length = str.length();
            }
        }
    }

    public [375:"class"] SealedClassUsage {
        public [376:"abstract"] sealed [377:"class"] Shape permits Circle, Rectangle {
        }

        public [378:"final"] [379:"class"] Circle extends Shape {
            [380:"private"] [381:"double"] radius;

            public Circle([383:"double"] radius) {
                [382:"this"].radius = radius;
            }

            public [386:"double"] getRadius() {
                [387:"return"] radius;
            }
        }

        public [390:"final"] [391:"class"] Rectangle extends Shape {
            [392:"private"] [393:"double"] length;
            [394:"private"] [395:"double"] width;

            public Rectangle([398:"double"] length, [399:"double"] width) {
                [396:"this"].length = length;
                [397:"this"].width = width;
            }

            public [404:"double"] getLength() {
                [405:"return"] length;
            }

            public [408:"double"] getWidth() {
                [409:"return"] width;
            }
        }
    }

    [412:"class"] NullUsage {
        public [413:"class"] Data {

            public [414:"void"] methodWithNullParam(String input) {
                input = [415:"null"];
            }

            public String methodReturningNull() {
                [418:"return"] [419:"null"];
            }

            public [422:"void"] methodWithNullField() {
                String field = [423:"null"];
            }

            public [426:"void"] methodWithNullCheck(String input) {
                if (input == [428:"null"]) {
                    [429:"return"];
                }
            }

            public String methodWithNullTernary(String input) {
                [430:"return"] input != [431:"null"] ? input : [432:"null"];
            }

            public [436:"void"] methodWithNullInArray() {
                String[] array = new String[10];
                array[0] = [437:"null"];
            }

            public [440:"void"] methodWithNullInCollection() {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add([441:"null"]);
            }

            public [444:"void"] methodWithNullInMap() {
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", [445:"null"]);
            }

            public [448:"void"] methodWithNullInStream() {
                java.util.List<String> list = java.util.Arrays.asList([449:"null"], "value");
                [450:"long"] count = list.stream().filter(java.util.Objects::isNull).count();
            }

            public [454:"void"] methodWithNullInOptional() {
                java.util.Optional<String> optional = java.util.Optional.ofNullable([455:"null"]);
            }

            public [458:"void"] methodWithNullInSupplier() {
                java.util.function.Supplier<String> supplier = () -> [459:"null"];
            }

            public [462:"void"] methodWithNullInLambda() {
                java.util.function.Function<String, String> function = input -> [463:"null"];
            }

            public [466:"void"] methodWithNullInMethodReference() {
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply([467:"null"]);
            }

            public [470:"void"] methodWithNullException() {
                [471:"try"] {
                    String value = [472:"null"];
                    value.length();
                } [473:"catch"] (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            public [478:"void"] methodWithNullInstanceof() {
                Object obj = [479:"null"];
                [480:"boolean"] isString = obj [481:"instanceof"] String;
            }

            public [486:"void"] methodWithNullDefaultValue() {
                String value = [487:"null"];
                String result = value == [488:"null"] ? "default" : value;
            }

            public [492:"void"] methodWithNullAssert() {
                String value = [493:"null"];
                assert value != [494:"null"] : "Value cannot be null";
            }

            public [498:"void"] methodWithNullSynchronize() {
                String value = [499:"null"];
                synchronized ([500:"this"]) {
                    if (value == [504:"null"]) {
                        [505:"return"];
                    }
                }
            }

        }
    }

    [506:"class"] SingletonUsage {
        [507:"void"] main() {
            var s = Singleton.[508:"INSTANCE"];
            System.out.println(Singleton.[509:"INSTANCE"].isOk());
            System.out.println(Singleton.[510:"INSTANCE"].main(Singleton.[511:"INSTANCE"].main(Singleton.getInstance())));

            var s2 = Singleton.[512:"INSTANCE"].OTHER_NAME;
            System.out.println(Singleton.[513:"INSTANCE"].OTHER_NAME.isOk());
            System.out.println(Singleton.[514:"INSTANCE"].OTHER_NAME.main(Singleton.[515:"INSTANCE"].OTHER_NAME.main(Singleton.getInstance())));
        }

        [525:"static"] [526:"class"] Singleton {
            [527:"static"] Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            [528:"boolean"] ok;

            Singleton main(Singleton s) {
                [529:"return"] [530:"this"];
            }

            public [533:"boolean"] isOk() {
                [534:"return"] ok;
            }

            public [537:"static"] Singleton getInstance() {
                [538:"return"] INSTANCE;
            }
        }
    }
}
