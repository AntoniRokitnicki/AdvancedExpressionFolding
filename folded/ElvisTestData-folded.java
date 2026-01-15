package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        ElvisTestData e = create();
        System.out.println(e ?: "");
        System.out.println(e?.sayHello() ?: "");
        System.out.println(e?.sayHello());
        System.out.println(e);
        System.out.println(e ?: ""); // Inverted Elvis should also fold to e ?: ""
        System.out.println(e?.sayHello());
        System.out.println(e != null && e.get() != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        System.out.println(e != null && e.get() != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        System.out.println(e != null ? convert(e) : null);
        System.out.println(f() != null ? f() : other());
        e?.get().sayHello();
        e.get()?.sayHello();
        e?.get()?.sayHello();
        e?.get()?.get()?.sayHello(); // Should be e?.get()?.get()?.sayHello();
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

    private static String convert(ElvisTestData data) {
        return data?.sayHello();
    }

    private static ElvisTestData f() {
        return Math.random() > 0.5 ? new ElvisTestData() : null;
    }

    private static String other() {
        return "fallback";
    }
}
