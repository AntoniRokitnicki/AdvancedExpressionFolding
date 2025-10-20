package data;

public class LombokPostConstructorTestData {

    public static class DataProcessor {
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public DataProcessor() {
            this(0, 0);
        }

        @PostConstructor private void initialize() {
            computedResult = value1 + value2;
        }
    }
}
