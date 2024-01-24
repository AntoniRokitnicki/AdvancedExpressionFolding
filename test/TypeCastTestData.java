public class TypeCastTestData {
    public static void main(String[] args) {
        TypeCastTestData t = new TypeCastTestData();
        if (t.getObject() instanceof TypeCastTestData &&
                ((TypeCastTestData) t.getObject()).getObject() instanceof TypeCastTestData) {
                System.out.println(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()).getObject());
        handle(((TypeCastTestData) ((TypeCastTestData) t.getObject()).getObject()));
        }
    }

    private Object getObject() {
        return this;
    }

    private static void handle(TypeCastTestData t) {
        System.out.println(t);
    }
}
