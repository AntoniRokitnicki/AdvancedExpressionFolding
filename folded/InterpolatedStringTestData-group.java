package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) {
        System.out.println("Hello, [0:"\" + "]args[0][0:")"];
        System.out.println("Hello, [1:"\" + "]args[0][1:" + \""]!");
        System.out.println[2:"("]args[0][2:" + \""], hello!");
        System.out.println[3:"("]args[0][3:" + \""], [3:"\" + "]args[0][3:")"];
        String name = args[0];
        System.out.println("Hello, [4:"\" + "]name[4:")"];
        System.out.println("Hello, [5:"\" + "]name[5:" + \""]!");
        System.out.println[6:"("]name[6:" + \""], hello!");
        System.out.println[7:"("]name[7:" + \""], [7:"\" + "]name[7:")"];
        System.out.println("Unicode: [8:"\" + "]'\u0041'[8:")"];
        System.out.println("Next: [9:"\" + "](char)('A' + 1)[9:")"];
        System.out.println("Length: [10:"\" + "]args.length[10:")"];
        System.out.println("Sum: [11:"\" + "](2 + 3)[11:")"];
        System.out.println("Upper: [12:"\" + "]name.toUpperCase()[12:")"];
    }
}
