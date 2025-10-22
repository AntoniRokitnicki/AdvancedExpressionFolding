package data;

@Getter @With public class LombokWithTestData {
    LombokWithTestData data;
    boolean ok;

    public class PartialWith {
        @With LombokWithTestData data;
        boolean ok;
    }
}
