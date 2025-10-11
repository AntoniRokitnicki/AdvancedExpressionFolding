package data;

public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) {
        EqualsCompareTestData a = new EqualsCompareTestData();
        EqualsCompareTestData b = new EqualsCompareTestData();
        System.out.println(a[0:".equals("][12:".equals("]b[0:")"][12:")"]);
        System.out.println([1:"!"][13:"!"]a[1:".equals("][13:".equals("]b[1:")"][13:")"]);
        System.out.println(a[2:".compareTo("][14:".compareTo("]b[2:") == 0"][14:") == 0"]);
        System.out.println(a[3:".compareTo("][15:".compareTo("]b[3:") != 0"][15:") != 0"]);

        System.out.println(a[4:".compareTo("][16:".compareTo("]b[4:") > 0"][16:") > 0"]);
        System.out.println(a[5:".compareTo("][17:".compareTo("]b[5:") == 1"][17:") == 1"]);
        System.out.println(a[6:".compareTo("][18:".compareTo("]b[6:") > -1"][18:") > -1"]);
        System.out.println(a[7:".compareTo("][19:".compareTo("]b[7:") >= 0"][19:") >= 0"]); // Should be a >= b

        System.out.println(a[8:".compareTo("][20:".compareTo("]b[8:") < 0"][20:") < 0"]);
        System.out.println(a[9:".compareTo("][21:".compareTo("]b[9:") == -1"][21:") == -1"]);
        System.out.println(a[10:".compareTo("][22:".compareTo("]b[10:") < 1"][22:") < 1"]);
        System.out.println(a[11:".compareTo("][23:".compareTo("]b[11:") <= 0"][23:") <= 0"]); // Should be a <= b
    }

    @Override
    public int compareTo(EqualsCompareTestData o) {
        return 0;
    }
}
