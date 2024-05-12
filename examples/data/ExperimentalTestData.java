package data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ExperimentalTestData {

    static class Const {
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        private static final List<String> LIST = new ArrayList<>();
        private static final List<String> LIST2 = List.of("1");

        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));

        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

    }

    void main(){
        var s = Singleton.INSTANCE;
        System.out.println(Singleton.INSTANCE.isOk());
        System.out.println(Singleton.INSTANCE.main(Singleton.INSTANCE.main(Singleton.getInstance())));
    }
    static class Singleton {
        static Singleton INSTANCE = new Singleton();
        boolean ok;
        Singleton main(Singleton s) {
            return this;
        }

        public boolean isOk() {
            return ok;
        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
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
