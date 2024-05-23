package data;

<fold text='@NoArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public FieldShiftSetters() <fold text='{}' expand='true'>{
    }</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setUsername(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.username = username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setActive(boolean active)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.active = active<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public void setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.userIdentifier = userIdentifier<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public void setChild(FieldShiftSetters child)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold>this.child = child<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold></fold>

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters mapRecord(UserDataRecord source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.username()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.active()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> var1 = setters;
        var1.<fold text='username = ' expand='false'>setUsername(</fold>record<fold text='<<' expand='false'>.username()</fold><fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>FieldShiftSetters</fold> var2 = var1;
        var2.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>record.<fold text='userIdentifier' expand='false'>userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>changer(record.<fold text='username' expand='false'>username()</fold>)<fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>var1.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername</fold><fold text='"${' expand='false'>(</fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='}' expand='false'> + "</fold>1"<fold text='' expand='false'>)</fold>;
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='username' expand='false'>getUsername()</fold> + source.<fold text='userIdentifier' expand='false'>getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>FieldShiftSetters</fold> setters2 = new FieldShiftSetters();
        setters2.<fold text='child = ' expand='false'>setChild(</fold>var1<fold text='<<' expand='false'>.getChild()</fold><fold text='' expand='false'>)</fold>;
        var1.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='' expand='false'>)</fold>;
        var1.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='child' expand='false'>getChild()</fold>;
        setters2.<fold text='active = ' expand='false'>setActive(</fold>setters<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='username = ' expand='false'>setUsername(</fold>setters<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        setters2.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>record<fold text='<<' expand='false'>.userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='child = ' expand='false'>setChild(</fold>setters2<fold text='' expand='false'>)</fold>;
        <fold text='val' expand='false'>FieldShiftSetters</fold> childBuilder2 = new FieldShiftSetters();
        childBuilder2.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='child = ' expand='false'>setChild(</fold>childBuilder2.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getChild()</fold><fold text='' expand='false'>)</fold>;
        return result;
    }</fold>

    private static String changer(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>username<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'>



    </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.username<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.active<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.userIdentifier<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public FieldShiftSetters getChild()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.child<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='@NoArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic static class UserData2 <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;<fold text='' expand='false'>

        <fold text='' expand='false'></fold>public UserData2() <fold text='{}' expand='true'>{
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setUsername(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.username = username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setActive(boolean active)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.active = active<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.userIdentifier = userIdentifier<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.username<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.active<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.userIdentifier<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>
}
