<fold text='📦' expand='false'>package</fold> data;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testMethodDefaultParametersTestData()}
 */</fold>
public <fold text='🏛️' expand='false'>class</fold> MethodDefaultParametersTestData {

    <fold text='🏛️' expand='false'>class</fold> OverloadedMethodAsDefaultParam <fold text='{...}' expand='true'>{

        public String applySort1(String criterionName)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort1(criterionName, <fold text='❌' expand='false'>false</fold>)<fold text='' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>
        public String applySort1(String criterionName<fold text=' = "DESC",' expand='true'>,</fold> <fold text='🔘' expand='false'>boolean</fold> descending<fold text=' = false)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String applySort1()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort1("DESC", <fold text='❌' expand='false'>false</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>


        public String applySort2(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending<fold text=' = System.getProperty("sort-desc") != null)' expand='true'>)</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='' expand='true'>public String applySort2(String criterionName) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort2(criterionName, System<fold text='[' expand='false'>.getProperty(</fold>"sort-desc"<fold text=']' expand='false'>)</fold> != <fold text='🕳️' expand='false'>null</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold></fold>


        public String applySortWrongFirstType(<fold text='🔢' expand='false'>int</fold> criterionName) <fold text='{...}' expand='true'>{<fold text=' ' expand='true'>
            </fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>applySortWrongFirstType(<fold text='' expand='false'>String.valueOf(</fold>criterionName<fold text='' expand='false'>)</fold>, <fold text='❌' expand='false'>false</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'>
        </fold>}</fold>
        public String applySortWrongFirstType(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>


        <fold text='🚫' expand='false'>private</fold> String applySort4(String criterionName)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>applySort2(criterionName, <fold text='❌' expand='false'>false</fold>)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        <fold text='🛡️' expand='false'>protected</fold> String applySort4(String criterionName, <fold text='🔘' expand='false'>boolean</fold> descending)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>criterionName<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>


    }</fold>
}
