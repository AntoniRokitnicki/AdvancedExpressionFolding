<fold text='@Builder p' expand='false'>p</fold>ublic class LombokBuilderFields {

    <fold text='@Builder p' expand='false'>p</fold>rivate String first;
    private String second;

    class LombokBuilderFieldsBuilder <fold text='{...}' expand='true'>{
        private String first;
        private String second;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public LombokBuilderFieldsBuilder first(String first) <fold text='{...}' expand='true'>{
            this.first = first;
            return this;
        }</fold></fold>

        public LombokBuilderFields build() <fold text='{...}' expand='true'>{
            LombokBuilderFields result = new LombokBuilderFields();
            result.first = this.first;
            result.second = this.second;
            return result;
        }</fold>
    }</fold>
}
