package data;

<fold text='@HasBuilder(BuilderFieldShiftBuilder) @AllArgsConstructor(default) @Getter p' expand='false'>p</fold>ublic class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) <fold text='{...}' expand='true'>{
        this.username = <fold text='<<' expand='false'>username</fold>;
        this.active = <fold text='<<' expand='false'>active</fold>;
        this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        this.child = <fold text='<<' expand='false'>child</fold>;
    }</fold></fold>


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>BuilderFieldShiftBuilder</fold> builder1 = builder
                .username(record<fold text='<<' expand='false'>.username()</fold>);
        <fold text='val' expand='false'>var</fold> builder2 = builder
                .active(source<fold text='<<' expand='false'>.isActive()</fold>);
        return FieldShiftBuilder.builder().username(record.<fold text='userIdentifier' expand='false'>userIdentifier()</fold>).username(changer(record<fold text='<<' expand='false'>.username()</fold>))
                .username(source<fold text='<<' expand='false'>.getUsername()</fold>).username(builder.username("a").build()<fold text='<<' expand='false'>.getUsername()</fold>)
                .username<fold text='("${' expand='false'>(</fold>source.<fold text='username' expand='false'>getUsername()</fold><fold text='}' expand='false'> + "</fold>1")
                .active(source<fold text='<<' expand='false'>.isActive()</fold>).userIdentifier(source<fold text='<<' expand='false'>.getUserIdentifier()</fold>)
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.<fold text='username' expand='false'>getUsername()</fold>)
                                .username(source<fold text='<<' expand='false'>.getUsername()</fold>)
                                .build()<fold text='<<' expand='false'>.getChild()</fold>)
                        .active(builder.build()<fold text='<<' expand='false'>.isActive()</fold>)
                        .username(builder.build()<fold text='<<' expand='false'>.getUsername()</fold>)
                        .userIdentifier(record<fold text='<<' expand='false'>.userIdentifier()</fold>)
                        .build())
                .child(builder2
                        .username(source<fold text='<<' expand='false'>.getUsername()</fold>)
                        .build())
                .build();
    }</fold>

    private static String changer(String username)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) <fold text='{...}' expand='true'>{
        return FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='false'>.getUsername()</fold>)
                .userIdentifier(source<fold text='<<' expand='false'>.getUserIdentifier()</fold>)
                .build();
    }</fold>

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) <fold text='{...}' expand='true'>{
        return FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='false'>.username()</fold>)
                .active(source<fold text='<<' expand='false'>.active()</fold>)
                .userIdentifier(source<fold text='<<' expand='false'>.userIdentifier()</fold>)
                .build();
    }</fold>

    public static BuilderFieldShiftBuilder builder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new BuilderFieldShiftBuilder()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.active<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
    </fold>}</fold><fold text='' expand='false'></fold>

    </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.userIdentifier<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold><fold text='' expand='false'>

    <fold text='' expand='false'></fold>public FieldShiftBuilder getChild()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.child<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    <fold text='@HasBuilder @AllArgsConstructor(default) @Getter p' expand='false'>p</fold>ublic static class UserData2 <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>UserData2(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
            this.username = <fold text='<<' expand='false'>username</fold>;
            this.active = <fold text='<<' expand='false'>active</fold>;
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        }</fold></fold>

        public static UserData2Builder builder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new UserData2Builder()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        <fold text='' expand='false'></fold>public String getUsername()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.username<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isActive()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.active<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public String getUserIdentifier()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this.userIdentifier<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold></fold>

        <fold text='@NoArgsConstructor(default) @ToString p' expand='false'>p</fold>ublic static class UserData2Builder <fold text='{...}' expand='true'>{
            private String username;
            private boolean active;
            private String userIdentifier;<fold text='' expand='false'>

            </fold><fold text='' expand='false'>UserData2Builder() <fold text='{}' expand='true'>{
            }</fold></fold>

            public UserData2Builder username(String username) <fold text='{...}' expand='true'>{
                this.username = <fold text='<<' expand='false'>username</fold>;
                return this;
            }</fold>

            public UserData2Builder active(boolean active) <fold text='{...}' expand='true'>{
                this.active = <fold text='<<' expand='false'>active</fold>;
                return this;
            }</fold>

            public UserData2Builder userIdentifier(String userIdentifier) <fold text='{...}' expand='true'>{
                this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
                return this;
            }</fold>

            public UserData2 build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new UserData2(username, active, userIdentifier)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold><fold text='' expand='false'>

            </fold><fold text='' expand='false'>public String toString() <fold text='{...}' expand='true'>{
                return "BuilderFieldShift.UserData2.UserData2Builder(username=<fold text='${' expand='false'>" + </fold>this.username<fold text='}' expand='false'> + "</fold>, active=<fold text='${' expand='false'>" + </fold>this.active<fold text='}' expand='false'> + "</fold>, userIdentifier=<fold text='${' expand='false'>" + </fold>this.userIdentifier<fold text='}' expand='false'> + "</fold>)";
            }</fold></fold>
        }</fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@NoArgsConstructor(default) @ToString p' expand='false'>p</fold>ublic static class BuilderFieldShiftBuilder <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;
        private FieldShiftBuilder child;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>BuilderFieldShiftBuilder() <fold text='{}' expand='true'>{
        }</fold></fold>

        public BuilderFieldShiftBuilder username(String username) <fold text='{...}' expand='true'>{
            this.username = <fold text='<<' expand='false'>username</fold>;
            return this;
        }</fold>

        public BuilderFieldShiftBuilder active(boolean active) <fold text='{...}' expand='true'>{
            this.active = <fold text='<<' expand='false'>active</fold>;
            return this;
        }</fold>

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) <fold text='{...}' expand='true'>{
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
            return this;
        }</fold>

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) <fold text='{...}' expand='true'>{
            this.child = <fold text='<<' expand='false'>child</fold>;
            return this;
        }</fold>

        public FieldShiftBuilder build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new FieldShiftBuilder(username, active, userIdentifier, child)<fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String toString() <fold text='{...}' expand='true'>{
            return "BuilderFieldShift.BuilderFieldShiftBuilder(username=<fold text='${' expand='false'>" + </fold>this.username<fold text='}' expand='false'> + "</fold>, active=<fold text='${' expand='false'>" + </fold>this.active<fold text='}' expand='false'> + "</fold>, userIdentifier=<fold text='${' expand='false'>" + </fold>this.userIdentifier<fold text='}' expand='false'> + "</fold>, child=<fold text='${' expand='false'>" + </fold>this.child<fold text='}' expand='false'> + "</fold>)";
        }</fold></fold>
    }</fold>
}
