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

    class MultipleDefaultParams {
        public String multipleDefaults(String name, int count, boolean flag) {
            return name + count + flag;
        }
        public String multipleDefaults(String name, int count) {
            return multipleDefaults(name, count, false);
        }
        public String multipleDefaults(String name) {
            return multipleDefaults(name, 10, false);
        }
    }

    class ChainedDefaults {
        public String chainedDefaults(String name, int count, boolean flag) {
            return name + count + flag;
        }
        public String chainedDefaults(String name, int count) {
            return chainedDefaults(name, count, true);
        }
        public String chainedDefaults(String name) {
            return chainedDefaults(name, 5);
        }
    }

    class ExpressionDefaults {
        public String expressionDefaults(String name, int count) {
            return name + count;
        }
        public String expressionDefaults(String name) {
            return expressionDefaults(name, "test".length() + 2);
        }
    }

    class StaticDefaults {
        public static String staticWithDefaults(String name, boolean flag) {
            return name + flag;
        }
        public static String staticWithDefaults(String name) {
            return staticWithDefaults(name, true);
        }
    }

    class DifferentReturnTypes {
        public String differentReturns(int value) {
            return String.valueOf(value);
        }
        public int differentReturns(int value, boolean asNumber) {
            return Integer.parseInt(differentReturns(value));
        }
    }

    class MixedParameterTypes {
        public String mixedTypes(String text, int number, boolean flag, double value) {
            return text + number + flag + value;
        }
        public String mixedTypes(String text, int number, boolean flag) {
            return mixedTypes(text, number, flag, 1.0);
        }
        public String mixedTypes(String text, int number) {
            return mixedTypes(text, number, false);
        }
        public String mixedTypes(String text) {
            return mixedTypes(text, 0);
        }
    }

    class VarargDefaults {
        public String varargMethod(String prefix, String... items) {
            return prefix + String.join(",", items);
        }
        public String varargMethod(String prefix) {
            return varargMethod(prefix, "default");
        }
    }

    class DifferentParameterNames {
        public String differentParamNames(String name, boolean enabled) {
            return name + enabled;
        }
        public String differentParamNames(String title) {
            return differentParamNames(title, true);
        }
    }

    class GenericMethodDefaults {
        public <T> String genericMethod(T item, boolean flag) {
            return item.toString() + flag;
        }
        public <T> String genericMethod(T item) {
            return genericMethod(item, false);
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