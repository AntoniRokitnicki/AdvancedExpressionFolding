package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        [0:"StringBuilder"] sb1 = [1:"new StringBuilder(\"[\")"];
        for [2:"(int "]i[2:" = 0; i < args.length; i++) {\n                String"] arg[2:" = "]args[2:"[i];"]
        sb1[23:".append("]arg[23:")"];
        if [24:"("]i < args.length - 1[24:")"] {
                sb1[25:".append("]","[25:")"];
            }
        }
                [3:"System.out."]println(sb1[4:".append("]"]"[4:").toString()"]);

        [6:"StringBuilder"] sb2 = [7:"new StringBuilder().append("]"["[7:")"];
        for [8:"(int "]i[8:" = 0; i < args.length; i++) {\n                String"] arg[8:" = "]args[8:"[i];"]
        sb2[34:".append("]arg[34:")"];
        if [35:"("]i < args.length - 1[35:")"] {
                sb2[36:".append("]","[36:")"];
            }
        }
                [9:"System.out."]println(sb2[10:".append("]"]"[10:").toString()"]);

        [12:"StringBuilder"] sb3 = [13:"new StringBuilder()"];
        for [14:"(int "]i[14:" = 0; i < args.length; i++) {\n                String"] arg[14:" = "]args[14:"[i];"]
        sb3[45:".append("]arg[45:")"];
        if [46:"("]i < args.length - 1[46:")"] {
                sb3[47:".append("]","[47:").append("]" "[47:")"];
            }
        }
                [15:"System.out."]println(sb3[16:".toString()"]);
    }
}
