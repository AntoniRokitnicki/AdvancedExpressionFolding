package data;

[0:"p"]ublic class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;[0:"\n\n    "][0:"FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) {\n        this.username = username;\n        this.active = active;\n        this.userIdentifier = userIdentifier;\n        this.child = child;\n    }"]ntifier"][7:"userIdentifier"];
        this.child = [4:"child"][8:"child"];
    }


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) {
        [9:"BuilderFieldShiftBuilder"][28:"BuilderFieldShiftBuilder"] builder1 = builder
                .username(record[10:".username()"][29:".username()"]);
        [11:"var"][30:"var"] builder2 = builder
                .active(source[12:".isActive()"][31:".isActive()"]);
        return FieldShiftBuilder.builder().username(record.[13:"userIdentifier()"][32:"userIdentifier()"]).username(changer(record[14:".username()"][33:".username()"]))
                .username(source[15:".getUsername()"][34:".getUsername()"]).username(builder.username("a").build()[16:".getUsername()"][35:".getUsername()"])
                .username[17:"("][36:"("]source.[18:"getUsername()"][37:"getUsername()"][17:" + \""][36:" + \""]1")
                .active(source[19:".isActive()"][38:".isActive()"]).userIdentifier(source[20:".getUserIdentifier()"][39:".getUserIdentifier()"])
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.[22:"getUsername()"][41:"getUsername()"])
                                .username(source[23:".getUsername()"][42:".getUsername()"])
                                .build()[21:".getChild()"][40:".getChild()"])
                        .active(builder.build()[24:".isActive()"][43:".isActive()"])
                        .username(builder.build()[25:".getUsername()"][44:".getUsername()"])
                        .userIdentifier(record[26:".userIdentifier()"][45:".userIdentifier()"])
                        .build())
                .child(builder2
                        .username(source[27:".getUsername()"][46:".getUsername()"])
                        .build())
                .build();
    }

    private static String changer(String username) {[47:"\n        "][47:"return"][47:" "]username[47:";"][47:"\n    "]}


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) {
        return FieldShiftBuilder.builder()
                .username(source[48:".getUsername()"][50:".getUsername()"])
                .userIdentifier(source[49:".getUserIdentifier()"][51:".getUserIdentifier()"])
                .build();
    }

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) {
        return FieldShiftBuilder.builder()
                .username(source[52:".username()"][55:".username()"])
                .active(source[53:".active()"][56:".active()"])
                .userIdentifier(source[54:".userIdentifier()"][57:".userIdentifier()"])
                .build();
    }

    public static BuilderFieldShiftBuilder builder() {[58:"\n        "][58:"return"][58:" "]new BuilderFieldShiftBuilder()[58:";"][58:"\n    "]}[0:"\n\n    "][0:"public String getUsername() {\n        return this.username;\n    }"]"]this.username[59:";"][59:"\n    "]}[0:"\n\n    "][0:"public boolean isActive() {\n        return this.active;\n    }"]" "]this.active[60:";"][60:"\n    "]}[0:"\n\n    "][0:"public String getUserIdentifier() {\n        return this.userIdentifier;\n    }"].userIdentifier[61:";"][61:"\n    "]}[0:"\n\n    "][0:"public FieldShiftBuilder getChild() {\n        return this.child;\n    }"]:" "]this.child[62:";"][62:"\n    "]}

    [63:"p"]ublic static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;[63:"\n\n        "][63:"UserData2(String username, boolean active, String userIdentifier) {\n            this.username = username;\n            this.active = active;\n            this.userIdentifier = userIdentifier;\n        }"]userIdentifier = [66:"userIdentifier"][69:"userIdentifier"];
        }

        public static UserData2Builder builder() {[70:"\n            "][70:"return"][70:" "]new UserData2Builder()[70:";"][70:"\n        "]}[63:"\n\n        "][63:"public String getUsername() {\n            return this.username;\n        }"]is.username[71:";"][71:"\n        "]}[63:"\n\n        "][63:"public boolean isActive() {\n            return this.active;\n        }"]this.active[72:";"][72:"\n        "]}[63:"\n\n        "][63:"public String getUserIdentifier() {\n            return this.userIdentifier;\n        }"]rIdentifier[73:";"][73:"\n        "]}

        [74:"p"]ublic static class UserData2Builder {
            private String username;
            private boolean active;
            private String userIdentifier;[74:"\n\n            "][74:"UserData2Builder() {\n            }"]

            public UserData2Builder username(String username) {
                this.username = [75:"username"][76:"username"];
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = [77:"active"][78:"active"];
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = [79:"userIdentifier"][80:"userIdentifier"];
                return this;
            }

            public UserData2 build() {[81:"\n                "][81:"return"][81:" "]new UserData2(username, active, userIdentifier)[81:";"][81:"\n            "]}[74:"\n\n            "][74:"public String toString() {\n                return \"BuilderFieldShift.UserData2.UserData2Builder(username=\" + this.username + \", active=\" + this.active + \", userIdentifier=\" + this.userIdentifier + \")\";\n            }"] + \""][83:" + \""], userIdentifier=[82:"\" + "][83:"\" + "]this.userIdentifier[82:" + \""][83:" + \""])";
            }
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
            this.username = [85:"username"][86:"username"];
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = [87:"active"][88:"active"];
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = [89:"userIdentifier"][90:"userIdentifier"];
            return this;
        }

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) {
            this.child = [91:"child"][92:"child"];
            return this;
        }

        public FieldShiftBuilder build() {[93:"\n            "][93:"return"][93:" "]new FieldShiftBuilder(username, active, userIdentifier, child)[93:";"][93:"\n        "]}[84:"\n\n        "][84:"public String toString() {\n            return \"BuilderFieldShift.BuilderFieldShiftBuilder(username=\" + this.username + \", active=\" + this.active + \", userIdentifier=\" + this.userIdentifier + \", child=\" + this.child + \")\";\n        }"]serIdentifier=[94:"\" + "][95:"\" + "]this.userIdentifier[94:" + \""][95:" + \""], child=[94:"\" + "][95:"\" + "]this.child[94:" + \""][95:" + \""])";
        }
    }
}
