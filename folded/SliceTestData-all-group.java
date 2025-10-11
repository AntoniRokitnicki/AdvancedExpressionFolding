package data;

import java.util.Arrays;
import java.util.List;

public class SliceTestData {
    public static void main(String[] args) {
        [0:"List<String>"][38:"List<String>"] list = Arrays.asList(args);
        [1:"System.out."][39:"System.out."]println(list[2:".subList("][3:".subList("][40:".subList("][41:".subList("]1[2:", list.size())"][3:", list.size())"][40:", list.size())"][41:", list.size())"]);
        [4:"System.out."][42:"System.out."]println(list[5:".subList("][6:".subList("][43:".subList("][44:".subList("]1[5:", "][6:", "][43:", "][44:", "]2[5:")"][6:")"][43:")"][44:")"]);
        [7:"System.out."][45:"System.out."]println(list[8:".subList("][9:".subList("][46:".subList("][47:".subList("]1[8:", list.size())"][9:", list.size())"][46:", list.size())"][47:", list.size())"]);
        [10:"System.out."][48:"System.out."]println(list[11:".subList(0"][12:".subList(0"][49:".subList(0"][50:".subList(0"][11:", "][12:", "][49:", "][50:", "]2[11:")"][12:")"][49:")"][50:")"]);
        [13:"System.out."][51:"System.out."]println(list[14:".subList("][15:".subList("][52:".subList("][53:".subList("]1[14:", list.size() "][15:", list.size() "][52:", list.size() "][53:", list.size() "]-[14:" "][15:" "][52:" "][53:" "]2[14:")"][15:")"][52:")"][53:")"]);
        [16:"System.out."][54:"System.out."]println(list[17:".subList(0"][18:".subList(0"][55:".subList(0"][56:".subList(0"][17:", list.size() "][18:", list.size() "][55:", list.size() "][56:", list.size() "]-[17:" "][18:" "][55:" "][56:" "]2[17:")"][18:")"][55:")"][56:")"]);
        [19:"String"][57:"String"] f = args[0];
        [20:"System.out."][58:"System.out."]println(f[21:".substring("][22:".substring("][59:".substring("][60:".substring("]1[21:")"][22:")"][59:")"][60:")"]);
        [23:"System.out."][61:"System.out."]println(f[24:".substring("][25:".substring("][62:".substring("][63:".substring("]1[24:", "][25:", "][62:", "][63:", "]2[24:")"][25:")"][62:")"][63:")"]);
        [26:"System.out."][64:"System.out."]println(f[27:".substring("][28:".substring("][65:".substring("][66:".substring("]1[27:", f.length())"][28:", f.length())"][65:", f.length())"][66:", f.length())"]);
        [29:"System.out."][67:"System.out."]println(f[30:".substring(0"][31:".substring(0"][68:".substring(0"][69:".substring(0"][30:", "][31:", "][68:", "][69:", "]2[30:")"][31:")"][68:")"][69:")"]);
        [32:"System.out."][70:"System.out."]println(f[33:".substring("][34:".substring("][71:".substring("][72:".substring("]1[33:", f.length() "][34:", f.length() "][71:", f.length() "][72:", f.length() "]-[33:" "][34:" "][71:" "][72:" "]2[33:")"][34:")"][71:")"][72:")"]);
        [35:"System.out."][73:"System.out."]println(f[36:".substring(0"][37:".substring(0"][74:".substring(0"][75:".substring(0"][36:", f.length() "][37:", f.length() "][74:", f.length() "][75:", f.length() "]-[36:" "][37:" "][74:" "][75:" "]2[36:")"][37:")"][74:")"][75:")"]);
    }
}
