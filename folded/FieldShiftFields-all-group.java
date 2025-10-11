package data;

import java.util.List;

[0:"p"]ublic class FieldShiftFields {
    [1:"p"]ublic String username;
    [2:"p"]ublic boolean active;
    [3:"p"]ublic String userIdentifier;
    [4:"p"]ublic FieldShiftFields child;[0:"\n\n\n    "][0:"public FieldShiftFields() {\n\n    }"]
    private List<String> list;

    public FieldShiftFields(String username, boolean active, String userIdentifier, FieldShiftFields child) {
        this.username = [5:"username"][12:"username"];
        this.active = [6:"active"][13:"active"];
        this.userIdentifier = [7:"userIdentifier"][14:"userIdentifier"];
        this.child = [8:"child"][15:"child"];
        this.userIdentifier = child.[9:"userIdentifier"][16:"userIdentifier"];
        this.userIdentifier = child.[10:"getUserIdentifier()"][11:"getUserIdentifier()"][17:"getUserIdentifier()"][18:"getUserIdentifier()"];
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        [19:"FieldShiftFields"][24:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.child.[20:"username"][25:"username"];
        result.userIdentifier = source.child.child.child.[21:"userIdentifier"][26:"userIdentifier"];
        result.active = source.child.[22:"active"][27:"active"];
        result.list = List.copyOf(source.[23:"list"][28:"list"]);
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        [29:"FieldShiftFields"][33:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[30:"username"][34:"username"];
        result.userIdentifier = source.[31:"userIdentifier"][35:"userIdentifier"];
        result.active = source.[32:"active"][36:"active"];
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        [37:"FieldShiftFields"][41:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[38:"username"][42:"username"];
        result.active = source.[39:"active"][43:"active"];
        result.userIdentifier = source.[40:"userIdentifier"][44:"userIdentifier"];
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        [45:"FieldShiftFields"][52:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = source.[46:"username()"][47:"username()"][53:"username()"][54:"username()"];
        result.active = source.[48:"active()"][49:"active()"][55:"active()"][56:"active()"];
        result.userIdentifier = source.[50:"userIdentifier()"][51:"userIdentifier()"][57:"userIdentifier()"][58:"userIdentifier()"];
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        [59:"FieldShiftFields"][80:"FieldShiftFields"] var1 = fields;
        var1.username = record.[60:"username"][81:"username"];
        [61:"FieldShiftFields"][82:"FieldShiftFields"] var2 = var1;
        var2.active = source.[62:"active"][83:"active"];
        [63:"FieldShiftFields"][84:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[64:"username"][85:"username"]);
        result.username = source.[65:"username"][86:"username"];
        result.username = var1.child.[66:"username"][87:"username"];
        result.username =[67:" "][88:" "]source.username[67:" + \""][88:" + \""]1";
        result.username = source.username + source.userIdentifier;
        result.active = source.[68:"active"][89:"active"];
        result.userIdentifier = source.[69:"userIdentifier"][90:"userIdentifier"];
        [70:"FieldShiftFields"][91:"FieldShiftFields"] setters2 = new FieldShiftFields();
        setters2.child = var1.[71:"child"][92:"child"];
        var1.userIdentifier = source.username;
        var1.username = source.[72:"username"][93:"username"];
        setters2.active = fields.[73:"active"][94:"active"];
        setters2.username = fields.[74:"username"][95:"username"];
        setters2.userIdentifier = record.[75:"userIdentifier()"][76:"userIdentifier()"][96:"userIdentifier()"][97:"userIdentifier()"];
        result.child = setters2;
        [77:"FieldShiftFields"][98:"FieldShiftFields"] childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[78:"username"][99:"username"];
        result.child = childBuilder2.child.child.child.[79:"child"][100:"child"];
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        [101:"FieldShiftFields"][147:"FieldShiftFields"] var1 = getters;
        var1.username = record.[102:"username()"][103:"username()"][148:"username()"][149:"username()"];
        [104:"FieldShiftFields"][150:"FieldShiftFields"] var2 = var1;
        var2.active = source.[105:"isActive()"][106:"isActive()"][151:"isActive()"][152:"isActive()"];
        [107:"FieldShiftFields"][153:"FieldShiftFields"] result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[108:"username()"][109:"username()"][154:"username()"][155:"username()"]);
        result.username = source.[110:"getUsername()"][111:"getUsername()"][156:"getUsername()"][157:"getUsername()"];
        result.username = var1.[114:"getChild()"][160:"getChild()"].[112:"getUsername()"][113:"getUsername()"][158:"getUsername()"][159:"getUsername()"];
        result.username =[115:" "][161:" "]source.[116:"getUsername()"][162:"getUsername()"][115:" + \""][161:" + \""]1";
        result.username = source.[117:"getUsername()"][163:"getUsername()"] + source.[118:"getUserIdentifier()"][164:"getUserIdentifier()"];
        result.active = source.[119:"isActive()"][120:"isActive()"][165:"isActive()"][166:"isActive()"];
        result.userIdentifier = source.[121:"getUserIdentifier()"][122:"getUserIdentifier()"][167:"getUserIdentifier()"][168:"getUserIdentifier()"];
        [123:"FieldShiftFields"][169:"FieldShiftFields"] setters2 = new FieldShiftFields();
        setters2.child = var1.[124:"getChild()"][125:"getChild()"][170:"getChild()"][171:"getChild()"];
        var1.userIdentifier = (source.[126:"getUsername()"][127:"getUsername()"][172:"getUsername()"]);
        var1.username = source.[128:"getUsername()"][129:"getUsername()"][173:"getUsername()"][174:"getUsername()"];
        setters2.[130:"getChild()"][175:"getChild()"];
        setters2.active = getters.[131:"isActive()"][132:"isActive()"][176:"isActive()"][177:"isActive()"];
        setters2.username = getters.[133:"getUsername()"][134:"getUsername()"][178:"getUsername()"][179:"getUsername()"];
        setters2.userIdentifier = record.[135:"userIdentifier()"][136:"userIdentifier()"][180:"userIdentifier()"][181:"userIdentifier()"];
        result.child = setters2.[137:"getChild()"][138:"getChild()"][182:"getChild()"][183:"getChild()"];
        [139:"FieldShiftFields"][184:"FieldShiftFields"] childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[140:"getUsername()"][141:"getUsername()"][185:"getUsername()"][186:"getUsername()"];
        result.child = childBuilder2.[146:"getChild()"][191:"getChild()"].[145:"getChild()"][190:"getChild()"].[144:"getChild()"][189:"getChild()"].[142:"getChild()"][143:"getChild()"][187:"getChild()"][188:"getChild()"];
        return result;
    }


    private static String changer(String username) {[192:"\n        "][192:"return"][192:" "]username[192:";"][192:"\n    "]}[1:"\n\n    "][1:"public String getUsername() {\n        return username;\n    }"]][193:" "]username[193:";"][193:"\n    "]}[2:"\n\n    "][2:"public boolean isActive() {\n        return active;\n    }"]n"][194:" "]active[194:";"][194:"\n    "]}[3:"\n\n    "][3:"public String getUserIdentifier() {\n        return userIdentifier;\n    }"]" "]userIdentifier[195:";"][195:"\n    "]}[4:"\n\n    "][4:"public FieldShiftFields getChild() {\n        return child;\n    }"]rn"][196:" "]child[196:";"][196:"\n    "]}

    [197:"p"]ublic static class UserData2 {
        public String username;
        public boolean active;
        public String userIdentifier;[197:"\n\n        "][197:"public String getUsername() {\n            return username;\n        }"]8:" "]username[198:";"][198:"\n        "]}[197:"\n\n        "][197:"public boolean isActive() {\n            return active;\n        }"]199:" "]active[199:";"][199:"\n        "]}[197:"\n\n        "][197:"public String getUserIdentifier() {\n            return userIdentifier;\n        }"]userIdentifier[200:";"][200:"\n        "]}
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
