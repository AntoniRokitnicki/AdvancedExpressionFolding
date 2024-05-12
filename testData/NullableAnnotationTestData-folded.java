package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Getter @Setterˣ @Serial public class NullableAnnotationTestData {
    
    NullableAnnotationTestData!! data;
    boolean ok;
    
    String? string;

    
    private NullableAnnotationTestData!! data2;
    boolean ok2;
    
    private String? string2;

    public void select(String? element,
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
        
        public Integer? select(String? element,
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

        
        public static int!! select(String? element,
                                 int i,
                                 Object!! o,
                                 LocalDate!! date
        ) {
            return 1;
        }

    }

    public record UserDataRecord(String!! username, boolean active, String? userIdentifier, String!! username2) {
    }

    @Getter class GetterNullable {
        NullableAnnotationTestData? getterNullable;
    }

    @Setter class SetterNullable {
        NullableAnnotationTestData!! setterNullable;
    }

}
