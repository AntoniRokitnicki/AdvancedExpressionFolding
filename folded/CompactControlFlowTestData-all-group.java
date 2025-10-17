package data;

public class CompactControlFlowTestData {
    public static void main(String[] args) {
        if [0:"("][9:"("]args.length > 0[0:")"][9:")"] {
                [10:"System.out."]println([11:"\"...\""]);
        }
        for [1:"("][12:"("][13:"String"] arg : args[1:")"][12:")"] {
                [14:"System.out."]println(arg);
        }
        for [2:"("][15:"("][16:"int"] i[2:" = "][15:" = "]0[2:"; i < "][15:"; i < "]args.length[2:"; i++"][15:"; i++"][2:")"][15:")"] {
                [17:"System.out."]println(i);
        }
        while [3:"("][18:"("]true[3:")"][18:")"] {
                [19:"System.out."]println([20:"\"...\""]);
        break;
        }
        do {
        break;
        } while [4:"("][21:"("]true[4:")"][21:")"];
        switch [5:"("][22:"("]args.length[5:")"][22:")"] {
        case 0:
            [23:"System.out."]println([24:"\"...\""]);
        }
            try {
                [6:"System.out."][25:"System.out."]println([7:"\"...\""][26:"\"...\""]);
        } catch [8:"("][27:"("]Exception e[8:")"][27:")"] {
                e.printStackTrace();
        }
            if (true){
                [28:"System.out."]println([29:"\"...\""]);
        }
    }
}
