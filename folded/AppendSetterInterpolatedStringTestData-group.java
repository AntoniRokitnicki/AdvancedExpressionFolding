package data;

public class AppendSetterInterpolatedStringTestData {
    private String name;

    public static void main(String[] args) {
        StringBuilder sb1 = [0:"new StringBuilder().append("][10:"new StringBuilder().append("]args[0][0:")"][10:")"];
        sb1[1:".append("][11:".append("]"Hello, [1:"\" + "][1:"\" + "][11:"\" + "][11:"\" + "]args[0][1:")"][11:")"];
        System.out.println(sb1[2:".toString()"][12:".toString()"]);
        StringBuilder sb2 = [3:"new StringBuilder(\"\")"][13:"new StringBuilder(\"\")"];
        sb2[4:".append"][14:".append"][4:"("][14:"("]args[0][4:" + \""][14:" + \""], hello!"[4:")"][14:")"];
        System.out.println(sb2[5:".toString()"][15:".toString()"]);
        StringBuilder sb3 = [6:"new StringBuilder(\"Hello, \")"][16:"new StringBuilder(\"Hello, \")"][7:".append("][17:".append("]args[0][7:")"][17:")"]; // Should be StringBuilder sb3 = "Hello, $(args[0)":
        System.out.println(sb3);

        new AppendSetterInterpolatedStringTestData().[8:"setName("][18:"setName("]"Hello, [8:"\" + "][8:"\" + "][18:"\" + "][18:"\" + "]args[0][8:")"][18:")"];
        new AppendSetterInterpolatedStringTestData().[9:"setName"][19:"setName"][9:"("][19:"("]args[0][9:" + \""][19:" + \""], hello!"[9:")"][19:")"];
    }

    public void setName(String name) {
        this.name = name;
    }
}
