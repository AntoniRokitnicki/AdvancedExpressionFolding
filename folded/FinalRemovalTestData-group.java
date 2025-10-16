package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public [0:"final"] String m() {
        [1:"final"] String s = "1";
        [2:"final"] var s2 = "2";
        var s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main([6:"final"] String arg, [7:"final"] int i, [8:"final"] Object o, Data data);
    }

    [12:"final"] static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    [13:"final"] public record UserDataRecord(String username, boolean active, String userIdentifier) {
        [14:"final"] void main([15:"final"] String arg, [16:"final"] int i, [17:"final"] Object o, Data data) {
        }
    }

}
