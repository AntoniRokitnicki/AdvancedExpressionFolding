package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotations(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotationsWithMsg(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
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
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
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
