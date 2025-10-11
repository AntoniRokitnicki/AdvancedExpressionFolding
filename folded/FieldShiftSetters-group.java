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
        this.username = [0:"username"];
    }

    public void setActive(boolean active) {
        this.active = [2:"active"];
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = [4:"userIdentifier"];
    }

    public void setChild(FieldShiftSetters child) {
        this.child = [6:"child"];
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = [8:"list"];
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[10:"setUsername("]source.[12:"getChild()"][11:".getUsername()"][10:")"];
        result.[13:"setUserIdentifier("]source.[17:"getChild()"].[16:"getChild()"].[15:"getChild()"][14:".getUserIdentifier()"][13:")"];
        result.[18:"setActive("]source.[20:"getChild()"][19:".isActive()"][18:")"];
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[32:"setUsername("]source[33:".getUsername()"][32:")"];
        result.[34:"setUserIdentifier("]source[35:".getUserIdentifier()"][34:")"];
        result.[36:"setActive("]source[37:".isActive()"][36:")"];
        result.[38:"setList("]List.copyOf(source[39:".getList()"])[38:")"];
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[48:"setUsername("]source[49:".username()"][48:")"];
        result.[50:"setActive("]source[51:".active()"][50:")"];
        result.[52:"setUserIdentifier("]source[53:".userIdentifier()"][52:")"];
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        FieldShiftSetters var1 = setters;
        var1.[60:"setUsername("]record[61:".username()"][60:")"];
        FieldShiftSetters var2 = var1;
        var2.[62:"setActive("]source[63:".isActive()"][62:")"];
        FieldShiftSetters result = new FieldShiftSetters();
        result.[64:"setUsername("]record.[65:"userIdentifier()"][64:")"];
        result.[66:"setUsername("]changer(record[67:".username()"])[66:")"];
        result.[68:"setUsername("]source[69:".getUsername()"][68:")"];
        result.[70:"setUsername("]var1.[72:"getChild()"][71:".getUsername()"][70:")"];
        result.[73:"setUsername("]source.[74:"getUsername()"] + "1"[73:")"];
        result.[75:"setUsername("]source.[76:"getUsername()"] + source.[77:"getUserIdentifier()"][75:")"];
        result.[78:"setActive("]source[79:".isActive()"][78:")"];
        result.[80:"setUserIdentifier("]source[81:".getUserIdentifier()"][80:")"];
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.[82:"setChild("]var1[83:".getChild()"][82:")"];
        var1.[84:"setUserIdentifier("]source.[85:"getUsername()"][84:")"];
        var1.[86:"setUsername("]source[87:".getUsername()"][86:")"];
        setters2.[88:"getChild()"];
        setters2.[89:"setActive("]setters[90:".isActive()"][89:")"];
        setters2.[91:"setUsername("]setters[92:".getUsername()"][91:")"];
        setters2.[93:"setUserIdentifier("]record[94:".userIdentifier()"][93:")"];
        result.[95:"setChild("]setters2[95:")"];
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.[96:"setUsername("]source[97:".getUsername()"][96:")"];
        result.[98:"setChild("]childBuilder2.[102:"getChild()"].[101:"getChild()"].[100:"getChild()"][99:".getChild()"][98:")"];
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
            this.username = [146:"username"];
        }

        public void setActive(boolean active) {
            this.active = [148:"active"];
        }

        public void setUserIdentifier(String userIdentifier) {
            this.userIdentifier = [150:"userIdentifier"];
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
