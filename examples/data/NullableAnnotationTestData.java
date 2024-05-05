package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
public class NullableAnnotationTestData {

    private static final long serialVersionUID = 1234567L;
    @NotNull
    NullableAnnotationTestData data;
    boolean ok;
    @Nullable
    String string;
    public NullableAnnotationTestData getData() {
        return data;
    }
    public void setData(NullableAnnotationTestData data) {
        this.data = data;
    }
    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }

    @NotNull
    private NullableAnnotationTestData data2;
    boolean ok2;
    @Nullable
    private String string2;

    public void select(@Nullable String element,
                       int i,
                       @NotNull Object o) {

    }

    @NotNull
    public String getStringNotNull() {
        return string;
    }

    @Nullable
    public String getStringNull() {
        return string;
    }

}
