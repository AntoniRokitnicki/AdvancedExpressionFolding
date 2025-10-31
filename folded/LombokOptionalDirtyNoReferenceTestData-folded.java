package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class OptionalTrueCases {
        // 4. optional = true, dirty = none (dirty heuristic fires but optional wins)
        @Getter(optional = true) private String optionalPlain;

        // 5. optional = true, dirty = dirty (optional still wins)
        @Getter(optional = true) private String optionalAliased;

        // 6. optional = true, dirty = dirtyNoReference (optional flag disappears without a field reference)
        @Getter(dirtyNoReference) private String optionalDetached;
    }
}
