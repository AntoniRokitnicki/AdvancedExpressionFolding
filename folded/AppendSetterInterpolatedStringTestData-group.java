package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) {
        StringBuilder sb1 = [0:"new StringBuilder().append("]{[0:"new StringBuilder().append("]}args[0][0:")"]{[0:")"]};
        sb1[0:".append("]{[0:".append("]}"Hello, [0:"" + "]{[0:"" + "]{[0:"" + "]{[0:"" + "]}}}args[0][0:")"]{[0:")"]};
        System.out.println(sb1[0:".toString()"]{[0:".toString()"]});
        StringBuilder sb2 = [0:"new StringBuilder("")"]{[0:"new StringBuilder("")"]};
        sb2[0:".append"]{[0:".append"]}[0:"("]{[0:"("]}args[0][0:" + ""]{[0:" + ""]}, hello!"[0:")"]{[0:")"]};
        System.out.println(sb2[0:".toString()"]{[0:".toString()"]});
        StringBuilder sb3 = [0:"new StringBuilder("Hello, ")"]{[0:"new StringBuilder("Hello, ")"]}[0:".append("]{[0:".append("]}args[0][0:")"]{[0:")"]}; // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().[0:"setName("]{[0:"setName("]}"Hello, [0:"" + "]{[0:"" + "]{[0:"" + "]{[0:"" + "]}}}args[0][0:")"]{[0:")"]};
        new AppendSetterInterpolatedStringTestData().[0:"setName"]{[0:"setName"]}[0:"("]{[0:"("]}args[0][0:" + ""]{[0:" + ""]}, hello!"[0:")"]{[0:")"]};
    }

    public void setName(String name) {
        this.name = name;
    }
}
