package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class DirtyOptionalNoReference <fold text='{...}' expand='true'>{
        private String optionalField;
        public Optional<String> getOptionalField()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>optionalField<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        private String fallback;
        <fold text='' expand='true'>public<fold text='' expand='true'></fold> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>getFallback<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"fallback"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
