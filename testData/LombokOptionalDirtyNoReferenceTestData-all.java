package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class OptionalTrueCases <fold text='{...}' expand='true'>{
        // 4. optional = true, dirty = none (dirty heuristic fires but optional wins)
        private String optionalPlain;
        public Optional<String> getOptionalPlain()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>optionalPlain<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        // 5. optional = true, dirty = dirty (optional still wins)
        private String optionalAliased;
        public Optional<String> getOptionalAliased()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> <fold text='' expand='false'></fold>Optional.ofNullable(</fold>optionalAliased<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        // 6. optional = true, dirty = dirtyNoReference (optional flag disappears without a field reference)
        private String optionalDetached;
        public Optional<String> getOptionalDetached()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>"fallback"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
