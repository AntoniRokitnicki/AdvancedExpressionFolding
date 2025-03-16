package data;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */
public class ExperimentalTestData {

    class OverloadedAsDefaultParam {

        public String applySort1(String criterionName) {
            return applySort1(criterionName, false);
        }
        public String applySort1(String criterionName = "DESC", boolean descending = false) {
            return criterionName;
        }
        


        public String applySort2(String criterionName, boolean descending = System.getProperty("sort-desc") != null) {
            return criterionName;
        }
        


        public String applySortWrongFirstType(int criterionName) {
            return applySortWrongFirstType(criterionName, false);
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
