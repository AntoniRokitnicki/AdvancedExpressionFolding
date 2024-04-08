package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) {
        println("Hello, ${args[0]}");
        println("Hello, ${args[0]}!");
        println("${args[0]}, hello!");
        println("${args[0]}, ${args[0]}");
        val name = args[0];
        println("Hello, $name");
        println("Hello, $name!");
        println("$name, hello!");
        println("$name, $name");
    }
}
