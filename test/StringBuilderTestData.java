public class StringBuilderTestData {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("[");
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb1.append(arg);
        if (i < args.length - 1) {
                sb1.append(",");
            }
        }
                System.out.println(sb1.append("]").toString());

        StringBuilder sb2 = new StringBuilder().append("[");
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb2.append(arg);
        if (i < args.length - 1) {
                sb2.append(",");
            }
        }
                System.out.println(sb2.append("]").toString());

        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
                String arg = args[i];
        sb3.append(arg);
        if (i < args.length - 1) {
                sb3.append(",").append(" ");
            }
        }
                System.out.println(sb3.toString());
    }
}
