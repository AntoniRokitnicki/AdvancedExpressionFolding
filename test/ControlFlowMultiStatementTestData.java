import java.util.Arrays;

public class ControlFlowMultiStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) {
                System.out.println("...");
        System.out.println("...");
        }
        if (args.length == 0) {
                System.out.println("...");
        System.out.println("...");
        } else {
                System.out.println("Success");
        }
        if (args.length > 0) {
                System.out.println("Terminating");
        } else {
                System.out.println("Terminating");
        System.out.println("...");
        }
        for (String arg : args) {
                System.out.println(arg);
        }
        int i = 0;
        for (String arg : args) {
                System.out.println(i++);
        System.out.println(arg);
        }
        while (true) {
                System.out.println("...");
        break;
        }
        while (true) {
        break;
        }
        try {
                System.out.println("...");
        } catch (Exception e) {
                e.printStackTrace();
        }
        try {
                System.out.println("...");
        System.out.println("...");
        } catch (Exception e) {
                System.out.println("...");
        e.printStackTrace();
        }
        do {
                System.out.println("...");
        break;
        } while (true);
        do {
        break;
        } while (true);
    }
}
