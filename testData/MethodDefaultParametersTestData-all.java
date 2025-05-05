package data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testMethodDefaultParametersTestData()}
 */</fold>
public class MethodDefaultParametersTestData {

    class OverloadedMethodAsDefaultParam <fold text='{...}' expand='true'>{
        public String applySort1(String criterionName)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort1(criterionName, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public String applySort1(String criterionName<fold text=' = "DESC",' expand='true'>,</fold> boolean descending<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String applySort1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort1("DESC", false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public String applySort2(String criterionName, boolean descending<fold text=' = System.getProperty("sort-desc") != null)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String applySort2(String criterionName) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort2(criterionName, System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold> != null)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold></fold>

        public String applySortWrongFirstType(int criterionName) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySortWrongFirstType(<fold text='' expand='false'>String.valueOf(</fold>criterionName<fold text='' expand='false'>)</fold>, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>
        public String applySortWrongFirstType(String criterionName, boolean descending)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        private String applySort4(String criterionName)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>applySort2(criterionName, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        protected String applySort4(String criterionName, boolean descending)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    class MultipleDefaultParams <fold text='{...}' expand='true'>{
        public String multipleDefaults(String name, int count<fold text=' = 10,' expand='true'>,</fold> boolean flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>name + count + flag<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public String multipleDefaults(String name, int count)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>multipleDefaults(name, count, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String multipleDefaults(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>multipleDefaults(name, 10, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class ChainedDefaults <fold text='{...}' expand='true'>{
        public String chainedDefaults(String name, int count, boolean flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>name + count + flag<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String chainedDefaults(String name, int count)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>chainedDefaults(name, count, true)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
        public String chainedDefaults(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>chainedDefaults(name, 5)<fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>
    }</fold>

    class ExpressionDefaults <fold text='{...}' expand='true'>{
        public String expressionDefaults(String name, int count<fold text=' = "test".length() + 2)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name + count<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String expressionDefaults(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>expressionDefaults(name, "test".length() + 2)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class StaticDefaults <fold text='{...}' expand='true'>{
        public static String staticWithDefaults(String name, boolean flag<fold text=' = true)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name + flag<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public static String staticWithDefaults(String name)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>staticWithDefaults(name, true)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class DifferentReturnTypes <fold text='{...}' expand='true'>{
        public String differentReturns(int value)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>String.valueOf(</fold>value<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public int differentReturns(int value, boolean asNumber)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Integer.parseInt(differentReturns(value))<fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>
    }</fold>

    class MixedParameterTypes <fold text='{...}' expand='true'>{
        public String mixedTypes(String text, int number, boolean flag, double value<fold text=' = 1.0)' expand='true'>)</fold> <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>text + number + flag + value<fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>
        <fold text='' expand='true'>public String mixedTypes(String text, int number, boolean flag)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>mixedTypes(text, number, flag, 1.0)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
        public String mixedTypes(String text, int number)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>mixedTypes(text, number, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public String mixedTypes(String text)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>mixedTypes(text, 0)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    class VarargDefaults <fold text='{...}' expand='true'>{
        public String varargMethod(String prefix, String... items<fold text=' = "default")' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prefix + String.join(",", items)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String varargMethod(String prefix)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>varargMethod(prefix, "default")<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class DifferentParameterNames <fold text='{...}' expand='true'>{
        public String differentParamNames(String name, boolean enabled<fold text=' = true)' expand='true'>)<fold text=' { ' expand='false'></fold> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name + enabled<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String differentParamNames(String title)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>differentParamNames(title, true)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class GenericMethodDefaults <fold text='{...}' expand='true'>{
        public <T> String genericMethod(T item, boolean flag<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>item<fold text='' expand='false'>.toString()</fold> + flag<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public <T> String genericMethod(T item)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>genericMethod(item, false)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    class ComplexBodyCase <fold text='{...}' expand='true'>{
        public String complexBody(String text, boolean flag)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>text + flag<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public String complexBody(String text) <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold>text.<fold text='empty' expand='false'>isEmpty()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                return "";
            }</fold>
            return complexBody(text.trim(), text.length() > 5);
        }</fold>
    }</fold>
}