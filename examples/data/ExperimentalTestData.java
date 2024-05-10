package data;

import java.util.ArrayList;
import java.util.List;

public class ExperimentalTestData {

    static class Const {
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private static final List<String> LIST = new ArrayList<>();

        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST.get(0));

        

        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        public static final String PUBLIC_STATIC_FINAL_VAR = "";
        private static final String PRIVATE_STATIC_FINAL_VAR = "";
        protected static final String PROTECTED_STATIC_FINAL_VAR = "";
        static final String DEFAULT_STATIC_FINAL_VAR = "";
        static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        static final int VERSION = 1;
        static final int VERSION_REF = VERSION;
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
    }
    static class SubConstClass extends ConstClass {

    }
}
