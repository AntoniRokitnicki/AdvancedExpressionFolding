package data;

import java.util.Arrays;

public class ControlFlowSingleStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) [0:"{"]
                System.out.println(Arrays.asList(args));
[0:"        }
"]        if (args.length == 0) [0:"{"]
                System.out.println("...");
        [0:"} "]else [0:"{"]
                System.out.println("...");
[0:"        }
"]        if (args.length == 0) {
                System.out.println("...");
        System.out.println("...");
        } else [0:"{"]
                System.out.println("...");
[0:"        }
"]        if (args.length > 0) [0:"{"]
                System.out.println("...");
        [0:"} "]else {
                System.out.println("...");
        System.out.println("...");
        }
        for (String arg : args) [0:"{"]{[0:"{"]}
                System.out.println(arg);
[0:"        }
"]{[0:"        }
"]}        for (int i = 0; i < args.length; i++) [0:"{"]{[0:"{"]}
                System.out.println(args[i]);
[0:"        }
"]{[0:"        }
"]}        for (int i = 0; i < args.length; i++) {
                System.out.println(i);
        System.out.println(args[i]);
        }
        while (true) [0:"{"]{[0:"{"]}
        break;
[0:"        }
"]{[0:"        }
"]}        while (true) {
                System.out.println("...");
        break;
        }
        do [0:"{"]{[0:"{"]}
        break;
        [0:"} "]{[0:"} "]}while (true);
        do {
                System.out.println("...");
        break;
        } while (true);
        try [0:"{"]{[0:"{"]}
                System.out.println("...");
        [0:"} "]{[0:"} "]}catch (Exception e) [0:"{"]{[0:"{"]}
                System.out.println("...");
[0:"        }
"]{[0:"        }
"]}        try {
                System.out.println("...");
        System.out.println("...");
        } catch (Exception e) [0:"{"]{[0:"{"]}
                System.out.println("...");
[0:"        }
"]{[0:"        }
"]}        try [0:"{"]{[0:"{"]}
                System.out.println("...");
        [0:"} "]{[0:"} "]}catch (Exception e) {
                System.out.println("...");
        System.out.println("...");
        }
    }
}
