package data;

public class GetterSetterTestData {
    public static void main(String[] args) {
        GetterSetterTestData d = new GetterSetterTestData();
        d.[0:"setParent("][6:"setParent("]d[0:")"][6:")"];
        d.[1:"setName("][7:"setName("]"Hello"[1:")"][7:")"];
        d.[3:"getParent()"][9:"getParent()"].[2:"setName("][8:"setName("]"Pum!"[2:")"][8:")"];
        System.out.println(d.[5:"getParent()"][11:"getParent()"].[4:"getName()"][10:"getName()"]);
    }

    private GetterSetterTestData parent;
    private String name;

    private void setParent(GetterSetterTestData parent) {
        this.parent = parent;
    }

    private GetterSetterTestData getParent() {
        return parent;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
