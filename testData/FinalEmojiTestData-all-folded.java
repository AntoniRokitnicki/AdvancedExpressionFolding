package data;

public class FinalEmojiTestData {

    const PUBLIC_STATIC_FINAL_VAR = "";
    const FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public  String m() {
         String s = "1";
         var s2 = "2";
        val s3 = "3";
        return s + s2 + s3;
    }

    interface A {
        void main( String arg,  int i,  Object o, Data data);
    }

     static class Data {
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

     public record UserDataRecord(String username, boolean active, String userIdentifier) {
         void main( String arg,  int i,  Object o, Data data) {
        }
    }

}
