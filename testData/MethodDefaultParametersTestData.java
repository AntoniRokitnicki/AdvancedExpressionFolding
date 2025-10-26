package data;

public class MethodDefaultParametersTestData {

    class OverloadedMethodAsDefaultParam <fold text='{...}' expand='true'>{
        public String applySort1(String criterionName)<fold text=' { ' expand='false'> {
            </fold>return applySort1(criterionName, false);<fold text=' }' expand='false'>
        }</fold>
        public String applySort1(String criterionName<fold text=' = "DESC",' expand='true'>,</fold> boolean descending<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return criterionName;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String applySort1()<fold text=' { ' expand='false'> {
            </fold>return applySort1("DESC", false);<fold text=' }' expand='false'>
        }</fold></fold>

        public String applySort2(String criterionName, boolean descending<fold text=' = System.getProperty("sort-desc") != null)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return criterionName;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String applySort2(String criterionName) <fold text='{...}' expand='true'>{
            return applySort2(criterionName, System.getProperty("sort-desc") != null);
        }</fold></fold>

        public String applySortWrongFirstType(int criterionName) <fold text='{...}' expand='true'>{
            return applySortWrongFirstType(<fold text='' expand='false'>String.valueOf(</fold>criterionName<fold text='' expand='false'>)</fold>, false);
        }</fold>
        public String applySortWrongFirstType(String criterionName, boolean descending)<fold text=' { ' expand='false'> {
            </fold>return criterionName;<fold text=' }' expand='false'>
        }</fold>

        private String applySort4(String criterionName)<fold text=' { ' expand='false'> {
            </fold>return applySort2(criterionName, false);<fold text=' }' expand='false'>
        }</fold>
        protected String applySort4(String criterionName, boolean descending)<fold text=' { ' expand='false'> {
            </fold>return criterionName;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class MultipleDefaultParams <fold text='{...}' expand='true'>{
        public String multipleDefaults(String name, int count<fold text=' = 10,' expand='true'>,</fold> boolean flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return name + count + flag;<fold text=' }' expand='false'>
        }</fold>
        public String multipleDefaults(String name, int count)<fold text=' { ' expand='false'> {
            </fold>return multipleDefaults(name, count, false);<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String multipleDefaults(String name)<fold text=' { ' expand='false'> {
            </fold>return multipleDefaults(name, 10, false);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class ChainedDefaults <fold text='{...}' expand='true'>{
        public String chainedDefaults(String name, int count, boolean flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return name + count + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String chainedDefaults(String name, int count)<fold text=' { ' expand='false'> {
            </fold>return chainedDefaults(name, count, true);<fold text=' }' expand='false'>
        }</fold></fold>
        public String chainedDefaults(String name)<fold text=' { ' expand='false'> {
            </fold>return chainedDefaults(name, 5);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class ExpressionDefaults <fold text='{...}' expand='true'>{
        public String expressionDefaults(String name, int count<fold text=' = "test".length() + 2)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return name + count;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String expressionDefaults(String name)<fold text=' { ' expand='false'> {
            </fold>return expressionDefaults(name, "test".length() + 2);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class StaticDefaults <fold text='{...}' expand='true'>{
        public static String staticWithDefaults(String name, boolean flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return name + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public static String staticWithDefaults(String name)<fold text=' { ' expand='false'> {
            </fold>return staticWithDefaults(name, true);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class DifferentReturnTypes <fold text='{...}' expand='true'>{
        public String differentReturns(int value)<fold text=' { ' expand='false'> {
            </fold>return <fold text='' expand='false'>String.valueOf(</fold>value<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
        public int differentReturns(int value, boolean asNumber)<fold text=' { ' expand='false'> {
            </fold>return Integer.parseInt(differentReturns(value));<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class MixedParameterTypes <fold text='{...}' expand='true'>{
        public String mixedTypes(String text, int number, boolean flag, double value<fold text=' = 1.0)' expand='true'>)</fold> <fold text='{...}' expand='true'>{
            return text + number + flag + value;
        }</fold>
        <fold text='' expand='true'>public String mixedTypes(String text, int number, boolean flag)<fold text=' { ' expand='false'> {
            </fold>return mixedTypes(text, number, flag, 1.0);<fold text=' }' expand='false'>
        }</fold></fold>
        public String mixedTypes(String text, int number)<fold text=' { ' expand='false'> {
            </fold>return mixedTypes(text, number, false);<fold text=' }' expand='false'>
        }</fold>
        public String mixedTypes(String text)<fold text=' { ' expand='false'> {
            </fold>return mixedTypes(text, 0);<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class VarargDefaults <fold text='{...}' expand='true'>{
        public String varargMethod(String prefix, String... items<fold text=' = "default")' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return prefix + String.join(",", items);<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String varargMethod(String prefix)<fold text=' { ' expand='false'> {
            </fold>return varargMethod(prefix, "default");<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class DifferentParameterNames <fold text='{...}' expand='true'>{
        public String differentParamNames(String name, boolean enabled<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return name + enabled;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public String differentParamNames(String title)<fold text=' { ' expand='false'> {
            </fold>return differentParamNames(title, true);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class GenericMethodDefaults <fold text='{...}' expand='true'>{
        public <T> String genericMethod(T item, boolean flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {
            </fold>return item<fold text='' expand='false'>.toString()</fold> + flag;<fold text=' }' expand='false'>
        }</fold>
        <fold text='' expand='true'>public <T> String genericMethod(T item)<fold text=' { ' expand='false'> {
            </fold>return genericMethod(item, false);<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>

    class ComplexBodyCase <fold text='{...}' expand='true'>{
        public String complexBody(String text, boolean flag)<fold text=' { ' expand='false'> {
            </fold>return text + flag;<fold text=' }' expand='false'>
        }</fold>
        public String complexBody(String text) <fold text='{...}' expand='true'>{
            if (text.isEmpty()) <fold text='{...}' expand='true'>{
                return "";
            }</fold>
            return complexBody(text.trim(), text.length() > 5);
        }</fold>
    }</fold>
}