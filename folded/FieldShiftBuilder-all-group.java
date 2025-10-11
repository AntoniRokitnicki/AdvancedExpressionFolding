package data;

[0:"p"]ublic class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;[0:"\n\n    "][0:"FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) {\n        this.username = username;\n        this.active = active;\n        this.userIdentifier = userIdentifier;\n        this.child = child;\n    }"]


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) {
        [9:"BuilderFieldShiftBuilder"] builder1 = builder
                .username(record[10:".username()"]);
        [11:"var"] builder2 = builder
                .active(source[12:".isActive()"]);
        return FieldShiftBuilder.builder().username(record.[13:"userIdentifier()"]).username(changer(record[14:".username()"]))
                .username(source[15:".getUsername()"]).username(builder.username("a").build()[16:".getUsername()"])
                .username[17:"("]source.[18:"getUsername()"][17:" + \""]1")
                .active(source[19:".isActive()"]).userIdentifier(source[20:".getUserIdentifier()"])
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.[22:"getUsername()"])
                                .username(source[23:".getUsername()"])
                                .build()[21:".getChild()"])
                        .active(builder.build()[24:".isActive()"])
                        .username(builder.build()[25:".getUsername()"])
                        .userIdentifier(record[26:".userIdentifier()"])
                        .build())
                .child(builder2
                        .username(source[27:".getUsername()"])
                        .build())
                .build();
    }

    private static String changer(String username) {[47:"\n        "][47:"return"][47:" "]username[47:";"][47:"\n    "]}


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) {
        return FieldShiftBuilder.builder()
                .username(source[48:".getUsername()"])
                .userIdentifier(source[49:".getUserIdentifier()"])
                .build();
    }

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) {
        return FieldShiftBuilder.builder()
                .username(source[52:".username()"])
                .active(source[53:".active()"])
                .userIdentifier(source[54:".userIdentifier()"])
                .build();
    }

    public static BuilderFieldShiftBuilder builder() {[58:"\n        "][58:"return"][58:" "]new BuilderFieldShiftBuilder()[58:";"][58:"\n    "]}[0:"\n\n    "][0:"public String getUsername() {\n        return this.username;\n    }"][0:"\n\n    "][0:"public boolean isActive() {\n        return this.active;\n    }"][0:"\n\n    "][0:"public String getUserIdentifier() {\n        return this.userIdentifier;\n    }"][0:"\n\n    "][0:"public FieldShiftBuilder getChild() {\n        return this.child;\n    }"]

    [63:"p"]ublic static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;[63:"\n\n        "][63:"UserData2(String username, boolean active, String userIdentifier) {\n            this.username = username;\n            this.active = active;\n            this.userIdentifier = userIdentifier;\n        }"]

        public static UserData2Builder builder() {[70:"\n            "][70:"return"][70:" "]new UserData2Builder()[70:";"][70:"\n        "]}[63:"\n\n        "][63:"public String getUsername() {\n            return this.username;\n        }"][63:"\n\n        "][63:"public boolean isActive() {\n            return this.active;\n        }"][63:"\n\n        "][63:"public String getUserIdentifier() {\n            return this.userIdentifier;\n        }"]

        [74:"p"]ublic static class UserData2Builder {
            private String username;
            private boolean active;
            private String userIdentifier;[74:"\n\n            "][74:"UserData2Builder() {\n            }"]

            public UserData2Builder username(String username) {
                this.username = [75:"username"];
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = [77:"active"];
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = [79:"userIdentifier"];
                return this;
            }

            public UserData2 build() {[81:"\n                "][81:"return"][81:" "]new UserData2(username, active, userIdentifier)[81:";"][81:"\n            "]}[74:"\n\n            "][74:"public String toString() {\n                return \"BuilderFieldShift.UserData2.UserData2Builder(username=\" + this.username + \", active=\" + this.active + \", userIdentifier=\" + this.userIdentifier + \")\";\n            }"]
        }
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }

    [84:"p"]ublic static class BuilderFieldShiftBuilder {
        private String username;
        private boolean active;
        private String userIdentifier;
        private FieldShiftBuilder child;[84:"\n\n        "][84:"BuilderFieldShiftBuilder() {\n        }"]

        public BuilderFieldShiftBuilder username(String username) {
            this.username = [85:"username"];
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = [87:"active"];
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = [89:"userIdentifier"];
            return this;
        }

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) {
            this.child = [91:"child"];
            return this;
        }

        public FieldShiftBuilder build() {[93:"\n            "][93:"return"][93:" "]new FieldShiftBuilder(username, active, userIdentifier, child)[93:";"][93:"\n        "]}[84:"\n\n        "][84:"public String toString() {\n            return \"BuilderFieldShift.BuilderFieldShiftBuilder(username=\" + this.username + \", active=\" + this.active + \", userIdentifier=\" + this.userIdentifier + \", child=\" + this.child + \")\";\n        }"]
    }
}
