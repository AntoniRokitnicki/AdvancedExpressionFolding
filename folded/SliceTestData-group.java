package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println(list[0:".subList("][12:".subList("]1[0:", list.size())"][12:", list.size())"]);
        System.out.println(list[1:".subList("][13:".subList("]1[1:", "][13:", "]2[1:")"][13:")"]);
        System.out.println(list[2:".subList("][14:".subList("]1[2:", list.size())"][14:", list.size())"]);
        System.out.println(list[3:".subList(0"][15:".subList(0"][3:", "][15:", "]2[3:")"][15:")"]);
        System.out.println(list[4:".subList("][16:".subList("]1[4:", list.size() "][16:", list.size() "]-[4:" "][16:" "]2[4:")"][16:")"]);
        System.out.println(list[5:".subList(0"][17:".subList(0"][5:", list.size() "][17:", list.size() "]-[5:" "][17:" "]2[5:")"][17:")"]);
        String f = args[0];
        System.out.println(f[6:".substring("][18:".substring("]1[6:")"][18:")"]);
        System.out.println(f[7:".substring("][19:".substring("]1[7:", "][19:", "]2[7:")"][19:")"]);
        System.out.println(f[8:".substring("][20:".substring("]1[8:", f.length())"][20:", f.length())"]);
        System.out.println(f[9:".substring(0"][21:".substring(0"][9:", "][21:", "]2[9:")"][21:")"]);
        System.out.println(f[10:".substring("][22:".substring("]1[10:", f.length() "][22:", f.length() "]-[10:" "][22:" "]2[10:")"][22:")"]);
        System.out.println(f[11:".substring(0"][23:".substring(0"][11:", f.length() "][23:", f.length() "]-[11:" "][23:" "]2[11:")"][23:")"]);
    }
}
