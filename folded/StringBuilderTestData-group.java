package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        StringBuilder sb1 = [0:"new StringBuilder(\"[\")"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb1[1:".append("]arg[1:")"];
        if (i < args.length - 1) {
                sb1[11:".append("]","[11:")"];
            }
        }
                System.out.println(sb1[2:".append("]"]"[2:").toString()"]);

        StringBuilder sb2 = [3:"new StringBuilder().append("]"["[3:")"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb2[4:".append("]arg[4:")"];
        if (i < args.length - 1) {
                sb2[15:".append("]","[15:")"];
            }
        }
                System.out.println(sb2[5:".append("]"]"[5:").toString()"]);

        StringBuilder sb3 = [6:"new StringBuilder()"];
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb3[7:".append("]arg[7:")"];
        if (i < args.length - 1) {
                sb3[19:".append("]","[19:").append("]" "[19:")"];
            }
        }
                System.out.println(sb3[8:".toString()"]);
    }
}
