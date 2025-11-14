<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.time.DayOfWeek;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> EmojifyTestData {

    public <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> FinalData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> finalField = 10;

        public <fold text='🔒' expand='false'>final</fold> <fold text='💀' expand='false'>void</fold> finalMethod()<fold text=' { ' expand='false'> {
            </fold><fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> localFinalVariable = 5;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> methodWithFinalParam(<fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> param) <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='💀' expand='false'>void</fold> anotherMethod() <fold text='{...}' expand='true'>{
            <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> anotherFinalVariable;
            anotherFinalVariable = 20;
        }</fold>

        public FinalData()<fold text=' { ' expand='false'> {
            </fold><fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> constructorFinalVariable = 30;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> StaticData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🔢' expand='false'>int</fold> staticField = 100;

        public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> staticMethod() <fold text='{}' expand='true'>{
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='{...}' expand='true'>{
            staticField = 200;
        }</fold>

        public StaticData()<fold text=' { ' expand='false'> {
            </fold>staticMethod();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> GetterData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field;

        public <fold text='🔢' expand='false'>int</fold> getField()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> field;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setField(<fold text='🔢' expand='false'>int</fold> field)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.field = field;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> printField()<fold text=' { ' expand='false'> {
            </fold><fold text='🔢' expand='false'>int</fold> value = getField();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🎨' expand='false'>abstract</fold> <fold text='🏛️' expand='false'>class</fold> AbstractData <fold text='{...}' expand='true'>{
        public <fold text='🎨' expand='false'>abstract</fold> <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> InterfaceData <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> interfaceMethod();
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> InterfaceImplementation implements InterfaceData <fold text='{...}' expand='true'>{
        @Override
        public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public <fold text='📊' expand='false'>enum</fold> EnumData <fold text='{...}' expand='true'>{
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

        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;

        <fold text='🚫' expand='false'>private</fold> EnumData()<fold text=' { ' expand='false'> {
            <fold text='📍' expand='false'></fold>this</fold>.value = 0;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setValue(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='🔢' expand='false'>int</fold> getValue()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> value;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='🎨' expand='false'>abstract</fold> <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='🖥️' expand='false'>interface</fold> InterfaceData <fold text='{...}' expand='true'>{
            <fold text='💀' expand='false'>void</fold> interfaceMethod();
        }</fold>
    }</fold>
    public <fold text='🏛️' expand='false'>class</fold> SynchronizedData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> counter;

        public synchronized <fold text='💀' expand='false'>void</fold> increment()<fold text=' { ' expand='false'> {
            </fold>counter++;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> incrementWithBlock() <fold text='{...}' expand='true'>{
            synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                counter++;
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TransientVolatileData implements java.io.Serializable <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🚂' expand='false'>transient</fold> <fold text='🔢' expand='false'>int</fold> transientField;
        <fold text='🚫' expand='false'>private</fold> <fold text='☢️' expand='false'>volatile</fold> <fold text='🔘' expand='false'>boolean</fold> volatileField;

        public TransientVolatileData(<fold text='🔢' expand='false'>int</fold> transientField, <fold text='🔘' expand='false'>boolean</fold> volatileField) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.transientField = transientField;
            <fold text='📍' expand='false'>this</fold>.volatileField = volatileField;
        }</fold>

        public <fold text='🔢' expand='false'>int</fold> getTransientField()<fold text=' { ' expand='false'> {
            <fold text='🔙' expand='false'></fold>return</fold> transientField;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setTransientField(<fold text='🔢' expand='false'>int</fold> transientField)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.transientField = transientField;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='🔘' expand='false'>boolean</fold> isVolatileField()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> volatileField;<fold text=' }' expand='false'>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> setVolatileField(<fold text='🔘' expand='false'>boolean</fold> volatileField)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.volatileField = volatileField;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> NativeData <fold text='{...}' expand='true'>{
        public <fold text='🏕️' expand='false'>native</fold> <fold text='💀' expand='false'>void</fold> nativeMethod();
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> InterfaceUsage implements Comparable<InterfaceUsage> <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;

        public InterfaceUsage(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {
            </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
        }</fold>

        @Override
        public <fold text='🔢' expand='false'>int</fold> compareTo(InterfaceUsage other)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> Integer.compare(<fold text='📍' expand='false'>this</fold>.value, other.value);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> AnonymousClassUsage <fold text='{...}' expand='true'>{
        public Runnable getRunnable() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> <fold text='run() → { ' expand='false'>new Runnable() {
                @Override
                public <fold text='💀' expand='false'>void</fold> run() {
                    </fold><fold text='🔢' expand='false'>int</fold> x = 5;<fold text=' }' expand='false'>
                }
            }</fold>;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LocalClassUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useLocalClass() <fold text='{...}' expand='true'>{
            <fold text='🏛️' expand='false'>class</fold> LocalClass <fold text='{...}' expand='true'>{
                <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> localValue;

                public LocalClass(<fold text='🔢' expand='false'>int</fold> localValue)<fold text=' { ' expand='false'> {
                    </fold><fold text='📍' expand='false'>this</fold>.localValue = localValue;<fold text=' }' expand='false'>
                }</fold>

                public <fold text='🔢' expand='false'>int</fold> getLocalValue()<fold text=' { ' expand='false'> {
                    </fold><fold text='🔙' expand='false'>return</fold> localValue;<fold text=' }' expand='false'>
                }</fold>
            }</fold>

            LocalClass localInstance = new LocalClass(10);
            <fold text='🔢' expand='false'>int</fold> value = localInstance.getLocalValue();
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> VarArgsUsage <fold text='{...}' expand='true'>{
        public <fold text='🔢' expand='false'>int</fold> sum(<fold text='🔢' expand='false'>int</fold>... numbers) <fold text='{...}' expand='true'>{
            <fold text='🔢' expand='false'>int</fold> sum = 0;
            <fold text='🔁' expand='false'>for</fold> (<fold text='🔢' expand='false'>int</fold> number : numbers) <fold text='{...}' expand='true'>{
                sum += number;
            }</fold>
            <fold text='🔙' expand='false'>return</fold> sum;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DiamondOperatorUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useDiamondOperator() <fold text='{...}' expand='true'>{
            java.util.List<<fold text='🪡' expand='false'>String</fold>> list = new java.util.ArrayList<>();
            list.add("Example");
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TryWithResourcesUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> readFile(<fold text='🪡' expand='false'>String</fold> filePath) <fold text='🪃' expand='false'>throws</fold> java.io.IOException <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) <fold text='{...}' expand='true'>{
                <fold text='🪡' expand='false'>String</fold> line = reader.readLine();
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> EnhancedForLoopUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> iterateList(java.util.List<<fold text='🪡' expand='false'>String</fold>> list) <fold text='{...}' expand='true'>{
            <fold text='🔁' expand='false'>for</fold> (<fold text='🪡' expand='false'>String</fold> item : list) <fold text='{...}' expand='true'>{
                <fold text='🔢' expand='false'>int</fold> length = item.length();
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LambdaExpressionUsage <fold text='{...}' expand='true'>{
        public java.util.function.IntBinaryOperator getAdder()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> (a, b) -> a + b;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> StreamAPIUsage <fold text='{...}' expand='true'>{
        public <fold text='🔢' expand='false'>int</fold> sumList(java.util.List<Integer> list)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> list.stream().mapToInt(Integer::intValue).sum();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> EnumSwitchUsage <fold text='{...}' expand='true'>{
        public <fold text='🪡' expand='false'>String</fold> getDayType(DayOfWeek day) <fold text='{...}' expand='true'>{
            <fold text='🔀' expand='false'>switch</fold> (day) <fold text='{...}' expand='true'>{
                <fold text='📦' expand='false'>case</fold> MONDAY:
                <fold text='📦' expand='false'>case</fold> TUESDAY:
                <fold text='📦' expand='false'>case</fold> WEDNESDAY:
                <fold text='📦' expand='false'>case</fold> THURSDAY:
                <fold text='📦' expand='false'>case</fold> FRIDAY:
                    <fold text='🔙' expand='false'>return</fold> "Weekday";
                <fold text='📦' expand='false'>case</fold> SATURDAY:
                <fold text='📦' expand='false'>case</fold> SUNDAY:
                    <fold text='🔙' expand='false'>return</fold> "Weekend";
                default:
                    <fold text='🔙' expand='false'>return</fold> "Unknown";
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> ForEachMethodUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> printList(java.util.List<String> list) <fold text='{...}' expand='true'>{
            list.forEach(item -> <fold text='{...}' expand='true'>{
                <fold text='🔢' expand='false'>int</fold> length = item.length();
            }</fold>);
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> OptionalUsage <fold text='{...}' expand='true'>{
        public <fold text='🪡' expand='false'>String</fold> getValueOrDefault(java.util.Optional<<fold text='🪡' expand='false'>String</fold>> optional) <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> optional.orElse("Default Value");
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> MethodReferenceUsage <fold text='{...}' expand='true'>{
        public java.util.function.Function<<fold text='🪡' expand='false'>String</fold>, Integer> getStringLengthFunction()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> <fold text='🪡' expand='false'>String</fold>::length;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> StaticImportUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useStaticImport()<fold text=' { ' expand='false'> {
            </fold><fold text='🔢' expand='false'>int</fold> max = java.lang.Math.max(5, 10);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DefaultMethodInInterface implements DefaultMethodInterface <fold text='{...}' expand='true'>{
        @Override
        public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> DefaultMethodInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> abstractMethod();

        default <fold text='💀' expand='false'>void</fold> defaultMethod()<fold text=' { ' expand='false'> {
            </fold><fold text='🔢' expand='false'>int</fold> defaultValue = 0;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> NestedClassUsage <fold text='{...}' expand='true'>{
        public <fold text='🏛️' expand='false'>class</fold> InnerClass <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;

            public InnerClass(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {
                </fold><fold text='📍' expand='false'>this</fold>.value = value;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='🔢' expand='false'>int</fold> getValue()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> value;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> BuilderPatternUsage <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field1;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🪡' expand='false'>String</fold> field2;

        <fold text='🚫' expand='false'>private</fold> BuilderPatternUsage(Builder builder) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = builder.field1;
            <fold text='📍' expand='false'>this</fold>.field2 = builder.field2;
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> Builder <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
            <fold text='🚫' expand='false'>private</fold> <fold text='🪡' expand='false'>String</fold> field2;

            public Builder setField1(<fold text='🔢' expand='false'>int</fold> field1) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = field1;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public Builder setField2(<fold text='🪡' expand='false'>String</fold> field2) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field2 = field2;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public BuilderPatternUsage build()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> new BuilderPatternUsage(<fold text='📍' expand='false'>this</fold>);<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> CopyConstructorUsage <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
        <fold text='🚫' expand='false'>private</fold> <fold text='🪡' expand='false'>String</fold> field2;

        public CopyConstructorUsage(CopyConstructorUsage other) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = other.field1;
            <fold text='📍' expand='false'>this</fold>.field2 = other.field2;
        }</fold>

        public CopyConstructorUsage(<fold text='🔢' expand='false'>int</fold> field1, <fold text='🪡' expand='false'>String</fold> field2) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = field1;
            <fold text='📍' expand='false'>this</fold>.field2 = field2;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> FinalizerUsage <fold text='{...}' expand='true'>{
        <fold text='🛡️' expand='false'>protected</fold> <fold text='💀' expand='false'>void</fold> performCleanup() <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> {
                // Cleanup logic
            } <fold text='🏁' expand='false'>finally</fold> <fold text='{...}' expand='true'>{
                completeCleanup();
            }</fold>
        }</fold>

        <fold text='🛡️' expand='false'>protected</fold> <fold text='💀' expand='false'>void</fold> completeCleanup() <fold text='{}' expand='true'>{
            // Base cleanup logic
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> VarUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useVar() <fold text='{...}' expand='true'>{
            var number = 10;
            var text = "Hello";
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TypeInferenceUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useTypeInference()<fold text=' { ' expand='false'> {
            </fold>java.util.Map<<fold text='🪡' expand='false'>String</fold>, Integer> map = new java.util.HashMap<>();<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> ResourceBundleUsage <fold text='{...}' expand='true'>{
        public <fold text='🪡' expand='false'>String</fold> getMessage(<fold text='🪡' expand='false'>String</fold> key) <fold text='{...}' expand='true'>{
            java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("messages");
            <fold text='🔙' expand='false'>return</fold> bundle.getString(key);
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> PatternMatchingInstanceof <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> checkObject(Object obj) <fold text='{...}' expand='true'>{
            if (obj <fold text='is' expand='false'>instanceof</fold> <fold text='🪡' expand='false'>String</fold> str) <fold text='{...}' expand='true'>{
                <fold text='🔢' expand='false'>int</fold> length = str.length();
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> SealedClassUsage <fold text='{...}' expand='true'>{
        public <fold text='🎨' expand='false'>abstract</fold> sealed <fold text='🏛️' expand='false'>class</fold> Shape permits Circle, Rectangle <fold text='{...}' expand='true'>{
        }</fold>

        public <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> Circle extends Shape <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> radius;

            public Circle(<fold text='⚖️' expand='false'>double</fold> radius)<fold text=' { ' expand='false'> {
                </fold><fold text='📍' expand='false'>this</fold>.radius = radius;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='⚖️' expand='false'>double</fold> getRadius()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> radius;<fold text=' }' expand='false'>
            }</fold>
        }</fold>

        public <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> Rectangle extends Shape <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> length;
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> width;

            public Rectangle(<fold text='⚖️' expand='false'>double</fold> length, <fold text='⚖️' expand='false'>double</fold> width) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.length = length;
                <fold text='📍' expand='false'>this</fold>.width = width;
            }</fold>

            public <fold text='⚖️' expand='false'>double</fold> getLength()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> length;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='⚖️' expand='false'>double</fold> getWidth()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> width;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> NullUsage <fold text='{...}' expand='true'>{
        public <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{

            public <fold text='💀' expand='false'>void</fold> methodWithNullParam(<fold text='🪡' expand='false'>String</fold> input)<fold text=' { ' expand='false'> {
                </fold>input = <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='🪡' expand='false'>String</fold> methodReturningNull()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullField()<fold text=' { ' expand='false'> {
                </fold><fold text='🪡' expand='false'>String</fold> field = <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullCheck(<fold text='🪡' expand='false'>String</fold> input) <fold text='{...}' expand='true'>{
                if (input == <fold text='🕳️' expand='false'>null</fold>) <fold text='{...}' expand='true'>{
                    <fold text='🔙' expand='false'>return</fold>;
                }</fold>
            }</fold>

            public <fold text='🪡' expand='false'>String</fold> methodWithNullTernary(<fold text='🪡' expand='false'>String</fold> input)<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> input != <fold text='🕳️' expand='false'>null</fold> ? input : <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInArray() <fold text='{...}' expand='true'>{
                <fold text='🪡' expand='false'>String</fold>[] array = new <fold text='🪡' expand='false'>String</fold>[10];
                array[0] = <fold text='🕳️' expand='false'>null</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInCollection() <fold text='{...}' expand='true'>{
                java.util.List<<fold text='🪡' expand='false'>String</fold>> list = new java.util.ArrayList<>();
                list.add(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMap() <fold text='{...}' expand='true'>{
                java.util.Map<<fold text='🪡' expand='false'>String</fold>, <fold text='🪡' expand='false'>String</fold>> map = new java.util.HashMap<>();
                map.put("key", <fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInStream() <fold text='{...}' expand='true'>{
                java.util.List<<fold text='🪡' expand='false'>String</fold>> list = java.util.Arrays.asList(<fold text='🕳️' expand='false'>null</fold>, "value");
                <fold text='📏' expand='false'>long</fold> count = list.stream().filter(java.util.Objects::isNull).count();
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInOptional() <fold text='{...}' expand='true'>{
                java.util.Optional<<fold text='🪡' expand='false'>String</fold>> optional = java.util.Optional.ofNullable(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInSupplier()<fold text=' { ' expand='false'> {
                </fold>java.util.function.Supplier<<fold text='🪡' expand='false'>String</fold>> supplier = () -> <fold text='🕳️' expand='false'>null</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInLambda() <fold text='{...}' expand='true'>{
                java.util.function.Function<<fold text='🪡' expand='false'>String</fold>, <fold text='🪡' expand='false'>String</fold>> function = input -> <fold text='🕳️' expand='false'>null</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInMethodReference() <fold text='{...}' expand='true'>{
                java.util.function.Function<Object, <fold text='🪡' expand='false'>String</fold>> function = Object::toString;
                <fold text='🪡' expand='false'>String</fold> result = function.apply(<fold text='🕳️' expand='false'>null</fold>);
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullException() <fold text='{...}' expand='true'>{
                <fold text='🤞' expand='false'>try</fold> <fold text='{...}' expand='true'>{
                    <fold text='🪡' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                    value.length();
                }</fold> <fold text='🎣' expand='false'>catch</fold> (NullPointerException e) <fold text='{...}' expand='true'>{
                    e.printStackTrace();
                }</fold>
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInstanceof() <fold text='{...}' expand='true'>{
                Object obj = <fold text='🕳️' expand='false'>null</fold>;
                <fold text='🔘' expand='false'>boolean</fold> isString = obj <fold text='is' expand='false'>instanceof</fold> <fold text='🪡' expand='false'>String</fold>;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullDefaultValue() <fold text='{...}' expand='true'>{
                <fold text='🪡' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                <fold text='🪡' expand='false'>String</fold> result = value == <fold text='🕳️' expand='false'>null</fold> ? "default" : value;
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullAssert() <fold text='{...}' expand='true'>{
                <fold text='🪡' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                assert value != <fold text='🕳️' expand='false'>null</fold> : "Value cannot be null";
            }</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullSynchronize() <fold text='{...}' expand='true'>{
                <fold text='🪡' expand='false'>String</fold> value = <fold text='🕳️' expand='false'>null</fold>;
                synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                    if (value == <fold text='🕳️' expand='false'>null</fold>) <fold text='{...}' expand='true'>{
                        <fold text='🔙' expand='false'>return</fold>;
                    }</fold>
                }</fold>
            }</fold>

        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> SingletonUsage <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> main() <fold text='{...}' expand='true'>{
            var s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.isOk());
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.getInstance())));

            var s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME;
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.isOk());
            System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.getInstance())));
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Singleton <fold text='{...}' expand='true'>{
            <fold text='⚡' expand='false'>static</fold> Singleton INSTANCE = new Singleton();
            Singleton OTHER_NAME = new Singleton();
            <fold text='🔘' expand='false'>boolean</fold> ok;

            Singleton main(Singleton s)<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> ok;<fold text=' }' expand='false'>
            }</fold>

            public <fold text='⚡' expand='false'>static</fold> Singleton getInstance()<fold text=' { ' expand='false'> {
                </fold><fold text='🔙' expand='false'>return</fold> INSTANCE;<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>
}
