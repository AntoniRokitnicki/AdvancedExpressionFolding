package data;

import java.util.Optional;

public class LombokOptionalDirtyNoReferenceTestData {

    public static class DirtyOptionalNoReference <fold text='{...}' expand='true'>{
        <fold text='@Getter(optional = true) p' expand='false'>p</fold>rivate String optionalField;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public Optional<String> getOptionalField()<fold text=' { ' expand='false'> {
            </fold>return Optional.ofNullable(optionalField);<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Getter(dirtyNoReference) p' expand='false'>p</fold>rivate String fallback;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public String getFallback()<fold text=' { ' expand='false'> {
            </fold>return "fallback";<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>
}
