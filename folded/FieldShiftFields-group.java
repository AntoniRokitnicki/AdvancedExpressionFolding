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
        this.username = [0:"username"];
        this.active = [1:"active"];
        this.userIdentifier = [2:"userIdentifier"];
        this.child = [3:"child"];
        this.userIdentifier = child.[4:"userIdentifier"];
        this.userIdentifier = child.[5:"getUserIdentifier()"];
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.[14:"username"];
        result.userIdentifier = source.child.child.child.[15:"userIdentifier"];
        result.active = source.child.[16:"active"];
        result.list = List.copyOf(source.[17:"list"]);
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[22:"username"];
        result.userIdentifier = source.[23:"userIdentifier"];
        result.active = source.[24:"active"];
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[28:"username"];
        result.active = source.[29:"active"];
        result.userIdentifier = source.[30:"userIdentifier"];
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[34:"username()"];
        result.active = source.[36:"active()"];
        result.userIdentifier = source.[38:"userIdentifier()"];
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        FieldShiftFields var1 = fields;
        var1.username = record.[46:"username"];
        FieldShiftFields var2 = var1;
        var2.active = source.[47:"active"];
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[48:"username"]);
        result.username = source.[49:"username"];
        result.username = var1.child.[50:"username"];
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.[51:"active"];
        result.userIdentifier = source.[52:"userIdentifier"];
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[53:"child"];
        var1.userIdentifier = source.username;
        var1.username = source.[54:"username"];
        setters2.active = fields.[55:"active"];
        setters2.username = fields.[56:"username"];
        setters2.userIdentifier = record.[57:"userIdentifier()"];
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[59:"username"];
        result.child = childBuilder2.child.child.child.[60:"child"];
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        FieldShiftFields var1 = getters;
        var1.username = record.[76:"username()"];
        FieldShiftFields var2 = var1;
        var2.active = source.[78:"isActive()"];
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[80:"username()"]);
        result.username = source.[82:"getUsername()"];
        result.username = var1.[86:"getChild()"].[84:"getUsername()"];
        result.username = source.[87:"getUsername()"] + "1";
        result.username = source.[88:"getUsername()"] + source.[89:"getUserIdentifier()"];
        result.active = source.[90:"isActive()"];
        result.userIdentifier = source.[92:"getUserIdentifier()"];
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[94:"getChild()"];
        var1.userIdentifier = (source.[96:"getUsername()"]);
        var1.username = source.[97:"getUsername()"];
        setters2.[99:"getChild()"];
        setters2.active = getters.[100:"isActive()"];
        setters2.username = getters.[102:"getUsername()"];
        setters2.userIdentifier = record.[104:"userIdentifier()"];
        result.child = setters2.[106:"getChild()"];
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[108:"getUsername()"];
        result.child = childBuilder2.[114:"getChild()"].[113:"getChild()"].[112:"getChild()"].[110:"getChild()"];
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
