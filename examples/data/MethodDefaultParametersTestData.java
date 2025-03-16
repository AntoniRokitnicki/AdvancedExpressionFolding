package data;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testMethodDefaultParametersTestData()}
 */
public class MethodDefaultParametersTestData {

    class OverloadedMethodAsDefaultParam {

        public String applySort1(String criterionName) {
            return applySort1(criterionName, false);
        }
        public String applySort1(String criterionName, boolean descending) {
            return criterionName;
        }
        public String applySort1() {
            return applySort1("DESC", false);
        }


        public String applySort2(String criterionName, boolean descending) {
            return criterionName;
        }
        public String applySort2(String criterionName) {
            return applySort2(criterionName, System.getProperty("sort-desc") != null);
        }


        public String applySortWrongFirstType(int criterionName) {
            return applySortWrongFirstType(String.valueOf(criterionName), false);
        }
        public String applySortWrongFirstType(String criterionName, boolean descending) {
            return criterionName;
        }


        private String applySort4(String criterionName) {
            return applySort2(criterionName, false);
        }
        protected String applySort4(String criterionName, boolean descending) {
            return criterionName;
        }


    }
}
