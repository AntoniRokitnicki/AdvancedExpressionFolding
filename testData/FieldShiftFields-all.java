package data;

import java.util.List;

<fold text='@NoArgsConstructor p' expand='false'>p</fold>ublic class FieldShiftFields {
    <fold text='@Getter p' expand='false'>p</fold>ublic String username;
    <fold text='@Getter p' expand='false'>p</fold>ublic boolean active;
    <fold text='@Getter p' expand='false'>p</fold>ublic String userIdentifier;
    <fold text='@Getter p' expand='false'>p</fold>ublic FieldShiftFields child;<fold text='' expand='false'>


    </fold><fold text='' expand='false'>public FieldShiftFields() <fold text='{}' expand='true'>{

    }</fold></fold>
    private List<String> list;

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) <fold text='{...}' expand='true'>{
        this.username = <fold text='<<' expand='true'>username</fold>;
        this.active = <fold text='<<' expand='true'>active</fold>;
        this.userIdentifier = <fold text='<<' expand='true'>userIdentifier</fold>;
        this.child = <fold text='<<' expand='true'>child</fold>;
        this.userIdentifier = child.<fold text='<<' expand='true'>userIdentifier</fold>;
        this.userIdentifier = child.<fold text='<<' expand='false'>getUserIdentifier()</fold>;
    }</fold>

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = source.child.<fold text='<<' expand='true'>username</fold>;
        result.userIdentifier = source.child.child.child.<fold text='<<' expand='true'>userIdentifier</fold>;
        result.active = source.child.<fold text='<<' expand='true'>active</fold>;
        result.list = List.copyOf(source.<fold text='<<' expand='true'>list</fold>);
        return result;
    }</fold>

    public static FieldShiftFields mapPojo(FieldShiftFields source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = source.<fold text='<<' expand='false'>username()</fold>;
        result.active = source.<fold text='<<' expand='false'>active()</fold>;
        result.userIdentifier = source.<fold text='<<' expand='false'>userIdentifier()</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> var1 = fields;
        var1.username = record.<fold text='<<' expand='true'>username</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> var2 = var1;
        var2.active = source.<fold text='<<' expand='true'>active</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.<fold text='<<' expand='true'>username</fold>);
        result.username = source.<fold text='<<' expand='true'>username</fold>;
        result.username = var1.child.<fold text='<<' expand='true'>username</fold>;
        result.username =<fold text=' "${' expand='false'> </fold>source.username<fold text='}' expand='false'> + "</fold>1";
        result.username = source.username + source.userIdentifier;
        result.active = source.<fold text='<<' expand='true'>active</fold>;
        result.userIdentifier = source.<fold text='<<' expand='true'>userIdentifier</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> setters2 = new FieldShiftFields();
        setters2.child = var1.<fold text='<<' expand='true'>child</fold>;
        var1.userIdentifier = source.username;
        var1.username = source.<fold text='<<' expand='true'>username</fold>;
        setters2.active = fields.<fold text='<<' expand='true'>active</fold>;
        setters2.username = fields.<fold text='<<' expand='true'>username</fold>;
        setters2.userIdentifier = record.<fold text='<<' expand='false'>userIdentifier()</fold>;
        result.child = setters2;
        <fold text='val' expand='false'>FieldShiftFields</fold> childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<fold text='<<' expand='true'>username</fold>;
        result.child = childBuilder2.child.child.child.<fold text='<<' expand='true'>child</fold>;
        return result;
    }</fold>

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftFields</fold> var1 = getters;
        var1.username = record.<fold text='<<' expand='false'>username()</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> var2 = var1;
        var2.active = source.<fold text='<<' expand='false'>isActive()</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.<fold text='<<' expand='false'>username()</fold>);
        result.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        result.username = var1.<fold text='child' expand='false'>getChild()</fold>.<fold text='<<' expand='false'>getUsername()</fold>;
        result.username =<fold text=' "${' expand='false'> </fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='}' expand='false'> + "</fold>1";
        result.username = source.<fold text='username' expand='false'>getUsername()</fold> + source.<fold text='userIdentifier' expand='false'>getUserIdentifier()</fold>;
        result.active = source.<fold text='<<' expand='false'>isActive()</fold>;
        result.userIdentifier = source.<fold text='<<' expand='false'>getUserIdentifier()</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> setters2 = new FieldShiftFields();
        setters2.child = var1.<fold text='<<' expand='false'>getChild()</fold>;
        var1.userIdentifier = (source.<fold text='username' expand='false'>getUsername()</fold>);
        var1.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        setters2.<fold text='child' expand='false'>getChild()</fold>;
        setters2.active = getters.<fold text='<<' expand='false'>isActive()</fold>;
        setters2.username = getters.<fold text='<<' expand='false'>getUsername()</fold>;
        setters2.userIdentifier = record.<fold text='<<' expand='false'>userIdentifier()</fold>;
        result.child = setters2.<fold text='<<' expand='false'>getChild()</fold>;
        <fold text='val' expand='false'>FieldShiftFields</fold> childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<fold text='<<' expand='false'>getUsername()</fold>;
        result.child = childBuilder2.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='<<' expand='false'>getChild()</fold>;
        return result;
    }</fold>


    private static String changer(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>active<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>userIdentifier<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public FieldShiftFields getChild()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>child<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='@Getter p' expand='false'>p</fold>ublic static class UserData2 <fold text='{...}' expand='true'>{
        public String username;
        public boolean active;
        public String userIdentifier;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>active<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>userIdentifier<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>
}
