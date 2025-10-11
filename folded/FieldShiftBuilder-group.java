package data;

public class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;

    FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) {
        this.username = [0:"username"][4:"username"];
        this.active = [1:"active"][5:"active"];
        this.userIdentifier = [2:"userIdentifier"][6:"userIdentifier"];
        this.child = [3:"child"][7:"child"];
    }


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) {
        BuilderFieldShiftBuilder builder1 = builder
                .username(record[8:".username()"][24:".username()"]);
        var builder2 = builder
                .active(source[9:".isActive()"][25:".isActive()"]);
        return FieldShiftBuilder.builder().username(record.[10:"userIdentifier()"][26:"userIdentifier()"]).username(changer(record[11:".username()"][27:".username()"]))
                .username(source[12:".getUsername()"][28:".getUsername()"]).username(builder.username("a").build()[13:".getUsername()"][29:".getUsername()"])
                .username(source.[14:"getUsername()"][30:"getUsername()"] + "1")
                .active(source[15:".isActive()"][31:".isActive()"]).userIdentifier(source[16:".getUserIdentifier()"][32:".getUserIdentifier()"])
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.[18:"getUsername()"][34:"getUsername()"])
                                .username(source[19:".getUsername()"][35:".getUsername()"])
                                .build()[17:".getChild()"][33:".getChild()"])
                        .active(builder.build()[20:".isActive()"][36:".isActive()"])
                        .username(builder.build()[21:".getUsername()"][37:".getUsername()"])
                        .userIdentifier(record[22:".userIdentifier()"][38:".userIdentifier()"])
                        .build())
                .child(builder2
                        .username(source[23:".getUsername()"][39:".getUsername()"])
                        .build())
                .build();
    }

    private static String changer(String username) {
        return username;
    }


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) {
        return FieldShiftBuilder.builder()
                .username(source[40:".getUsername()"][42:".getUsername()"])
                .userIdentifier(source[41:".getUserIdentifier()"][43:".getUserIdentifier()"])
                .build();
    }

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) {
        return FieldShiftBuilder.builder()
                .username(source[44:".username()"][47:".username()"])
                .active(source[45:".active()"][48:".active()"])
                .userIdentifier(source[46:".userIdentifier()"][49:".userIdentifier()"])
                .build();
    }

    public static BuilderFieldShiftBuilder builder() {
        return new BuilderFieldShiftBuilder();
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getUserIdentifier() {
        return this.userIdentifier;
    }

    public FieldShiftBuilder getChild() {
        return this.child;
    }

    public static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;

        UserData2(String username, boolean active, String userIdentifier) {
            this.username = [50:"username"][53:"username"];
            this.active = [51:"active"][54:"active"];
            this.userIdentifier = [52:"userIdentifier"][55:"userIdentifier"];
        }

        public static UserData2Builder builder() {
            return new UserData2Builder();
        }

        public String getUsername() {
            return this.username;
        }

        public boolean isActive() {
            return this.active;
        }

        public String getUserIdentifier() {
            return this.userIdentifier;
        }

        public static class UserData2Builder {
            private String username;
            private boolean active;
            private String userIdentifier;

            UserData2Builder() {
            }

            public UserData2Builder username(String username) {
                this.username = [56:"username"][57:"username"];
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = [58:"active"][59:"active"];
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = [60:"userIdentifier"][61:"userIdentifier"];
                return this;
            }

            public UserData2 build() {
                return new UserData2(username, active, userIdentifier);
            }

            public String toString() {
                return "BuilderFieldShift.UserData2.UserData2Builder(username=" + this.username + ", active=" + this.active + ", userIdentifier=" + this.userIdentifier + ")";
            }
        }
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }

    public static class BuilderFieldShiftBuilder {
        private String username;
        private boolean active;
        private String userIdentifier;
        private FieldShiftBuilder child;

        BuilderFieldShiftBuilder() {
        }

        public BuilderFieldShiftBuilder username(String username) {
            this.username = [62:"username"][63:"username"];
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = [64:"active"][65:"active"];
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = [66:"userIdentifier"][67:"userIdentifier"];
            return this;
        }

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) {
            this.child = [68:"child"][69:"child"];
            return this;
        }

        public FieldShiftBuilder build() {
            return new FieldShiftBuilder(username, active, userIdentifier, child);
        }

        public String toString() {
            return "BuilderFieldShift.BuilderFieldShiftBuilder(username=" + this.username + ", active=" + this.active + ", userIdentifier=" + this.userIdentifier + ", child=" + this.child + ")";
        }
    }
}
