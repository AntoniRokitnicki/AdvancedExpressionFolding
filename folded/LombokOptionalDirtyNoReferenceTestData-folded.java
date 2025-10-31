package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class DirtyOptionalNoReference {
        @Getter(optional = true) private String optionalField;

        @Getter(dirtyNoReference) private String fallback;
    }
}
