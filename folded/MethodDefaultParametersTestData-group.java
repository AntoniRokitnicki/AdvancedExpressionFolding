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
        public String applySort1(String criterionName[0:","] boolean descending[0:")"] {
            return criterionName;
        }
        [0:"public String applySort1() {\n            return applySort1(\"DESC\", false);\n        }"]

        public String applySort2(String criterionName, boolean descending[1:")"] {
            return criterionName;
        }
        [1:"public String applySort2(String criterionName) {\n            return applySort2(criterionName, System.getProperty(\"sort-desc\") != null);\n        }"]

        public String applySortWrongFirstType(int criterionName) {
            return applySortWrongFirstType([2:"String.valueOf("]criterionName[2:")"], false);
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
        public String multipleDefaults(String name, int count[4:","] boolean flag[4:")"] {
            return name + count + flag;
        }
        public String multipleDefaults(String name, int count) {
            return multipleDefaults(name, count, false);
        }
        [4:"public String multipleDefaults(String name) {\n            return multipleDefaults(name, 10, false);\n        }"]
    }

    class ChainedDefaults {
        public String chainedDefaults(String name, int count, boolean flag[5:")"] {
            return name + count + flag;
        }
        [5:"public String chainedDefaults(String name, int count) {\n            return chainedDefaults(name, count, true);\n        }"]
        public String chainedDefaults(String name) {
            return chainedDefaults(name, 5);
        }
    }

    class ExpressionDefaults {
        public String expressionDefaults(String name, int count[6:")"] {
            return name + count;
        }
        [6:"public String expressionDefaults(String name) {\n            return expressionDefaults(name, \"test\".length() + 2);\n        }"]
    }

    class StaticDefaults {
        public static String staticWithDefaults(String name, boolean flag[7:")"] {
            return name + flag;
        }
        [7:"public static String staticWithDefaults(String name) {\n            return staticWithDefaults(name, true);\n        }"]
    }

    class DifferentReturnTypes {
        public String differentReturns(int value) {
            return [8:"String.valueOf("]value[8:")"];
        }
        public int differentReturns(int value, boolean asNumber) {
            return Integer.parseInt(differentReturns(value));
        }
    }

    class MixedParameterTypes {
        public String mixedTypes(String text, int number, boolean flag, double value[10:")"] {
            return text + number + flag + value;
        }
        [10:"public String mixedTypes(String text, int number, boolean flag) {\n            return mixedTypes(text, number, flag, 1.0);\n        }"]
        public String mixedTypes(String text, int number) {
            return mixedTypes(text, number, false);
        }
        public String mixedTypes(String text) {
            return mixedTypes(text, 0);
        }
    }

    class VarargDefaults {
        public String varargMethod(String prefix, String... items[11:")"] {
            return prefix + String.join(",", items);
        }
        [11:"public String varargMethod(String prefix) {\n            return varargMethod(prefix, \"default\");\n        }"]
    }

    class DifferentParameterNames {
        public String differentParamNames(String name, boolean enabled[12:")"] {
            return name + enabled;
        }
        [12:"public String differentParamNames(String title) {\n            return differentParamNames(title, true);\n        }"]
    }

    class GenericMethodDefaults {
        public <T> String genericMethod(T item, boolean flag[13:")"] {
            return item[14:".toString()"] + flag;
        }
        [13:"public <T> String genericMethod(T item) {\n            return genericMethod(item, false);\n        }"]
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