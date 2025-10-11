package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        System.out.println(list[0:".subList("]1[0:", list.size())"]);
        System.out.println(list[1:".subList("]1[1:", "]2[1:")"]);
        System.out.println(list[2:".subList("]1[2:", list.size())"]);
        System.out.println(list[3:".subList(0"][3:", "]2[3:")"]);
        System.out.println(list[4:".subList("]1[4:", list.size() "]-[4:" "]2[4:")"]);
        System.out.println(list[5:".subList(0"][5:", list.size() "]-[5:" "]2[5:")"]);
        String f = args[0];
        System.out.println(f[6:".substring("]1[6:")"]);
        System.out.println(f[7:".substring("]1[7:", "]2[7:")"]);
        System.out.println(f[8:".substring("]1[8:", f.length())"]);
        System.out.println(f[9:".substring(0"][9:", "]2[9:")"]);
        System.out.println(f[10:".substring("]1[10:", f.length() "]-[10:" "]2[10:")"]);
        System.out.println(f[11:".substring(0"][11:", f.length() "]-[11:" "]2[11:")"]);
    }
}
