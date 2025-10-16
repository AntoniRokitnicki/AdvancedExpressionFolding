package data;

import java.util.Arrays;

public class ControlFlowSingleStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) [8:"{"]
                System.out.println(Arrays.asList(args));
[8:"        }\n"]        if (args.length == 0) [9:"{"]
                System.out.println("...");
        [9:"} "]else [10:"{"]
                System.out.println("...");
[10:"        }\n"]        if (args.length == 0) {
                System.out.println("...");
        System.out.println("...");
        } else [11:"{"]
                System.out.println("...");
[11:"        }\n"]        if (args.length > 0) [12:"{"]
                System.out.println("...");
        [12:"} "]else {
                System.out.println("...");
        System.out.println("...");
        }
        for (String arg : args) [0:"{"]
                System.out.println(arg);
[0:"        }\n"]        for (int i = 0; i < args.length; i++) [1:"{"]
                System.out.println(args[i]);
[1:"        }\n"]        for (int i = 0; i < args.length; i++) {
                System.out.println(i);
        System.out.println(args[i]);
        }
        while (true) [2:"{"]
        break;
[2:"        }\n"]        while (true) {
                System.out.println("...");
        break;
        }
        do [3:"{"]
        break;
        [3:"} "]while (true);
        do {
                System.out.println("...");
        break;
        } while (true);
        try [4:"{"]
                System.out.println("...");
        [4:"} "]catch (Exception e) [5:"{"]
                System.out.println("...");
[5:"        }\n"]        try {
                System.out.println("...");
        System.out.println("...");
        } catch (Exception e) [6:"{"]
                System.out.println("...");
[6:"        }\n"]        try [7:"{"]
                System.out.println("...");
        [7:"} "]catch (Exception e) {
                System.out.println("...");
        System.out.println("...");
        }
    }
}
