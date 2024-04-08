package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if args.length > 0 {
                println("...");
        }
        for val arg : args {
                println(arg);
        }
        for val i : [0, args.length) {
                println(i);
        }
        while true {
                println("...");
        break;
        }
        do {
        break;
        } while true;
        switch args.length {
        case 0:
            println("...");
        }
            try {
                println("...");
        } catch Exception e {
                e.printStackTrace();
        }
            if (true){
                println("...");
        }
    }
}
