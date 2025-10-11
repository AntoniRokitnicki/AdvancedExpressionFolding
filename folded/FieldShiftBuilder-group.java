package data;

public class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;

    FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) {
        this.username = [0:"username"]{[0:"username"]};
        this.active = [0:"active"]{[0:"active"]};
        this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
        this.child = [0:"child"]{[0:"child"]};
    }


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) {
        BuilderFieldShiftBuilder builder1 = builder
                .username(record[0:".username()"]{[0:".username()"]});
        var builder2 = builder
                .active(source[0:".isActive()"]{[0:".isActive()"]});
        return FieldShiftBuilder.builder().username(record.[0:"userIdentifier()"]{[0:"userIdentifier()"]}).username(changer(record[0:".username()"]{[0:".username()"]}))
                .username(source[0:".getUsername()"]{[0:".getUsername()"]}).username(builder.username("a").build()[0:".getUsername()"]{[0:".getUsername()"]})
                .username(source.[0:"getUsername()"]{[0:"getUsername()"]} + "1")
                .active(source[0:".isActive()"]{[0:".isActive()"]}).userIdentifier(source[0:".getUserIdentifier()"]{[0:".getUserIdentifier()"]})
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.[0:"getUsername()"]{[0:"getUsername()"]})
                                .username(source[0:".getUsername()"]{[0:".getUsername()"]})
                                .build()[0:".getChild()"]{[0:".getChild()"]})
                        .active(builder.build()[0:".isActive()"]{[0:".isActive()"]})
                        .username(builder.build()[0:".getUsername()"]{[0:".getUsername()"]})
                        .userIdentifier(record[0:".userIdentifier()"]{[0:".userIdentifier()"]})
                        .build())
                .child(builder2
                        .username(source[0:".getUsername()"]{[0:".getUsername()"]})
                        .build())
                .build();
    }

    private static String changer(String username) {
        return username;
    }


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) {
        return FieldShiftBuilder.builder()
                .username(source[0:".getUsername()"]{[0:".getUsername()"]})
                .userIdentifier(source[0:".getUserIdentifier()"]{[0:".getUserIdentifier()"]})
                .build();
    }

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) {
        return FieldShiftBuilder.builder()
                .username(source[0:".username()"]{[0:".username()"]})
                .active(source[0:".active()"]{[0:".active()"]})
                .userIdentifier(source[0:".userIdentifier()"]{[0:".userIdentifier()"]})
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
            this.username = [0:"username"]{[0:"username"]};
            this.active = [0:"active"]{[0:"active"]};
            this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
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
                this.username = [0:"username"]{[0:"username"]};
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = [0:"active"]{[0:"active"]};
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
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
            this.username = [0:"username"]{[0:"username"]};
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = [0:"active"]{[0:"active"]};
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
            return this;
        }

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) {
            this.child = [0:"child"]{[0:"child"]};
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
