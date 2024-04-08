package data;

public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        ElvisTestData e = create();
        System.out.println(e != null ? e : "");
        System.out.println(e != null ? e.sayHello() : "");
        System.out.println(e != null && e.get() != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        System.out.println(e != null && e.get() != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        if (e != null) {
                e.get().sayHello();
        }
        if (e.get() != null) {
                e.get().sayHello();
        }
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
