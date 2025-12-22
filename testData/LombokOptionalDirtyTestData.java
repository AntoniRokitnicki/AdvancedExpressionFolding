package data;

import java.util.Optional;

public class LombokOptionalDirtyTestData {

    public static class DirtyOptional <fold text='{...}' expand='true'>{
        <fold text='@Getter(optional = true) p' expand='false'>p</fold>rivate String optionalField;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public Optional<String> getOptionalField()<fold text=' { ' expand='false'> {
            </fold>return Optional.ofNullable(optionalField);<fold text=' }' expand='false'>
        }</fold></fold>

        <fold text='@Getter(dirty) p' expand='false'>p</fold>rivate String alias;<fold text='' expand='false'>
        </fold><fold text='' expand='false'>public String getAlias()<fold text=' { ' expand='false'> {
            </fold>return optionalField != null ? optionalField : alias;<fold text=' }' expand='false'>
        }</fold></fold>
    }</fold>
}
