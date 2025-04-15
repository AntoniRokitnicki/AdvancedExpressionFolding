<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> java.time.DayOfWeek;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> EmojifyTestData {

    public <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> FinalData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> finalField = 10;

        public <fold text='🔒' expand='false'>final</fold> <fold text='💀' expand='false'>void</fold> finalMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            <fold text='🔒' expand='false'></fold><fold text='val' expand='false'></fold>final</fold> <fold text='🔢' expand='false'>int</fold></fold> localFinalVariable = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> methodWithFinalParam(<fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> param) <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='💀' expand='false'>void</fold> anotherMethod() <fold text='{...}' expand='true'>{
            <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> anotherFinalVariable;
            anotherFinalVariable = 20;
        }</fold>

        public FinalData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='🔒' expand='false'><fold text='val' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold></fold> constructorFinalVariable = 30;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> StaticData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🔢' expand='false'>int</fold> staticField = 100;

        public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> staticMethod() <fold text='{}' expand='true'>{
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='{...}' expand='true'>{
            staticField = 200;
        }</fold>

        public StaticData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>staticMethod()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> GetterData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>field<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public <fold text='💀' expand='false'>void</fold> setField(<fold text='🔢' expand='false'>int</fold> field)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.field = <fold text='<<' expand='false'>field</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='💀' expand='false'>void</fold> printField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='val' expand='false'></fold>int</fold> value = <fold text='field' expand='false'>getField()</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🎨' expand='false'>abstract</fold> <fold text='🏛️' expand='false'>class</fold> AbstractData <fold text='{...}' expand='true'>{
        public <fold text='🎨' expand='false'>abstract</fold> <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> InterfaceData <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> interfaceMethod();
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> InterfaceImplementation implements InterfaceDat<fold text='a(1-interfaceMethod)' expand='true'>a</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{ // overrides from InterfaceData' expand='true'><fold text='{}' expand='true'>{</fold>
        }</fold>
    }</fold>

    <fold text='@Getter @Setter p' expand='false'>p</fold>ublic <fold text='📊' expand='false'>enum</fold> EnumData <fold text='{...}' expand='true'>{
        ENUM_CONSTANT_1 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override</fold>
            public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>,
        ENUM_CONSTANT_2 <fold text='{...}' expand='true'>{
            <fold text='' expand='true'>@Override</fold>
            public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{}' expand='true'>{
            }</fold>

            public <fold text='💀' expand='false'>void</fold> interfaceMethod() <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;

        <fold text='🚫' expand='false'>private</fold> EnumData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = 0<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setValue(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>value<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='🎨' expand='false'>abstract</fold> <fold text='💀' expand='false'>void</fold> abstractMethod();

        public <fold text='💀' expand='false'>void</fold> concreteMethod() <fold text='{}' expand='true'>{
        }</fold>

        public <fold text='🖥️' expand='false'>interface</fold> InterfaceData <fold text='{...}' expand='true'>{
            <fold text='💀' expand='false'>void</fold> interfaceMethod();
        }</fold>
    }</fold>
    public <fold text='🏛️' expand='false'>class</fold> SynchronizedData <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> counter;

        public synchronized <fold text='💀' expand='false'>void</fold> increment()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>counter++<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public <fold text='💀' expand='false'>void</fold> incrementWithBlock() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>synchronized (<fold text='📍' expand='false'>this</fold>) <fold text='{...}' expand='true'>{
                counter++;
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    <fold text='@AllArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> TransientVolatileData implements java.io.Serializabl<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🚂' expand='false'>transient</fold> <fold text='🔢' expand='false'>int</fold> transientField;
        <fold text='🚫' expand='false'>private</fold> <fold text='☢️' expand='false'>volatile</fold> <fold text='🔘' expand='false'>boolean</fold> volatileField;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public TransientVolatileData(<fold text='🔢' expand='false'>int</fold> transientField, <fold text='🔘' expand='false'>boolean</fold> volatileField) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.transientField = <fold text='<<' expand='false'>transientField</fold>;
            <fold text='📍' expand='false'>this</fold>.volatileField = <fold text='<<' expand='false'>volatileField</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getTransientField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>transientField<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setTransientField(<fold text='🔢' expand='false'>int</fold> transientField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.transientField = <fold text='<<' expand='false'>transientField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public <fold text='🔘' expand='false'>boolean</fold> isVolatileField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>volatileField<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setVolatileField(<fold text='🔘' expand='false'>boolean</fold> volatileField)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.volatileField = <fold text='<<' expand='false'>volatileField</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> NativeData <fold text='{...}' expand='true'>{
        public <fold text='🏕️' expand='false'>native</fold> <fold text='💀' expand='false'>void</fold> nativeMethod();
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> InterfaceUsage implements Comparabl<fold text='e(0-)' expand='true'>e</fold><InterfaceUsage> <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public InterfaceUsage(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='' expand='true'>@Override</fold>
        public <fold text='🔢' expand='false'>int</fold> compareTo(InterfaceUsage other)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>Integer.compare(<fold text='📍' expand='false'>this</fold>.value, other.value)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> AnonymousClassUsage <fold text='{...}' expand='true'>{
        public Runnable getRunnable() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> <fold text='run() → { ' expand='false'>new Runnable() {
                <fold text='' expand='true'>@Override</fold>
                public <fold text='💀' expand='false'>void</fold> run() {<fold text=' ' expand='true'>
                    </fold></fold><fold text='val' expand='false'>int</fold> x = 5;<fold text=' ' expand='true'><fold text=' }' expand='false'>
                </fold>}
            }</fold>;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LocalClassUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useLocalClass() <fold text='{...}' expand='true'>{
            <fold text='@AllArgsConstructor @Getter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> LocalClass <fold text='{...}' expand='true'>{
                <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> localValue;<fold text='' expand='false'>

                </fold><fold text='' expand='false'>public LocalClass(<fold text='🔢' expand='false'>int</fold> localValue)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='📍' expand='false'>this</fold>.localValue = <fold text='<<' expand='false'>localValue</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
                </fold>}</fold><fold text='' expand='false'></fold>

                </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getLocalValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                    </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>localValue<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
                </fold>}</fold></fold>
            }</fold>

            <fold text='val' expand='false'>LocalClass</fold> localInstance = new LocalClass(10);
            <fold text='val' expand='false'>int</fold> value = localInstance.<fold text='localValue' expand='false'>getLocalValue()</fold>;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> VarArgsUsage <fold text='{...}' expand='true'>{
        public <fold text='🔢' expand='false'>int</fold> sum(<fold text='🔢' expand='false'>int</fold>... numbers) <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>int</fold> sum = 0;
            <fold text='🔁' expand='false'>for</fold> <fold text='' expand='false'>(</fold><fold text='val' expand='false'>int</fold> number : numbers<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                sum += number;
            }</fold>
            <fold text='🔙' expand='false'>return</fold> sum;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DiamondOperatorUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useDiamondOperator() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>java.util.List<String></fold> list = <fold text='[]' expand='false'>new java.util.ArrayList<>()</fold>;
            list<fold text=' += ' expand='false'>.add(</fold>"Example"<fold text='' expand='false'>)</fold>;
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TryWithResourcesUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> readFile(String filePath) <fold text='🪃' expand='false'>throws</fold> java.io.IOException <fold text='{...}' expand='true'>{
            <fold text='🤞' expand='false'>try</fold> (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> line = reader.readLine();
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> EnhancedForLoopUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> iterateList(java.util.List<String> list) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='🔁' expand='false'>for</fold> <fold text='' expand='false'>(</fold><fold text='val' expand='false'>String</fold> item : list<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>int</fold> length = item.length();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> LambdaExpressionUsage <fold text='{...}' expand='true'>{
        public java.util.function.IntBinaryOperator getAdder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>(a, b) -> a + b<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> StreamAPIUsage <fold text='{...}' expand='true'>{
        public <fold text='🔢' expand='false'>int</fold> sumList(java.util.List<Integer> list)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>list<fold text='.' expand='false'>.stream().</fold>mapToInt(Integer::intValue).sum()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> EnumSwitchUsage <fold text='{...}' expand='true'>{
        public String getDayType(DayOfWeek day) <fold text='{...}' expand='true'>{
            <fold text='🔀' expand='false'>switch</fold> <fold text='' expand='false'>(</fold>day<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
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
        public <fold text='💀' expand='false'>void</fold> printList(java.util.List<String> list) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>list.forEach(item -> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>int</fold> length = item.length();
            }</fold>)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> OptionalUsage <fold text='{...}' expand='true'>{
        public String getValueOrDefault(java.util.Optional<String> optional) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>optional<fold text=' ?: ' expand='false'>.orElse(</fold>"Default Value"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> MethodReferenceUsage <fold text='{...}' expand='true'>{
        public java.util.function.Function<String, Integer> getStringLengthFunction()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>String::length<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> StaticImportUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useStaticImport()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>int</fold> max = <fold text='max(' expand='false'>java.lang.Math.max(</fold>5, 10<fold text=')' expand='false'>)</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> DefaultMethodInInterface implements DefaultMethodInterfac<fold text='e(1-abstractMethod)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        public <fold text='💀' expand='false'>void</fold> abstractMethod() <fold text='{ // overrides from DefaultMethodInterface' expand='true'><fold text='{}' expand='true'>{</fold>
        }</fold>
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> DefaultMethodInterface <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> abstractMethod();

        default <fold text='💀' expand='false'>void</fold> defaultMethod()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='val' expand='false'></fold>int</fold> defaultValue = 0;<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> NestedClassUsage <fold text='{...}' expand='true'>{
        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> InnerClass <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> value;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public InnerClass(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.value = <fold text='<<' expand='false'>value</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='🔢' expand='false'>int</fold> getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return<fold text='' expand='true'></fold> </fold>value<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='@Builder(Builder) p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> BuilderPatternUsage <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🔢' expand='false'>int</fold> field1;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String field2;

        <fold text='🚫' expand='false'>private</fold> BuilderPatternUsage(Builder builder) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = builder.<fold text='<<' expand='true'>field1</fold>;
            <fold text='📍' expand='false'>this</fold>.field2 = builder.<fold text='<<' expand='true'>field2</fold>;
        }</fold>

        public <fold text='🏛️' expand='false'>class</fold> Builder <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
            <fold text='🚫' expand='false'>private</fold> String field2;

            public Builder setField1(<fold text='🔢' expand='false'>int</fold> field1) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public Builder setField2(String field2) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public BuilderPatternUsage build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>new BuilderPatternUsage(<fold text='📍' expand='false'>this</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>

    <fold text='@AllArgsConstructor p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> CopyConstructorUsage <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> <fold text='🔢' expand='false'>int</fold> field1;
        <fold text='🚫' expand='false'>private</fold> String field2;

        public CopyConstructorUsage(CopyConstructorUsage other) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = other.<fold text='<<' expand='true'>field1</fold>;
            <fold text='📍' expand='false'>this</fold>.field2 = other.<fold text='<<' expand='true'>field2</fold>;
        }<fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public CopyConstructorUsage(<fold text='🔢' expand='false'>int</fold> field1, String field2) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.field1 = <fold text='<<' expand='false'>field1</fold>;
            <fold text='📍' expand='false'>this</fold>.field2 = <fold text='<<' expand='false'>field2</fold>;
        }</fold></fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> FinalizerUsage <fold text='{...}' expand='true'>{
        <fold text='' expand='true'>@Override</fold>
        <fold text='🛡️' expand='false'>protected</fold> <fold text='💀' expand='false'>void</fold> finalize() <fold text='🪃' expand='false'>throws</fold> Throwable <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='🤞' expand='false'>try</fold> {
                // Finalization logic
            } <fold text='🏁' expand='false'>finally</fold> <fold text='{...}' expand='true'>{
                <fold text='💪' expand='false'>super</fold>.finalize();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> VarUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useVar() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>var</fold> number = 10;
            <fold text='val' expand='false'>var</fold> text = "Hello";
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> TypeInferenceUsage <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> useTypeInference()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='val' expand='false'>java.util.Map<String, Integer></fold> map = new java.util.HashMap<>();<fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> ResourceBundleUsage <fold text='{...}' expand='true'>{
        public String getMessage(String key) <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>java.util.ResourceBundle</fold> bundle = java.util.ResourceBundle.getBundle("messages");
            <fold text='🔙' expand='false'>return</fold> bundle.getString(key);
        }</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> PatternMatchingInstanceof <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> checkObject(Object obj) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold>if <fold text='' expand='false'>(</fold>obj <fold text='is' expand='false'>instanceof</fold> String str<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>int</fold> length = str.length();
            }</fold><fold text=' ' expand='true'>
        </fold>}</fold>
    }</fold>

    public <fold text='🏛️' expand='false'>class</fold> SealedClassUsage <fold text='{...}' expand='true'>{
        public <fold text='🎨' expand='false'>abstract</fold> sealed <fold text='🏛️' expand='false'>class</fold> Shape permits Circle, Rectangle <fold text='{...}' expand='true'>{
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> Circle extends Shap<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> radius;<fold text='' expand='false'>

            <fold text='' expand='false'></fold>public Circle(<fold text='⚖️' expand='false'>double</fold> radius)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.radius = <fold text='<<' expand='false'>radius</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='⚖️' expand='false'>double</fold> getRadius()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='false'></fold>return</fold><fold text='' expand='true'> </fold>radius<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>

        <fold text='@AllArgsConstructor @Getter p' expand='false'>p</fold>ublic <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> Rectangle extends Shap<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> length;
            <fold text='🚫' expand='false'>private</fold> <fold text='⚖️' expand='false'>double</fold> width;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>public Rectangle(<fold text='⚖️' expand='false'>double</fold> length, <fold text='⚖️' expand='false'>double</fold> width) <fold text='{...}' expand='true'>{
                <fold text='📍' expand='false'>this</fold>.length = <fold text='<<' expand='false'>length</fold>;
                <fold text='📍' expand='false'>this</fold>.width = <fold text='<<' expand='false'>width</fold>;
            }</fold></fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='⚖️' expand='false'>double</fold> getLength()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>length<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'></fold>

            </fold><fold text='' expand='false'>public <fold text='⚖️' expand='false'>double</fold> getWidth()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>width<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> NullUsage <fold text='{...}' expand='true'>{
        public <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{

            public <fold text='💀' expand='false'>void</fold> methodWithNullParam(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>input = <fold text='🕳️' expand='false'>null<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public String methodReturningNull()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'></fold>String</fold> field = <fold text='🕳️' expand='false'>null</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullCheck(String input) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold>if <fold text='' expand='false'>(</fold>input == <fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    <fold text='🔙' expand='false'>return</fold>;
                }<fold text=' ' expand='true'></fold>
            </fold>}</fold>

            public String methodWithNullTernary(String input)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>input != null ? </fold>input<fold text=' ?: ' expand='false'> : </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
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

            public <fold text='💀' expand='false'>void</fold> methodWithNullInOptional() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
                </fold><fold text='val' expand='false'>java.util.Optional<String></fold> optional = java.util.<fold text='' expand='false'>Optional.ofNullable(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;<fold text=' ' expand='true'>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInSupplier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='val' expand='false'>java.util.function.Supplier<String></fold> supplier = () -> <fold text='🕳️' expand='false'>null</fold>;<fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public <fold text='💀' expand='false'>void</fold> methodWithNullInLambda() <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
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
                <fold text='val' expand='false'>boolean</fold> isString = obj <fold text='is' expand='false'>instanceof</fold> String;
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
                        <fold text='🔙' expand='false'>return</fold>;
                    }</fold>
                }</fold>
            }</fold>

        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> SingletonUsage <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> main() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>var</fold> s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));

            <fold text='val' expand='false'>var</fold> s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME;
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.<fold text='ok' expand='false'>isOk()</fold>);
            <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.OTHER_NAME.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));
        }</fold>

        <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Singleton <fold text='{...}' expand='true'>{
            <fold text='⚡' expand='false'>static</fold> Singleton INSTANCE =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> <fold text='' expand='true'></fold>Singleton</fold><fold text='' expand='true'>()</fold>;
            Singleton OTHER_NAME =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
            <fold text='@Getter b' expand='false'><fold text='🔘' expand='false'>b</fold>oolean</fold> ok;

            Singleton main(Singleton s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold></fold>

            public <fold text='⚡' expand='false'>static</fold> Singleton getInstance()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>INSTANCE<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>
}
