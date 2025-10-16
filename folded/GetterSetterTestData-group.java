package data;

public class GetterSetterTestData {
    public static void main(String[] args) {
        GetterSetterTestData d = new GetterSetterTestData();
        d.[0:"setParent("]d[0:")"];
        d.[1:"setName("]"Hello"[1:")"];
        d.[3:"getParent()"].[2:"setName("]"Pum!"[2:")"];
        System.out.println(d.[5:"getParent()"].[4:"getName()"]);
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
