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

    class MultipleDefaultParams {
        public String multipleDefaults(String name, int count = 10, boolean flag = false) {
            return name + count + flag;
        }
        public String multipleDefaults(String name, int count) {
            return multipleDefaults(name, count, false);
        }
        
    }

    class ChainedDefaults {
        public String chainedDefaults(String name, int count, boolean flag = true) {
            return name + count + flag;
        }
        
        public String chainedDefaults(String name) {
            return chainedDefaults(name, 5);
        }
    }

    class ExpressionDefaults {
        public String expressionDefaults(String name, int count = "test".length() + 2) {
            return name + count;
        }
        
    }

    class StaticDefaults {
        public static String staticWithDefaults(String name, boolean flag = true) {
            return name + flag;
        }
        
    }

    class DifferentReturnTypes {
        public String differentReturns(int value) {
            return value;
        }
        public int differentReturns(int value, boolean asNumber) {
            return Integer.parseInt(differentReturns(value));
        }
    }

    class MixedParameterTypes {
        public String mixedTypes(String text, int number, boolean flag, double value = 1.0) {
            return text + number + flag + value;
        }
        
        public String mixedTypes(String text, int number) {
            return mixedTypes(text, number, false);
        }
        public String mixedTypes(String text) {
            return mixedTypes(text, 0);
        }
    }

    class VarargDefaults {
        public String varargMethod(String prefix, String... items = "default") {
            return prefix + String.join(",", items);
        }
        
    }

    class DifferentParameterNames {
        public String differentParamNames(String name, boolean enabled = true) {
            return name + enabled;
        }
        
    }

    class GenericMethodDefaults {
        public <T> String genericMethod(T item, boolean flag = false) {
            return item + flag;
        }
        
    }

    class ComplexBodyCase {
        public String complexBody(String text, boolean flag) {
            return text + flag;
        }
        public String complexBody(String text) {
            if (text.isEmpty()) {
                return "";
            }
            return complexBody(text.trim(), text.length() > 5);
        }
    }
}