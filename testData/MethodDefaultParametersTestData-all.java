<fold text='📦' expand='false'>package</fold> data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testMethodDefaultParametersTestData()}
 */</fold>
public <fold text='🏛️' expand='false'>class</fold> MethodDefaultParametersTestData {

    <fold text='🏛️' expand='false'>class</fold> OverloadedMethodAsDefaultParam <fold text='{...}' expand='true'>{
        public String applySort1(String criterionName)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> applySort1(criterionName, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold>
        public String applySort1(String criterionName<fold text=' = "DESC",' expand='true'>,</fold> <fold text='🔘' expand='false'>boolean</fold> descending<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> criterionName;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String applySort1()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> applySort1("DESC", <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold></fold>

        public String applySort2(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending<fold text=' = System.getProperty("sort-desc") != null)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> criterionName;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String applySort2(String criterionName) <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> applySort2(criterionName, System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold> != <fold text='🕳️' expand='false'>null</fold>);
        }</fold></fold>

        public String applySortWrongFirstType(<fold text='🔢' expand='false'>int</fold> criterionName) <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> applySortWrongFirstType(<fold text='' expand='false'>String.valueOf(</fold>criterionName<fold text='' expand='false'>)</fold>, <fold text='❌' expand='false'>false</fold>);
        }</fold>
        public String applySortWrongFirstType(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> criterionName;<fold text=' }' expand='false'>
        }</fold>

        <fold text='🚫' expand='false'>private</fold> String applySort4(String criterionName)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> applySort2(criterionName, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold>
        <fold text='🛡️' expand='false'>protected</fold> String applySort4(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> criterionName;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> MultipleDefaultParams <fold text='{...}' expand='true'>{
        public String multipleDefaults(String name, <fold text='🔢' expand='false'>int</fold> count<fold text=' = 10,' expand='true'>,</fold> <fold text='🔘' expand='false'>boolean</fold> flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> name + count + flag;<fold text=' }' expand='false'>
        }</fold>
        public String multipleDefaults(String name, <fold text='🔢' expand='false'>int</fold> count)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> multipleDefaults(name, count, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String multipleDefaults(String name)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> multipleDefaults(name, 10, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> ChainedDefaults <fold text='{...}' expand='true'>{
        public String chainedDefaults(String name, <fold text='🔢' expand='false'>int</fold> count, <fold text='🔘' expand='false'>boolean</fold> flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> name + count + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String chainedDefaults(String name, <fold text='🔢' expand='false'>int</fold> count)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> chainedDefaults(name, count, <fold text='✅' expand='false'>true</fold>);<fold text=' }' expand='false'>
        }</fold></fold>
        public String chainedDefaults(String name)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> chainedDefaults(name, 5);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> ExpressionDefaults <fold text='{...}' expand='true'>{
        public String expressionDefaults(String name, <fold text='🔢' expand='false'>int</fold> count<fold text=' = "test".length() + 2)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> name + count;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String expressionDefaults(String name)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> expressionDefaults(name, "test".length() + 2);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> StaticDefaults <fold text='{...}' expand='true'>{
        public <fold text='⚡' expand='false'>static</fold> String staticWithDefaults(String name, <fold text='🔘' expand='false'>boolean</fold> flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> name + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public <fold text='⚡' expand='false'>static</fold> String staticWithDefaults(String name)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> staticWithDefaults(name, <fold text='✅' expand='false'>true</fold>);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> DifferentReturnTypes <fold text='{...}' expand='true'>{
        public String differentReturns(<fold text='🔢' expand='false'>int</fold> value)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> <fold text='' expand='false'>String.valueOf(</fold>value<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
        public <fold text='🔢' expand='false'>int</fold> differentReturns(<fold text='🔢' expand='false'>int</fold> value, <fold text='🔘' expand='false'>boolean</fold> asNumber)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> Integer.parseInt(differentReturns(value));<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> MixedParameterTypes <fold text='{...}' expand='true'>{
        public String mixedTypes(String text, <fold text='🔢' expand='false'>int</fold> number, <fold text='🔘' expand='false'>boolean</fold> flag, <fold text='⚖️' expand='false'>double</fold> value<fold text=' = 1.0)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> text + number + flag + value;
        }</fold>
        <fold text='' expand='true'>public String mixedTypes(String text, <fold text='🔢' expand='false'>int</fold> number, <fold text='🔘' expand='false'>boolean</fold> flag)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> mixedTypes(text, number, flag, 1.0);<fold text=' }' expand='false'>
        }</fold></fold>
        public String mixedTypes(String text, <fold text='🔢' expand='false'>int</fold> number)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> mixedTypes(text, number, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold>
        public String mixedTypes(String text)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> mixedTypes(text, 0);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> VarargDefaults <fold text='{...}' expand='true'>{
        public String varargMethod(String prefix, String... items<fold text=' = "default")' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> prefix + String.join(",", items);<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String varargMethod(String prefix)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> varargMethod(prefix, "default");<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> DifferentParameterNames <fold text='{...}' expand='true'>{
        public String differentParamNames(String name, <fold text='🔘' expand='false'>boolean</fold> enabled<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> name + enabled;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String differentParamNames(String title)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> differentParamNames(title, <fold text='✅' expand='false'>true</fold>);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> GenericMethodDefaults <fold text='{...}' expand='true'>{
        public <T> String genericMethod(T item, <fold text='🔘' expand='false'>boolean</fold> flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> item<fold text='' expand='false'>.toString()</fold> + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public <T> String genericMethod(T item)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> genericMethod(item, <fold text='❌' expand='false'>false</fold>);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> ComplexBodyCase <fold text='{...}' expand='true'>{
        public String complexBody(String text, <fold text='🔘' expand='false'>boolean</fold> flag)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> text + flag;<fold text=' }' expand='false'>
        }</fold>
        public String complexBody(String text) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>text.<fold text='empty' expand='false'>isEmpty()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='🔙' expand='false'>return</fold> "";
            }</fold>
            <fold text='🔙' expand='false'>return</fold> complexBody(text.trim(), text.length() > 5);
        }</fold>
    }</fold>
}