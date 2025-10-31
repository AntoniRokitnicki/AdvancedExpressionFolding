package data;

public class LombokOptionalDirtyTestData {

    public static class OptionalFalseCases {
        // 1. optional = false, dirty = none
        private String plain;

        public String getPlain() {
            return plain;
        }

        // 2. optional = false, dirty = dirty
        private String alias;
        private String fallback;

        public String getAlias() {
            return alias != null ? alias : fallback;
        }

        // 3. optional = false, dirty = dirtyNoReference
        private String detached;

        public String getDetached() {
            return "fallback";
        }
    }
}
