package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if [0:"("][7:"("]args.length > 0[0:")"][7:")"] {
                System.out.println("...");
        }
        for [1:"("][8:"("]String arg : args[1:")"][8:")"] {
                System.out.println(arg);
        }
        for [2:"("][9:"("]int i = 0; i < args.length; i++[2:")"][9:")"] {
                System.out.println(i);
        }
        while [3:"("][10:"("]true[3:")"][10:")"] {
                System.out.println("...");
        break;
        }
        do {
        break;
        } while [4:"("][11:"("]true[4:")"][11:")"];
        switch [5:"("][12:"("]args.length[5:")"][12:")"] {
        case 0:
            System.out.println("...");
        }
            try {
                System.out.println("...");
        } catch [6:"("][13:"("]Exception e[6:")"][13:")"] {
                e.printStackTrace();
        }
            if (true){
                System.out.println("...");
        }
    }
}
