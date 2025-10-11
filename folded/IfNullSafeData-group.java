package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        var threeChains = [0:"data != null
                && data.getData1() != null"]{[0:"data != null
                && data.getData1() != null"]{[0:"getData1()"]}}
                && [0:"data != null
                && data.getData1() != null"]{[0:"data != null
                && data.getData1() != null"]{[0:"getData1()"]}}
                && data != null
                && [0:"data != null
                && data.getData1() != null
                && data.getData1().isActive()"]{[0:"data != null
                && data.getData1() != null
                && data.getData1().isActive()"]{[0:"getData1()"] [0:"getData1()"] [0:"isActive()"]}};

        var notChain = data != null && !data.[0:"getData1()"]{[0:"getData1()"]}.[0:"isActive()"]{[0:"isActive()"]};
        var chain = [0:"data != null && data.getData1() != null && data.getData1().getData4() != null"]{[0:"data != null && data.getData1() != null && data.getData1().getData4() != null"]{[0:"getData1()"] [0:"getData1()"] [0:"getData4()"]}};

        if ([0:"data != null && data.getData1() != null &&
                data.getData1().getData2() != null && data.getData1().
                getData2()
                .getData3() != null"]{[0:"getData1()"] [0:"getData1()"] [0:"getData2()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"]}) {
            System.out.println("data?.data1?.data2?.data3 != null");
        }
        if ([0:"data != null && data.getData1() != null"]{[0:"getData1()"]}) {
            System.out.println("data?.data1 != null");
        }
        if ([0:"data != null && data.isActive()"]{[0:"isActive()"]}) {
            System.out.println("data?.active == true");
        }
        if ([0:"data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null"]{[0:"getData1()"] [0:"getData1()"] [0:"getData2()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"getData4()"]}
                && data != null
                && [0:"data != null
                && data.getData1() != null
                && !data.getData1().isActive()"]{[0:"getData1()"] [0:"getData1()"] [0:"isActive()"]}
        ) {
            System.out.println("2chainz");
        }
        boolean has = [0:"data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null"]{[0:"data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null"]{[0:"getData1()"] [0:"getData1()"] [0:"getData2()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"getData4()"]}};
        var active = [0:"data != null
                && data.getData1() != null
                && data.getData1().isActive()"]{[0:"data != null
                && data.getData1() != null
                && data.getData1().isActive()"]{[0:"getData1()"] [0:"getData1()"] [0:"isActive()"]}};
        var inactive = [0:"data != null && !data.isActive()"]{[0:"data != null && !data.isActive()"]{[0:"isActive()"]}};
        while ([0:"data != null && data.getData2() != null && !data.getData2().isActive()"]{[0:"data != null && data.getData2() != null && !data.getData2().isActive()"]{[0:"getData2()"] [0:"getData2()"] [0:"isActive()"]}}) {
            active = !data.[0:"getData1()"]{[0:"getData1()"]}.[0:"isActive()"]{[0:"isActive()"]};
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if (([0:"data != null && data.getData6() != null &&
                data.getData6().isActive()"]{[0:"getData6()"] [0:"getData6()"] [0:"isActive()"]})) {
            System.out.println("Conditions met!");
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if (([0:"data != null && data.getData6() != null &&
                !data.getData6().isActive()"]{[0:"getData6()"] [0:"getData6()"] [0:"isActive()"]})) {
            System.out.println("Conditions met!");
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if ((flag
                || [0:"data != null
                && data.getData1() != null
                && data.getData1().isActive()"]{[0:"getData1()"] [0:"getData1()"] [0:"isActive()"]})

                && ([0:"data != null
                && data.getData2() != null
                && data.getData2().isActive()"]{[0:"getData2()"] [0:"getData2()"] [0:"isActive()"]} ||

                [0:"data != null
                        && data.getData3() != null
                        && data.getData3().isActive()"]{[0:"getData3()"] [0:"getData3()"] [0:"isActive()"]}
                        && [0:"data.getData3().getData4() != null
                        && data.getData3().getData4().isActive()"]{[0:"getData3()"] [0:"getData4()"] [0:"getData3()"] [0:"getData4()"] [0:"isActive()"]}) ||

                ([0:"data != null
                        && data.getData5() != null
                        && data.getData5().isActive()"]{[0:"getData5()"] [0:"getData5()"] [0:"isActive()"]}) &&
                        (flag && flag || flag &&

                                [0:"data != null &&
                                data.getData6() != null &&
                                data.getData6().isActive()"]{[0:"getData6()"] [0:"getData6()"] [0:"isActive()"]})) {
            System.out.println("Conditions met!");
        }
    }

    public void notFullRoll(Data data) {
        Data data2 = data;
        if ([0:"data.getData1().getData2().getData3() != null &&
                data.getData1().getData2().getData3().isActive()"]{[0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"getData1()"] [0:"getData2()"] [0:"getData3()"] [0:"isActive()"]}) {

        }

        if ([0:"data2.getData1().getData2() != null &&
                data2.getData1().getData2().isActive()"]{[0:"getData1()"] [0:"getData2()"] [0:"getData1()"] [0:"getData2()"] [0:"isActive()"]}) {

        }


        if ([0:"data2.getData1() != null &&
                data2.getData1().isActive()"]{[0:"getData1()"] [0:"getData1()"] [0:"isActive()"]}) {

        }
    }

    static class Data {
        public Data getData1() {
            return null;
        }

        public Data getData2() {
            return null;
        }

        public Data getData3() {
            return null;
        }

        public Data getData4() {
            return null;
        }

        public Data getData5() {
            return null;
        }

        public Data getData6() {
            return null;
        }

        public boolean isActive() {
            return true;
        }
    }
}
