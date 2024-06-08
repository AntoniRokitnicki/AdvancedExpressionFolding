<fold text='📦' expand='false'>package</fold> data;

<fold text='@NoArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> FieldShiftSetters {
    <fold text='🚫' expand='false'>private</fold> String username;
    <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
    <fold text='🚫' expand='false'>private</fold> String userIdentifier;
    <fold text='🚫' expand='false'>private</fold> FieldShiftSetters child;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public FieldShiftSetters() <fold text='{}' expand='true'>{
    }</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setUsername(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.username = <fold text='<<' expand='false'>username</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setActive(<fold text='🔘' expand='false'>boolean</fold> active)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.active = <fold text='<<' expand='false'>active<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setChild(FieldShiftSetters child)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='📍' expand='false'>this</fold>.child = <fold text='<<' expand='false'>child</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    public <fold text='⚡' expand='false'>static</fold> FieldShiftSetters mapPojoChain(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold>.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source.<fold text='child' expand='false'>getChild()</fold><fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        <fold text='🔙' expand='false'>return</fold> result;
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> FieldShiftSetters mapPojo(FieldShiftSetters source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.getUsername()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.getUserIdentifier()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.isActive()</fold><fold text='' expand='false'>)</fold>;
        <fold text='🔙' expand='false'>return</fold> result;
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> FieldShiftSetters mapRecord(UserDataRecord source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>FieldShiftSetters</fold> result = new FieldShiftSetters();
        result.<fold text='username = ' expand='false'>setUsername(</fold>source<fold text='<<' expand='false'>.username()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='active = ' expand='false'>setActive(</fold>source<fold text='<<' expand='false'>.active()</fold><fold text='' expand='false'>)</fold>;
        result.<fold text='userIdentifier = ' expand='false'>setUserIdentifier(</fold>source<fold text='<<' expand='false'>.userIdentifier()</fold><fold text='' expand='false'>)</fold>;
        <fold text='🔙' expand='false'>return</fold> result;
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) <fold text='{...}' expand='true'>{
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
        <fold text='🔙' expand='false'>return</fold> result;
    }</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String changer(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'>



    </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.active<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public FieldShiftSetters getChild()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.child<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='@NoArgsConstructor @Getter @Setter p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> UserData2 <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> String username;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
        <fold text='🚫' expand='false'>private</fold> String userIdentifier;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public UserData2() <fold text='{}' expand='true'>{
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setUsername(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.username = <fold text='<<' expand='false'>username</fold><fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text=' ' expand='true'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setActive(<fold text='🔘' expand='false'>boolean</fold> active)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.active = <fold text='<<' expand='false'>active<fold text='' expand='true'></fold>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setUserIdentifier(String userIdentifier)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> <fold text='📍' expand='false'></fold>this</fold>.username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.active<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
    }</fold>

    public <fold text='📀' expand='false'>record</fold> UserDataRecord(String username, <fold text='🔘' expand='false'>boolean</fold> active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>
}
