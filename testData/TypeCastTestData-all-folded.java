package data;

public class TypeCastTestData {
    public static void main(String[] args) {
        val t = new TypeCastTestData();
        if t.object instanceof TypeCastTestData &&
                t.object.object instanceof TypeCastTestData {
                System.out.println(t.object.object.object);
        handle(t.object.object);
        }
    }

    private Object getObject() {
        return this;
    }

    private static void handle(TypeCastTestData t) {
        System.out.println(t);
    }
}
