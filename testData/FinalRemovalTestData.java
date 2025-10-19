package data;

public class FinalRemovalTestData {

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    final private static String FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";

    public<fold text='' expand='false'> final</fold> String m() <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>final </fold>String s = "1";
        <fold text='' expand='false'>final </fold>var s2 = "2";
        <fold text='' expand='false'>final </fold>var s3 = "3";
        @Deprecated<fold text='' expand='false'> final</fold> String annotated = "4";
        return s + s2 + s3;
    }</fold>

    public synchronized<fold text='' expand='false'> final</fold> String methodFinalLast()<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

    public<fold text='' expand='false'> final</fold> synchronized String methodFinalMiddle()<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

    <fold text='' expand='false'>final </fold>public synchronized String methodFinalFirst()<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

    public
<fold text='' expand='false'>    final 
</fold>    String methodFinalSeparatedLines()<fold text=' { ' expand='false'> {
        </fold>return "";<fold text=' }' expand='false'>
    }</fold>

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
