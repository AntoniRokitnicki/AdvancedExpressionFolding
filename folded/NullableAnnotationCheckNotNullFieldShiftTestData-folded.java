package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullFieldShiftTestData {

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        private void printStatus() {
            new HashMap<String, String>().put("a", "b");
        }
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {
            return o;
        }
        public static <T> T checkNotNull(T o) {
            return o;
        }
        public Preconditions getData() {
            return this;
        }
    }

}
