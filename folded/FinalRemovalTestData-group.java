package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public [0:"final"]{[0:"final"]} String m() {
        [0:"final"]{[0:"final"]} String s = "1";
        [0:"final"]{[0:"final"]} var s2 = "2";
        var s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main([0:"final"]{[0:"final"]} String arg, [0:"final"]{[0:"final"]} int i, [0:"final"]{[0:"final"]} Object o, Data data);
    }

    [0:"final"] static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    [0:"final"] public record UserDataRecord(String username, boolean active, String userIdentifier) {
        [0:"final"]{[0:"final"]} void main([0:"final"]{[0:"final"]} String arg, [0:"final"]{[0:"final"]} int i, [0:"final"]{[0:"final"]} Object o, Data data) {
        }
    }

}
