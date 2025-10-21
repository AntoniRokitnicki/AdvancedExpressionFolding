package data;

public class LombokPostConstructorTestData {

    public static class DataProcessor {
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
            initialize();
        }

        public DataProcessor() {
            this(0, 0);
            initialize();
        }

        private void initialize() {
            computedResult = value1 + value2;
        }
    }

    public static class TaskPipeline {
        private final int seed;
        private boolean prepared;
        private boolean executed;

        public TaskPipeline() {
            this.seed = -1;
            prepare();
            execute();
        }

        public TaskPipeline(int seed) {
            this.seed = seed;
            prepare();
            execute();
        }

        private void prepare() {
            prepared = true;
        }

        private void execute() {
            executed = prepared;
        }
    }
}
