package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class OptionalTrueCases <fold text='{...}' expand='true'>{
        // 4. optional = true, dirty = none (dirty heuristic fires but optional wins)
        <fold text='@Getter(optional = true) p' expand='false'>p</fold>rivate String optionalPlain;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public Optional<String> getOptionalPlain()<fold text=' { ' expand='false'> {
            </fold>return Optional.ofNullable(optionalPlain);<fold text=' }' expand='false'>
        }</fold></fold>

        // 5. optional = true, dirty = dirty (optional still wins)
        <fold text='@Getter(optional = true) p' expand='false'>p</fold>rivate String optionalAliased;<fold text='' expand='false'>
        <fold text='' expand='false'></fold>public Optional<String> getOptionalAliased()<fold text=' { ' expand='false'> {
            </fold>return Optional.ofNullable(optionalAliased);<fold text=' }' expand='false'>
        }</fold></fold>

        // 6. optional = true, dirty = dirtyNoReference (optional flag disappears without a field reference)
        <fold text='@Getter(dirtyNoReference) p' expand='false'>p</fold>rivate String optionalDetached;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public Optional<String> getOptionalDetached()<fold text=' { ' expand='false'> {
            </fold>return Optional.ofNullable("fallback");<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>
}
