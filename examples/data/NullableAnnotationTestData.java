package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;

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

    @Nonnull
    private NullableAnnotationTestData data2;
    boolean ok2;
    @Nullable
    private String string2;

    public void select(@Nullable String element,
                       int i,
                       @NotNull Object o,
                       @Nonnull LocalDate date
                       ) {

    }

    @NotNull
    public String getStringNotNull() {
        return string;
    }

    @Nonnull
    public String getStringNotNull2() {
        return string;
    }

    @Nullable
    public String getStringNull() {
        return string;
    }

    interface Datable {
        @Nullable
        public Integer select(@Nullable String element,
                           int i,
                           @NotNull Object o,
                           @Nonnull LocalDate date
        );
    }

    public enum FieldFoldingAnnotation {
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;

        FieldFoldingAnnotation(String... annotations) {

        }

        @Nonnull
        public static int select(@Nullable String element,
                                 int i,
                                 @NotNull Object o,
                                 @Nonnull LocalDate date
        ) {
            return 1;
        }

    }

    public record UserDataRecord(@Nonnull String username, boolean active, @Nullable String userIdentifier, @NotNull String username2) {
    }

    class GetterNullable {
        NullableAnnotationTestData getterNullable;

        @Nullable
        public NullableAnnotationTestData getGetterNullable() {
            return getterNullable;
        }
    }

    class SetterNullable {
        NullableAnnotationTestData setterNullable;

        public void setSetterNullable(@Nonnull NullableAnnotationTestData setterNullable) {
            this.setterNullable = setterNullable;
        }
    }

}
