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


    static class ConstClass {
        boolean ok;
        String string;

        public ConstClass() {
        }

        public ConstClass(boolean ok) {
            this.ok = ok;
        }

        public ConstClass(boolean ok, String string) {
            this.ok = ok;
            this.string = string;
        }
    }
    static class SubConstClass extends ConstClass {

    }
    static class Const {
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, "1");


        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        public static final String PUBLIC_STATIC_FINAL_VAR = "";
        private static final String PRIVATE_STATIC_FINAL_VAR = "";
        protected static final String PROTECTED_STATIC_FINAL_VAR = "";
        static final String DEFAULT_STATIC_FINAL_VAR = "";
        static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        static final int VERSION = 1;
        static final int VERSION_REF = VERSION;
    }

}
