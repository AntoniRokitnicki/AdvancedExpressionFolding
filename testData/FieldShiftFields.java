package data;

public class FieldShiftFields {
    public String username;
    public boolean active;
    public String userIdentifier;
    public FieldShiftFields child;


    public FieldShiftFields() <fold text='{}' expand='true'>{

    }</fold>

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) <fold text='{...}' expand='true'>{
        this.username = <fold text='<<' expand='false'>username</fold>;
        this.active = <fold text='<<' expand='false'>active</fold>;
        this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        this.child = <fold text='<<' expand='false'>child</fold>;
        this.userIdentifier = child.<fold text='<<' expand='true'>userIdentifier</fold>;
        this.userIdentifier = child.<fold text='<<' expand='false'>getUserIdentifier()</fold>;
    }</fold>

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) <fold text='{...}' expand='true'>{
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.<fold text='<<' expand='true'>username</fold>;
        result.userIdentifier = source.child.child.child.<fold text='<<' expand='true'>userIdentifier</fold>;
        result.active = source.child.<fold text='<<' expand='true'>active</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapPojo(FieldShiftFields source) <fold text='{...}' expand='true'>{
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) <fold text='{...}' expand='true'>{
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) <fold text='{...}' expand='true'>{
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='false'>username()</fold>;
        result.active = source.<fold text='<<' expand='false'>active()</fold>;
        result.userIdentifier = source.<fold text='<<' expand='false'>userIdentifier()</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) <fold text='{...}' expand='true'>{
        FieldShiftFields var1 = fields;
        var1.username = record.<fold text='<<' expand='true'>username</fold>;
        FieldShiftFields var2 = var1;
        var2.active = source.<fold text='<<' expand='true'>active</fold>;
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.username = var1.child.<fold text='<<' expand='true'>username</fold>;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.<fold text='<<' expand='true'>child</fold>;
        var1.userIdentifier = source.username;
        var1.username = source.<fold text='<<' expand='true'>username</fold>;
        setters2.active = fields.<fold text='<<' expand='true'>active</fold>;
        setters2.username = fields.<fold text='<<' expand='true'>username</fold>;
        setters2.userIdentifier = record.<fold text='<<' expand='false'>userIdentifier()</fold>;
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<fold text='<<' expand='true'>username</fold>;
        result.child = childBuilder2.child.child.child.<fold text='<<' expand='true'>child</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) <fold text='{...}' expand='true'>{
        FieldShiftFields var1 = getters;
        var1.username = record.<fold text='<<' expand='false'>username()</fold>;
        FieldShiftFields var2 = var1;
        var2.active = source.<fold text='<<' expand='false'>isActive()</fold>;
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.<fold text='username' expand='false'>username()</fold>);
        result.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        result.username = var1.<fold text='child' expand='false'>getChild()</fold>.<fold text='<<' expand='false'>getUsername()</fold>;
        result.username = source.<fold text='username' expand='false'>getUsername()</fold> + "1";
        result.username = source.<fold text='username' expand='false'>getUsername()</fold> + source.<fold text='userIdentifier' expand='false'>getUserIdentifier()</fold>;
        result.active = source.<fold text='<<' expand='false'>isActive()</fold>;
        result.userIdentifier = source.<fold text='<<' expand='false'>getUserIdentifier()</fold>;
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.<fold text='<<' expand='false'>getChild()</fold>;
        var1.userIdentifier = (source.<fold text='username' expand='false'>getUsername()</fold>);
        var1.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        setters2.<fold text='child' expand='false'>getChild()</fold>;
        setters2.active = getters.<fold text='<<' expand='false'>isActive()</fold>;
        setters2.username = getters.<fold text='<<' expand='false'>getUsername()</fold>;
        setters2.userIdentifier = record.<fold text='<<' expand='false'>userIdentifier()</fold>;
        result.child = setters2.<fold text='<<' expand='false'>getChild()</fold>;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        result.child = childBuilder2.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='<<' expand='false'>getChild()</fold>;
        return result;
    }</fold>


    private static String changer(String username)<fold text=' { ' expand='false'> {
        </fold>return username;<fold text=' }' expand='false'>
    }</fold>

    public String getUsername()<fold text=' { ' expand='false'> {
        </fold>return username;<fold text=' }' expand='false'>
    }</fold>

    public boolean isActive()<fold text=' { ' expand='false'> {
        </fold>return active;<fold text=' }' expand='false'>
    }</fold>

    public String getUserIdentifier()<fold text=' { ' expand='false'> {
        </fold>return userIdentifier;<fold text=' }' expand='false'>
    }</fold>

    public FieldShiftFields getChild()<fold text=' { ' expand='false'> {
        </fold>return child;<fold text=' }' expand='false'>
    }</fold>

    public static class UserData2 <fold text='{...}' expand='true'>{
        public String username;
        public boolean active;
        public String userIdentifier;

        public String getUsername()<fold text=' { ' expand='false'> {
            </fold>return username;<fold text=' }' expand='false'>
        }</fold>

        public boolean isActive()<fold text=' { ' expand='false'> {
            </fold>return active;<fold text=' }' expand='false'>
        }</fold>

        public String getUserIdentifier()<fold text=' { ' expand='false'> {
            </fold>return userIdentifier;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>
}
