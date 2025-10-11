package data;

public class FinalRemovalTestData {

    [0:"public static final "][0:"String"] PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public [1:"final"] String m() {
        [3:"final"] String s = "1";
        [5:"final"] var s2 = "2";
        [6:"var"] s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main([13:"final"] String arg, [14:"final"] int i, [15:"final"] Object o, Data data);
    }

    [16:"final"] static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    [17:"final"] public record UserDataRecord(String username, boolean active, String userIdentifier) {
        [18:"final"] void main([19:"final"] String arg, [20:"final"] int i, [21:"final"] Object o, Data data) {
        }
    }

}
