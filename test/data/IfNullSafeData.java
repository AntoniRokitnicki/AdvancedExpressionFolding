package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        var threeChains = data != null
                && data.getData1() != null
                && data != null
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();

        var notChain = data != null && !data.getData1().isActive();
        var chain = data != null && data.getData1() != null && data.getData1().getData4() != null;

        if (data != null && data.getData1() != null &&
                data.getData1().getData2() != null && data.getData1().
                getData2()
                .getData3() != null) {
            System.out.println("data?.data1?.data2?.data3 != null");
        }
        if (data != null && data.getData1() != null) {
            System.out.println("data?.data1 != null");
        }
        if (data != null && data.isActive()) {
            System.out.println("data?.active == true");
        }
        if (data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null
                && data != null
                && data != null
                && data.getData1() != null
                && !data.getData1().isActive()
        ) {
            System.out.println("2chainz");
        }
        boolean has = data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null;
        var active = data != null
                && data.getData1() != null
                && data.getData1().isActive();
        var inactive = data != null && !data.isActive();
        while (data != null && data.getData2() != null && !data.getData2().isActive()) {
            active = !data.getData1().isActive();
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if ((data != null && data.getData6() != null &&
                data.getData6().isActive())) {
            System.out.println("Conditions met!");
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if ((data != null && data.getData6() != null &&
                !data.getData6().isActive())) {
            System.out.println("Conditions met!");
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if ((flag
                || data != null
                && data.getData1() != null
                && data.getData1().isActive())

                && (data != null
                && data.getData2() != null
                && data.getData2().isActive() ||

                data != null
                        && data.getData3() != null
                        && data.getData3().isActive()
                        && data.getData3().getData4() != null
                        && data.getData3().getData4().isActive()) ||

                (data != null
                        && data.getData5() != null
                        && data.getData5().isActive()) &&
                        (flag && flag || flag &&

                                data != null &&
                                data.getData6() != null &&
                                data.getData6().isActive())) {
            System.out.println("Conditions met!");
        }
    }

    public void notFullRoll(Data data) {
        Data data2 = data;
        if (data.getData1().getData2().getData3() != null &&
                data.getData1().getData2().getData3().isActive()) {

        }

        if (data2.getData1().getData2() != null &&
                data2.getData1().getData2().isActive()) {

        }


        if (data2.getData1() != null &&
                data2.getData1().isActive()) {

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
