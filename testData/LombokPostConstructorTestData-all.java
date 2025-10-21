package data;

public class LombokPostConstructorTestData {

    public static class DataProcessor <fold text='{...}' expand='true'>{
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) <fold text='{...}' expand='true'>{
            this.value1 = <fold text='<<' expand='false'>value1</fold>;
            this.value2 = <fold text='<<' expand='false'>value2</fold>;
            initialize();
        }</fold>

        public DataProcessor() <fold text='{...}' expand='true'>{
            this(0, 0);
            initialize();
        }</fold>

        <fold text='@PostConstructor p' expand='false'>p</fold>rivate void initialize()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>computedResult = value1 + value2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    public static class TaskPipeline <fold text='{...}' expand='true'>{
        private final int seed;
        private boolean prepared;
        private boolean executed;

        public TaskPipeline() <fold text='{...}' expand='true'>{
            this.seed = -1;
            prepare();
            execute();
        }</fold>

        public TaskPipeline(int seed) <fold text='{...}' expand='true'>{
            this.seed = <fold text='<<' expand='false'>seed</fold>;
            prepare();
            execute();
        }</fold>

        <fold text='@PostConstructor(1) p' expand='false'>p</fold>rivate void prepare()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>prepared = true<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        <fold text='@PostConstructor(2) p' expand='false'>p</fold>rivate void execute()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>executed = prepared<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
