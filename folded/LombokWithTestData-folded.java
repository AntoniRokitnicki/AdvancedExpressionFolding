package data;

@Getter public class LombokWithTestData {
    @Setter(dirty) LombokWithTestData data;
    @Setter(dirty) boolean ok;

    public class PartialWith {
        @Setter(dirty) LombokWithTestData data;
        boolean ok;
    }
}
