package data;

public class EqualsCompareTestData implements Comparable<EqualsCompareTestData> {
    public static void main(String[] args) {
        EqualsCompareTestData a = new EqualsCompareTestData();
        EqualsCompareTestData b = new EqualsCompareTestData();
        System.out.println(a[0:".equals("]{[0:".equals("]}b[0:")"]{[0:")"]});
        System.out.println([0:"!"]{[0:"!"]}a[0:".equals("]{[0:".equals("]}b[0:")"]{[0:")"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") == 0"]{[0:") == 0"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") != 0"]{[0:") != 0"]});

        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") > 0"]{[0:") > 0"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") == 1"]{[0:") == 1"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") > -1"]{[0:") > -1"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") >= 0"]{[0:") >= 0"]}); // Should be a >= b

        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") < 0"]{[0:") < 0"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") == -1"]{[0:") == -1"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") < 1"]{[0:") < 1"]});
        System.out.println(a[0:".compareTo("]{[0:".compareTo("]}b[0:") <= 0"]{[0:") <= 0"]}); // Should be a <= b
    }

    @Override
    public int compareTo(EqualsCompareTestData o) {
        return 0;
    }
}
