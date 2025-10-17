package data;

public class EqualsCompareTestData implements Comparabl[0:"e"]<EqualsCompareTestData> {
    public static void main(String[] args) {
        [1:"EqualsCompareTestData"][39:"EqualsCompareTestData"] a = new EqualsCompareTestData();
        [2:"EqualsCompareTestData"][40:"EqualsCompareTestData"] b = new EqualsCompareTestData();
        [3:"System.out."][41:"System.out."]println(a[4:".equals("][5:".equals("][42:".equals("][43:".equals("]b[4:")"][5:")"][42:")"][43:")"]);
        [6:"System.out."][44:"System.out."]println([7:"!"][8:"!"][45:"!"][46:"!"]a[7:".equals("][8:".equals("][45:".equals("][46:".equals("]b[7:")"][8:")"][45:")"][46:")"]);
        [9:"System.out."][47:"System.out."]println(a[10:".compareTo("][11:".compareTo("][48:".compareTo("][49:".compareTo("]b[10:") == 0"][11:") == 0"][48:") == 0"][49:") == 0"]);
        [12:"System.out."][50:"System.out."]println(a[13:".compareTo("][14:".compareTo("][51:".compareTo("][52:".compareTo("]b[13:") != 0"][14:") != 0"][51:") != 0"][52:") != 0"]);

        [15:"System.out."][53:"System.out."]println(a[16:".compareTo("][17:".compareTo("][54:".compareTo("][55:".compareTo("]b[16:") > 0"][17:") > 0"][54:") > 0"][55:") > 0"]);
        [18:"System.out."][56:"System.out."]println(a[19:".compareTo("][20:".compareTo("][57:".compareTo("][58:".compareTo("]b[19:") == 1"][20:") == 1"][57:") == 1"][58:") == 1"]);
        [21:"System.out."][59:"System.out."]println(a[22:".compareTo("][23:".compareTo("][60:".compareTo("][61:".compareTo("]b[22:") > -1"][23:") > -1"][60:") > -1"][61:") > -1"]);
        [24:"System.out."][62:"System.out."]println(a[25:".compareTo("][26:".compareTo("][63:".compareTo("][64:".compareTo("]b[25:") >= 0"][26:") >= 0"][63:") >= 0"][64:") >= 0"]); // Should be a >= b

        [27:"System.out."][65:"System.out."]println(a[28:".compareTo("][29:".compareTo("][66:".compareTo("][67:".compareTo("]b[28:") < 0"][29:") < 0"][66:") < 0"][67:") < 0"]);
        [30:"System.out."][68:"System.out."]println(a[31:".compareTo("][32:".compareTo("][69:".compareTo("][70:".compareTo("]b[31:") == -1"][32:") == -1"][69:") == -1"][70:") == -1"]);
        [33:"System.out."][71:"System.out."]println(a[34:".compareTo("][35:".compareTo("][72:".compareTo("][73:".compareTo("]b[34:") < 1"][35:") < 1"][72:") < 1"][73:") < 1"]);
        [36:"System.out."][74:"System.out."]println(a[37:".compareTo("][38:".compareTo("][75:".compareTo("][76:".compareTo("]b[37:") <= 0"][38:") <= 0"][75:") <= 0"][76:") <= 0"]); // Should be a <= b
    }

    [78:"@Override"][78:"\n    "][77:"public"][77:" "][77:"int"][77:" "]compareTo(EqualsCompareTestData o) {[77:"\n        "][77:"return"][77:" "]0[77:";"][77:"\n    "]}
}
