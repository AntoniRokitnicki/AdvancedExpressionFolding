package data;

@ToString* public class FinalRemovalTestData {

    const PUBLIC_STATIC_FINAL_VAR = "";
    const FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

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
