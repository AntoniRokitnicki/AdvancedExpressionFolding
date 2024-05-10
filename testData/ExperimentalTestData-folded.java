package data;

import java.util.ArrayList;
import java.util.List;

public class ExperimentalTestData {

    static class Const {
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        const List<String> LIST = new ArrayList<>();

        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, LIST.getFirst());



        const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        const PUBLIC_STATIC_FINAL_VAR = "";
        const PRIVATE_STATIC_FINAL_VAR = "";
        const PROTECTED_STATIC_FINAL_VAR = "";
        const DEFAULT_STATIC_FINAL_VAR = "";
        const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        const VERSION = 1;
        const int VERSION_REF = VERSION;
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
