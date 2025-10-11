package data;

@SuppressWarnings("ALL")
public class ElvisTestData {
    private ElvisTestData e = create();

    public static void main(String[] args) {
        [0:"ElvisTestData"][16:"ElvisTestData"] e = create();
        [1:"System.out."][17:"System.out."]println([2:"e != null ? "][3:"e != null ? "][18:"e != null ? "][19:"e != null ? "]e[2:" : "][3:" : "][18:" : "][19:" : "]"");
        [4:"System.out."][20:"System.out."]println([5:"e != null ? "][6:"e != null ? "][21:"e != null ? "][22:"e != null ? "]e[5:"."][6:"."][21:"."][22:"."]sayHello()[5:" : "][6:" : "][21:" : "][22:" : "]"");
        [7:"System.out."][23:"System.out."]println([8:"e != null && e.get() != null"][9:"e != null && e.get() != null"][24:"e != null && e.get() != null"][25:"e != null && e.get() != null"] ? e.get() : ""); // Should be System.out.println(e?.get ?: "")
        [10:"System.out."][26:"System.out."]println([11:"e != null && e.get() != null"][12:"e != null && e.get() != null"][27:"e != null && e.get() != null"][28:"e != null && e.get() != null"] ? e.get().sayHello() : ""); // Should be System.out.println(e?.get?.sayHello() ?: "")
        [13:"if (e != null) {\n                "][29:"if (e != null) {\n                "]e[13:"."][29:"."]get().sayHello();[13:"\n        }"][29:"\n        }"]
        [14:"if (e.get() != null) {\n                "][30:"if (e.get() != null) {\n                "]e.get()[14:"."][30:"."]sayHello();[14:"\n        }"][30:"\n        }"]
        if [15:"("][31:"("][32:"e != null && e.get() != null"][15:")"][31:")"] {
                e.get().sayHello();
        }
    }

    private String sayHello() {[33:"\n        "][33:"return"][33:" "]"Hello"[33:";"][33:"\n    "]}

    private static ElvisTestData create() {[34:"\n        "]if [35:"("][36:"("][37:"("]Math.random() > 0.5[35:")"][36:")"][37:")"] {
        return new ElvisTestData();
        } else {
        return null;
        }[34:"\n    "]}

    private ElvisTestData get() {[38:"\n        "][38:"return"][38:" "]e[38:";"][38:"\n    "]}
}
