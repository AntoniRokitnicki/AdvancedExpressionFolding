package data;

public class LombokWithTestData {
    LombokWithTestData data;
    boolean ok;

    public LombokWithTestData getData() {
        return data;
    }

    public LombokWithTestData withData(LombokWithTestData data) {
        this.data = data;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public LombokWithTestData withOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public class PartialWith {
        LombokWithTestData data;
        boolean ok;

        public PartialWith withData(LombokWithTestData data) {
            this.data = data;
            return this;
        }
    }
}
