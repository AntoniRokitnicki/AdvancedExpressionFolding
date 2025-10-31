package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class DirtyOptionalNoReference {
        private String optionalField;
        private String fallback;

        public Optional<String> getOptionalField() {
            return Optional.ofNullable(optionalField);
        }

        public String getFallback() {
            return "fallback";
        }
    }
}
