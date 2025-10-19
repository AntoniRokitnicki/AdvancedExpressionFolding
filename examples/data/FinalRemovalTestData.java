package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public final String m() {
        final String s = "1";
        final var s2 = "2";
        final var s3 = "3";
        @Deprecated final String annotated = "4";
        return s + s2 + s3;
    }

    public synchronized final String methodFinalLast() {
        return "";
    }

    public final synchronized String methodFinalMiddle() {
        return "";
    }

    final public synchronized String methodFinalFirst() {
        return "";
    }

    public
    final
    String methodFinalSeparatedLines() {
        return "";
    }

    interface A {
        void main(final String arg, final int i, final Object o, Data data);

        void modifiers(final int first,
                @Deprecated final String second,
                final @Deprecated Object third,
                Data data,
                final
                        String multilineFinal,
                String plain);
    }

    final static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    final public record UserDataRecord(String username, boolean active, String userIdentifier) {
        final void main(final String arg, final int i, final Object o, Data data) {
        }
    }

}
