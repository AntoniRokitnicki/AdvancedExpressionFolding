package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder().append(args[0]);
        sb1.append("Hello, " + args[0]);
        System.out.println(sb1.toString());
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(args[0] + ", hello!");
        System.out.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder("Hello, ").append(args[0]); // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().setName("Hello, " + args[0]);
        new AppendSetterInterpolatedStringTestData().setName(args[0] + ", hello!");
    }

    public void setName(String name) {
        this.name = name;
    }
}
