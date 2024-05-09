package data;

import org.jetbrains.annotations.Nullable;

@Getter @SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    private String saaa;

    class PreconditionsCheck {
        public void main(String!!! args, Object o, Long!!! l, NullableAnnotationCheckNotNullTestData z) {args!!;l!!;
            z.saaa!!;
            o!!;
            System.out.println();
        }

        public void mainMsgs(String!!! args, Object o, Long!!! l, NullableAnnotationCheckNotNullTestData z) {args!!;l!!;
            z.saaa!!;
            o!!;
            System.out.println();
        }

        public void mainNullable(@Nullable String!!! args,  Object? o, @Nullable Long!!! l,  NullableAnnotationCheckNotNullTestData? z) {args!!;l!!;
            z.saaa!!;
            o!!;
            System.out.println();
        }

        public void mainMsgsNullable(@Nullable String!!! args,  Object? o, @Nullable Long!!! l,  NullableAnnotationCheckNotNullTestData? z) {args!!;l!!;
            z.saaa!!;
            o!!;
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
            this.saaa = z.saaa!!;
            this.o = o!!;
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) {
            this.args = args!!;
            this.l = l!!;
            this.saaa = z.saaa!!;
            this.o = o!!;
            printStatus();
        }

        public void mainNullable( String? args,  Object? o,  Long? l,  NullableAnnotationCheckNotNullTestData? z) {
            this.args = args!!;
            this.l = l!!;
            this.saaa = z.saaa!!;
            this.o = o!!;
            printStatus();
        }

        public void mainMsgsNullable( String? args,  Object? o,  Long? l,  NullableAnnotationCheckNotNullTestData? z) {
            this.args = args!!;
            this.l = l!!;
            this.saaa = z.saaa!!;
            this.o = o!!;
            printStatus();
        }

        private void printStatus() {
        }
    }


    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {
            return o;
        }
        public static <T> T checkNotNull(T o) {
            return o;
        }
    }
}
