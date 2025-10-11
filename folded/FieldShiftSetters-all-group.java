package data;

import java.util.List;

[0:"p"]ublic class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;
    private List<String> list;[0:"\n\n    "][0:"public FieldShiftSetters() {\n    }"][0:"\n\n    "][0:"public void setUsername(String username) {\n        this.username = username;\n    }"]name"][3:"username"][4:"username"][5:"username"][1:";"][1:"\n    "]}[0:"\n\n    "][0:"public void setActive(boolean active) {\n        this.active = active;\n    }"]tive"][8:"active"][9:"active"][10:"active"][6:";"][6:"\n    "]}[0:"\n\n    "][0:"public void setUserIdentifier(String userIdentifier) {\n        this.userIdentifier = userIdentifier;\n    }"]tifier"][13:"userIdentifier"][14:"userIdentifier"][15:"userIdentifier"][11:";"][11:"\n    "]}[0:"\n\n    "][0:"public void setChild(FieldShiftSetters child) {\n        this.child = child;\n    }"]"child"][18:"child"][19:"child"][20:"child"][16:";"][16:"\n    "]}[0:"\n\n    "][0:"public List<String> getList() {\n        return list;\n    }"]n"][21:" "]list[21:";"][21:"\n    "]}[0:"\n\n    "][0:"public void setList(List<String> list) {\n        this.list = list;\n    }"]:"list"][24:"list"][25:"list"][26:"list"][22:";"][22:"\n    "]}

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        [27:"FieldShiftSetters"][39:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[28:"setUsername("][40:"setUsername("]source.[30:"getChild()"][42:"getChild()"][29:".getUsername()"][41:".getUsername()"][28:")"][40:")"];
        result.[31:"setUserIdentifier("][43:"setUserIdentifier("]source.[35:"getChild()"][47:"getChild()"].[34:"getChild()"][46:"getChild()"].[33:"getChild()"][45:"getChild()"][32:".getUserIdentifier()"][44:".getUserIdentifier()"][31:")"][43:")"];
        result.[36:"setActive("][48:"setActive("]source.[38:"getChild()"][50:"getChild()"][37:".isActive()"][49:".isActive()"][36:")"][48:")"];
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        [51:"FieldShiftSetters"][60:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[52:"setUsername("][61:"setUsername("]source[53:".getUsername()"][62:".getUsername()"][52:")"][61:")"];
        result.[54:"setUserIdentifier("][63:"setUserIdentifier("]source[55:".getUserIdentifier()"][64:".getUserIdentifier()"][54:")"][63:")"];
        result.[56:"setActive("][65:"setActive("]source[57:".isActive()"][66:".isActive()"][56:")"][65:")"];
        result.[58:"setList("][67:"setList("]List.copyOf(source[59:".getList()"][68:".getList()"])[58:")"][67:")"];
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        [69:"FieldShiftSetters"][76:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[70:"setUsername("][77:"setUsername("]source[71:".username()"][78:".username()"][70:")"][77:")"];
        result.[72:"setActive("][79:"setActive("]source[73:".active()"][80:".active()"][72:")"][79:")"];
        result.[74:"setUserIdentifier("][81:"setUserIdentifier("]source[75:".userIdentifier()"][82:".userIdentifier()"][74:")"][81:")"];
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        [83:"FieldShiftSetters"][131:"FieldShiftSetters"] var1 = setters;
        var1.[84:"setUsername("][132:"setUsername("]record[85:".username()"][133:".username()"][84:")"][132:")"];
        [86:"FieldShiftSetters"][134:"FieldShiftSetters"] var2 = var1;
        var2.[87:"setActive("][135:"setActive("]source[88:".isActive()"][136:".isActive()"][87:")"][135:")"];
        [89:"FieldShiftSetters"][137:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[90:"setUsername("][138:"setUsername("]record.[91:"userIdentifier()"][139:"userIdentifier()"][90:")"][138:")"];
        result.[92:"setUsername("][140:"setUsername("]changer(record[93:".username()"][141:".username()"])[92:")"][140:")"];
        result.[94:"setUsername("][142:"setUsername("]source[95:".getUsername()"][143:".getUsername()"][94:")"][142:")"];
        result.[96:"setUsername("][144:"setUsername("]var1.[98:"getChild()"][146:"getChild()"][97:".getUsername()"][145:".getUsername()"][96:")"][144:")"];
        result.[99:"setUsername"][147:"setUsername"][99:"("][147:"("]source.[100:"getUsername()"][148:"getUsername()"][99:" + \""][147:" + \""]1"[99:")"][147:")"];
        result.[101:"setUsername("][149:"setUsername("]source.[102:"getUsername()"][150:"getUsername()"] + source.[103:"getUserIdentifier()"][151:"getUserIdentifier()"][101:")"][149:")"];
        result.[104:"setActive("][152:"setActive("]source[105:".isActive()"][153:".isActive()"][104:")"][152:")"];
        result.[106:"setUserIdentifier("][154:"setUserIdentifier("]source[107:".getUserIdentifier()"][155:".getUserIdentifier()"][106:")"][154:")"];
        [108:"FieldShiftSetters"][156:"FieldShiftSetters"] setters2 = new FieldShiftSetters();
        setters2.[109:"setChild("][157:"setChild("]var1[110:".getChild()"][158:".getChild()"][109:")"][157:")"];
        var1.[111:"setUserIdentifier("][159:"setUserIdentifier("]source.[112:"getUsername()"][160:"getUsername()"][111:")"][159:")"];
        var1.[113:"setUsername("][161:"setUsername("]source[114:".getUsername()"][162:".getUsername()"][113:")"][161:")"];
        setters2.[115:"getChild()"][163:"getChild()"];
        setters2.[116:"setActive("][164:"setActive("]setters[117:".isActive()"][165:".isActive()"][116:")"][164:")"];
        setters2.[118:"setUsername("][166:"setUsername("]setters[119:".getUsername()"][167:".getUsername()"][118:")"][166:")"];
        setters2.[120:"setUserIdentifier("][168:"setUserIdentifier("]record[121:".userIdentifier()"][169:".userIdentifier()"][120:")"][168:")"];
        result.[122:"setChild("][170:"setChild("]setters2[122:")"][170:")"];
        [123:"FieldShiftSetters"][171:"FieldShiftSetters"] childBuilder2 = new FieldShiftSetters();
        childBuilder2.[124:"setUsername("][172:"setUsername("]source[125:".getUsername()"][173:".getUsername()"][124:")"][172:")"];
        result.[126:"setChild("][174:"setChild("]childBuilder2.[130:"getChild()"][178:"getChild()"].[129:"getChild()"][177:"getChild()"].[128:"getChild()"][176:"getChild()"][127:".getChild()"][175:".getChild()"][126:")"][174:")"];
        return result;
    }

    private static String changer(String username) {[179:"\n        "][179:"return"][179:" "]username[179:";"][179:"\n    "]}[0:"\n\n\n\n    "][0:"public String getUsername() {\n        return this.username;\n    }"]:" "]this.username[180:";"][180:"\n    "]}[0:"\n\n    "][0:"public boolean isActive() {\n        return this.active;\n    }"]81:" "]this.active[181:";"][181:"\n    "]}[0:"\n\n    "][0:"public String getUserIdentifier() {\n        return this.userIdentifier;\n    }"]his.userIdentifier[182:";"][182:"\n    "]}[0:"\n\n    "][0:"public FieldShiftSetters getChild() {\n        return this.child;\n    }"]183:" "]this.child[183:";"][183:"\n    "]}

    [184:"p"]ublic static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;[184:"\n\n        "][184:"public UserData2() {\n        }"][184:"\n\n        "][184:"public void setUsername(String username) {\n            this.username = username;\n        }"]name"][187:"username"][188:"username"][189:"username"][185:";"][185:"\n        "]}[184:"\n\n        "][184:"public void setActive(boolean active) {\n            this.active = active;\n        }"]tive"][192:"active"][193:"active"][194:"active"][190:";"][190:"\n        "]}[184:"\n\n        "][184:"public void setUserIdentifier(String userIdentifier) {\n            this.userIdentifier = userIdentifier;\n        }"]fier"][197:"userIdentifier"][198:"userIdentifier"][199:"userIdentifier"][195:";"][195:"\n        "]}[184:"\n\n        "][184:"public String getUsername() {\n            return this.username;\n        }"]]this.username[200:";"][200:"\n        "]}[184:"\n\n        "][184:"public boolean isActive() {\n            return this.active;\n        }"] "]this.active[201:";"][201:"\n        "]}[184:"\n\n        "][184:"public String getUserIdentifier() {\n            return this.userIdentifier;\n        }"]userIdentifier[202:";"][202:"\n        "]}
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
