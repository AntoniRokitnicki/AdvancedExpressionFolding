package data;

public class LombokPostConstructorTestData {

    public static class DataProcessor <fold text='{...}' expand='true'>{
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) <fold text='{...}' expand='true'>{
            this.value1 = <fold text='<<' expand='false'>value1</fold>;
            this.value2 = <fold text='<<' expand='false'>value2</fold>;<fold text='' expand='false'>
            </fold><fold text='' expand='false'>initialize();</fold>
        }</fold>

        public DataProcessor() <fold text='{...}' expand='true'>{
            this(0, 0);<fold text='' expand='false'>
            </fold><fold text='' expand='false'>initialize();</fold>
        }</fold>

        <fold text='@PostConstructor p' expand='false'>p</fold>rivate void initialize()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>computedResult = value1 + value2<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
