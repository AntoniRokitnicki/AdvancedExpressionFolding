public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) {
        EqualsCompareTestData a = new EqualsCompareTestData();
        EqualsCompareTestData b = new EqualsCompareTestData();
        System.out.println(a.equals(b));
        System.out.println(!a.equals(b));
        System.out.println(a.compareTo(b) == 0);
        System.out.println(a.compareTo(b) != 0);

        System.out.println(a.compareTo(b) > 0);
        System.out.println(a.compareTo(b) == 1);
        System.out.println(a.compareTo(b) > -1);
        System.out.println(a.compareTo(b) >= 0); // Should be a >= b

        System.out.println(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b) == -1);
        System.out.println(a.compareTo(b) < 1);
        System.out.println(a.compareTo(b) <= 0); // Should be a <= b
    }

    @Override
    public int compareTo(EqualsCompareTestData o) {
        return 0;
    }
}
