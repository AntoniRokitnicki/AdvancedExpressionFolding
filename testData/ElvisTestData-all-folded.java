package data;

public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        val e = create();
        println(e ?: "");
        println(e?.sayHello() ?: "");
        println(e?.get != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        println(e?.get != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        e?.get().sayHello();
        e.get()?.sayHello();
        if e?.get != null {
                e.get().sayHello();
        }
    }

    private String sayHello() {
        return "Hello";
    }

    private static ElvisTestData create() {
        if Math.random() > 0.5 {
        return new ElvisTestData();
        } else {
        return null;
        }
    }

    private ElvisTestData get() {
        return e;
    }
}
