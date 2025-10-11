package data;

import java.util.List;

[0:"p"]ublic class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;
    private List<String> list;[0:"\n\n    "][0:"public FieldShiftSetters() {\n    }"][0:"\n\n    "][0:"public void setUsername(String username) {\n        this.username = username;\n    }"][0:"\n\n    "][0:"public void setActive(boolean active) {\n        this.active = active;\n    }"][0:"\n\n    "][0:"public void setUserIdentifier(String userIdentifier) {\n        this.userIdentifier = userIdentifier;\n    }"][0:"\n\n    "][0:"public void setChild(FieldShiftSetters child) {\n        this.child = child;\n    }"][0:"\n\n    "][0:"public List<String> getList() {\n        return list;\n    }"][0:"\n\n    "][0:"public void setList(List<String> list) {\n        this.list = list;\n    }"]

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        [27:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[28:"setUsername("]source.[30:"getChild()"][29:".getUsername()"][28:")"];
        result.[31:"setUserIdentifier("]source.[35:"getChild()"].[34:"getChild()"].[33:"getChild()"][32:".getUserIdentifier()"][31:")"];
        result.[36:"setActive("]source.[38:"getChild()"][37:".isActive()"][36:")"];
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        [51:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[52:"setUsername("]source[53:".getUsername()"][52:")"];
        result.[54:"setUserIdentifier("]source[55:".getUserIdentifier()"][54:")"];
        result.[56:"setActive("]source[57:".isActive()"][56:")"];
        result.[58:"setList("]List.copyOf(source[59:".getList()"])[58:")"];
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        [69:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[70:"setUsername("]source[71:".username()"][70:")"];
        result.[72:"setActive("]source[73:".active()"][72:")"];
        result.[74:"setUserIdentifier("]source[75:".userIdentifier()"][74:")"];
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        [83:"FieldShiftSetters"] var1 = setters;
        var1.[84:"setUsername("]record[85:".username()"][84:")"];
        [86:"FieldShiftSetters"] var2 = var1;
        var2.[87:"setActive("]source[88:".isActive()"][87:")"];
        [89:"FieldShiftSetters"] result = new FieldShiftSetters();
        result.[90:"setUsername("]record.[91:"userIdentifier()"][90:")"];
        result.[92:"setUsername("]changer(record[93:".username()"])[92:")"];
        result.[94:"setUsername("]source[95:".getUsername()"][94:")"];
        result.[96:"setUsername("]var1.[98:"getChild()"][97:".getUsername()"][96:")"];
        result.[99:"setUsername"][99:"("]source.[100:"getUsername()"][99:" + \""]1"[99:")"];
        result.[101:"setUsername("]source.[102:"getUsername()"] + source.[103:"getUserIdentifier()"][101:")"];
        result.[104:"setActive("]source[105:".isActive()"][104:")"];
        result.[106:"setUserIdentifier("]source[107:".getUserIdentifier()"][106:")"];
        [108:"FieldShiftSetters"] setters2 = new FieldShiftSetters();
        setters2.[109:"setChild("]var1[110:".getChild()"][109:")"];
        var1.[111:"setUserIdentifier("]source.[112:"getUsername()"][111:")"];
        var1.[113:"setUsername("]source[114:".getUsername()"][113:")"];
        setters2.[115:"getChild()"];
        setters2.[116:"setActive("]setters[117:".isActive()"][116:")"];
        setters2.[118:"setUsername("]setters[119:".getUsername()"][118:")"];
        setters2.[120:"setUserIdentifier("]record[121:".userIdentifier()"][120:")"];
        result.[122:"setChild("]setters2[122:")"];
        [123:"FieldShiftSetters"] childBuilder2 = new FieldShiftSetters();
        childBuilder2.[124:"setUsername("]source[125:".getUsername()"][124:")"];
        result.[126:"setChild("]childBuilder2.[130:"getChild()"].[129:"getChild()"].[128:"getChild()"][127:".getChild()"][126:")"];
        return result;
    }

    private static String changer(String username) {[179:"\n        "][179:"return"][179:" "]username[179:";"][179:"\n    "]}[0:"\n\n\n\n    "][0:"public String getUsername() {\n        return this.username;\n    }"][0:"\n\n    "][0:"public boolean isActive() {\n        return this.active;\n    }"][0:"\n\n    "][0:"public String getUserIdentifier() {\n        return this.userIdentifier;\n    }"][0:"\n\n    "][0:"public FieldShiftSetters getChild() {\n        return this.child;\n    }"]

    [184:"p"]ublic static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;[184:"\n\n        "][184:"public UserData2() {\n        }"][184:"\n\n        "][184:"public void setUsername(String username) {\n            this.username = username;\n        }"][184:"\n\n        "][184:"public void setActive(boolean active) {\n            this.active = active;\n        }"][184:"\n\n        "][184:"public void setUserIdentifier(String userIdentifier) {\n            this.userIdentifier = userIdentifier;\n        }"][184:"\n\n        "][184:"public String getUsername() {\n            return this.username;\n        }"][184:"\n\n        "][184:"public boolean isActive() {\n            return this.active;\n        }"][184:"\n\n        "][184:"public String getUserIdentifier() {\n            return this.userIdentifier;\n        }"]
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
