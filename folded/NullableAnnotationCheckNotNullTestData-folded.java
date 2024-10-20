package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String!!! args, Object!!! o, Long!!! l, Preconditions z) {args!!;l!!;
            Preconditions.checkNotNull(z.getData());o!!;
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainMsgs(String!!! args, Object!!! o, Long!!! l, Preconditions z) {args!!;l!!;
            Preconditions.checkNotNull(z.getData(), "o is null");o!!;
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotations(@Nullable String!!! args, @Nullable Object!!! o, @Nullable Long!!! l, @Nullable Preconditions z) {args!!;l!!;
            Preconditions.checkNotNull(z.getData());o!!;
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotationsWithMsg(@Nullable String!!! args, @Nullable Object!!! o, @Nullable Long!!! l, @Nullable Preconditions z) {args!!;l!!;
            Preconditions.checkNotNull(z.getData(), "o is null");o!!;
            new HashMap<String, String>().put("a", "b");
            System.out.println();
        }
    }

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = args!!;
            this.l = l!!;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = o!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = args!!;
            this.l = l!!;
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = o!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = o!!;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgsNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = o!!;
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
