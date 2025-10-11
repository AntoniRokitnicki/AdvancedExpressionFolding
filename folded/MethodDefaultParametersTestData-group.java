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
        [0:"public String applySort1() {
            return applySort1("DESC", false);
        }"]

        public String applySort2(String criterionName, boolean descending[0:")"] {
            return criterionName;
        }
        [0:"public String applySort2(String criterionName) {
            return applySort2(criterionName, System.getProperty("sort-desc") != null);
        }"]

        public String applySortWrongFirstType(int criterionName) {
            return applySortWrongFirstType([0:"String.valueOf("]{[0:"String.valueOf("]}criterionName[0:")"]{[0:")"]}, false);
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
        public String multipleDefaults(String name, int count[0:","] boolean flag[0:")"] {
            return name + count + flag;
        }
        public String multipleDefaults(String name, int count) {
            return multipleDefaults(name, count, false);
        }
        [0:"public String multipleDefaults(String name) {
            return multipleDefaults(name, 10, false);
        }"]
    }

    class ChainedDefaults {
        public String chainedDefaults(String name, int count, boolean flag[0:")"] {
            return name + count + flag;
        }
        [0:"public String chainedDefaults(String name, int count) {
            return chainedDefaults(name, count, true);
        }"]
        public String chainedDefaults(String name) {
            return chainedDefaults(name, 5);
        }
    }

    class ExpressionDefaults {
        public String expressionDefaults(String name, int count[0:")"] {
            return name + count;
        }
        [0:"public String expressionDefaults(String name) {
            return expressionDefaults(name, "test".length() + 2);
        }"]
    }

    class StaticDefaults {
        public static String staticWithDefaults(String name, boolean flag[0:")"] {
            return name + flag;
        }
        [0:"public static String staticWithDefaults(String name) {
            return staticWithDefaults(name, true);
        }"]
    }

    class DifferentReturnTypes {
        public String differentReturns(int value) {
            return [0:"String.valueOf("]{[0:"String.valueOf("]}value[0:")"]{[0:")"]};
        }
        public int differentReturns(int value, boolean asNumber) {
            return Integer.parseInt(differentReturns(value));
        }
    }

    class MixedParameterTypes {
        public String mixedTypes(String text, int number, boolean flag, double value[0:")"] {
            return text + number + flag + value;
        }
        [0:"public String mixedTypes(String text, int number, boolean flag) {
            return mixedTypes(text, number, flag, 1.0);
        }"]
        public String mixedTypes(String text, int number) {
            return mixedTypes(text, number, false);
        }
        public String mixedTypes(String text) {
            return mixedTypes(text, 0);
        }
    }

    class VarargDefaults {
        public String varargMethod(String prefix, String... items[0:")"] {
            return prefix + String.join(",", items);
        }
        [0:"public String varargMethod(String prefix) {
            return varargMethod(prefix, "default");
        }"]
    }

    class DifferentParameterNames {
        public String differentParamNames(String name, boolean enabled[0:")"] {
            return name + enabled;
        }
        [0:"public String differentParamNames(String title) {
            return differentParamNames(title, true);
        }"]
    }

    class GenericMethodDefaults {
        public <T> String genericMethod(T item, boolean flag[0:")"] {
            return item[0:".toString()"]{[0:".toString()"]} + flag;
        }
        [0:"public <T> String genericMethod(T item) {
            return genericMethod(item, false);
        }"]
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