package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if [0:"("]{[0:"("]}args.length > 0[0:")"]{[0:")"]} {
                System.out.println("...");
        }
        for [0:"("]{[0:"("]}String arg : args[0:")"]{[0:")"]} {
                System.out.println(arg);
        }
        for [0:"("]{[0:"("]}int i = 0; i < args.length; i++[0:")"]{[0:")"]} {
                System.out.println(i);
        }
        while [0:"("]{[0:"("]}true[0:")"]{[0:")"]} {
                System.out.println("...");
        break;
        }
        do {
        break;
        } while [0:"("]{[0:"("]}true[0:")"]{[0:")"]};
        switch [0:"("]{[0:"("]}args.length[0:")"]{[0:")"]} {
        case 0:
            System.out.println("...");
        }
            try {
                System.out.println("...");
        } catch [0:"("]{[0:"("]}Exception e[0:")"]{[0:")"]} {
                e.printStackTrace();
        }
            if (true){
                System.out.println("...");
        }
    }
}
