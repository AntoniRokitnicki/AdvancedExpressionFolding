package data;

<fold text='@ToStringˣ p' expand='false'>p</fold>ublic class FinalEmojiTestData {

    <fold text='const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>final private static </fold><fold text='' expand='false'>String</fold> FINAL_FIRST_MANY = "";
    final String ONLY_FINAL = "";<fold text='' expand='false'>

    </fold><fold text='' expand='false'>@Override
    public <fold text='' expand='false'>final</fold> String toString() <fold text='{...}' expand='true'>{
        <fold text='' expand='false'><fold text='val' expand='false'>final</fold> String</fold> s = "1";
        <fold text='' expand='false'><fold text='val' expand='false'>final</fold> var</fold> s2 = "2";
        <fold text='val' expand='false'>var</fold> s3 = "3";
        return s + s2 + s3;
    }</fold></fold>

    interface A <fold text='{...}' expand='true'>{
        void main(<fold text='' expand='false'>final</fold> String arg, <fold text='' expand='false'>final</fold> int i, <fold text='' expand='false'>final</fold> Object o, data.FinalRemovalTestData.Data data);
    }</fold>

    <fold text='' expand='false'>final</fold> static class Data <fold text='{...}' expand='true'>{
        data.FinalRemovalTestData.Data data;
        final boolean ok = true;
        protected final boolean ok2 = true;
        final protected boolean ok3 = true;
    }</fold>

    <fold text='' expand='false'>final</fold> public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
        <fold text='' expand='false'>final</fold> void main(<fold text='' expand='false'>final</fold> String arg, <fold text='' expand='false'>final</fold> int i, <fold text='' expand='false'>final</fold> Object o, data.FinalRemovalTestData.Data data) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

}

