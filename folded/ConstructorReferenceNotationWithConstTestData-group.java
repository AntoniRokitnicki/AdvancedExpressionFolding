package data;

import java.util.*;

public class ConstructorReferenceNotationWithConstTestData {

    static class Const {
        [1:"public static final"] ConstClass SELF =[0:" "]new[0:" "][0:"ConstClass"][0:"()"];
        [3:"public static final"] ConstClass SELF_ANN =[2:" "]new[2:" "][2:"ConstClass() {\n        }"]     };
        [4:"public static final"] ConstClass SELF_SUB = new SubConstClass();
        [5:"public static final"] ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private [7:"static final"] HashMap<String, String> MAP =[6:" "]new[6:" "][6:"HashMap<>"][6:"()"];
        private [9:"static final"] HashMap<String, String> MAP2 =[8:" "]new[8:" "][8:"HashMap<String, String>"][8:"()"];
        private [10:"static final"] Map<String, String> MAP3 = new HashMap<>();
        private [11:"static final"] Map<String, String> MAP_TREE = new TreeMap<>();
        private [12:"static final"] Map<String, String> MAP4 = Map.of();

        private [13:"static final"] List<String> LIST = new ArrayList<>();
        private [14:"static final"] List<String> LIST2 = List.of();
        private [15:"static final"] List<String> LIST_SINGLE = List.of("1");
        private [16:"static final"] List<String> LIST_LINKED = new LinkedList<>();


        [18:"public static final"] ConstClass SELF_PARAM_1 =[17:" "]new[17:" "][17:"ConstClass"](true);
        [20:"public static final"] ConstClass SELF_PARAM_2 =[19:" "]new[19:" "][19:"ConstClass"](false, LIST_SINGLE.get(0));

        [21:"public static final"] ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        [22:"public static final"] ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        protected static ConstClass SELF_NULL = null;
        protected static ConstClass EMPTY;
    }
    static class Fields {
        final ConstClass SELF =[23:" "]new[23:" "][23:"ConstClass"][23:"()"];
        ConstClass SELF_ANN =[24:" "]new[24:" "][24:"ConstClass() {\n        }"]      };
        public final ConstClass SELF_SUB = new SubConstClass();
        public final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private final HashMap<String, String> MAP =[25:" "]new[25:" "][25:"HashMap<>"][25:"()"];
        private final HashMap<String, String> MAP2 =[26:" "]new[26:" "][26:"HashMap<String, String>"][26:"()"];
        private final Map<String, String> MAP3 = new HashMap<>();

        private final List<String> LIST = new ArrayList<>();
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 =[27:" "]new[27:" "][27:"ConstClass"](true);
        public final ConstClass SELF_PARAM_2 =[28:" "]new[28:" "][28:"ConstClass"](false, LIST2.get(0));

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

    static class ConstClass {
        boolean ok;
        String string;

        public ConstClass() {
        }

        public ConstClass(boolean ok) {
            this.ok = ok;
        }

        public ConstClass(boolean ok, String string) {
            this.ok = ok;
            this.string = string;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ConstClass.class.getSimpleName() + "[", "]")
                    .add("string='" + string + "'")
                    .toString();
        }
    }

    static class SubConstClass extends ConstClass {
    }

    static final class SubConstClass2 extends ConstClass {
    }
}
