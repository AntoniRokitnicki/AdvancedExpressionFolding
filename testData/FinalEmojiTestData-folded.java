package data;

public class FinalEmojiTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    @Override
    public 🔒 String toString() {
        🔒 String s = "1";
        🔒 var s2 = "2";
        var s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main(🔒 String arg, 🔒 int i, 🔒 Object o, data.FinalRemovalTestData.Data data);
    }

    🔒 static class Data {
        data.FinalRemovalTestData.Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

    🔒 public record UserDataRecord(String username, boolean active, String userIdentifier) {
        🔒 void main(🔒 String arg, 🔒 int i, 🔒 Object o, data.FinalRemovalTestData.Data data) {
        }
    }

}

