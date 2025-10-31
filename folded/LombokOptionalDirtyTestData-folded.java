package data;

import java.util.Optional;

public class LombokOptionalDirtyTestData {

    public static class DirtyOptional {
        @Getter(optional = true) private String optionalField;

        @Getter(dirty) private String alias;
    }
}
