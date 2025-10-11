package data;

public class GetterSetterTestData {
    public static void main(String[] args) {
        GetterSetterTestData d = new GetterSetterTestData();
        d.[0:"setParent("]{[0:"setParent("]}d[0:")"]{[0:")"]};
        d.[0:"setName("]{[0:"setName("]}"Hello"[0:")"]{[0:")"]};
        d.[0:"getParent()"]{[0:"getParent()"]}.[0:"setName("]{[0:"setName("]}"Pum!"[0:")"]{[0:")"]};
        System.out.println(d.[0:"getParent()"]{[0:"getParent()"]}.[0:"getName()"]{[0:"getName()"]});
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
