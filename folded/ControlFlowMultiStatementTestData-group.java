package data;

public class ControlFlowMultiStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) [5:"{"]
                System.out.println("...");
        System.out.println("...");
[5:"        }\n"]        if (args.length == 0) [6:"{"]
                System.out.println("...");
        System.out.println("...");
        [6:"} "]else {
                System.out.println("Success");
        }
        if (args.length > 0) {
                System.out.println("Terminating");
        } else [7:"{"]
                System.out.println("Terminating");
        System.out.println("...");
[7:"        }\n"]        for (String arg : args) {
                System.out.println(arg);
        }
        int i = 0;
        for (String arg : args) [0:"{"][8:"{"]
                System.out.println(i++);
        System.out.println(arg);
[0:"        }\n"][8:"        }\n"]        while (true) [1:"{"][9:"{"]
                System.out.println("...");
        break;
[1:"        }\n"][9:"        }\n"]        while (true) {
        break;
        }
        try {
                System.out.println("...");
        } catch (Exception e) {
                e.printStackTrace();
        }
        try [2:"{"][10:"{"]
                System.out.println("...");
        System.out.println("...");
        [2:"} "][10:"} "]catch (Exception e) [3:"{"][11:"{"]
                System.out.println("...");
        e.printStackTrace();
[3:"        }\n"][11:"        }\n"]        do [4:"{"][12:"{"]
                System.out.println("...");
        break;
        [4:"} "][12:"} "]while (true);
        do {
        break;
        } while (true);
    }
}
