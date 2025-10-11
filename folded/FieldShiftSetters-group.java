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
        this.username = [0:"username"]{[0:"username"]};
    }

    public void setActive(boolean active) {
        this.active = [0:"active"]{[0:"active"]};
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
    }

    public void setChild(FieldShiftSetters child) {
        this.child = [0:"child"]{[0:"child"]};
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = [0:"list"]{[0:"list"]};
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[0:"setUsername("]{[0:"setUsername("]}source.[0:"getChild()"]{[0:"getChild()"]}[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        result.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}source.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}[0:".getUserIdentifier()"]{[0:".getUserIdentifier()"]}[0:")"]{[0:")"]};
        result.[0:"setActive("]{[0:"setActive("]}source.[0:"getChild()"]{[0:"getChild()"]}[0:".isActive()"]{[0:".isActive()"]}[0:")"]{[0:")"]};
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[0:"setUsername("]{[0:"setUsername("]}source[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        result.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}source[0:".getUserIdentifier()"]{[0:".getUserIdentifier()"]}[0:")"]{[0:")"]};
        result.[0:"setActive("]{[0:"setActive("]}source[0:".isActive()"]{[0:".isActive()"]}[0:")"]{[0:")"]};
        result.[0:"setList("]{[0:"setList("]}List.copyOf(source[0:".getList()"]{[0:".getList()"]})[0:")"]{[0:")"]};
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        FieldShiftSetters result = new FieldShiftSetters();
        result.[0:"setUsername("]{[0:"setUsername("]}source[0:".username()"]{[0:".username()"]}[0:")"]{[0:")"]};
        result.[0:"setActive("]{[0:"setActive("]}source[0:".active()"]{[0:".active()"]}[0:")"]{[0:")"]};
        result.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}source[0:".userIdentifier()"]{[0:".userIdentifier()"]}[0:")"]{[0:")"]};
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        FieldShiftSetters var1 = setters;
        var1.[0:"setUsername("]{[0:"setUsername("]}record[0:".username()"]{[0:".username()"]}[0:")"]{[0:")"]};
        FieldShiftSetters var2 = var1;
        var2.[0:"setActive("]{[0:"setActive("]}source[0:".isActive()"]{[0:".isActive()"]}[0:")"]{[0:")"]};
        FieldShiftSetters result = new FieldShiftSetters();
        result.[0:"setUsername("]{[0:"setUsername("]}record.[0:"userIdentifier()"]{[0:"userIdentifier()"]}[0:")"]{[0:")"]};
        result.[0:"setUsername("]{[0:"setUsername("]}changer(record[0:".username()"]{[0:".username()"]})[0:")"]{[0:")"]};
        result.[0:"setUsername("]{[0:"setUsername("]}source[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        result.[0:"setUsername("]{[0:"setUsername("]}var1.[0:"getChild()"]{[0:"getChild()"]}[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        result.[0:"setUsername("]{[0:"setUsername("]}source.[0:"getUsername()"]{[0:"getUsername()"]} + "1"[0:")"]{[0:")"]};
        result.[0:"setUsername("]{[0:"setUsername("]}source.[0:"getUsername()"]{[0:"getUsername()"]} + source.[0:"getUserIdentifier()"]{[0:"getUserIdentifier()"]}[0:")"]{[0:")"]};
        result.[0:"setActive("]{[0:"setActive("]}source[0:".isActive()"]{[0:".isActive()"]}[0:")"]{[0:")"]};
        result.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}source[0:".getUserIdentifier()"]{[0:".getUserIdentifier()"]}[0:")"]{[0:")"]};
        FieldShiftSetters setters2 = new FieldShiftSetters();
        setters2.[0:"setChild("]{[0:"setChild("]}var1[0:".getChild()"]{[0:".getChild()"]}[0:")"]{[0:")"]};
        var1.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}source.[0:"getUsername()"]{[0:"getUsername()"]}[0:")"]{[0:")"]};
        var1.[0:"setUsername("]{[0:"setUsername("]}source[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        setters2.[0:"getChild()"]{[0:"getChild()"]};
        setters2.[0:"setActive("]{[0:"setActive("]}setters[0:".isActive()"]{[0:".isActive()"]}[0:")"]{[0:")"]};
        setters2.[0:"setUsername("]{[0:"setUsername("]}setters[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        setters2.[0:"setUserIdentifier("]{[0:"setUserIdentifier("]}record[0:".userIdentifier()"]{[0:".userIdentifier()"]}[0:")"]{[0:")"]};
        result.[0:"setChild("]{[0:"setChild("]}setters2[0:")"]{[0:")"]};
        FieldShiftSetters childBuilder2 = new FieldShiftSetters();
        childBuilder2.[0:"setUsername("]{[0:"setUsername("]}source[0:".getUsername()"]{[0:".getUsername()"]}[0:")"]{[0:")"]};
        result.[0:"setChild("]{[0:"setChild("]}childBuilder2.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}.[0:"getChild()"]{[0:"getChild()"]}[0:".getChild()"]{[0:".getChild()"]}[0:")"]{[0:")"]};
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
            this.username = [0:"username"]{[0:"username"]};
        }

        public void setActive(boolean active) {
            this.active = [0:"active"]{[0:"active"]};
        }

        public void setUserIdentifier(String userIdentifier) {
            this.userIdentifier = [0:"userIdentifier"]{[0:"userIdentifier"]};
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
