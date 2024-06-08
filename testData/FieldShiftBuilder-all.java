<fold text='📦' expand='false'>package</fold> data;

<fold text='@Builder @AllArgsConstructor(default) @Getter p' expand='false'>p</fold>ublic <fold text='🏛️' expand='false'>class</fold> FieldShiftBuilder {
    <fold text='🚫' expand='false'>private</fold> String username;
    <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
    <fold text='🚫' expand='false'>private</fold> String userIdentifier;
    <fold text='🚫' expand='false'>private</fold> FieldShiftBuilder child;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>FieldShiftBuilder(String username, <fold text='🔘' expand='false'>boolean</fold> active, String userIdentifier, FieldShiftBuilder child) <fold text='{...}' expand='true'>{
        this.username = <fold text='<<' expand='false'>username</fold>;
        this.active = <fold text='<<' expand='false'>active</fold>;
        this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        this.child = <fold text='<<' expand='false'>child</fold>;
    }</fold></fold>


    public <fold text='⚡' expand='false'>static</fold> FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>BuilderFieldShiftBuilder</fold> builder1 = builder
                .username(record<fold text='<<' expand='true'>.username()</fold>);
        <fold text='val' expand='false'>var</fold> builder2 = builder
                .active(source<fold text='<<' expand='true'>.isActive()</fold>);
        <fold text='🔙' expand='false'>return</fold> FieldShiftBuilder.builder().username(record.<fold text='userIdentifier' expand='false'>userIdentifier()</fold>).username(changer(record.<fold text='username' expand='false'>username()</fold>))
                .username(source<fold text='<<' expand='true'>.getUsername()</fold>).username(builder.username("a").build()<fold text='<<' expand='true'>.getUsername()</fold>)
                .username<fold text='("${' expand='false'>(</fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='}' expand='false'> + "</fold>1")
                .active(source<fold text='<<' expand='true'>.isActive()</fold>).userIdentifier(source<fold text='<<' expand='true'>.getUserIdentifier()</fold>)
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.getUsername())
                                .username(source<fold text='<<' expand='true'>.getUsername()</fold>)
                                .build()<fold text='<<' expand='true'>.getChild()</fold>)
                        .active(builder.build()<fold text='<<' expand='true'>.isActive()</fold>)
                        .username(builder.build()<fold text='<<' expand='true'>.getUsername()</fold>)
                        .userIdentifier(record<fold text='<<' expand='true'>.userIdentifier()</fold>)
                        .build())
                .child(builder2
                        .username(source<fold text='<<' expand='true'>.getUsername()</fold>)
                        .build())
                .build();
    }</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String changer(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>


    public <fold text='⚡' expand='false'>static</fold> FieldShiftBuilder mapSimple(FieldShiftBuilder source) <fold text='{...}' expand='true'>{
        <fold text='🔙' expand='false'>return</fold> FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='true'>.getUsername()</fold>)
                .userIdentifier(source<fold text='<<' expand='true'>.getUserIdentifier()</fold>)
                .build();
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) <fold text='{...}' expand='true'>{
        <fold text='🔙' expand='false'>return</fold> FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='true'>.username()</fold>)
                .active(source<fold text='<<' expand='true'>.active()</fold>)
                .userIdentifier(source<fold text='<<' expand='true'>.userIdentifier()</fold>)
                .build();
    }</fold>

    public <fold text='⚡' expand='false'>static</fold> BuilderFieldShiftBuilder builder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new BuilderFieldShiftBuilder()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.active<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public FieldShiftBuilder getChild()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.child<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='@Builder @AllArgsConstructor(default) @Getter p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> UserData2 <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> String username;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
        <fold text='🚫' expand='false'>private</fold> String userIdentifier;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>UserData2(String username, <fold text='🔘' expand='false'>boolean</fold> active, String userIdentifier) <fold text='{...}' expand='true'>{
            this.username = <fold text='<<' expand='false'>username</fold>;
            this.active = <fold text='<<' expand='false'>active</fold>;
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        }</fold></fold>

        public <fold text='⚡' expand='false'>static</fold> UserData2Builder builder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>new UserData2Builder()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.username<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.active<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        <fold text='@NoArgsConstructor(default) @ToString p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> UserData2Builder <fold text='{...}' expand='true'>{
            <fold text='🚫' expand='false'>private</fold> String username;
            <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
            <fold text='🚫' expand='false'>private</fold> String userIdentifier;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>UserData2Builder() <fold text='{}' expand='true'>{
            }</fold></fold>

            public UserData2Builder username(String username) <fold text='{...}' expand='true'>{
                this.username = <fold text='<<' expand='false'>username</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public UserData2Builder active(<fold text='🔘' expand='false'>boolean</fold> active) <fold text='{...}' expand='true'>{
                this.active = <fold text='<<' expand='false'>active</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public UserData2Builder userIdentifier(String userIdentifier) <fold text='{...}' expand='true'>{
                this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
                <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
            }</fold>

            public UserData2 build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new UserData2(username, active, userIdentifier)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public String toString() <fold text='{...}' expand='true'>{
                <fold text='🔙' expand='false'>return</fold> "BuilderFieldShift.UserData2.UserData2Builder(username=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.username<fold text='}' expand='false'> + "</fold>, active=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.active<fold text='}' expand='false'> + "</fold>, userIdentifier=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='}' expand='false'> + "</fold>)";
            }</fold></fold>
        }</fold>
    }</fold>

    public <fold text='📀' expand='false'>record</fold> UserDataRecord(String username, <fold text='🔘' expand='false'>boolean</fold> active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@NoArgsConstructor(default) @ToString p' expand='false'>p</fold>ublic <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> BuilderFieldShiftBuilder <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> String username;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔘' expand='false'>boolean</fold> active;
        <fold text='🚫' expand='false'>private</fold> String userIdentifier;
        <fold text='🚫' expand='false'>private</fold> FieldShiftBuilder child;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>BuilderFieldShiftBuilder() <fold text='{}' expand='true'>{
        }</fold></fold>

        public BuilderFieldShiftBuilder username(String username) <fold text='{...}' expand='true'>{
            this.username = <fold text='<<' expand='false'>username</fold>;
            <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
        }</fold>

        public BuilderFieldShiftBuilder active(<fold text='🔘' expand='false'>boolean</fold> active) <fold text='{...}' expand='true'>{
            this.active = <fold text='<<' expand='false'>active</fold>;
            <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
        }</fold>

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) <fold text='{...}' expand='true'>{
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
            <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
        }</fold>

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) <fold text='{...}' expand='true'>{
            this.child = <fold text='<<' expand='false'>child</fold>;
            <fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;
        }</fold>

        public FieldShiftBuilder build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new FieldShiftBuilder(username, active, userIdentifier, child)<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String toString() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> "BuilderFieldShift.BuilderFieldShiftBuilder(username=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.username<fold text='}' expand='false'> + "</fold>, active=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.active<fold text='}' expand='false'> + "</fold>, userIdentifier=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.userIdentifier<fold text='}' expand='false'> + "</fold>, child=<fold text='${' expand='false'>" + </fold><fold text='📍' expand='false'>this</fold>.child<fold text='}' expand='false'> + "</fold>)";
        }</fold></fold>
    }</fold>
}
