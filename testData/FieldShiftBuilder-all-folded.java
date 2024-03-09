package data;

public class FieldShiftBuilder {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftBuilder child;

    FieldShiftBuilder(String username, boolean active, String userIdentifier, FieldShiftBuilder child) {
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
    }


    public static FieldShiftBuilder map(UserData2 source, BuilderFieldShiftBuilder builder, UserDataRecord record) {
        val builder1 = builder
                .username(record<<);
        val builder2 = builder
                .active(source<<);
        return FieldShiftBuilder.builder().username(record.userIdentifier).username(changer(record.username))
                .username(source<<).username(builder.username("a").build()<<)
                .username("${source.username}1")
                .active(source<<).userIdentifier(source<<)
                .child(FieldShiftBuilder.builder()
                        .child(builder1
                                .userIdentifier(source.getUsername())
                                .username(source<<)
                                .build()<<)
                        .active(builder.build()<<)
                        .username(builder.build()<<)
                        .userIdentifier(record<<)
                        .build())
                .child(builder2
                        .username(source<<)
                        .build())
                .build();
    }

    private static String changer(String username) {
        return username;
    }


    public static FieldShiftBuilder mapSimple(FieldShiftBuilder source) {
        return FieldShiftBuilder.builder()
                .username(source<<)
                .userIdentifier(source<<)
                .build();
    }

    public static FieldShiftBuilder mapUserDataAllFields(UserDataRecord source) {
        return FieldShiftBuilder.builder()
                .username(source<<)
                .active(source<<)
                .userIdentifier(source<<)
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
            this.username = <<;
            this.active = <<;
            this.userIdentifier = <<;
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
                this.username = <<;
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = <<;
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = <<;
                return this;
            }

            public UserData2 build() {
                return new UserData2(username, active, userIdentifier);
            }

            public String toString() {
                return "BuilderFieldShift.UserData2.UserData2Builder(username=${this.username}, active=${this.active}, userIdentifier=${this.userIdentifier})";
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
            this.username = <<;
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = <<;
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = <<;
            return this;
        }

        public BuilderFieldShiftBuilder child(FieldShiftBuilder child) {
            this.child = <<;
            return this;
        }

        public FieldShiftBuilder build() {
            return new FieldShiftBuilder(username, active, userIdentifier, child);
        }

        public String toString() {
            return "BuilderFieldShift.BuilderFieldShiftBuilder(username=${this.username}, active=${this.active}, userIdentifier=${this.userIdentifier}, child=${this.child})";
        }
    }
}
