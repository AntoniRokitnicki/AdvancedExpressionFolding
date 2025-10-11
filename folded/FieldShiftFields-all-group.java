package data;

import java.util.List;

[0:"p"]ublic class FieldShiftFields {
    [1:"p"]ublic String username;
    [2:"p"]ublic boolean active;
    [3:"p"]ublic String userIdentifier;
    [4:"p"]ublic FieldShiftFields child;[0:"\n\n\n    "][0:"public FieldShiftFields() {\n\n    }"]
    private List<String> list;

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) {
        this.username = [5:"username"];
        this.active = [6:"active"];
        this.userIdentifier = [7:"userIdentifier"];
        this.child = [8:"child"];
        this.userIdentifier = child.[9:"userIdentifier"];
        this.userIdentifier = child.[10:"getUserIdentifier()"];
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        [19:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.child.[20:"username"];
        result.userIdentifier = source.child.child.child.[21:"userIdentifier"];
        result.active = source.child.[22:"active"];
        result.list = List.copyOf(source.[23:"list"]);
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        [29:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[30:"username"];
        result.userIdentifier = source.[31:"userIdentifier"];
        result.active = source.[32:"active"];
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        [37:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[38:"username"];
        result.active = source.[39:"active"];
        result.userIdentifier = source.[40:"userIdentifier"];
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        [45:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[46:"username()"];
        result.active = source.[48:"active()"];
        result.userIdentifier = source.[50:"userIdentifier()"];
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        [59:"FieldShiftFields"] var1 = fields;
        var1.username = record.[60:"username"];
        [61:"FieldShiftFields"] var2 = var1;
        var2.active = source.[62:"active"];
        [63:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[64:"username"]);
        result.username = source.[65:"username"];
        result.username = var1.child.[66:"username"];
        result.username =[67:" "]source.username[67:" + \""]1";
        result.username = source.username + source.userIdentifier;
        result.active = source.[68:"active"];
        result.userIdentifier = source.[69:"userIdentifier"];
        [70:"FieldShiftFields"] setters2 = new FieldShiftFields();
        setters2.child = var1.[71:"child"];
        var1.userIdentifier = source.username;
        var1.username = source.[72:"username"];
        setters2.active = fields.[73:"active"];
        setters2.username = fields.[74:"username"];
        setters2.userIdentifier = record.[75:"userIdentifier()"];
        result.child = setters2;
        [77:"FieldShiftFields"] childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[78:"username"];
        result.child = childBuilder2.child.child.child.[79:"child"];
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        [101:"FieldShiftFields"] var1 = getters;
        var1.username = record.[102:"username()"];
        [104:"FieldShiftFields"] var2 = var1;
        var2.active = source.[105:"isActive()"];
        [107:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[108:"username()"]);
        result.username = source.[110:"getUsername()"];
        result.username = var1.[114:"getChild()"].[112:"getUsername()"];
        result.username =[115:" "]source.[116:"getUsername()"][115:" + \""]1";
        result.username = source.[117:"getUsername()"] + source.[118:"getUserIdentifier()"];
        result.active = source.[119:"isActive()"];
        result.userIdentifier = source.[121:"getUserIdentifier()"];
        [123:"FieldShiftFields"] setters2 = new FieldShiftFields();
        setters2.child = var1.[124:"getChild()"];
        var1.userIdentifier = (source.[126:"getUsername()"]);
        var1.username = source.[128:"getUsername()"];
        setters2.[130:"getChild()"];
        setters2.active = getters.[131:"isActive()"];
        setters2.username = getters.[133:"getUsername()"];
        setters2.userIdentifier = record.[135:"userIdentifier()"];
        result.child = setters2.[137:"getChild()"];
        [139:"FieldShiftFields"] childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[140:"getUsername()"];
        result.child = childBuilder2.[146:"getChild()"].[145:"getChild()"].[144:"getChild()"].[142:"getChild()"];
        return result;
    }


    private static String changer(String username) {[192:"\n        "][192:"return"][192:" "]username[192:";"][192:"\n    "]}[1:"\n\n    "][1:"public String getUsername() {\n        return username;\n    }"][2:"\n\n    "][2:"public boolean isActive() {\n        return active;\n    }"][3:"\n\n    "][3:"public String getUserIdentifier() {\n        return userIdentifier;\n    }"][4:"\n\n    "][4:"public FieldShiftFields getChild() {\n        return child;\n    }"]

    [197:"p"]ublic static class UserData2 {
        public String username;
        public boolean active;
        public String userIdentifier;[197:"\n\n        "][197:"public String getUsername() {\n            return username;\n        }"][197:"\n\n        "][197:"public boolean isActive() {\n            return active;\n        }"][197:"\n\n        "][197:"public String getUserIdentifier() {\n            return userIdentifier;\n        }"]
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
