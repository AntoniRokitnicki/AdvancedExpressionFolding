[0:"package"] data;

[1:"import"] java.time.DayOfWeek;

@SuppressWarnings("ALL")
public [2:"class"] EmojifyTestData {

    public [3:"final"] [4:"class"] FinalData {
        [5:"private"] [6:"final"] [7:"int"] finalField = 10;

        public [8:"final"][12:"final"] [9:"void"][13:"void"] finalMethod() {
            [10:"final"][14:"final"] [11:"int"][15:"int"] localFinalVariable = 5;
        }

        public [16:"void"][19:"void"] methodWithFinalParam([17:"final"][20:"final"] [18:"int"][21:"int"] param) {
        }

        public [22:"void"][25:"void"] anotherMethod() {
            [23:"final"][26:"final"] [24:"int"][27:"int"] anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {
            [28:"final"][30:"final"] [29:"int"][31:"int"] constructorFinalVariable = 30;
        }
    }

    public [32:"static"] [33:"class"] StaticData {
        [34:"private"] [35:"static"] [36:"int"] staticField = 100;

        public [37:"static"][39:"static"] [38:"void"][40:"void"] staticMethod() {
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

        public [45:"int"][47:"int"] getField() {
            [46:"return"][48:"return"] field;
        }

        public [49:"void"][52:"void"] setField([51:"int"][53:"int"] field) {
            [50:"this"][54:"this"].field = field;
        }

        public [55:"void"][57:"void"] printField() {
            [56:"int"][58:"int"] value = getField();
        }
    }

    public [59:"abstract"] [60:"class"] AbstractData {
        public [61:"abstract"][63:"abstract"] [62:"void"][64:"void"] abstractMethod();

        public [65:"void"][66:"void"] concreteMethod() {
        }
    }

    public [67:"interface"] InterfaceData {
        [68:"void"][69:"void"] interfaceMethod();
    }

    public [70:"class"] InterfaceImplementation implements InterfaceData {
        @Override
        public [71:"void"][72:"void"] interfaceMethod() {
        }
    }

    public [73:"enum"] EnumData {
        ENUM_CONSTANT_1 {
            @Override
            public [74:"void"][75:"void"] abstractMethod() {
            }

            public [76:"void"][77:"void"] interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            @Override
            public [78:"void"][79:"void"] abstractMethod() {
            }

            public [80:"void"][81:"void"] interfaceMethod() {
            }
        };

        [82:"private"] [83:"int"] value;

        [84:"private"][86:"private"] EnumData() {
            [85:"this"][87:"this"].value = 0;
        }

        public [88:"void"][91:"void"] setValue([90:"int"][92:"int"] value) {
            [89:"this"][93:"this"].value = value;
        }

        public [94:"int"][96:"int"] getValue() {
            [95:"return"][97:"return"] value;
        }

        public [98:"abstract"][100:"abstract"] [99:"void"][101:"void"] abstractMethod();

        public [102:"void"][103:"void"] concreteMethod() {
        }

        public [104:"interface"] InterfaceData {
            [105:"void"][106:"void"] interfaceMethod();
        }
    }
    public [107:"class"] SynchronizedData {
        [108:"private"] [109:"int"] counter;

        public synchronized [110:"void"][111:"void"] increment() {
            counter++;
        }

        public [112:"void"][114:"void"] incrementWithBlock() {
            synchronized ([113:"this"][115:"this"]) {
                counter++;
            }
        }
    }

    public [116:"class"] TransientVolatileData implements java.io.Serializable {
        [117:"private"] [118:"transient"] [119:"int"] transientField;
        [120:"private"] [121:"volatile"] [122:"boolean"] volatileField;

        public TransientVolatileData([125:"int"][127:"int"] transientField, [126:"boolean"][128:"boolean"] volatileField) {
            [123:"this"][129:"this"].transientField = transientField;
            [124:"this"][130:"this"].volatileField = volatileField;
        }

        public [131:"int"][133:"int"] getTransientField() {
            [132:"return"][134:"return"] transientField;
        }

        public [135:"void"][138:"void"] setTransientField([137:"int"][139:"int"] transientField) {
            [136:"this"][140:"this"].transientField = transientField;
        }

        public [141:"boolean"][143:"boolean"] isVolatileField() {
            [142:"return"][144:"return"] volatileField;
        }

        public [145:"void"][148:"void"] setVolatileField([147:"boolean"][149:"boolean"] volatileField) {
            [146:"this"][150:"this"].volatileField = volatileField;
        }
    }

    public [151:"class"] NativeData {
        public [152:"native"][154:"native"] [153:"void"][155:"void"] nativeMethod();
    }

    public [156:"class"] InterfaceUsage implements Comparable<InterfaceUsage> {
        [157:"private"] [158:"int"] value;

        public InterfaceUsage([160:"int"][161:"int"] value) {
            [159:"this"][162:"this"].value = value;
        }

        @Override
        public [163:"int"][166:"int"] compareTo(InterfaceUsage other) {
            [164:"return"][167:"return"] Integer.compare([165:"this"][168:"this"].value, other.value);
        }
    }

    public [169:"class"] AnonymousClassUsage {
        public Runnable getRunnable() {
            [170:"return"][173:"return"] new Runnable() {
                @Override
                public [171:"void"][174:"void"][176:"void"] run() {
                    [172:"int"][175:"int"][177:"int"] x = 5;
                }
            };
        }
    }

    public [178:"class"] LocalClassUsage {
        public [179:"void"][188:"void"] useLocalClass() {
            [180:"class"][189:"class"] LocalClass {
                [181:"private"][190:"private"] [182:"int"][191:"int"] localValue;

                public LocalClass([184:"int"][193:"int"][194:"int"] localValue) {
                    [183:"this"][192:"this"][195:"this"].localValue = localValue;
                }

                public [185:"int"][196:"int"][198:"int"] getLocalValue() {
                    [186:"return"][197:"return"][199:"return"] localValue;
                }
            }

            LocalClass localInstance = new LocalClass(10);
            [187:"int"][200:"int"] value = localInstance.getLocalValue();
        }
    }

    public [201:"class"] VarArgsUsage {
        public [202:"int"][208:"int"] sum([207:"int"][209:"int"]... numbers) {
            [203:"int"][210:"int"] sum = 0;
            [204:"for"][211:"for"] ([205:"int"][212:"int"] number : numbers) {
                sum += number;
            }
            [206:"return"][213:"return"] sum;
        }
    }

    public [214:"class"] DiamondOperatorUsage {
        public [215:"void"][216:"void"] useDiamondOperator() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }
    }

    public [217:"class"] TryWithResourcesUsage {
        public [218:"void"][220:"void"] readFile(String filePath) [221:"throws"] java.io.IOException {
            [219:"try"][222:"try"] (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line = reader.readLine();
            }
        }
    }

    public [223:"class"] EnhancedForLoopUsage {
        public [224:"void"][227:"void"] iterateList(java.util.List<String> list) {
            [225:"for"][228:"for"] (String item : list) {
                [226:"int"][229:"int"] length = item.length();
            }
        }
    }

    public [230:"class"] LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {
            [231:"return"][232:"return"] (a, b) -> a + b;
        }
    }

    public [233:"class"] StreamAPIUsage {
        public [234:"int"][236:"int"] sumList(java.util.List<Integer> list) {
            [235:"return"][237:"return"] list.stream().mapToInt(Integer::intValue).sum();
        }
    }

    public [238:"class"] EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            [239:"switch"][250:"switch"] (day) {
                [240:"case"][251:"case"] MONDAY:
                [241:"case"][252:"case"] TUESDAY:
                [242:"case"][253:"case"] WEDNESDAY:
                [243:"case"][254:"case"] THURSDAY:
                [244:"case"][255:"case"] FRIDAY:
                    [245:"return"][256:"return"] "Weekday";
                [246:"case"][257:"case"] SATURDAY:
                [247:"case"][258:"case"] SUNDAY:
                    [248:"return"][259:"return"] "Weekend";
                default:
                    [249:"return"][260:"return"] "Unknown";
            }
        }
    }

    public [261:"class"] ForEachMethodUsage {
        public [262:"void"][265:"void"] printList(java.util.List<String> list) {
            list.forEach(item -> {
                [263:"int"][264:"int"][266:"int"][267:"int"] length = item.length();
            });
        }
    }

    public [268:"class"] OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {
            [269:"return"][270:"return"] optional.orElse("Default Value");
        }
    }

    public [271:"class"] MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {
            [272:"return"][273:"return"] String::length;
        }
    }

    public [274:"class"] StaticImportUsage {
        public [275:"void"][277:"void"] useStaticImport() {
            [276:"int"][278:"int"] max = java.lang.Math.max(5, 10);
        }
    }

    public [279:"class"] DefaultMethodInInterface implements DefaultMethodInterface {
        @Override
        public [280:"void"][281:"void"] abstractMethod() {
        }
    }

    public [282:"interface"] DefaultMethodInterface {
        [283:"void"][284:"void"] abstractMethod();

        default [285:"void"][287:"void"] defaultMethod() {
            [286:"int"][288:"int"] defaultValue = 0;
        }
    }

    public [289:"class"] NestedClassUsage {
        public [290:"class"] InnerClass {
            [291:"private"] [292:"int"] value;

            public InnerClass([294:"int"][295:"int"] value) {
                [293:"this"][296:"this"].value = value;
            }

            public [297:"int"][299:"int"] getValue() {
                [298:"return"][300:"return"] value;
            }
        }
    }

    public [301:"class"] BuilderPatternUsage {
        [302:"private"] [303:"final"] [304:"int"] field1;
        [305:"private"] [306:"final"] String field2;

        [307:"private"][310:"private"] BuilderPatternUsage(Builder builder) {
            [308:"this"][311:"this"].field1 = builder.field1;
            [309:"this"][312:"this"].field2 = builder.field2;
        }

        public [313:"class"] Builder {
            [314:"private"] [315:"int"] field1;
            [316:"private"] String field2;

            public Builder setField1([320:"int"][321:"int"] field1) {
                [317:"this"][322:"this"].field1 = field1;
                [318:"return"][323:"return"] [319:"this"][324:"this"];
            }

            public Builder setField2(String field2) {
                [325:"this"][328:"this"].field2 = field2;
                [326:"return"][329:"return"] [327:"this"][330:"this"];
            }

            public BuilderPatternUsage build() {
                [331:"return"][333:"return"] new BuilderPatternUsage([332:"this"][334:"this"]);
            }
        }
    }

    public [335:"class"] CopyConstructorUsage {
        [336:"private"] [337:"int"] field1;
        [338:"private"] String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            [339:"this"][341:"this"].field1 = other.field1;
            [340:"this"][342:"this"].field2 = other.field2;
        }

        public CopyConstructorUsage([345:"int"][346:"int"] field1, String field2) {
            [343:"this"][347:"this"].field1 = field1;
            [344:"this"][348:"this"].field2 = field2;
        }
    }

    public [349:"class"] FinalizerUsage {
        @Override
        [350:"protected"][355:"protected"] [351:"void"][356:"void"] finalize() [357:"throws"] Throwable {
            [352:"try"][358:"try"] {
                // Finalization logic
            } [353:"finally"][359:"finally"] {
                [354:"super"][360:"super"].finalize();
            }
        }
    }

    public [361:"class"] VarUsage {
        public [362:"void"][363:"void"] useVar() {
            var number = 10;
            var text = "Hello";
        }
    }

    public [364:"class"] TypeInferenceUsage {
        public [365:"void"][366:"void"] useTypeInference() {
            java.util.Map<String, Integer> map = new java.util.HashMap<>();
        }
    }

    public [367:"class"] ResourceBundleUsage {
        public String getMessage(String key) {
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            [368:"return"][369:"return"] bundle.getString(key);
        }
    }

    public [370:"class"] PatternMatchingInstanceof {
        public [371:"void"][372:"void"] checkObject(Object obj) {
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

            public Circle([383:"double"][384:"double"] radius) {
                [382:"this"][385:"this"].radius = radius;
            }

            public [386:"double"][388:"double"] getRadius() {
                [387:"return"][389:"return"] radius;
            }
        }

        public [390:"final"] [391:"class"] Rectangle extends Shape {
            [392:"private"] [393:"double"] length;
            [394:"private"] [395:"double"] width;

            public Rectangle([398:"double"][400:"double"] length, [399:"double"][401:"double"] width) {
                [396:"this"][402:"this"].length = length;
                [397:"this"][403:"this"].width = width;
            }

            public [404:"double"][406:"double"] getLength() {
                [405:"return"][407:"return"] length;
            }

            public [408:"double"][410:"double"] getWidth() {
                [409:"return"][411:"return"] width;
            }
        }
    }

    [412:"class"] NullUsage {
        public [413:"class"] Data {

            public [414:"void"][416:"void"] methodWithNullParam(String input) {
                input = [415:"null"][417:"null"];
            }

            public String methodReturningNull() {
                [418:"return"][420:"return"] [419:"null"][421:"null"];
            }

            public [422:"void"][424:"void"] methodWithNullField() {
                String field = [423:"null"][425:"null"];
            }

            public [426:"void"][427:"void"] methodWithNullCheck(String input) {
                if (input == [428:"null"]) {
                    [429:"return"];
                }
            }

            public String methodWithNullTernary(String input) {
                [430:"return"][433:"return"] input != [431:"null"][434:"null"] ? input : [432:"null"][435:"null"];
            }

            public [436:"void"][438:"void"] methodWithNullInArray() {
                String[] array = new String[10];
                array[0] = [437:"null"][439:"null"];
            }

            public [440:"void"][442:"void"] methodWithNullInCollection() {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add([441:"null"][443:"null"]);
            }

            public [444:"void"][446:"void"] methodWithNullInMap() {
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", [445:"null"][447:"null"]);
            }

            public [448:"void"][451:"void"] methodWithNullInStream() {
                java.util.List<String> list = java.util.Arrays.asList([449:"null"][452:"null"], "value");
                [450:"long"][453:"long"] count = list.stream().filter(java.util.Objects::isNull).count();
            }

            public [454:"void"][456:"void"] methodWithNullInOptional() {
                java.util.Optional<String> optional = java.util.Optional.ofNullable([455:"null"][457:"null"]);
            }

            public [458:"void"][460:"void"] methodWithNullInSupplier() {
                java.util.function.Supplier<String> supplier = () -> [459:"null"][461:"null"];
            }

            public [462:"void"][464:"void"] methodWithNullInLambda() {
                java.util.function.Function<String, String> function = input -> [463:"null"][465:"null"];
            }

            public [466:"void"][468:"void"] methodWithNullInMethodReference() {
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply([467:"null"][469:"null"]);
            }

            public [470:"void"][474:"void"] methodWithNullException() {
                [471:"try"][475:"try"] {
                    String value = [472:"null"][476:"null"];
                    value.length();
                } [473:"catch"][477:"catch"] (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            public [478:"void"][482:"void"] methodWithNullInstanceof() {
                Object obj = [479:"null"][483:"null"];
                [480:"boolean"][484:"boolean"] isString = obj [481:"instanceof"][485:"instanceof"] String;
            }

            public [486:"void"][489:"void"] methodWithNullDefaultValue() {
                String value = [487:"null"][490:"null"];
                String result = value == [488:"null"][491:"null"] ? "default" : value;
            }

            public [492:"void"][495:"void"] methodWithNullAssert() {
                String value = [493:"null"][496:"null"];
                assert value != [494:"null"][497:"null"] : "Value cannot be null";
            }

            public [498:"void"][501:"void"] methodWithNullSynchronize() {
                String value = [499:"null"][502:"null"];
                synchronized ([500:"this"][503:"this"]) {
                    if (value == [504:"null"]) {
                        [505:"return"];
                    }
                }
            }

        }
    }

    [506:"class"] SingletonUsage {
        [507:"void"][516:"void"] main() {
            var s = Singleton.[508:"INSTANCE"][517:"INSTANCE"];
            System.out.println(Singleton.[509:"INSTANCE"][518:"INSTANCE"].isOk());
            System.out.println(Singleton.[510:"INSTANCE"][519:"INSTANCE"].main(Singleton.[511:"INSTANCE"][520:"INSTANCE"].main(Singleton.getInstance())));

            var s2 = Singleton.[512:"INSTANCE"][521:"INSTANCE"].OTHER_NAME;
            System.out.println(Singleton.[513:"INSTANCE"][522:"INSTANCE"].OTHER_NAME.isOk());
            System.out.println(Singleton.[514:"INSTANCE"][523:"INSTANCE"].OTHER_NAME.main(Singleton.[515:"INSTANCE"][524:"INSTANCE"].OTHER_NAME.main(Singleton.getInstance())));
        }

        [525:"static"] [526:"class"] Singleton {
            [527:"static"] Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            [528:"boolean"] ok;

            Singleton main(Singleton s) {
                [529:"return"][531:"return"] [530:"this"][532:"this"];
            }

            public [533:"boolean"][535:"boolean"] isOk() {
                [534:"return"][536:"return"] ok;
            }

            public [537:"static"][539:"static"] Singleton getInstance() {
                [538:"return"][540:"return"] INSTANCE;
            }
        }
    }
}
