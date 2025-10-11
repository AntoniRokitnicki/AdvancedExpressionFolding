package data;

public class TypeCastTestData {
    public static void main(String[] args) {
        TypeCastTestData t = new TypeCastTestData();
        if (t.getObject() instanceof TypeCastTestData &&
                [0:"((TypeCastTestData) "]t.getObject()[0:")."]getObject() instanceof TypeCastTestData) {
                System.out.println([0:"((TypeCastTestData) "][0:"((TypeCastTestData) "]t.getObject()[0:")."]getObject()[0:")."]getObject());
        handle([0:"((TypeCastTestData) "][0:"((TypeCastTestData) "]t.getObject()[0:")."]getObject()[0:")"]);
        }
    }

    private Object getObject() {
        return this;
    }

    private static void handle(TypeCastTestData t) {
        System.out.println(t);
    }
}
