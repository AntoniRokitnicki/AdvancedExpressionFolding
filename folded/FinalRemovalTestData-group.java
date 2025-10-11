package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public [0:"final"][3:"final"] String m() {
        [1:"final"][4:"final"] String s = "1";
        [2:"final"][5:"final"] var s2 = "2";
        var s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main([6:"final"][9:"final"] String arg, [7:"final"][10:"final"] int i, [8:"final"][11:"final"] Object o, Data data);
    }

    [12:"final"] static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    [13:"final"] public record UserDataRecord(String username, boolean active, String userIdentifier) {
        [14:"final"][18:"final"] void main([15:"final"][19:"final"] String arg, [16:"final"][20:"final"] int i, [17:"final"][21:"final"] Object o, Data data) {
        }
    }

}
