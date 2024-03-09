package data;

@Getter @Setter public class GetterSetterTestData {
    public static void main(String[] args) {
        val d = new GetterSetterTestData();
        d.parent = d;
        d.name = "Hello";
        d.parent.name = "Pum!";
        System.out.println(d.parent.name);
    }

    private GetterSetterTestData parent;
    private String name;
}
