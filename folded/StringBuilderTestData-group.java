package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        StringBuilder sb1 = [0:"new StringBuilder(\"[\")"][9:"new StringBuilder(\"[\")"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb1[1:".append("][10:".append("]arg[1:")"][10:")"];
        if (i < args.length - 1) {
                sb1[11:".append("]","[11:")"];
            }
        }
                System.out.println(sb1[2:".append("][12:".append("]"]"[2:").toString()"][12:").toString()"]);

        StringBuilder sb2 = [3:"new StringBuilder().append("][13:"new StringBuilder().append("]"["[3:")"][13:")"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb2[4:".append("][14:".append("]arg[4:")"][14:")"];
        if (i < args.length - 1) {
                sb2[15:".append("]","[15:")"];
            }
        }
                System.out.println(sb2[5:".append("][16:".append("]"]"[5:").toString()"][16:").toString()"]);

        StringBuilder sb3 = [6:"new StringBuilder()"][17:"new StringBuilder()"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb3[7:".append("][18:".append("]arg[7:")"][18:")"];
        if (i < args.length - 1) {
                sb3[19:".append("]","[19:").append("]" "[19:")"];
            }
        }
                System.out.println(sb3[8:".toString()"][20:".toString()"]);
    }
}
