package data;

public class StringBuilderTestData {
    public static void main(String[] args) {
        val sb1 = "[";
        for val (i, arg : args) {

        sb1 += arg;
        if i < args.length - 1 {
                sb1 += ",";
            }
        }
                println(sb1 + "]");

        val sb2 = "[";
        for val (i, arg : args) {

        sb2 += arg;
        if i < args.length - 1 {
                sb2 += ",";
            }
        }
                println(sb2 + "]");

        val sb3 = "";
        for val (i, arg : args) {

        sb3 += arg;
        if i < args.length - 1 {
                sb3 += "," + " ";
            }
        }
                println(sb3);
    }
}
