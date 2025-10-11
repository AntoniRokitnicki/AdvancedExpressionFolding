package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        [0:"StringBuilder"][18:"StringBuilder"] sb1 = [1:"new StringBuilder(\"[\")"][19:"new StringBuilder(\"[\")"];
        for [2:"(int "][20:"(int "]"int"] i[2:" = 0; i < args.length; i++) {\n                String"][20:" = 0; i < args.length; i++) {\n                String"]tring"] arg[2:" = "][20:" = "]args[2:"[i];"][20:"[i];"]
        sb1[23:".append("]arg[23:")"];
        if [24:"("]i < args.length - 1[24:")"] {
                sb1[25:".append("]","[25:")"];
            }
        }
                [3:"System.out."][26:"System.out."]println(sb1[4:".append("][5:".append("][27:".append("][28:".append("]"]"[4:").toString()"][5:").toString()"][27:").toString()"][28:").toString()"]);

        [6:"StringBuilder"][29:"StringBuilder"] sb2 = [7:"new StringBuilder().append("][30:"new StringBuilder().append("]"["[7:")"][30:")"];
        for [8:"(int "][31:"(int "]"int"] i[8:" = 0; i < args.length; i++) {\n                String"][31:" = 0; i < args.length; i++) {\n                String"]tring"] arg[8:" = "][31:" = "]args[8:"[i];"][31:"[i];"]
        sb2[34:".append("]arg[34:")"];
        if [35:"("]i < args.length - 1[35:")"] {
                sb2[36:".append("]","[36:")"];
            }
        }
                [9:"System.out."][37:"System.out."]println(sb2[10:".append("][11:".append("][38:".append("][39:".append("]"]"[10:").toString()"][11:").toString()"][38:").toString()"][39:").toString()"]);

        [12:"StringBuilder"][40:"StringBuilder"] sb3 = [13:"new StringBuilder()"][41:"new StringBuilder()"];
        for [14:"(int "][42:"(int "]"int"] i[14:" = 0; i < args.length; i++) {\n                String"][42:" = 0; i < args.length; i++) {\n                String"]tring"] arg[14:" = "][42:" = "]args[14:"[i];"][42:"[i];"]
        sb3[45:".append("]arg[45:")"];
        if [46:"("]i < args.length - 1[46:")"] {
                sb3[47:".append("]","[47:").append("]" "[47:")"];
            }
        }
                [15:"System.out."][48:"System.out."]println(sb3[16:".toString()"][17:".toString()"][49:".toString()"][50:".toString()"]);
    }
}
