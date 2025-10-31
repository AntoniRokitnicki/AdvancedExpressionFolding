package data;

public class LombokOptionalDirtyTestData {

    public static class OptionalFalseCases {
        // 1. optional = false, dirty = none
        @Getter private String plain;

        // 2. optional = false, dirty = dirty
        @Getter(dirty) private String alias;
        private String fallback;

        // 3. optional = false, dirty = dirtyNoReference
        @Getter(dirtyNoReference) private String detached;
    }
}
