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

    public record UserDataRecord(@Nonnull String username, boolean active, @Nullable String userIdentifier, @NotNull String username2) {
    }

}
