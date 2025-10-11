package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        [0:"List<String>"] list = Arrays.asList(args);
        [1:"System.out."]println(list[2:".subList("]1[2:", list.size())"]);
        [4:"System.out."]println(list[5:".subList("]1[5:", "]2[5:")"]);
        [7:"System.out."]println(list[8:".subList("]1[8:", list.size())"]);
        [10:"System.out."]println(list[11:".subList(0"][11:", "]2[11:")"]);
        [13:"System.out."]println(list[14:".subList("]1[14:", list.size() "]-[14:" "]2[14:")"]);
        [16:"System.out."]println(list[17:".subList(0"][17:", list.size() "]-[17:" "]2[17:")"]);
        [19:"String"] f = args[0];
        [20:"System.out."]println(f[21:".substring("]1[21:")"]);
        [23:"System.out."]println(f[24:".substring("]1[24:", "]2[24:")"]);
        [26:"System.out."]println(f[27:".substring("]1[27:", f.length())"]);
        [29:"System.out."]println(f[30:".substring(0"][30:", "]2[30:")"]);
        [32:"System.out."]println(f[33:".substring("]1[33:", f.length() "]-[33:" "]2[33:")"]);
        [35:"System.out."]println(f[36:".substring(0"][36:", f.length() "]-[36:" "]2[36:")"]);
    }
}
