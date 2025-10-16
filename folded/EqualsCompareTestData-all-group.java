package data;

public class EqualsCompareTestData implements Comparabl[0:"e"]<EqualsCompareTestData> {
    public static void main(String[] args) {
        [1:"EqualsCompareTestData"] a = new EqualsCompareTestData();
        [2:"EqualsCompareTestData"] b = new EqualsCompareTestData();
        [3:"System.out."]println(a[4:".equals("]b[4:")"]);
        [6:"System.out."]println([7:"!"]a[7:".equals("]b[7:")"]);
        [9:"System.out."]println(a[10:".compareTo("]b[10:") == 0"]);
        [12:"System.out."]println(a[13:".compareTo("]b[13:") != 0"]);

        [15:"System.out."]println(a[16:".compareTo("]b[16:") > 0"]);
        [18:"System.out."]println(a[19:".compareTo("]b[19:") == 1"]);
        [21:"System.out."]println(a[22:".compareTo("]b[22:") > -1"]);
        [24:"System.out."]println(a[25:".compareTo("]b[25:") >= 0"]); // Should be a >= b

        [27:"System.out."]println(a[28:".compareTo("]b[28:") < 0"]);
        [30:"System.out."]println(a[31:".compareTo("]b[31:") == -1"]);
        [33:"System.out."]println(a[34:".compareTo("]b[34:") < 1"]);
        [36:"System.out."]println(a[37:".compareTo("]b[37:") <= 0"]); // Should be a <= b
    }

    [78:"@Override"][78:"\n    "][77:"public"][77:" "][77:"int"][77:" "]compareTo(EqualsCompareTestData o) {[77:"\n        "][77:"return"][77:" "]0[77:";"][77:"\n    "]}
}
