package data;

public class FinalEmojiTestData {

    [0:"public static final "][0:"String"] PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public [1:"final"][7:"final"] String m() {
        [3:"final"][9:"final"]inal String"][8:"final String"] s = "1";
        [5:"final"][11:"final"]inal var"][10:"final var"] s2 = "2";
        [6:"var"][12:"var"] s3 = "3";
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
        [18:"final"][22:"final"] void main([19:"final"][23:"final"] String arg, [20:"final"][24:"final"] int i, [21:"final"][25:"final"] Object o, Data data) {
        }
    }

}
