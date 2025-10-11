[0:"package"] data;

[0:"import"] java.time.DayOfWeek;

@SuppressWarnings("ALL")
public [0:"class"] EmojifyTestData {

    public [0:"final"] [0:"class"] FinalData {
        [0:"private"] [0:"final"] [0:"int"] finalField = 10;

        public [0:"final"]{[0:"final"]} [0:"void"]{[0:"void"]} finalMethod() {
            [0:"final"]{[0:"final"]} [0:"int"]{[0:"int"]} localFinalVariable = 5;
        }

        public [0:"void"]{[0:"void"]} methodWithFinalParam([0:"final"]{[0:"final"]} [0:"int"]{[0:"int"]} param) {
        }

        public [0:"void"]{[0:"void"]} anotherMethod() {
            [0:"final"]{[0:"final"]} [0:"int"]{[0:"int"]} anotherFinalVariable;
            anotherFinalVariable = 20;
        }

        public FinalData() {
            [0:"final"]{[0:"final"]} [0:"int"]{[0:"int"]} constructorFinalVariable = 30;
        }
    }

    public [0:"static"] [0:"class"] StaticData {
        [0:"private"] [0:"static"] [0:"int"] staticField = 100;

        public [0:"static"]{[0:"static"]} [0:"void"]{[0:"void"]} staticMethod() {
        }

        [0:"static"] {
            staticField = 200;
        }

        public StaticData() {
            staticMethod();
        }
    }

    public [0:"class"] GetterData {
        [0:"private"] [0:"int"] field;

        public [0:"int"]{[0:"int"]} getField() {
            [0:"return"]{[0:"return"]} field;
        }

        public [0:"void"]{[0:"void"]} setField([0:"int"]{[0:"int"]} field) {
            [0:"this"]{[0:"this"]}.field = field;
        }

        public [0:"void"]{[0:"void"]} printField() {
            [0:"int"]{[0:"int"]} value = getField();
        }
    }

    public [0:"abstract"] [0:"class"] AbstractData {
        public [0:"abstract"]{[0:"abstract"]} [0:"void"]{[0:"void"]} abstractMethod();

        public [0:"void"]{[0:"void"]} concreteMethod() {
        }
    }

    public [0:"interface"] InterfaceData {
        [0:"void"]{[0:"void"]} interfaceMethod();
    }

    public [0:"class"] InterfaceImplementation implements InterfaceData {
        @Override
        public [0:"void"]{[0:"void"]} interfaceMethod() {
        }
    }

    public [0:"enum"] EnumData {
        ENUM_CONSTANT_1 {
            @Override
            public [0:"void"]{[0:"void"]} abstractMethod() {
            }

            public [0:"void"]{[0:"void"]} interfaceMethod() {
            }
        },
        ENUM_CONSTANT_2 {
            @Override
            public [0:"void"]{[0:"void"]} abstractMethod() {
            }

            public [0:"void"]{[0:"void"]} interfaceMethod() {
            }
        };

        [0:"private"] [0:"int"] value;

        [0:"private"]{[0:"private"]} EnumData() {
            [0:"this"]{[0:"this"]}.value = 0;
        }

        public [0:"void"]{[0:"void"]} setValue([0:"int"]{[0:"int"]} value) {
            [0:"this"]{[0:"this"]}.value = value;
        }

        public [0:"int"]{[0:"int"]} getValue() {
            [0:"return"]{[0:"return"]} value;
        }

        public [0:"abstract"]{[0:"abstract"]} [0:"void"]{[0:"void"]} abstractMethod();

        public [0:"void"]{[0:"void"]} concreteMethod() {
        }

        public [0:"interface"] InterfaceData {
            [0:"void"]{[0:"void"]} interfaceMethod();
        }
    }
    public [0:"class"] SynchronizedData {
        [0:"private"] [0:"int"] counter;

        public synchronized [0:"void"]{[0:"void"]} increment() {
            counter++;
        }

        public [0:"void"]{[0:"void"]} incrementWithBlock() {
            synchronized ([0:"this"]{[0:"this"]}) {
                counter++;
            }
        }
    }

    public [0:"class"] TransientVolatileData implements java.io.Serializable {
        [0:"private"] [0:"transient"] [0:"int"] transientField;
        [0:"private"] [0:"volatile"] [0:"boolean"] volatileField;

        public TransientVolatileData([0:"int"]{[0:"int"]} transientField, [0:"boolean"]{[0:"boolean"]} volatileField) {
            [0:"this"]{[0:"this"]}.transientField = transientField;
            [0:"this"]{[0:"this"]}.volatileField = volatileField;
        }

        public [0:"int"]{[0:"int"]} getTransientField() {
            [0:"return"]{[0:"return"]} transientField;
        }

        public [0:"void"]{[0:"void"]} setTransientField([0:"int"]{[0:"int"]} transientField) {
            [0:"this"]{[0:"this"]}.transientField = transientField;
        }

        public [0:"boolean"]{[0:"boolean"]} isVolatileField() {
            [0:"return"]{[0:"return"]} volatileField;
        }

        public [0:"void"]{[0:"void"]} setVolatileField([0:"boolean"]{[0:"boolean"]} volatileField) {
            [0:"this"]{[0:"this"]}.volatileField = volatileField;
        }
    }

    public [0:"class"] NativeData {
        public [0:"native"]{[0:"native"]} [0:"void"]{[0:"void"]} nativeMethod();
    }

    public [0:"class"] InterfaceUsage implements Comparable<InterfaceUsage> {
        [0:"private"] [0:"int"] value;

        public InterfaceUsage([0:"int"]{[0:"int"]} value) {
            [0:"this"]{[0:"this"]}.value = value;
        }

        @Override
        public [0:"int"]{[0:"int"]} compareTo(InterfaceUsage other) {
            [0:"return"]{[0:"return"]} Integer.compare([0:"this"]{[0:"this"]}.value, other.value);
        }
    }

    public [0:"class"] AnonymousClassUsage {
        public Runnable getRunnable() {
            [0:"return"]{[0:"return"]} new Runnable() {
                @Override
                public [0:"void"]{[0:"void"]{[0:"void"]}} run() {
                    [0:"int"]{[0:"int"]{[0:"int"]}} x = 5;
                }
            };
        }
    }

    public [0:"class"] LocalClassUsage {
        public [0:"void"]{[0:"void"]} useLocalClass() {
            [0:"class"]{[0:"class"]} LocalClass {
                [0:"private"]{[0:"private"]} [0:"int"]{[0:"int"]} localValue;

                public LocalClass([0:"int"]{[0:"int"]{[0:"int"]}} localValue) {
                    [0:"this"]{[0:"this"]{[0:"this"]}}.localValue = localValue;
                }

                public [0:"int"]{[0:"int"]{[0:"int"]}} getLocalValue() {
                    [0:"return"]{[0:"return"]{[0:"return"]}} localValue;
                }
            }

            LocalClass localInstance = new LocalClass(10);
            [0:"int"]{[0:"int"]} value = localInstance.getLocalValue();
        }
    }

    public [0:"class"] VarArgsUsage {
        public [0:"int"]{[0:"int"]} sum([0:"int"]{[0:"int"]}... numbers) {
            [0:"int"]{[0:"int"]} sum = 0;
            [0:"for"]{[0:"for"]} ([0:"int"]{[0:"int"]} number : numbers) {
                sum += number;
            }
            [0:"return"]{[0:"return"]} sum;
        }
    }

    public [0:"class"] DiamondOperatorUsage {
        public [0:"void"]{[0:"void"]} useDiamondOperator() {
            java.util.List<String> list = new java.util.ArrayList<>();
            list.add("Example");
        }
    }

    public [0:"class"] TryWithResourcesUsage {
        public [0:"void"]{[0:"void"]} readFile(String filePath) [0:"throws"] java.io.IOException {
            [0:"try"]{[0:"try"]} (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
                String line = reader.readLine();
            }
        }
    }

    public [0:"class"] EnhancedForLoopUsage {
        public [0:"void"]{[0:"void"]} iterateList(java.util.List<String> list) {
            [0:"for"]{[0:"for"]} (String item : list) {
                [0:"int"]{[0:"int"]} length = item.length();
            }
        }
    }

    public [0:"class"] LambdaExpressionUsage {
        public java.util.function.IntBinaryOperator getAdder() {
            [0:"return"]{[0:"return"]} (a, b) -> a + b;
        }
    }

    public [0:"class"] StreamAPIUsage {
        public [0:"int"]{[0:"int"]} sumList(java.util.List<Integer> list) {
            [0:"return"]{[0:"return"]} list.stream().mapToInt(Integer::intValue).sum();
        }
    }

    public [0:"class"] EnumSwitchUsage {
        public String getDayType(DayOfWeek day) {
            [0:"switch"]{[0:"switch"]} (day) {
                [0:"case"]{[0:"case"]} MONDAY:
                [0:"case"]{[0:"case"]} TUESDAY:
                [0:"case"]{[0:"case"]} WEDNESDAY:
                [0:"case"]{[0:"case"]} THURSDAY:
                [0:"case"]{[0:"case"]} FRIDAY:
                    [0:"return"]{[0:"return"]} "Weekday";
                [0:"case"]{[0:"case"]} SATURDAY:
                [0:"case"]{[0:"case"]} SUNDAY:
                    [0:"return"]{[0:"return"]} "Weekend";
                default:
                    [0:"return"]{[0:"return"]} "Unknown";
            }
        }
    }

    public [0:"class"] ForEachMethodUsage {
        public [0:"void"]{[0:"void"]} printList(java.util.List<String> list) {
            list.forEach(item -> {
                [0:"int"]{[0:"int"]{[0:"int"]{[0:"int"]}}} length = item.length();
            });
        }
    }

    public [0:"class"] OptionalUsage {
        public String getValueOrDefault(java.util.Optional<String> optional) {
            [0:"return"]{[0:"return"]} optional.orElse("Default Value");
        }
    }

    public [0:"class"] MethodReferenceUsage {
        public java.util.function.Function<String, Integer> getStringLengthFunction() {
            [0:"return"]{[0:"return"]} String::length;
        }
    }

    public [0:"class"] StaticImportUsage {
        public [0:"void"]{[0:"void"]} useStaticImport() {
            [0:"int"]{[0:"int"]} max = java.lang.Math.max(5, 10);
        }
    }

    public [0:"class"] DefaultMethodInInterface implements DefaultMethodInterface {
        @Override
        public [0:"void"]{[0:"void"]} abstractMethod() {
        }
    }

    public [0:"interface"] DefaultMethodInterface {
        [0:"void"]{[0:"void"]} abstractMethod();

        default [0:"void"]{[0:"void"]} defaultMethod() {
            [0:"int"]{[0:"int"]} defaultValue = 0;
        }
    }

    public [0:"class"] NestedClassUsage {
        public [0:"class"] InnerClass {
            [0:"private"] [0:"int"] value;

            public InnerClass([0:"int"]{[0:"int"]} value) {
                [0:"this"]{[0:"this"]}.value = value;
            }

            public [0:"int"]{[0:"int"]} getValue() {
                [0:"return"]{[0:"return"]} value;
            }
        }
    }

    public [0:"class"] BuilderPatternUsage {
        [0:"private"] [0:"final"] [0:"int"] field1;
        [0:"private"] [0:"final"] String field2;

        [0:"private"]{[0:"private"]} BuilderPatternUsage(Builder builder) {
            [0:"this"]{[0:"this"]}.field1 = builder.field1;
            [0:"this"]{[0:"this"]}.field2 = builder.field2;
        }

        public [0:"class"] Builder {
            [0:"private"] [0:"int"] field1;
            [0:"private"] String field2;

            public Builder setField1([0:"int"]{[0:"int"]} field1) {
                [0:"this"]{[0:"this"]}.field1 = field1;
                [0:"return"]{[0:"return"]} [0:"this"]{[0:"this"]};
            }

            public Builder setField2(String field2) {
                [0:"this"]{[0:"this"]}.field2 = field2;
                [0:"return"]{[0:"return"]} [0:"this"]{[0:"this"]};
            }

            public BuilderPatternUsage build() {
                [0:"return"]{[0:"return"]} new BuilderPatternUsage([0:"this"]{[0:"this"]});
            }
        }
    }

    public [0:"class"] CopyConstructorUsage {
        [0:"private"] [0:"int"] field1;
        [0:"private"] String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) {
            [0:"this"]{[0:"this"]}.field1 = other.field1;
            [0:"this"]{[0:"this"]}.field2 = other.field2;
        }

        public CopyConstructorUsage([0:"int"]{[0:"int"]} field1, String field2) {
            [0:"this"]{[0:"this"]}.field1 = field1;
            [0:"this"]{[0:"this"]}.field2 = field2;
        }
    }

    public [0:"class"] FinalizerUsage {
        @Override
        [0:"protected"]{[0:"protected"]} [0:"void"]{[0:"void"]} finalize() [0:"throws"] Throwable {
            [0:"try"]{[0:"try"]} {
                // Finalization logic
            } [0:"finally"]{[0:"finally"]} {
                [0:"super"]{[0:"super"]}.finalize();
            }
        }
    }

    public [0:"class"] VarUsage {
        public [0:"void"]{[0:"void"]} useVar() {
            var number = 10;
            var text = "Hello";
        }
    }

    public [0:"class"] TypeInferenceUsage {
        public [0:"void"]{[0:"void"]} useTypeInference() {
            java.util.Map<String, Integer> map = new java.util.HashMap<>();
        }
    }

    public [0:"class"] ResourceBundleUsage {
        public String getMessage(String key) {
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            [0:"return"]{[0:"return"]} bundle.getString(key);
        }
    }

    public [0:"class"] PatternMatchingInstanceof {
        public [0:"void"]{[0:"void"]} checkObject(Object obj) {
            if (obj [0:"instanceof"] String str) {
                [0:"int"] length = str.length();
            }
        }
    }

    public [0:"class"] SealedClassUsage {
        public [0:"abstract"] sealed [0:"class"] Shape permits Circle, Rectangle {
        }

        public [0:"final"] [0:"class"] Circle extends Shape {
            [0:"private"] [0:"double"] radius;

            public Circle([0:"double"]{[0:"double"]} radius) {
                [0:"this"]{[0:"this"]}.radius = radius;
            }

            public [0:"double"]{[0:"double"]} getRadius() {
                [0:"return"]{[0:"return"]} radius;
            }
        }

        public [0:"final"] [0:"class"] Rectangle extends Shape {
            [0:"private"] [0:"double"] length;
            [0:"private"] [0:"double"] width;

            public Rectangle([0:"double"]{[0:"double"]} length, [0:"double"]{[0:"double"]} width) {
                [0:"this"]{[0:"this"]}.length = length;
                [0:"this"]{[0:"this"]}.width = width;
            }

            public [0:"double"]{[0:"double"]} getLength() {
                [0:"return"]{[0:"return"]} length;
            }

            public [0:"double"]{[0:"double"]} getWidth() {
                [0:"return"]{[0:"return"]} width;
            }
        }
    }

    [0:"class"] NullUsage {
        public [0:"class"] Data {

            public [0:"void"]{[0:"void"]} methodWithNullParam(String input) {
                input = [0:"null"]{[0:"null"]};
            }

            public String methodReturningNull() {
                [0:"return"]{[0:"return"]} [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullField() {
                String field = [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullCheck(String input) {
                if (input == [0:"null"]) {
                    [0:"return"];
                }
            }

            public String methodWithNullTernary(String input) {
                [0:"return"]{[0:"return"]} input != [0:"null"]{[0:"null"]} ? input : [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullInArray() {
                String[] array = new String[10];
                array[0] = [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullInCollection() {
                java.util.List<String> list = new java.util.ArrayList<>();
                list.add([0:"null"]{[0:"null"]});
            }

            public [0:"void"]{[0:"void"]} methodWithNullInMap() {
                java.util.Map<String, String> map = new java.util.HashMap<>();
                map.put("key", [0:"null"]{[0:"null"]});
            }

            public [0:"void"]{[0:"void"]} methodWithNullInStream() {
                java.util.List<String> list = java.util.Arrays.asList([0:"null"]{[0:"null"]}, "value");
                [0:"long"]{[0:"long"]} count = list.stream().filter(java.util.Objects::isNull).count();
            }

            public [0:"void"]{[0:"void"]} methodWithNullInOptional() {
                java.util.Optional<String> optional = java.util.Optional.ofNullable([0:"null"]{[0:"null"]});
            }

            public [0:"void"]{[0:"void"]} methodWithNullInSupplier() {
                java.util.function.Supplier<String> supplier = () -> [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullInLambda() {
                java.util.function.Function<String, String> function = input -> [0:"null"]{[0:"null"]};
            }

            public [0:"void"]{[0:"void"]} methodWithNullInMethodReference() {
                java.util.function.Function<Object, String> function = Object::toString;
                String result = function.apply([0:"null"]{[0:"null"]});
            }

            public [0:"void"]{[0:"void"]} methodWithNullException() {
                [0:"try"]{[0:"try"]} {
                    String value = [0:"null"]{[0:"null"]};
                    value.length();
                } [0:"catch"]{[0:"catch"]} (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            public [0:"void"]{[0:"void"]} methodWithNullInstanceof() {
                Object obj = [0:"null"]{[0:"null"]};
                [0:"boolean"]{[0:"boolean"]} isString = obj [0:"instanceof"]{[0:"instanceof"]} String;
            }

            public [0:"void"]{[0:"void"]} methodWithNullDefaultValue() {
                String value = [0:"null"]{[0:"null"]};
                String result = value == [0:"null"]{[0:"null"]} ? "default" : value;
            }

            public [0:"void"]{[0:"void"]} methodWithNullAssert() {
                String value = [0:"null"]{[0:"null"]};
                assert value != [0:"null"]{[0:"null"]} : "Value cannot be null";
            }

            public [0:"void"]{[0:"void"]} methodWithNullSynchronize() {
                String value = [0:"null"]{[0:"null"]};
                synchronized ([0:"this"]{[0:"this"]}) {
                    if (value == [0:"null"]) {
                        [0:"return"];
                    }
                }
            }

        }
    }

    [0:"class"] SingletonUsage {
        [0:"void"]{[0:"void"]} main() {
            var s = Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]};
            System.out.println(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.isOk());
            System.out.println(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.main(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.main(Singleton.getInstance())));

            var s2 = Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.OTHER_NAME;
            System.out.println(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.OTHER_NAME.isOk());
            System.out.println(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.OTHER_NAME.main(Singleton.[0:"INSTANCE"]{[0:"INSTANCE"]}.OTHER_NAME.main(Singleton.getInstance())));
        }

        [0:"static"] [0:"class"] Singleton {
            [0:"static"] Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            [0:"boolean"] ok;

            Singleton main(Singleton s) {
                [0:"return"]{[0:"return"]} [0:"this"]{[0:"this"]};
            }

            public [0:"boolean"]{[0:"boolean"]} isOk() {
                [0:"return"]{[0:"return"]} ok;
            }

            public [0:"static"]{[0:"static"]} Singleton getInstance() {
                [0:"return"]{[0:"return"]} INSTANCE;
            }
        }
    }
}
