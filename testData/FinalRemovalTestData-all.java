package data;

public class FinalRemovalTestData {

    <fold text='const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public<fold text='' expand='false'> final</fold> String m() <fold text='{...}' expand='true'>{
        <fold text='' expand='false'><fold text='val' expand='false'>final </fold>String</fold> s = "1";
        <fold text='' expand='false'><fold text='val' expand='false'>final </fold>var</fold> s2 = "2";
        <fold text='' expand='false'><fold text='val' expand='false'>final </fold>var</fold> s3 = "3";
        <fold text='val' expand='false'>@Deprecated<fold text='' expand='false'> final</fold> String</fold> annotated = "4";
        return s + s2 + s3;
    }</fold>

    <fold text='' expand='true'>public</fold> <fold text='' expand='true'>synchronized</fold><fold text='' expand='false'> <fold text='' expand='false'>final</fold></fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>methodFinalLast<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>""<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='true'>public</fold><fold text='' expand='false'> <fold text='' expand='false'>final</fold></fold> <fold text='' expand='true'>synchronized</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String<fold text='' expand='true'></fold> </fold>methodFinalMiddle<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>""<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='' expand='false'><fold text='' expand='false'>final</fold> </fold><fold text='' expand='true'>public</fold> <fold text='' expand='true'>synchronized</fold><fold text='' expand='true'> </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>methodFinalFirst<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>""<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold>

    <fold text='' expand='true'>public</fold>
    <fold text='' expand='false'>final</fold><fold text='' expand='true'> 
    </fold><fold text='' expand='true'>String</fold><fold text='' expand='true'> </fold>methodFinalSeparatedLines<fold text='' expand='true'>()</fold><fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>""<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    interface A <fold text='{...}' expand='true'>{
        void main(<fold text='' expand='false'>final </fold>String arg, <fold text='' expand='false'>final </fold>int i, <fold text='' expand='false'>final </fold>Object o, Data data);

        void modifiers(<fold text='' expand='false'>final </fold>int first,
                @Deprecated<fold text='' expand='false'> final</fold> String second,
                <fold text='' expand='false'>final </fold>@Deprecated Object third,
                Data data,
                <fold text='' expand='false'>final 
</fold>                        String multilineFinal,
                String plain);
    }</fold>

    <fold text='' expand='false'>final </fold>static class Data <fold text='{...}' expand='true'>{
        Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }</fold>

    <fold text='' expand='false'>final </fold>public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>final </fold>void main(<fold text='' expand='false'>final </fold>String arg, <fold text='' expand='false'>final </fold>int i, <fold text='' expand='false'>final </fold>Object o, Data data) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

}
