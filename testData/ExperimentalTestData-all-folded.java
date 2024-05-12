package data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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

    }

    void main(){
        val s = Singleton.🧍;
        println(Singleton.🧍.ok);
        println(Singleton.🧍.main(Singleton.🧍.main(Singleton.instance)));
    }
    @Getter static class Singleton {
        static Singleton INSTANCE = new Singleton();
        boolean ok;
        Singleton main(Singleton s) {
            return this;
        }

        public static Singleton getInstance() {
            return INSTANCE;
        }
    }

    @ToStringˣ static class ConstClass {
        boolean ok;
        String string;

        public ConstClass() {
        }

        public ConstClass(boolean ok) {
            this.ok = <<;
        }

        public ConstClass(boolean ok, String string) {
            this.ok = <<;
            this.string = <<;
        }
    }


    static class SubConstClass extends ConstClass {

    }

    static  class SubConstClass2 extends ConstClass {

    }
}
