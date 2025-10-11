package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.processor.NullableExt#createExpression(com.intellij.psi.PsiMethod)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testNullableAnnotationTestData()}
 */
@SuppressWarnings("ALL")
public class NullableAnnotationTestData {
    [0:"@NotNull"]
    [0:"N"]ullableAnnotationTestData[0:" "]data;
    [0:"b"]oolean ok;
    [0:"@Nullable"]
    [0:"S"]tring[0:" "]string;[0:"
    "][0:"public NullableAnnotationTestData getData() {
        return data;
    }"][0:"
    "][0:"public void setData(NullableAnnotationTestData data) {
        this.data = data;
    }"][0:"
    "][0:"public boolean isOk() {
        return ok;
    }"][0:"
    "][0:"public void setOk(boolean ok) {
        this.ok = ok;
    }"][0:"
    "][0:"public String getString() {
        return string;
    }"][0:"
    "][0:"public void setString(String string) {
        this.string = string;
    }"]

    [0:"@Nonnull"]
    private NullableAnnotationTestData[0:" "]data2;
    boolean ok2;
    [0:"@Nullable"]
    private String[0:" "]string2;

    public void select([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]}element,
                       int i,
                       [0:"@NotNull"]{[0:"@NotNull"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o,
                       [0:"@Nonnull"]{[0:"@Nonnull"]}[0:" "]{[0:" "]}LocalDate[0:" "]{[0:" "]}date
                       ) {
        new HashMap<String, String>().put("a", "b");

    }

    [0:"@NotNull"]
    public String[0:" "]getStringNotNull() {
        return string;
    }

    [0:"@Nonnull"]
    public String[0:" "]getStringNotNull2() {
        return string;
    }

    [0:"@Nullable"]
    public String[0:" "]getStringNull() {
        return string;
    }

    interface Datable {
        [0:"@Nullable"]
        public Integer[0:" "]select([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]}element,
                           int i,
                           [0:"@NotNull"]{[0:"@NotNull"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o,
                           [0:"@Nonnull"]{[0:"@Nonnull"]}[0:" "]{[0:" "]}LocalDate[0:" "]{[0:" "]}date
        );
    }

    public enum FieldFoldingAnnotation {
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;

        FieldFoldingAnnotation(String... annotations) {

        }

        [0:"@Nonnull"]
        public static int[0:" "]select([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]}element,
                                 int i,
                                 [0:"@NotNull"]{[0:"@NotNull"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o,
                                 [0:"@Nonnull"]{[0:"@Nonnull"]}[0:" "]{[0:" "]}LocalDate[0:" "]{[0:" "]}date
        ) {
            return 1;
        }

    }

    public record UserDataRecord([0:"@Nonnull"][0:" "]String[0:" "]username, boolean active, [0:"@Nullable"][0:" "]String[0:" "]userIdentifier, [0:"@NotNull"][0:" "]String[0:" "]username2) {
    }

    [0:"c"]lass GetterNullable {
        NullableAnnotationTestData getterNullable;[0:"

        "][0:"@Nullable
        public NullableAnnotationTestData getGetterNullable() {
            return getterNullable;
        }"]{[0:"@Nullable"] [0:" "]}
    }

    [0:"c"]lass SetterNullable {
        NullableAnnotationTestData setterNullable;[0:"

        "][0:"public void setSetterNullable(@Nonnull NullableAnnotationTestData setterNullable) {
            this.setterNullable = setterNullable;
        }"]{[0:"@Nonnull"]{[0:"@Nonnull"]} [0:" "]{[0:" "]} [0:" "]{[0:" "]}}
    }

    public class LombokFieldLevelIntegration {
        public class HasGetter {
            [0:"@Nullable"]
            [0:"p"]rivate String[0:" "]field;
            private String bla;[0:"

            "][0:"public @Nullable String getField() {
                return field;
            }"]{[0:"@Nullable"] [0:" "]}
        }

        public class HasSetter {
            [0:"@Nullable"]
            [0:"p"]rivate String[0:" "]field;
            private String bla;[0:"

            "][0:"public void setField(@Nullable String field) {
                this.field = field;
            }"]{[0:"@Nullable"]{[0:"@Nullable"]} [0:" "]{[0:" "]} [0:" "]{[0:" "]}}
        }

        public class HasGetterSetter {
            [0:"@Nullable"]
            [0:"p"]rivate String[0:" "]field;
            private String bla;[0:"

            "][0:"public @Nullable String getField() {
                new HashMap<String, String>().put("a", "b");
                return field;
            }"]{[0:"@Nullable"] [0:" "]}[0:"

            "][0:"public void setField(@Nullable String field) {
                this.field = field;
            }"]{[0:"@Nullable"]{[0:"@Nullable"]} [0:" "]{[0:" "]} [0:" "]{[0:" "]}}
        }
    }

    public class LombokFieldLevelNotPrivateIntegration {
        public class HasGetter {
            [0:"@Nullable"]
            [0:"S"]tring[0:" "]field;
            String bla;[0:"

            "][0:"public @Nullable String getField() {
                return field;
            }"]{[0:"@Nullable"] [0:" "]}
        }

        public class HasSetter {
            [0:"@Nullable"]
            [0:"S"]tring[0:" "]field;
            String bla;[0:"

            "][0:"public void setField(@Nullable String field) {
                this.field = field;
            }"]{[0:"@Nullable"]{[0:"@Nullable"]} [0:" "]{[0:" "]} [0:" "]{[0:" "]}}
        }

        public class HasGetterSetter {
            [0:"@Nullable"]
            [0:"S"]tring[0:" "]field;
            String bla;[0:"

            "][0:"public @Nullable String getField() {
                return field;
            }"]{[0:"@Nullable"] [0:" "]}[0:"

            "][0:"public void setField(@Nullable String field) {
                this.field = field;
            }"]{[0:"@Nullable"]{[0:"@Nullable"]} [0:" "]{[0:" "]} [0:" "]{[0:" "]}}
        }
    }


}
