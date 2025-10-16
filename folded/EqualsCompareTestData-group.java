package data;

public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) {
        EqualsCompareTestData a = new EqualsCompareTestData();
        EqualsCompareTestData b = new EqualsCompareTestData();
        System.out.println(a[0:".equals("]b[0:")"]);
        System.out.println([1:"!"]a[1:".equals("]b[1:")"]);
        System.out.println(a[2:".compareTo("]b[2:") == 0"]);
        System.out.println(a[3:".compareTo("]b[3:") != 0"]);

        System.out.println(a[4:".compareTo("]b[4:") > 0"]);
        System.out.println(a[5:".compareTo("]b[5:") == 1"]);
        System.out.println(a[6:".compareTo("]b[6:") > -1"]);
        System.out.println(a[7:".compareTo("]b[7:") >= 0"]); // Should be a >= b

        System.out.println(a[8:".compareTo("]b[8:") < 0"]);
        System.out.println(a[9:".compareTo("]b[9:") == -1"]);
        System.out.println(a[10:".compareTo("]b[10:") < 1"]);
        System.out.println(a[11:".compareTo("]b[11:") <= 0"]); // Should be a <= b
    }

    @Override
    public int compareTo(EqualsCompareTestData o) {
        return 0;
    }
}
