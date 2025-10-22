<fold text='@Builder p' expand='false'>p</fold>ublic class LombokBuilderFields {

    <fold text='@Builder p' expand='false'>p</fold>rivate String first;
    private String second;

    class LombokBuilderFieldsBuilder <fold text='{...}' expand='true'>{
        private String first;
        private String second;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokBuilderFieldsBuilder first(String first) <fold text='{...}' expand='true'>{
            this.first = <fold text='<<' expand='false'>first</fold>;
            return this;
        }</fold></fold>

        public LombokBuilderFields build() <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>LombokBuilderFields</fold> result = new LombokBuilderFields();
            result.first = this.<fold text='<<' expand='true'>first</fold>;
            result.second = this.<fold text='<<' expand='true'>second</fold>;
            return result;
        }</fold>
    }</fold>
}
