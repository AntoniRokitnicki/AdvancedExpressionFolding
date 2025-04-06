<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.List;
<fold text='🚢' expand='false'>import</fold> java.util.Map;</fold>

public <fold text='🏛️' expand='false'>class</fold> PatternMatchingInstanceofTestData {

    public <fold text='💀' expand='false'>void</fold> main(String arg, <fold text='🔢' expand='false'>int</fold> i, Object o, Data data) <fold text='{...}' expand='true'>{
        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(s.length());
        }</fold>

        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> Integer<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Integer</fold> num = <fold text='' expand='false'>(Integer) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(num * 2);
        }</fold>

        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> List<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>List<?></fold> list = <fold text='' expand='false'>(List<?>) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(list.size());
        }</fold>

        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> Map<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Map<?, ?></fold> map = <fold text='' expand='false'>(Map<?, ?>) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(map.keySet());
        }</fold>

        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> Data<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>Data</fold> d = <fold text='' expand='false'>(Data) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(d.<fold text='value' expand='false'>getValue()</fold>);
        }</fold>

        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> <fold text='🔢' expand='false'>int</fold>[]<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='🔢' expand='false'><fold text='val' expand='false'>int</fold>[]</fold> arr = <fold text='' expand='false'>(int[]) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(arr.length);
        }</fold>


        if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> DayOfWeek<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>DayOfWeek</fold> day = <fold text='' expand='false'>(DayOfWeek) </fold>o;
            <fold text='' expand='false'>System.out.</fold>println(day.name());
        }</fold>
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        public <fold text='🔢' expand='false'>int</fold> getValue()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>42<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    <fold text='📊' expand='false'>enum</fold> DayOfWeek <fold text='{...}' expand='true'>{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }</fold>

    // Test for type matching
    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> TypeMatchingTest <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>Integer</fold> i = <fold text='' expand='false'>(Integer) </fold>o; // Type mismatch
                <fold text='' expand='false'>System.out.</fold>println(i);
            }</fold>
        }</fold>
    }</fold>

    // Test for variable usage in instanceof
    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> VariableUsageTest <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='object' expand='false'>getObject()</fold> <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{ // Method call instead of variable
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        <fold text='🚫' expand='false'>private</fold> Object getObject()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>new Object()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    // Test for simple assignment
    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> SimpleAssignmentTest <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = "Hello"; // Different assignment
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>
    }</fold>

    // Test for cast assignment
    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> CastAssignmentTest <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> positiveTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>o;
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>

        public <fold text='💀' expand='false'>void</fold> negativeTest(Object o) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>o <fold text='is' expand='false'>instanceof</fold> String<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>String</fold> s = <fold text='' expand='false'>(String) </fold>"Hello"; // Cast of a different object
                <fold text='' expand='false'>System.out.</fold>println(s.length());
            }</fold>
        }</fold>
    }</fold>
}