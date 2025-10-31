package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class OptionalTrueCases {
        // 4. optional = true, dirty = none (dirty heuristic fires but optional wins)
        private String optionalPlain;

        public Optional<String> getOptionalPlain() {
            return Optional.ofNullable(optionalPlain);
        }

        // 5. optional = true, dirty = dirty (optional still wins)
        private String optionalAliased;

        public Optional<String> getOptionalAliased() {
            return Optional.ofNullable(optionalAliased);
        }

        // 6. optional = true, dirty = dirtyNoReference (optional flag disappears without a field reference)
        private String optionalDetached;

        public Optional<String> getOptionalDetached() {
            return Optional.ofNullable("fallback");
        }
    }
}
