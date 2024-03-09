package data;

@Getter @Setter public class FieldShiftSetters {
    private String username;
    private boolean active;
    private String userIdentifier;
    private FieldShiftSetters child;

    public FieldShiftSetters() {
    }

    public static FieldShiftSetters mapPojoChain(FieldShiftSetters source) {
        val result = new FieldShiftSetters();
        result.username = source.child<<;
        result.userIdentifier = source.child.child.child<<;
        result.active = source.child<<;
        return result;
    }

    public static FieldShiftSetters mapPojo(FieldShiftSetters source) {
        val result = new FieldShiftSetters();
        result.username = source<<;
        result.userIdentifier = source<<;
        result.active = source<<;
        return result;
    }

    public static FieldShiftSetters mapRecord(UserDataRecord source) {
        val result = new FieldShiftSetters();
        result.username = source<<;
        result.active = source<<;
        result.userIdentifier = source<<;
        return result;
    }

    public static FieldShiftSetters map(UserData2 source, FieldShiftSetters setters, UserDataRecord record) {
        val var1 = setters;
        var1.username = record<<;
        val var2 = var1;
        var2.active = source<<;
        val result = new FieldShiftSetters();
        result.username = record.userIdentifier;
        result.username = changer(record.username);
        result.username = source<<;
        result.username = var1.child<<;
        result.username = "${source.username}1";
        result.username = source.username + source.userIdentifier;
        result.active = source<<;
        result.userIdentifier = source<<;
        val setters2 = new FieldShiftSetters();
        setters2.child = var1<<;
        var1.userIdentifier = source.username;
        var1.username = source<<;
        setters2.child;
        setters2.active = setters<<;
        setters2.username = setters<<;
        setters2.userIdentifier = record<<;
        result.child = setters2;
        val childBuilder2 = new FieldShiftSetters();
        childBuilder2.username = source<<;
        result.child = childBuilder2.child.child.child<<;
        return result;
    }

    private static String changer(String username) {
        return username;
    }

    @Getter @Setter public static class UserData2 {
        private String username;
        private boolean active;
        private String userIdentifier;

        public UserData2() {
        }
    }

    public record UserDataRecord(String username, boolean active, String userIdentifier) {
    }
}
