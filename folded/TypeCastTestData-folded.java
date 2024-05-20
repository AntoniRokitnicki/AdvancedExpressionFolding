package data;

public class TypeCastTestData {
    public static void main(String[] args) {
        TypeCastTestData t = new TypeCastTestData();
        if (t.getObject() instanceof TypeCastTestData &&
                t.getObject().getObject() instanceof TypeCastTestData) {
                System.out.println(t.getObject().getObject().getObject());
        handle(t.getObject().getObject());
        }
    }

    private Object getObject() {
        return this;
    }

    private static void handle(TypeCastTestData t) {
        System.out.println(t);
    }
}
