package data;

public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) {
        val a = new EqualsCompareTestData();
        val b = new EqualsCompareTestData();
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);
        System.out.println(a ≡ b);
        System.out.println(a ≢ b);

        System.out.println(a > b);
        System.out.println(a > b);
        System.out.println(a ≥ b);
        System.out.println(a ≥ b); // Should be a >= b

        System.out.println(a < b);
        System.out.println(a < b);
        System.out.println(a ≤ b);
        System.out.println(a ≤ b); // Should be a <= b
    }

    @Override
    public int compareTo(EqualsCompareTestData o) {
        return 0;
    }
}
