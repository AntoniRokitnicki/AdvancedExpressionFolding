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
        this.username = [0:"username"][7:"username"];
        this.active = [1:"active"][8:"active"];
        this.userIdentifier = [2:"userIdentifier"][9:"userIdentifier"];
        this.child = [3:"child"][10:"child"];
        this.userIdentifier = child.[4:"userIdentifier"][11:"userIdentifier"];
        this.userIdentifier = child.[5:"getUserIdentifier()"][6:"getUserIdentifier()"][12:"getUserIdentifier()"][13:"getUserIdentifier()"];
    }

    public static FieldShiftFields mapPojoChain(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.child.[14:"username"][18:"username"];
        result.userIdentifier = source.child.child.child.[15:"userIdentifier"][19:"userIdentifier"];
        result.active = source.child.[16:"active"][20:"active"];
        result.list = List.copyOf(source.[17:"list"][21:"list"]);
        return result;
    }

    public static FieldShiftFields mapPojo(FieldShiftFields source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[22:"username"][25:"username"];
        result.userIdentifier = source.[23:"userIdentifier"][26:"userIdentifier"];
        result.active = source.[24:"active"][27:"active"];
        return result;
    }

    public static FieldShiftFields mapRecordByFields(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[28:"username"][31:"username"];
        result.active = source.[29:"active"][32:"active"];
        result.userIdentifier = source.[30:"userIdentifier"][33:"userIdentifier"];
        return result;
    }

    public static FieldShiftFields mapRecordByGetters(UserDataRecord source) {
        FieldShiftFields result = new FieldShiftFields();
        result.username = source.[34:"username()"][35:"username()"][40:"username()"][41:"username()"];
        result.active = source.[36:"active()"][37:"active()"][42:"active()"][43:"active()"];
        result.userIdentifier = source.[38:"userIdentifier()"][39:"userIdentifier()"][44:"userIdentifier()"][45:"userIdentifier()"];
        return result;
    }

    public static FieldShiftFields mapFields(UserData2 source, FieldShiftFields fields, UserDataRecord record) {
        FieldShiftFields var1 = fields;
        var1.username = record.[46:"username"][61:"username"];
        FieldShiftFields var2 = var1;
        var2.active = source.[47:"active"][62:"active"];
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[48:"username"][63:"username"]);
        result.username = source.[49:"username"][64:"username"];
        result.username = var1.child.[50:"username"][65:"username"];
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.[51:"active"][66:"active"];
        result.userIdentifier = source.[52:"userIdentifier"][67:"userIdentifier"];
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[53:"child"][68:"child"];
        var1.userIdentifier = source.username;
        var1.username = source.[54:"username"][69:"username"];
        setters2.active = fields.[55:"active"][70:"active"];
        setters2.username = fields.[56:"username"][71:"username"];
        setters2.userIdentifier = record.[57:"userIdentifier()"][58:"userIdentifier()"][72:"userIdentifier()"][73:"userIdentifier()"];
        result.child = setters2;
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[59:"username"][74:"username"];
        result.child = childBuilder2.child.child.child.[60:"child"][75:"child"];
        return result;
    }

    public static FieldShiftFields mapGetters(UserData2 source, FieldShiftFields getters, UserDataRecord record) {
        FieldShiftFields var1 = getters;
        var1.username = record.[76:"username()"][77:"username()"][115:"username()"][116:"username()"];
        FieldShiftFields var2 = var1;
        var2.active = source.[78:"isActive()"][79:"isActive()"][117:"isActive()"][118:"isActive()"];
        FieldShiftFields result = new FieldShiftFields();
        result.username = record.userIdentifier;
        result.username = changer(record.[80:"username()"][81:"username()"][119:"username()"][120:"username()"]);
        result.username = source.[82:"getUsername()"][83:"getUsername()"][121:"getUsername()"][122:"getUsername()"];
        result.username = var1.[86:"getChild()"][125:"getChild()"].[84:"getUsername()"][85:"getUsername()"][123:"getUsername()"][124:"getUsername()"];
        result.username = source.[87:"getUsername()"][126:"getUsername()"] + "1";
        result.username = source.[88:"getUsername()"][127:"getUsername()"] + source.[89:"getUserIdentifier()"][128:"getUserIdentifier()"];
        result.active = source.[90:"isActive()"][91:"isActive()"][129:"isActive()"][130:"isActive()"];
        result.userIdentifier = source.[92:"getUserIdentifier()"][93:"getUserIdentifier()"][131:"getUserIdentifier()"][132:"getUserIdentifier()"];
        FieldShiftFields setters2 = new FieldShiftFields();
        setters2.child = var1.[94:"getChild()"][95:"getChild()"][133:"getChild()"][134:"getChild()"];
        var1.userIdentifier = (source.[96:"getUsername()"][135:"getUsername()"]);
        var1.username = source.[97:"getUsername()"][98:"getUsername()"][136:"getUsername()"][137:"getUsername()"];
        setters2.[99:"getChild()"][138:"getChild()"];
        setters2.active = getters.[100:"isActive()"][101:"isActive()"][139:"isActive()"][140:"isActive()"];
        setters2.username = getters.[102:"getUsername()"][103:"getUsername()"][141:"getUsername()"][142:"getUsername()"];
        setters2.userIdentifier = record.[104:"userIdentifier()"][105:"userIdentifier()"][143:"userIdentifier()"][144:"userIdentifier()"];
        result.child = setters2.[106:"getChild()"][107:"getChild()"][145:"getChild()"][146:"getChild()"];
        FieldShiftFields childBuilder2 = new FieldShiftFields();
        childBuilder2.username = source.[108:"getUsername()"][109:"getUsername()"][147:"getUsername()"][148:"getUsername()"];
        result.child = childBuilder2.[114:"getChild()"][153:"getChild()"].[113:"getChild()"][152:"getChild()"].[112:"getChild()"][151:"getChild()"].[110:"getChild()"][111:"getChild()"][149:"getChild()"][150:"getChild()"];
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
