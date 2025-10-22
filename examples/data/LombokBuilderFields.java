public class LombokBuilderFields {

    private String first;
    private String second;

    class LombokBuilderFieldsBuilder {
        private String first;
        private String second;

        public LombokBuilderFieldsBuilder first(String first) {
            this.first = first;
            return this;
        }

        public LombokBuilderFields build() {
            LombokBuilderFields result = new LombokBuilderFields();
            result.first = this.first;
            result.second = this.second;
            return result;
        }
    }
}
