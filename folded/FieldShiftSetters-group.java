package data;

import java.util.List;

public class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;
    private List<String> list;

    public FieldShiftSetters() {
    }

    public void setUsername(String username) {
        this.username = [0:"username"][1:"username"];
    }

    public void setActive(boolean active) {
        this.active = [2:"active"][3:"active"];
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = [4:"userIdentifier"][5:"userIdentifier"];
    }

    public void setChild(FieldShiftSetters child) {
        this.child = [6:"child"][7:"child"];
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = [8:"list"][9:"list"];
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[10:"setUsername("][21:"setUsername("]source.[12:"getChild()"][23:"getChild()"][11:".getUsername()"][22:".getUsername()"][10:")"][21:")"];
        result.[13:"setUserIdentifier("][24:"setUserIdentifier("]source.[17:"getChild()"][28:"getChild()"].[16:"getChild()"][27:"getChild()"].[15:"getChild()"][26:"getChild()"][14:".getUserIdentifier()"][25:".getUserIdentifier()"][13:")"][24:")"];
        result.[18:"setActive("][29:"setActive("]source.[20:"getChild()"][31:"getChild()"][19:".isActive()"][30:".isActive()"][18:")"][29:")"];
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[32:"setUsername("][40:"setUsername("]source[33:".getUsername()"][41:".getUsername()"][32:")"][40:")"];
        result.[34:"setUserIdentifier("][42:"setUserIdentifier("]source[35:".getUserIdentifier()"][43:".getUserIdentifier()"][34:")"][42:")"];
        result.[36:"setActive("][44:"setActive("]source[37:".isActive()"][45:".isActive()"][36:")"][44:")"];
        result.[38:"setList("][46:"setList("]List.copyOf(source[39:".getList()"][47:".getList()"])[38:")"][46:")"];
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[48:"setUsername("][54:"setUsername("]source[49:".username()"][55:".username()"][48:")"][54:")"];
        result.[50:"setActive("][56:"setActive("]source[51:".active()"][57:".active()"][50:")"][56:")"];
        result.[52:"setUserIdentifier("][58:"setUserIdentifier("]source[53:".userIdentifier()"][59:".userIdentifier()"][52:")"][58:")"];
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        FieldShiftSetters var1 = setters;
        var1.[60:"setUsername("][103:"setUsername("]record[61:".username()"][104:".username()"][60:")"][103:")"];
        FieldShiftSetters var2 = var1;
        var2.[62:"setActive("][105:"setActive("]source[63:".isActive()"][106:".isActive()"][62:")"][105:")"];
        FieldShiftSetters result = new FieldShiftSetters();
        result.[64:"setUsername("][107:"setUsername("]record.[65:"userIdentifier()"][108:"userIdentifier()"][64:")"][107:")"];
        result.[66:"setUsername("][109:"setUsername("]changer(record[67:".username()"][110:".username()"])[66:")"][109:")"];
        result.[68:"setUsername("][111:"setUsername("]source[69:".getUsername()"][112:".getUsername()"][68:")"][111:")"];
        result.[70:"setUsername("][113:"setUsername("]var1.[72:"getChild()"][115:"getChild()"][71:".getUsername()"][114:".getUsername()"][70:")"][113:")"];
        result.[73:"setUsername("][116:"setUsername("]source.[74:"getUsername()"][117:"getUsername()"] + "1"[73:")"][116:")"];
        result.[75:"setUsername("][118:"setUsername("]source.[76:"getUsername()"][119:"getUsername()"] + source.[77:"getUserIdentifier()"][120:"getUserIdentifier()"][75:")"][118:")"];
        result.[78:"setActive("][121:"setActive("]source[79:".isActive()"][122:".isActive()"][78:")"][121:")"];
        result.[80:"setUserIdentifier("][123:"setUserIdentifier("]source[81:".getUserIdentifier()"][124:".getUserIdentifier()"][80:")"][123:")"];
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.[82:"setChild("][125:"setChild("]var1[83:".getChild()"][126:".getChild()"][82:")"][125:")"];
        var1.[84:"setUserIdentifier("][127:"setUserIdentifier("]source.[85:"getUsername()"][128:"getUsername()"][84:")"][127:")"];
        var1.[86:"setUsername("][129:"setUsername("]source[87:".getUsername()"][130:".getUsername()"][86:")"][129:")"];
        setters2.[88:"getChild()"][131:"getChild()"];
        setters2.[89:"setActive("][132:"setActive("]setters[90:".isActive()"][133:".isActive()"][89:")"][132:")"];
        setters2.[91:"setUsername("][134:"setUsername("]setters[92:".getUsername()"][135:".getUsername()"][91:")"][134:")"];
        setters2.[93:"setUserIdentifier("][136:"setUserIdentifier("]record[94:".userIdentifier()"][137:".userIdentifier()"][93:")"][136:")"];
        result.[95:"setChild("][138:"setChild("]setters2[95:")"][138:")"];
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.[96:"setUsername("][139:"setUsername("]source[97:".getUsername()"][140:".getUsername()"][96:")"][139:")"];
        result.[98:"setChild("][141:"setChild("]childBuilder2.[102:"getChild()"][145:"getChild()"].[101:"getChild()"][144:"getChild()"].[100:"getChild()"][143:"getChild()"][99:".getChild()"][142:".getChild()"][98:")"][141:")"];
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
            this.username = [146:"username"][147:"username"];
        }

        public void setActive(boolean active) {
            this.active = [148:"active"][149:"active"];
        }

        public void setUserIdentifier(String userIdentifier) {
            this.userIdentifier = [150:"userIdentifier"][151:"userIdentifier"];
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
