package data;

import java.util.List;

public class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;
    private List<String> list;

    public FieldShiftSetters() <fold text='{}' expand='true'>{
    }</fold>

    public void setUsername(String username)<fold text=' { ' expand='false'> {
        </fold>this.username = <fold text='<<' expand='true'>username</fold>;<fold text=' }' expand='false'>
    }</fold>

    public void setActive(boolean active)<fold text=' { ' expand='false'> {
        </fold>this.active = <fold text='<<' expand='true'>active</fold>;<fold text=' }' expand='false'>
    }</fold>

    public void setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {
        </fold>this.userIdentifier = <fold text='<<' expand='true'>userIdentifier</fold>;<fold text=' }' expand='false'>
    }</fold>

    public void setChild(FieldShiftSetters child)<fold text=' { ' expand='false'> {
        </fold>this.child = <fold text='<<' expand='true'>child</fold>;<fold text=' }' expand='false'>
    }</fold>

    public List<String> getList()<fold text=' { ' expand='false'> {
        </fold>return list;<fold text=' }' expand='false'>
    }</fold>

    public void setList(List<String> list)<fold text=' { ' expand='false'> {
        </fold>this.list = <fold text='<<' expand='true'>list</fold>;<fold text=' }' expand='false'>
    }</fold>

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        FieldShiftSetters result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        FieldShiftSetters result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='list = ' expand='false'>setList(</fold>List.copyOf(source<fold text='<<' expand='false'>.getList()</fold>)<fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters mapRecord(UserDataRecord source) <fold text='{...}' expand='true'>{
        FieldShiftSetters result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.username()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.active()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) <fold text='{...}' expand='true'>{
        FieldShiftSetters var1 = setters;
        var1.<fold text='username = ' expand='false'>setUsername(</fold>record<fold text='<<' expand='false'>.username()</fold><fold text='' expand='false'>)</fold>;
        FieldShiftSetters var2 = var1;
        var2.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        FieldShiftSetters result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>record.<fold text='userIdentifier' expand='false'>userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>changer(record<fold text='<<' expand='false'>.username()</fold>)<fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>var1.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='username' expand='false'>getUsername()</fold> + "1"<fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='username' expand='false'>getUsername()</fold> + source.<fold text='userIdentifier' expand='false'>getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.<fold text='child = ' expand='false'>setChild(</fold>var1<fold text='<<' expand='false'>.getChild()</fold><fold text='' expand='false'>)</fold>;
        var1.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='' expand='false'>)</fold>;
        var1.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='child' expand='false'>getChild()</fold>;
        setters2.<fold text='active = ' expand='false'>setActive(</fold>setters<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='username = ' expand='false'>setUsername(</fold>setters<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>record<fold text='<<' expand='false'>.userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='child = ' expand='false'>setChild(</fold>setters2<fold text='' expand='false'>)</fold>;
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='child = ' expand='false'>setChild(</fold>childBuilder2.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getChild()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    private static String changer(String username)<fold text=' { ' expand='false'> {
        </fold>return username;<fold text=' }' expand='false'>
    }</fold>



    public String getUsername()<fold text=' { ' expand='false'> {
        </fold>return this.username;<fold text=' }' expand='false'>
    }</fold>

    public boolean isActive()<fold text=' { ' expand='false'> {
        </fold>return this.active;<fold text=' }' expand='false'>
    }</fold>

    public String getUserIdentifier()<fold text=' { ' expand='false'> {
        </fold>return this.userIdentifier;<fold text=' }' expand='false'>
    }</fold>

    public FieldShiftSetters getChild()<fold text=' { ' expand='false'> {
        </fold>return this.child;<fold text=' }' expand='false'>
    }</fold>

    public static class UserData2 <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;

        public UserData2() <fold text='{}' expand='true'>{
        }</fold>

        public void setUsername(String username)<fold text=' { ' expand='false'> {
            </fold>this.username = <fold text='<<' expand='true'>username</fold>;<fold text=' }' expand='false'>
        }</fold>

        public void setActive(boolean active)<fold text=' { ' expand='false'> {
            </fold>this.active = <fold text='<<' expand='true'>active</fold>;<fold text=' }' expand='false'>
        }</fold>

        public void setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {
            </fold>this.userIdentifier = <fold text='<<' expand='true'>userIdentifier</fold>;<fold text=' }' expand='false'>
        }</fold>

        public String getUsername()<fold text=' { ' expand='false'> {
            </fold>return this.username;<fold text=' }' expand='false'>
        }</fold>

        public boolean isActive()<fold text=' { ' expand='false'> {
            </fold>return this.active;<fold text=' }' expand='false'>
        }</fold>

        public String getUserIdentifier()<fold text=' { ' expand='false'> {
            </fold>return this.userIdentifier;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>
}
