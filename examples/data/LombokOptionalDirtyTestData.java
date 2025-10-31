package data;

import java.util.Optional;

public class LombokOptionalDirtyTestData {

    public static class DirtyOptional {
        private String optionalField;
        private String alias;

        public Optional<String> getOptionalField() {
            return Optional.ofNullable(optionalField);
        }

        public String getAlias() {
            return optionalField != null ? optionalField : alias;
        }
    }
}
