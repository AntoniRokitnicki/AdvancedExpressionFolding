package data;

import java.util.Objects;

public class LombokSetterNullCheckTestData {
    @Setter(onParam_ = @NonNull) private String withThrow;
    @Setter(onParam_ = @NonNull) private String withRequireNonNull;
    @Setter(dirty, onParam_ = @NonNull) private String withTrim;
}
