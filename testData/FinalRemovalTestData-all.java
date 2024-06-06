<fold text='📦' expand='false'>package</fold> data;

public class FinalRemovalTestData {

    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    <fold text='🔒' expand='false'>final</fold> private <fold text='⚡' expand='false'>static</fold> String FINAL_FIRST_MANY = "";
    <fold text='🔒' expand='false'>final</fold> String ONLY_FINAL = "";

    public <fold text='🔒' expand='false'>final</fold> String m() <fold text='{...}' expand='true'>{
        <fold text='🔒' expand='false'><fold text='val' expand='false'>final</fold> String</fold> s = "1";
        <fold text='🔒' expand='false'><fold text='val' expand='false'>final</fold> var</fold> s2 = "2";
        <fold text='val' expand='false'>var</fold> s3 = "3";
        return s + s2 + s3;
    }</fold>

    interface A <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> main(<fold text='🔒' expand='false'>final</fold> String arg, <fold text='🔒' expand='false'>final</fold> int i, <fold text='🔒' expand='false'>final</fold> Object o, Data data);
    }</fold>

    <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> class Data <fold text='{...}' expand='true'>{
        Data data;
        <fold text='🔒' expand='false'>final</fold> boolean ok = <fold text='✅' expand='false'>true</fold>;
        <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'>final</fold> boolean ok2 = <fold text='✅' expand='false'>true</fold>;
        <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> boolean ok3 = <fold text='✅' expand='false'>true</fold>;
    }</fold>

    <fold text='🔒' expand='false'>final</fold> public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
        <fold text='🔒' expand='false'>final</fold> <fold text='💀' expand='false'>void</fold> main(<fold text='🔒' expand='false'>final</fold> String arg, <fold text='🔒' expand='false'>final</fold> int i, <fold text='🔒' expand='false'>final</fold> Object o, Data data) <fold text='{}' expand='true'>{
        }</fold>
    }</fold>

}
