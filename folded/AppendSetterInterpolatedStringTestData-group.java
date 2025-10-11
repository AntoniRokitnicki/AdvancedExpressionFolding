package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) {
        StringBuilder sb1 = [0:"new StringBuilder().append("]args[0][0:")"];
        sb1[1:".append("]"Hello, [1:"\" + "]args[0][1:")"];
        System.out.println(sb1[2:".toString()"]);
        StringBuilder sb2 = [3:"new StringBuilder(\"\")"];
        sb2[4:".append"][4:"("]args[0][4:" + \""], hello!"[4:")"];
        System.out.println(sb2[5:".toString()"]);
        StringBuilder sb3 = [6:"new StringBuilder(\"Hello, \")"][7:".append("]args[0][7:")"]; // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().[8:"setName("]"Hello, [8:"\" + "]args[0][8:")"];
        new AppendSetterInterpolatedStringTestData().[9:"setName"][9:"("]args[0][9:" + \""], hello!"[9:")"];
    }

    public void setName(String name) {
        this.name = name;
    }
}
