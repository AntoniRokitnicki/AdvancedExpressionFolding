package data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
public class ExperimentalTestData {

    class OverloadedAsDefaultParam <fold text='{...}' expand='true'>{

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
            return applySort2(criterionName, System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold> != null);
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
}
