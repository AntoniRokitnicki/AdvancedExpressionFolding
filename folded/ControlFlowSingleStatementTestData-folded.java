package data;

import java.util.Arrays;

public class ControlFlowSingleStatementTestData {
    public static void main(String[] args) {
        if (args.length > 0) 
                System.out.println(Arrays.asList(args));
        if (args.length == 0) 
                System.out.println("...");
        else 
                System.out.println("...");
        if (args.length == 0) {
                System.out.println("...");
        System.out.println("...");
        } else 
                System.out.println("...");
        if (args.length > 0) 
                System.out.println("...");
        else {
                System.out.println("...");
        System.out.println("...");
        }
        for (String arg : args) 
                System.out.println(arg);
        for (int i = 0; i < args.length; i++) 
                System.out.println(args[i]);
        for (int i = 0; i < args.length; i++) {
                System.out.println(i);
        System.out.println(args[i]);
        }
        while (true) 
        break;
        while (true) {
                System.out.println("...");
        break;
        }
        do 
        break;
        while (true);
        do {
                System.out.println("...");
        break;
        } while (true);
        try 
                System.out.println("...");
        catch (Exception e) 
                System.out.println("...");
        try {
                System.out.println("...");
        System.out.println("...");
        } catch (Exception e) 
                System.out.println("...");
        try 
                System.out.println("...");
        catch (Exception e) {
                System.out.println("...");
        System.out.println("...");
        }
    }
}
