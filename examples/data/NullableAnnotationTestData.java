package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.extension.NullableExt#createExpression(com.intellij.psi.PsiMethod)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testNullableAnnotationTestData()}
 */
@SuppressWarnings("ALL")
public class NullableAnnotationTestData {
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

    public class LombokFieldLevelIntegration {
        public class HasGetter {
            @Nullable
            private String field;
            private String bla;

            public @Nullable String getField() {
                return field;
            }
        }

        public class HasSetter {
            @Nullable
            private String field;
            private String bla;

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }

        public class HasGetterSetter {
            @Nullable
            private String field;
            private String bla;

            public @Nullable String getField() {
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }
    }

    public class LombokFieldLevelNotPrivateIntegration {
        public class HasGetter {
            @Nullable
            String field;
            String bla;

            public @Nullable String getField() {
                return field;
            }
        }

        public class HasSetter {
            @Nullable
            String field;
            String bla;

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }

        public class HasGetterSetter {
            @Nullable
            String field;
            String bla;

            public @Nullable String getField() {
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }
    }


}
