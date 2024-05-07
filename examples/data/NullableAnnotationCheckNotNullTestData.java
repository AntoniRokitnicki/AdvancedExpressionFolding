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

        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }

        public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable NullableAnnotationCheckNotNullTestData z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
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
