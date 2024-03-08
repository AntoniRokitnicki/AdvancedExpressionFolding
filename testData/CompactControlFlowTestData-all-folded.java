package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if args.length > 0 {
                System.out.println("...");
        }
        for val arg : args {
                System.out.println(arg);
        }
        for val i : [0, args.length) {
                System.out.println(i);
        }
        while true {
                System.out.println("...");
        break;
        }
        do {
        break;
        } while true;
        switch args.length {
        case 0:
            System.out.println("...");
        }
            try {
                System.out.println("...");
        } catch Exception e {
                e.printStackTrace();
        }
            if (true){
                System.out.println("...");
        }
    }
}
