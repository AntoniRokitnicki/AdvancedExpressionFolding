package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        ElvisTestData e = create();
        System.out.println([0:"e != null ? "]{[0:"e != null ? "]}e[0:" : "]{[0:" : "]}"");
        System.out.println([0:"e != null ? "]{[0:"e != null ? "]}e[0:"."]{[0:"."]}sayHello()[0:" : "]{[0:" : "]}"");
        System.out.println(e != null && e.get() != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        System.out.println(e != null && e.get() != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        [0:"if (e != null) {
                "]{[0:"if (e != null) {
                "]}e[0:"."]{[0:"."]}get().sayHello();[0:"
        }"]{[0:"
        }"]}
        [0:"if (e.get() != null) {
                "]{[0:"if (e.get() != null) {
                "]}e.get()[0:"."]{[0:"."]}sayHello();[0:"
        }"]{[0:"
        }"]}
        if (e != null && e.get() != null) {
                e.get().sayHello();
        }
    }

    private String sayHello() {
        return "Hello";
    }

    private static ElvisTestData create() {
        if (Math.random() > 0.5) {
        return new ElvisTestData();
        } else {
        return null;
        }
    }

    private ElvisTestData get() {
        return e;
    }
}
