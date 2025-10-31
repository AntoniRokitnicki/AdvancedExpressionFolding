package data;

public class LombokOptionalDirtyTestData {

    public static class OptionalFalseCases <fold text='{...}' expand='true'>{
        // 1. optional = false, dirty = none
        <fold text='@Getter p' expand='false'>p</fold>rivate String plain;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public String getPlain()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>plain<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        // 2. optional = false, dirty = dirty
        private String alias;
        private String fallback;
        public String getAlias()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>alias != null ? </fold>alias<fold text=' ?: ' expand='false'> : </fold>fallback<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        // 3. optional = false, dirty = dirtyNoReference
        private String detached;
        <fold text='' expand='true'>public</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>getDetached<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"fallback"<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
