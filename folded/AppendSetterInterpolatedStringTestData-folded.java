package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) {
        StringBuilder sb1 = args[0];
        sb1 += "Hello, ${args[0]}";
        System.out.println(sb1);
        StringBuilder sb2 = "";
        sb2 += "${args[0]}, hello!";
        System.out.println(sb2);
        StringBuilder sb3 = "Hello, " + args[0]; // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().name = "Hello, ${args[0]}";
        new AppendSetterInterpolatedStringTestData().name = "${args[0]}, hello!";
    }

    public void setName(String name) {
        this.name = name;
    }
}
