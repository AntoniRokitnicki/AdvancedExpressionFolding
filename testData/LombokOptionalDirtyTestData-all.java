package data;

import java.util.Optional;

public class LombokOptionalDirtyTestData {

    public static class DirtyOptional <fold text='{...}' expand='true'>{
        private String optionalField;
        public Optional<String> getOptionalField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>optionalField<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        private String alias;
        public String getAlias()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>optionalField != null ? </fold>optionalField<fold text=' ?: ' expand='false'> : </fold>alias<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
