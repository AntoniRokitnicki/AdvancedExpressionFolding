package data;

import java.util.Optional;

public class LombokOptionalDirtyTestData {

    public static class DirtyOptional {
        // When Lombok folding spots multiple dirty heuristics, optional wins before dirty/dirtyNoReference.
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
