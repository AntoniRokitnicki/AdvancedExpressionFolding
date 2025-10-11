package data;

public class ControlFlowMultiStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) [0:"{"]
                System.out.println("...");
        System.out.println("...");
[0:"        }
"]        if (args.length == 0) [0:"{"]
                System.out.println("...");
        System.out.println("...");
        [0:"} "]else {
                System.out.println("Success");
        }
        if (args.length > 0) {
                System.out.println("Terminating");
        } else [0:"{"]
                System.out.println("Terminating");
        System.out.println("...");
[0:"        }
"]        for (String arg : args) {
                System.out.println(arg);
        }
        int i = 0;
        for (String arg : args) [0:"{"]{[0:"{"]}
                System.out.println(i++);
        System.out.println(arg);
[0:"        }
"]{[0:"        }
"]}        while (true) [0:"{"]{[0:"{"]}
                System.out.println("...");
        break;
[0:"        }
"]{[0:"        }
"]}        while (true) {
        break;
        }
        try {
                System.out.println("...");
        } catch (Exception e) {
                e.printStackTrace();
        }
        try [0:"{"]{[0:"{"]}
                System.out.println("...");
        System.out.println("...");
        [0:"} "]{[0:"} "]}catch (Exception e) [0:"{"]{[0:"{"]}
                System.out.println("...");
        e.printStackTrace();
[0:"        }
"]{[0:"        }
"]}        do [0:"{"]{[0:"{"]}
                System.out.println("...");
        break;
        [0:"} "]{[0:"} "]}while (true);
        do {
        break;
        } while (true);
    }
}
