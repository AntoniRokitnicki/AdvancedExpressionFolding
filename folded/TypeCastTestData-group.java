package data;

public class TypeCastTestData {
    public static void main(String[] args) {
        TypeCastTestData t = new TypeCastTestData();
        if (t.getObject() instanceof TypeCastTestData &&
                [0:"((TypeCastTestData) "]t.getObject()[0:")."]getObject() instanceof TypeCastTestData) {
                System.out.println([1:"((TypeCastTestData) "][2:"((TypeCastTestData) "]t.getObject()[2:")."]getObject()[1:")."]getObject());
        handle([3:"((TypeCastTestData) "][4:"((TypeCastTestData) "]t.getObject()[4:")."]getObject()[3:")"]);
        }
    }

    private Object getObject() {
        return this;
    }

    private static void handle(TypeCastTestData t) {
        System.out.println(t);
    }
}
