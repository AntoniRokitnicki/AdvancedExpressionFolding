package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        ElvisTestData e = create();
        System.out.println([0:"e != null ? "]e[0:" : "]"");
        System.out.println([1:"e != null ? "]e[1:"."]sayHello()[1:" : "]"");
        System.out.println(e != null && e.get() != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        System.out.println(e != null && e.get() != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        [2:"if (e != null) {\n                "]e[2:"."]get().sayHello();[2:"\n        }"]
        [3:"if (e.get() != null) {\n                "]e.get()[3:"."]sayHello();[3:"\n        }"]
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
