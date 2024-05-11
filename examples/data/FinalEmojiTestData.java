package data;

public class FinalEmojiTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public final String m() {
        final String s = "1";
        final var s2 = "2";
        var s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main(final String arg, final int i, final Object o, Data data);
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
