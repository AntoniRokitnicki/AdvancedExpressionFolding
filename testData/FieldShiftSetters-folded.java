package data;

public class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;

    public FieldShiftSetters() {
    }

    public void setUsername(String username) {
        this.username = <<;
    }

    public void setActive(boolean active) {
        this.active = <<;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = <<;
    }

    public void setChild(FieldShiftSetters child) {
        this.child = <<;
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.username = source.child<<;
        result.userIdentifier = source.child.child.child<<;
        result.active = source.child<<;
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.username = source<<;
        result.userIdentifier = source<<;
        result.active = source<<;
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.username = source<<;
        result.active = source<<;
        result.userIdentifier = source<<;
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        FieldShiftSetters var1 = setters;
        var1.username = record<<;
        FieldShiftSetters var2 = var1;
        var2.active = source<<;
        FieldShiftSetters result = new FieldShiftSetters();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source<<;
        result.username = var1.child<<;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source<<;
        result.userIdentifier = source<<;
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.child = var1<<;
        var1.userIdentifier = source.username;
        var1.username = source<<;
        setters2.child;
        setters2.active = setters<<;
        setters2.username = setters<<;
        setters2.userIdentifier = record<<;
        result.child = setters2;
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.username = source<<;
        result.child = childBuilder2.child.child.child<<;
        return result;
    }

    private static String changer(String username) {
        return username;
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

    public FieldShiftSetters getChild() {
        return this.child;
    }

    public static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;

        public UserData2() {
        }

        public void setUsername(String username) {
            this.username = <<;
        }

        public void setActive(boolean active) {
            this.active = <<;
        }

        public void setUserIdentifier(String userIdentifier) {
            this.userIdentifier = <<;
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
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
