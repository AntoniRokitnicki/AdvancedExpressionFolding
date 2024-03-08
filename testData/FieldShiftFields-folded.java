package data;

public class FieldShiftFields {
    public String username;
    public boolean active;
    public String userIdentifier;
    public FieldShiftFields child;


    public FieldShiftFields() {

    }

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) {
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
        this.userIdentifier = child.<<;
        this.userIdentifier = child.<<;
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.<<;
        result.userIdentifier = source.child.child.child.<<;
        result.active = source.child.<<;
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<<;
        result.userIdentifier = source.<<;
        result.active = source.<<;
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<<;
        result.active = source.<<;
        result.userIdentifier = source.<<;
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.<<;
        result.active = source.<<;
        result.userIdentifier = source.<<;
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        FieldShiftFields var1 = fields;
        var1.username = record.<<;
        FieldShiftFields var2 = var1;
        var2.active = source.<<;
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source.<<;
        result.username = var1.child.<<;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.<<;
        result.userIdentifier = source.<<;
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.<<;
        var1.userIdentifier = source.username;
        var1.username = source.<<;
        setters2.active = fields.<<;
        setters2.username = fields.<<;
        setters2.userIdentifier = record.<<;
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<<;
        result.child = childBuilder2.child.child.child.<<;
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        FieldShiftFields var1 = getters;
        var1.username = record.<<;
        FieldShiftFields var2 = var1;
        var2.active = source.<<;
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source.<<;
        result.username = var1.child.<<;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.<<;
        result.userIdentifier = source.<<;
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.<<;
        var1.userIdentifier = (source.username);
        var1.username = source.<<;
        setters2.child;
        setters2.active = getters.<<;
        setters2.username = getters.<<;
        setters2.userIdentifier = record.<<;
        result.child = setters2.<<;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.<<;
        result.child = childBuilder2.child.child.child.<<;
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
