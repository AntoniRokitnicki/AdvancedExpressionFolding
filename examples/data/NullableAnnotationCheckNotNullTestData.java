package data;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    private String saaa;

    public String getSaaa() {
        return saaa;
    }

    class PreconditionsCheck {
        public void main(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }

        public void mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
        }

        public void mainConflictAnnotations(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }

        public void mainConflictAnnotationsWithMsg(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
        }
    }

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private String saaa;

        public void main(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = Preconditions.checkNotNull(o);
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.saaa = Preconditions.checkNotNull(z.getSaaa(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
            printStatus();
        }

        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = Preconditions.checkNotNull(o);
            printStatus();
        }

        public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.saaa = Preconditions.checkNotNull(z.getSaaa(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
            printStatus();
        }

        private void printStatus() {
        }
    }


    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {
            return (T) o;
        }
        public static <T> T checkNotNull(T o) {
            return (T) o;
        }
    }
}
