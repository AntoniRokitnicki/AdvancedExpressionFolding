package data;

import java.util.Objects;

public class LombokSetterNullCheckTestData {
    private String withThrow;
    private String withRequireNonNull;

    public void setWithThrow(String withThrow) {
        if (withThrow == null) {
            throw new NullPointerException("withThrow");
        }
        this.withThrow = withThrow;
    }

    public void setWithRequireNonNull(String withRequireNonNull) {
        this.withRequireNonNull = Objects.requireNonNull(withRequireNonNull, "withRequireNonNull");
    }
}
