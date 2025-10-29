package data;

import java.util.Objects;

public class LombokSetterNullCheckTestData {
    <fold text='@Setter(onParam_ = @NonNull) p' expand='false'>p</fold>rivate String withThrow;
    <fold text='@Setter(onParam_ = @NonNull) p' expand='false'>p</fold>rivate String withRequireNonNull;
    <fold text='@Setter(dirty, onParam_ = @NonNull) p' expand='false'>p</fold>rivate String withTrim;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setWithThrow(String withThrow) <fold text='{...}' expand='true'>{
        if (withThrow == null) <fold text='{...}' expand='true'>{
            throw new NullPointerException("withThrow");
        }</fold>
        this.withThrow = withThrow;
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setWithRequireNonNull(String withRequireNonNull) <fold text='{...}' expand='true'>{
        this.withRequireNonNull = Objects.requireNonNull(withRequireNonNull, "withRequireNonNull");
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setWithTrim(String withTrim) <fold text='{...}' expand='true'>{
        Objects.requireNonNull(withTrim);
        this.withTrim = withTrim.trim();
    }</fold></fold>
}
