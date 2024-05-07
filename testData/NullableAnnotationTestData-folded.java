package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;

@Getter @Setter* @Serial @SuppressWarnings("ALL")
public class NullableAnnotationTestData {
    
    NullableAnnotationTestData!! data;
    boolean ok;
    
    String? string;

    
    private NullableAnnotationTestData!! data2;
    boolean ok2;
    
    private String? string2;

    public void select( String? element,
                       int i,
                        Object!! o,
                        LocalDate!! date
    ) {

    }

    
    public String!! getStringNotNull() {
        return string;
    }

    
    public String!! getStringNotNull2() {
        return string;
    }

    
    public String? getStringNull() {
        return string;
    }

    interface Datable {
        
        public Integer? select( String? element,
                              int i,
                               Object!! o,
                               LocalDate!! date
        );
    }

    public enum FieldFoldingAnnotation {
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;

        FieldFoldingAnnotation(String... annotations) {

        }

        
        public static int!! select( String? element,
                                 int i,
                                  Object!! o,
                                  LocalDate!! date
        ) {
            return 1;
        }

    }

    public record UserDataRecord( String!! username, boolean active,  String? userIdentifier,  String!! username2) {
    }

    @Getter class GetterNullable {
        NullableAnnotationTestData? getterNullable;
    }

    @Setter class SetterNullable {
        NullableAnnotationTestData!! setterNullable;
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
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
        };

        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, "1");


        const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
            int i = 1;
        };
        const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
            public void setOk(boolean ok) {
            }
        };

        const PUBLIC_STATIC_FINAL_VAR = "";
        const PRIVATE_STATIC_FINAL_VAR = "";
        const PROTECTED_STATIC_FINAL_VAR = "";
        const DEFAULT_STATIC_FINAL_VAR = "";
        const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        const VERSION = 1;
        const int VERSION_REF = VERSION;
    }

}
