package data;

import java.util.List;

public class FieldShiftFields {
    public String username;
    public boolean active;
    public String userIdentifier;
    public FieldShiftFields child;


    public FieldShiftFields() {

    }
    private List<String> list;

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) {
        this.username = [0:"username"]{[0:"username"]};
        this.active = [0:"active"]{[0:"active"]};
        this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
        this.child = [0:"child"]{[0:"child"]};
        this.userIdentifier = child.[0:"userIdentifier"]{[0:"userIdentifier"]};
        this.userIdentifier = child.[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]}}};
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.[0:"username"]{[0:"username"]};
        result.userIdentifier = source.child.child.child.[0:"userIdentifier"]{[0:"userIdentifier"]};
        result.active = source.child.[0:"active"]{[0:"active"]};
        result.list = List.copyOf(source.[0:"list"]{[0:"list"]});
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[0:"username"]{[0:"username"]};
        result.userIdentifier = source.[0:"userIdentifier"]{[0:"userIdentifier"]};
        result.active = source.[0:"active"]{[0:"active"]};
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[0:"username"]{[0:"username"]};
        result.active = source.[0:"active"]{[0:"active"]};
        result.userIdentifier = source.[0:"userIdentifier"]{[0:"userIdentifier"]};
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[0:"username()"]{[0:"username()"]{[0:"username()"]{[0:"username()"]}}};
        result.active = source.[0:"active()"]{[0:"active()"]{[0:"active()"]{[0:"active()"]}}};
        result.userIdentifier = source.[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]}}};
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        FieldShiftFields var1 = fields;
        var1.username = record.[0:"username"]{[0:"username"]};
        FieldShiftFields var2 = var1;
        var2.active = source.[0:"active"]{[0:"active"]};
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[0:"username"]{[0:"username"]});
        result.username = source.[0:"username"]{[0:"username"]};
        result.username = var1.child.[0:"username"]{[0:"username"]};
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.[0:"active"]{[0:"active"]};
        result.userIdentifier = source.[0:"userIdentifier"]{[0:"userIdentifier"]};
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[0:"child"]{[0:"child"]};
        var1.userIdentifier = source.username;
        var1.username = source.[0:"username"]{[0:"username"]};
        setters2.active = fields.[0:"active"]{[0:"active"]};
        setters2.username = fields.[0:"username"]{[0:"username"]};
        setters2.userIdentifier = record.[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]}}};
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[0:"username"]{[0:"username"]};
        result.child = childBuilder2.child.child.child.[0:"child"]{[0:"child"]};
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        FieldShiftFields var1 = getters;
        var1.username = record.[0:"username()"]{[0:"username()"]{[0:"username()"]{[0:"username()"]}}};
        FieldShiftFields var2 = var1;
        var2.active = source.[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]}}};
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[0:"username()"]{[0:"username()"]{[0:"username()"]{[0:"username()"]}}});
        result.username = source.[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]}}};
        result.username = var1.[0:"getChild()"]{[0:"getChild()"]}.[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]}}};
        result.username = source.[0:"getUsername()"]{[0:"getUsername()"]} + "1";
        result.username = source.[0:"getUsername()"]{[0:"getUsername()"]} + source.[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]};
        result.active = source.[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]}}};
        result.userIdentifier = source.[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]}}};
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]}}};
        var1.userIdentifier = (source.[0:"getUsername()"]{[0:"getUsername()"]});
        var1.username = source.[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]}}};
        setters2.[0:"getChild()"]{[0:"getChild()"]};
        setters2.active = getters.[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]{[0:"isActive()"]}}};
        setters2.username = getters.[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]}}};
        setters2.userIdentifier = record.[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]{[0:"userIdentifier()"]}}};
        result.child = setters2.[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]}}};
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]{[0:"getUsername()"]}}};
        result.child = childBuilder2.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]{[0:"getChild()"]}}};
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
