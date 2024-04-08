package data;

public class FieldShiftFields {
    public String username;
    public boolean active;
    public String userIdentifier;
    public FieldShiftFields child;


    public FieldShiftFields() {

    }

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) {
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
        this.userIdentifier = child.userIdentifier;
        this.userIdentifier = child.getUserIdentifier();
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.username;
        result.userIdentifier = source.child.child.child.userIdentifier;
        result.active = source.child.active;
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.username;
        result.userIdentifier = source.userIdentifier;
        result.active = source.active;
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.username;
        result.active = source.active;
        result.userIdentifier = source.userIdentifier;
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.username();
        result.active = source.active();
        result.userIdentifier = source.userIdentifier();
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        FieldShiftFields var1 = fields;
        var1.username = record.username;
        FieldShiftFields var2 = var1;
        var2.active = source.active;
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source.username;
        result.username = var1.child.username;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.active;
        result.userIdentifier = source.userIdentifier;
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.child;
        var1.userIdentifier = source.username;
        var1.username = source.username;
        setters2.active = fields.active;
        setters2.username = fields.username;
        setters2.userIdentifier = record.userIdentifier();
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.username;
        result.child = childBuilder2.child.child.child.child;
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        FieldShiftFields var1 = getters;
        var1.username = record.username();
        FieldShiftFields var2 = var1;
        var2.active = source.isActive();
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.username());
        result.username = source.getUsername();
        result.username = var1.getChild().getUsername();
        result.username = source.getUsername() + "1";
        result.username = source.getUsername() + source.getUserIdentifier();
        result.active = source.isActive();
        result.userIdentifier = source.getUserIdentifier();
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.getChild();
        var1.userIdentifier = (source.getUsername());
        var1.username = source.getUsername();
        setters2.getChild();
        setters2.active = getters.isActive();
        setters2.username = getters.getUsername();
        setters2.userIdentifier = record.userIdentifier();
        result.child = setters2.getChild();
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.getUsername();
        result.child = childBuilder2.getChild().getChild().getChild().getChild();
        return result;
    }


    private static String changer(String username) {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public FieldShiftFields getChild() {
        return child;
    }

    public static class UserData2 {
        public String username;
        public boolean active;
        public String userIdentifier;

        public String getUsername() {
            return username;
        }

        public boolean isActive() {
            return active;
        }

        public String getUserIdentifier() {
            return userIdentifier;
        }
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
