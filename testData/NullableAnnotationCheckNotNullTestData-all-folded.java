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

    class Preconditions {
        public static void checkNotNull(Object o, String s) {
        }
        public static void checkNotNull(Object o) {
        }
    }
}
