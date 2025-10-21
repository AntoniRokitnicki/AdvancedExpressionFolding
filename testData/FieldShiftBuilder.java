package data;

public class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;

    FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) <fold text='{...}' expand='true'>{
        this.username = <fold text='<<' expand='false'>username</fold>;
        this.active = <fold text='<<' expand='false'>active</fold>;
        this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        this.child = <fold text='<<' expand='false'>child</fold>;
    }</fold>


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) <fold text='{...}' expand='true'>{
        BuilderFieldShiftBuilder builder1 = builder
                .username(record<fold text='<<' expand='false'>.username()</fold>)
                .withUserIdentifier(record<fold text='<<' expand='false'>.userIdentifier()</fold>);
        var builder2 = builder
                .active(source<fold text='<<' expand='false'>.isActive()</fold>);
        return FieldShiftBuilder.builder().username(record.<fold text='userIdentifier' expand='false'>userIdentifier()</fold>).username(changer(record<fold text='<<' expand='false'>.username()</fold>))
                .username(source<fold text='<<' expand='false'>.getUsername()</fold>).username(builder.username("a").build()<fold text='<<' expand='false'>.getUsername()</fold>)
                .username(source.<fold text='username' expand='false'>getUsername()</fold> + "1")
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

    private static String changer(String username)<fold text=' { ' expand='false'> {
        </fold>return username;<fold text=' }' expand='false'>
    }</fold>


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) <fold text='{...}' expand='true'>{
        return FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='false'>.getUsername()</fold>)
                .userIdentifier(source<fold text='<<' expand='false'>.getUserIdentifier()</fold>)
                .withUserIdentifier(source<fold text='<<' expand='false'>.getUserIdentifier()</fold>)
                .build();
    }</fold>

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) <fold text='{...}' expand='true'>{
        return FieldShiftBuilder.builder()
                .username(source<fold text='<<' expand='false'>.username()</fold>)
                .active(source<fold text='<<' expand='false'>.active()</fold>)
                .userIdentifier(source<fold text='<<' expand='false'>.userIdentifier()</fold>)
                .withUserIdentifier(source<fold text='<<' expand='false'>.userIdentifier()</fold>)
                .build();
    }</fold>

    public static BuilderFieldShiftBuilder builder()<fold text=' { ' expand='false'> {
        </fold>return new BuilderFieldShiftBuilder();<fold text=' }' expand='false'>
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

    public FieldShiftBuilder getChild()<fold text=' { ' expand='false'> {
        </fold>return this.child;<fold text=' }' expand='false'>
    }</fold>

    public static class UserData2 <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;

        UserData2(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
            this.username = <fold text='<<' expand='false'>username</fold>;
            this.active = <fold text='<<' expand='false'>active</fold>;
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
        }</fold>

        public static UserData2Builder builder()<fold text=' { ' expand='false'> {
            </fold>return new UserData2Builder();<fold text=' }' expand='false'>
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

        public static class UserData2Builder <fold text='{...}' expand='true'>{
            private String username;
            private boolean active;
            private String userIdentifier;

            UserData2Builder() <fold text='{}' expand='true'>{
            }</fold>

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

            public UserData2 build()<fold text=' { ' expand='false'> {
                </fold>return new UserData2(username, active, userIdentifier);<fold text=' }' expand='false'>
            }</fold>

            public String toString() <fold text='{...}' expand='true'>{
                return "BuilderFieldShift.UserData2.UserData2Builder(username=" + this.username + ", active=" + this.active + ", userIdentifier=" + this.userIdentifier + ")";
            }</fold>
        }</fold>
    }</fold>

    public record UserDataRecord(String username, boolean active, String userIdentifier) <fold text='{...}' expand='true'>{
    }</fold>

    public static class BuilderFieldShiftBuilder <fold text='{...}' expand='true'>{
        private String username;
        private boolean active;
        private String userIdentifier;
        private FieldShiftBuilder child;

        BuilderFieldShiftBuilder() <fold text='{}' expand='true'>{
        }</fold>

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

        public BuilderFieldShiftBuilder withUserIdentifier(String userIdentifier) <fold text='{...}' expand='true'>{
            this.userIdentifier = <fold text='<<' expand='false'>userIdentifier</fold>;
            return this;
        }</fold>

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) <fold text='{...}' expand='true'>{
            this.child = <fold text='<<' expand='false'>child</fold>;
            return this;
        }</fold>

        public FieldShiftBuilder build()<fold text=' { ' expand='false'> {
            </fold>return new FieldShiftBuilder(username, active, userIdentifier, child);<fold text=' }' expand='false'>
        }</fold>

        public String toString() <fold text='{...}' expand='true'>{
            return "BuilderFieldShift.BuilderFieldShiftBuilder(username=" + this.username + ", active=" + this.active + ", userIdentifier=" + this.userIdentifier + ", child=" + this.child + ")";
        }</fold>
    }</fold>
}
