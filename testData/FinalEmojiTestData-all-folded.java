package data;

@ToString* public class FinalEmojiTestData {

    const PUBLIC_STATIC_FINAL_VAR = "";
    const FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    interface A {
        void main( String arg,  int i,  Object o, data.FinalRemovalTestData.Data data);
    }

     static class Data {
        data.FinalRemovalTestData.Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }

     public record UserDataRecord(String username, boolean active, String userIdentifier) {
         void main( String arg,  int i,  Object o, data.FinalRemovalTestData.Data data) {
        }
    }

}

