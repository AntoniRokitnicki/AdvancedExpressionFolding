@Builder public class LombokBuilderFields {

    @Builder private String first;
    private String second;

    class LombokBuilderFieldsBuilder {
        private String first;
        private String second;

        public LombokBuilderFields build() {
            LombokBuilderFields result = new LombokBuilderFields();
            result.first = this.first;
            result.second = this.second;
            return result;
        }
    }
}
