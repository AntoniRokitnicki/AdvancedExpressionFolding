package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) {
        System.out.println("Hello, " + args[0]);
        System.out.println("Hello, " + args[0] + "!");
        System.out.println(args[0] + ", hello!");
        System.out.println(args[0] + ", " + args[0]);
        String name = args[0];
        System.out.println("Hello, " + name);
        System.out.println("Hello, " + name + "!");
        System.out.println(name + ", hello!");
        System.out.println(name + ", " + name);
        System.out.println("!" + args[0] + '!');
        System.out.println('!' + name + "!");
    }
}
