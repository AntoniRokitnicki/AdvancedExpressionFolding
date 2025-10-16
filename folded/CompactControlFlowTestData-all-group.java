package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if [0:"("]args.length > 0[0:")"] {
                [10:"System.out."]println([11:"\"...\""]);
        }
        for [1:"("][13:"String"] arg : args[1:")"] {
                [14:"System.out."]println(arg);
        }
        for [2:"("][16:"int"] i[2:" = "]0[2:"; i < "]args.length[2:"; i++"][2:")"] {
                [17:"System.out."]println(i);
        }
        while [3:"("]true[3:")"] {
                [19:"System.out."]println([20:"\"...\""]);
        break;
        }
        do {
        break;
        } while [4:"("]true[4:")"];
        switch [5:"("]args.length[5:")"] {
        case 0:
            [23:"System.out."]println([24:"\"...\""]);
        }
            try {
                [6:"System.out."]println([7:"\"...\""]);
        } catch [8:"("]Exception e[8:")"] {
                e.printStackTrace();
        }
            if (true){
                [28:"System.out."]println([29:"\"...\""]);
        }
    }
}
