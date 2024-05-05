package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter* @Setter* @Serial @SuppressWarnings("ALL")
public class NullableAnnotationTestData {
    
    NullableAnnotationTestData!! data;
    boolean ok;
    
    String? string;

    
    private NullableAnnotationTestData!! data2;
    boolean ok2;
    
    private String? string2;

    public void select( String? element,
                       int i,
                        Object!! o) {

    }

    
    public String!! getStringNotNull() {
        return string;
    }

    
    public String? getStringNull() {
        return string;
    }

}
