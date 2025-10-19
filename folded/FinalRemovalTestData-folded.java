package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public String m() {
        String s = "1";
        var s2 = "2";
        var s3 = "3";
        @Deprecated String annotated = "4";
        return s + s2 + s3;
    }

    public synchronized String methodFinalLast() {
        return "";
    }

    public synchronized String methodFinalMiddle() {
        return "";
    }

    public synchronized String methodFinalFirst() {
        return "";
    }

    public
    String methodFinalSeparatedLines() {
        return "";
    }

    interface A {
        void main(String arg, int i, Object o, Data data);

        void modifiers(int first,
                @Deprecated String second,
                @Deprecated Object third,
                Data data,
                                        String multilineFinal,
                String plain);
    }

    static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
        void main(String arg, int i, Object o, Data data) {
        }
    }

}
