package data;

public class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;

    public FieldShiftSetters() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public void setChild(FieldShiftSetters child) {
        this.child = child;
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.setUsername(source.getChild().getUsername());
        result.setUserIdentifier(source.getChild().getChild().getChild().getUserIdentifier());
        result.setActive(source.getChild().isActive());
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.setUsername(source.getUsername());
        result.setUserIdentifier(source.getUserIdentifier());
        result.setActive(source.isActive());
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.setUsername(source.username());
        result.setActive(source.active());
        result.setUserIdentifier(source.userIdentifier());
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        FieldShiftSetters var1 = setters;
        var1.setUsername(record.username());
        FieldShiftSetters var2 = var1;
        var2.setActive(source.isActive());
        FieldShiftSetters result = new FieldShiftSetters();
        result.setUsername(record.userIdentifier());
        result.setUsername(changer(record.username()));
        result.setUsername(source.getUsername());
        result.setUsername(var1.getChild().getUsername());
        result.setUsername(source.getUsername() + "1");
        result.setUsername(source.getUsername() + source.getUserIdentifier());
        result.setActive(source.isActive());
        result.setUserIdentifier(source.getUserIdentifier());
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.setChild(var1.getChild());
        var1.setUserIdentifier(source.getUsername());
        var1.setUsername(source.getUsername());
        setters2.getChild();
        setters2.setActive(setters.isActive());
        setters2.setUsername(setters.getUsername());
        setters2.setUserIdentifier(record.userIdentifier());
        result.setChild(setters2);
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.setUsername(source.getUsername());
        result.setChild(childBuilder2.getChild().getChild().getChild().getChild());
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
            this.username = username;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setUserIdentifier(String userIdentifier) {
            this.userIdentifier = userIdentifier;
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
