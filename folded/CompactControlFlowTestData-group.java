package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if [0:"("]args.length > 0[0:")"] {
                System.out.println("...");
        }
        for [1:"("]String arg : args[1:")"] {
                System.out.println(arg);
        }
        for [2:"("]int i = 0; i < args.length; i++[2:")"] {
                System.out.println(i);
        }
        while [3:"("]true[3:")"] {
                System.out.println("...");
        break;
        }
        do {
        break;
        } while [4:"("]true[4:")"];
        switch [5:"("]args.length[5:")"] {
        case 0:
            System.out.println("...");
        }
            try {
                System.out.println("...");
        } catch [6:"("]Exception e[6:")"] {
                e.printStackTrace();
        }
            if (true){
                System.out.println("...");
        }
    }
}
