package data;

import java.util.*;

public class ConstructorReferenceNotationWithConstTestData {

    static class Const {
        [1:"public static final"] ConstClass SELF =[0:" "]new[0:" "][0:"ConstClass"][0:"()"];
        [3:"public static final"] ConstClass SELF_ANN =[2:" "]new[2:" "][2:"ConstClass() {\n        }"];
        [4:"public static final"] ConstClass SELF_SUB = new SubConstClass();
        [5:"public static final"] ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private [7:"static final"] HashMap<String, String> MAP =[6:" "]new[6:" "][6:"HashMap<>"][6:"()"];
        private [9:"static final"] HashMap<String, String> MAP2 =[8:" "]new[8:" "][8:"HashMap<String, String>"][8:"()"];
        private [10:"static final"] Map<String, String> MAP3 = new HashMap<>();
        private [11:"static final"] Map<String, String> MAP_TREE = new TreeMap<>();
        private [12:"static final"] Map<String, String> MAP4 = Map.of();

        private [13:"static final"] List<String> LIST = [14:"new ArrayList<>()"];
        private [15:"static final"] List<String> LIST2 = List.of();
        private [16:"static final"] List<String> LIST_SINGLE = List.of("1");
        private [17:"static final"] List<String> LIST_LINKED = new LinkedList<>();


        [19:"public static final"] ConstClass SELF_PARAM_1 =[18:" "]new[18:" "][18:"ConstClass"](true);
        [22:"public static final"] ConstClass SELF_PARAM_2 =[20:" "]new[20:" "][20:"ConstClass"](false, LIST_SINGLE[21:".get"]([21:"0"]));

        [24:"public static final"] ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        [25:"public static final"] ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        protected static ConstClass SELF_NULL = null;
        protected static ConstClass EMPTY;
    }
    static class Fields {
        final ConstClass SELF =[26:" "]new[26:" "][26:"ConstClass"][26:"()"];
        ConstClass SELF_ANN =[27:" "]new[27:" "][27:"ConstClass() {\n        }"];
        public final ConstClass SELF_SUB = new SubConstClass();
        public final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private final HashMap<String, String> MAP =[28:" "]new[28:" "][28:"HashMap<>"][28:"()"];
        private final HashMap<String, String> MAP2 =[29:" "]new[29:" "][29:"HashMap<String, String>"][29:"()"];
        private final Map<String, String> MAP3 = new HashMap<>();

        private final List<String> LIST = [30:"new ArrayList<>()"];
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 =[31:" "]new[31:" "][31:"ConstClass"](true);
        public final ConstClass SELF_PARAM_2 =[32:" "]new[32:" "][32:"ConstClass"](false, LIST2[33:".get"]([33:"0"]));

        public final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        protected ConstClass SELF_NULL = null;
        protected ConstClass EMPTY;
    }

    [35:"s"]tatic class ConstClass {
        [36:"b"]oolean ok;
        [37:"S"]tring string;[35:"\n\n        "][35:"public ConstClass() {\n        }"][36:"\n\n        "][36:"public ConstClass(boolean ok) {\n            this.ok = ok;\n        }"][35:"\n\n        "][35:"public ConstClass(boolean ok, String string) {\n            this.ok = ok;\n            this.string = string;\n        }"][37:"\n\n        "][47:"@Override"][47:"\n        "]public String toString() {
            return new StringJoiner(", ",[48:" "]ConstClass.class.[49:"getSimpleName()"][48:" + \""][", "]")
                    .add("string='[50:"\" + "]string[50:" + \""]'")
                    .toString();
        }
    }

    static class SubConstClass extends ConstClas[54:"s"] {
    }

    static [56:"final"] class SubConstClass2 extends ConstClas[55:"s"] {
    }
}
