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
    
    @Getter @Setter NullableAnnotationTestData!! data;
    @Getter @Setter boolean ok;
    
    @Getter @Setter String? string;

    
    private NullableAnnotationTestData!! data2;
    boolean ok2;
    
    private String? string2;

    public void select(String? element,
                       int i,
                       Object!! o,
                       LocalDate!! date
                       ) {
        new HashMap<String, String>().put("a", "b");

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
        NullableAnnotationTestData getterNullable;
        public NullableAnnotationTestData? getGetterNullable() {
            return getterNullable;
        }
    }

    @Setter class SetterNullable {
        NullableAnnotationTestData setterNullable;
    }

    public class LombokFieldLevelIntegration {
        public class HasGetter {
            
            @Getter private String? field;
            private String bla;
        }

        public class HasSetter {
            
            @Setter private String? field;
            private String bla;
        }

        public class HasGetterSetter {
            
            @Getter(dirty) @Setter private String? field;
            private String bla;
        }
    }

    public class LombokFieldLevelNotPrivateIntegration {
        public class HasGetter {
            
            @Getter String? field;
            String bla;
        }

        public class HasSetter {
            
            @Setter String? field;
            String bla;
        }

        public class HasGetterSetter {
            
            @Getter @Setter String? field;
            String bla;
        }
    }


}
