package data;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getMethodDefaultParameters()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testMethodDefaultParametersTestData()}
 */
public class MethodDefaultParametersTestData {

    class OverloadedMethodAsDefaultParam {
        public String applySort1(String criterionName) {[2:"\n            "][2:"return"][2:" "]applySort1(criterionName, false)[2:";"][2:"\n        "]}
        public String applySort1(String criterionName[0:","] boolean descending[0:")"] {[3:"\n            "][3:"return"][3:" "]criterionName[3:";"][3:"\n        "]}
        [0:"public String applySort1() {\n            return applySort1(\"DESC\", false);\n        }"]

        public String applySort2(String criterionName, boolean descending[1:")"] {[5:"\n            "][5:"return"][5:" "]criterionName[5:";"][5:"\n        "]}
        [1:"public String applySort2(String criterionName) {\n            return applySort2(criterionName, System.getProperty(\"sort-desc\") != null);\n        }"]

        public String applySortWrongFirstType(int criterionName) {[9:"\n            "][9:"return"][9:" "]applySortWrongFirstType([10:"String.valueOf("]criterionName[10:")"], false)[9:";"][9:"\n        "]}
        public String applySortWrongFirstType(String criterionName, boolean descending) {[12:"\n            "][12:"return"][12:" "]criterionName[12:";"][12:"\n        "]}

        private String applySort4(String criterionName) {[13:"\n            "][13:"return"][13:" "]applySort2(criterionName, false)[13:";"][13:"\n        "]}
        protected String applySort4(String criterionName, boolean descending) {[14:"\n            "][14:"return"][14:" "]criterionName[14:";"][14:"\n        "]}
    }

    class MultipleDefaultParams {
        public String multipleDefaults(String name, int count[15:","] boolean flag[15:")"] {[16:"\n            "][16:"return"][16:" "]name + count + flag[16:";"][16:"\n        "]}
        public String multipleDefaults(String name, int count) {[17:"\n            "][17:"return"][17:" "]multipleDefaults(name, count, false)[17:";"][17:"\n        "]}
        [15:"public String multipleDefaults(String name) {\n            return multipleDefaults(name, 10, false);\n        }"]
    }

    class ChainedDefaults {
        public String chainedDefaults(String name, int count, boolean flag[19:")"] {[20:"\n            "][20:"return"][20:" "]name + count + flag[20:";"][20:"\n        "]}
        [19:"public String chainedDefaults(String name, int count) {\n            return chainedDefaults(name, count, true);\n        }"]
        public String chainedDefaults(String name) {[22:"\n            "][22:"return"][22:" "]chainedDefaults(name, 5)[22:";"][22:"\n        "]}
    }

    class ExpressionDefaults {
        public String expressionDefaults(String name, int count[23:")"] {[24:"\n            "][24:"return"][24:" "]name + count[24:";"][24:"\n        "]}
        [23:"public String expressionDefaults(String name) {\n            return expressionDefaults(name, \"test\".length() + 2);\n        }"]
    }

    class StaticDefaults {
        public static String staticWithDefaults(String name, boolean flag[26:")"] {[27:"\n            "][27:"return"][27:" "]name + flag[27:";"][27:"\n        "]}
        [26:"public static String staticWithDefaults(String name) {\n            return staticWithDefaults(name, true);\n        }"]
    }

    class DifferentReturnTypes {
        public String differentReturns(int value) {[29:"\n            "][29:"return"][29:" "][30:"String.valueOf("]value[30:")"][29:";"][29:"\n        "]}
        public int differentReturns(int value, boolean asNumber) {[32:"\n            "][32:"return"][32:" "]Integer.parseInt(differentReturns(value))[32:";"][32:"\n        "]}
    }

    class MixedParameterTypes {
        public String mixedTypes(String text, int number, boolean flag, double value[33:")"] {[34:"\n            "][34:"return"][34:" "]text + number + flag + value[34:";"][34:"\n        "]}
        [33:"public String mixedTypes(String text, int number, boolean flag) {\n            return mixedTypes(text, number, flag, 1.0);\n        }"]
        public String mixedTypes(String text, int number) {[36:"\n            "][36:"return"][36:" "]mixedTypes(text, number, false)[36:";"][36:"\n        "]}
        public String mixedTypes(String text) {[37:"\n            "][37:"return"][37:" "]mixedTypes(text, 0)[37:";"][37:"\n        "]}
    }

    class VarargDefaults {
        public String varargMethod(String prefix, String... items[38:")"] {[39:"\n            "][39:"return"][39:" "]prefix + String.join(",", items)[39:";"][39:"\n        "]}
        [38:"public String varargMethod(String prefix) {\n            return varargMethod(prefix, \"default\");\n        }"]
    }

    class DifferentParameterNames {
        public String differentParamNames(String name, boolean enabled[41:")"] {[42:"\n            "][42:"return"][42:" "]name + enabled[42:";"][42:"\n        "]}
        [41:"public String differentParamNames(String title) {\n            return differentParamNames(title, true);\n        }"]
    }

    class GenericMethodDefaults {
        public <T> String genericMethod(T item, boolean flag[44:")"] {[45:"\n            "][45:"return"][45:" "]item[46:".toString()"] + flag[45:";"][45:"\n        "]}
        [44:"public <T> String genericMethod(T item) {\n            return genericMethod(item, false);\n        }"]
    }

    class ComplexBodyCase {
        public String complexBody(String text, boolean flag) {[49:"\n            "][49:"return"][49:" "]text + flag[49:";"][49:"\n        "]}
        public String complexBody(String text) {
            if [50:"("]text.[52:"isEmpty()"][50:")"] {
                return "";
            }
            return complexBody(text.trim(), text.length() > 5);
        }
    }
}