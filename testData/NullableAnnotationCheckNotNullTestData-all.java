package data;

import org.jetbrains.annotations.Nullable;

<fold text='@Getter @' expand='false'>@</fold>SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    private String saaa;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getSaaa()<fold text=' { ' expand='false'> {
        </fold>return saaa;<fold text=' }' expand='false'>
    }</fold></fold>

    class PreconditionsCheck <fold text='{...}' expand='true'>{
        public void main(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='true'><fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
        }</fold>

        public void mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
        }</fold>

        public void mainNullable(@Nullable String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold> Object<fold text='? ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold> NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
        }</fold>

        public void mainMsgsNullable(@Nullable String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold> Object<fold text='? ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold> NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
        }</fold>

    }</fold>

    class Preconditions <fold text='{...}' expand='true'>{
        public static void checkNotNull(Object o, String s) <fold text='{}' expand='true'>{
        }</fold>
        public static void checkNotNull(Object o) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>
}
