package data;

public class BuilderFieldShift {
    private String username;
    private boolean active;
    private String userIdentifier;
    private BuilderFieldShift child;

    BuilderFieldShift(String username, boolean active, String userIdentifier, BuilderFieldShift child) {
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
    }


    public static BuilderFieldShift map(UserData2 source, BuilderFieldShift.BuilderFieldShiftBuilder builder, UserDataRecord record) {
        BuilderFieldShift.BuilderFieldShiftBuilder builder1 = builder
                .username(record.username());
        var builder2 = builder
                .active(source.isActive());
        return BuilderFieldShift.builder().username(record.userIdentifier())
                .username(source.getUsername()).username(builder.username("a").build().getUsername())
                .username(source.getUsername() + "1")
                .active(source.isActive()).userIdentifier(source.getUserIdentifier())
                .child(BuilderFieldShift.builder()
                        .child(builder1
                                .userIdentifier(source.getUsername())
                                .username(source.getUsername())
                                .build().getChild())
                        .active(builder.build().isActive())
                        .username(builder.build().getUsername())
                        .userIdentifier(record.userIdentifier())
                        .build())
                .child(builder2
                        .username(source.getUsername())
                        .build())
                .build();
    }


    public static BuilderFieldShift mapSimple(BuilderFieldShift source) {
        return BuilderFieldShift.builder()
                .username(source.getUsername())
                .userIdentifier(source.getUserIdentifier())
                .build();
    }

    public static BuilderFieldShift mapUserDataAllFields(UserDataRecord source) {
        return BuilderFieldShift.builder()
                .username(source.username())
                .active(source.active())
                .userIdentifier(source.userIdentifier())
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

    public BuilderFieldShift getChild() {
        return this.child;
    }

    public static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;

        UserData2(String username, boolean active, String userIdentifier) {
            this.username = username;
            this.active = active;
            this.userIdentifier = userIdentifier;
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
                this.username = username;
                return this;
            }

            public UserData2Builder active(boolean active) {
                this.active = active;
                return this;
            }

            public UserData2Builder userIdentifier(String userIdentifier) {
                this.userIdentifier = userIdentifier;
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
        private BuilderFieldShift child;

        BuilderFieldShiftBuilder() {
        }

        public BuilderFieldShiftBuilder username(String username) {
            this.username = username;
            return this;
        }

        public BuilderFieldShiftBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public BuilderFieldShiftBuilder userIdentifier(String userIdentifier) {
            this.userIdentifier = userIdentifier;
            return this;
        }

        public BuilderFieldShiftBuilder child(BuilderFieldShift child) {
            this.child = child;
            return this;
        }

        public BuilderFieldShift build() {
            return new BuilderFieldShift(username, active, userIdentifier, child);
        }

        public String toString() {
            return "BuilderFieldShift.BuilderFieldShiftBuilder(username=" + this.username + ", active=" + this.active + ", userIdentifier=" + this.userIdentifier + ", child=" + this.child + ")";
        }
    }
}
