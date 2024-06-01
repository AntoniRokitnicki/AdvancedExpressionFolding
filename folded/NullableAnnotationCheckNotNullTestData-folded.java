package data;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
@Getter public class NullableAnnotationCheckNotNullTestData {

    private String saaa;

    class PreconditionsCheck {
        public void main(String!!! args, Object o, Long!!! l, NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }

        public void mainMsgs(String!!! args, Object o, Long!!! l, NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
        }

        public void mainConflictAnnotations(@Nullable String!!! args, Object? o, @Nullable Long!!! l, NullableAnnotationCheckNotNullTestData? z) {
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }

        public void mainConflictAnnotationsWithMsg(@Nullable String!!! args, Object? o, @Nullable Long!!! l, NullableAnnotationCheckNotNullTestData? z) {
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
            this.args = args!!;
            this.l = l!!;
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = o!!;
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            this.args = args!!;
            this.l = l!!;
            this.saaa = Preconditions.checkNotNull(z.getSaaa(), "saaa is null");
            this.o = o!!;
            printStatus();
        }

        public void mainNullable(String? args, Object? o, Long? l, NullableAnnotationCheckNotNullTestData? z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = Preconditions.checkNotNull(o);
            printStatus();
        }

        public void mainMsgsNullable(String? args, Object? o, Long? l, NullableAnnotationCheckNotNullTestData? z) {
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
