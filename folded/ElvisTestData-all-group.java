package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        [0:"ElvisTestData"] e = create();
        [1:"System.out."]println([2:"e != null ? "]e[2:" : "]"");
        [4:"System.out."]println([5:"e != null ? "]e[5:"."]sayHello()[5:" : "]"");
        [7:"System.out."]println([8:"e != null && e.get() != null"] ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        [10:"System.out."]println([11:"e != null && e.get() != null"] ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        [13:"if (e != null) {\n                "]e[13:"."]get().sayHello();[13:"\n        }"]
        [14:"if (e.get() != null) {\n                "]e.get()[14:"."]sayHello();[14:"\n        }"]
        if [15:"("][32:"e != null && e.get() != null"][15:")"] {
                e.get().sayHello();
        }
    }

    private String sayHello() {[33:"\n        "][33:"return"][33:" "]"Hello"[33:";"][33:"\n    "]}

    private static ElvisTestData create() {[34:"\n        "]if [35:"("]Math.random() > 0.5[35:")"] {
        return new ElvisTestData();
        } else {
        return null;
        }[34:"\n    "]}

    private ElvisTestData get() {[38:"\n        "][38:"return"][38:" "]e[38:";"][38:"\n    "]}
}
