package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        ElvisTestData e = create();
        System.out.println([0:"e != null ? "][4:"e != null ? "]e[0:" : "][4:" : "]"");
        System.out.println([1:"e != null ? "][5:"e != null ? "]e[1:"."][5:"."]sayHello()[1:" : "][5:" : "]"");
        System.out.println(e != null && e.get() != null ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        System.out.println(e != null && e.get() != null ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        [2:"if (e != null) {\n                "][6:"if (e != null) {\n                "]e[2:"."][6:"."]get().sayHello();[2:"\n        }"][6:"\n        }"]
        [3:"if (e.get() != null) {\n                "][7:"if (e.get() != null) {\n                "]e.get()[3:"."][7:"."]sayHello();[3:"\n        }"][7:"\n        }"]
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
