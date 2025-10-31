package data;

public class LombokOptionalDirtyTestData {

    public static class OptionalFalseCases <fold text='{...}' expand='true'>{
        // 1. optional = false, dirty = none
        <fold text='@Getter p' expand='false'>p</fold>rivate String plain;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public String getPlain()<fold text=' { ' expand='false'> {
            </fold>return plain;<fold text=' }' expand='false'>
        }</fold></fold>

        // 2. optional = false, dirty = dirty
        <fold text='@Getter(dirty) p' expand='false'>p</fold>rivate String alias;
        private String fallback;<fold text='' expand='false'>
        <fold text='' expand='false'></fold>public String getAlias()<fold text=' { ' expand='false'> {
            </fold>return alias != null ? alias : fallback;<fold text=' }' expand='false'>
        }</fold></fold>

        // 3. optional = false, dirty = dirtyNoReference
        <fold text='@Getter(dirtyNoReference) p' expand='false'>p</fold>rivate String detached;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public String getDetached()<fold text=' { ' expand='false'> {
            </fold>return "fallback";<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>
}
