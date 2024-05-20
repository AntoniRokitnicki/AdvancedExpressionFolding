package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        var threeChains = data?.data1 != null
                && data?.data1 != null
                && data != null
                && data?.data1?.active == true;

        var notChain = data != null && !data.data1.active;
        var chain = data?.data1?.data4 != null;

        if (data?.data1?.data2?.data3 != null) {
            System.out.println("data?.data1?.data2?.data3 != null");
        }
        if (data?.data1 != null) {
            System.out.println("data?.data1 != null");
        }
        if (data?.active == true) {
            System.out.println("data?.active == true");
        }
        if (data?.data1?.data2?.data3?.data4 != null
                && data != null
                && data?.data1?.active == false
        ) {
            System.out.println("2chainz");
        }
        boolean has = data?.data1?.data2?.data3?.data4 != null;
        var active = data?.data1?.active == true;
        var inactive = data?.active == false;
        while (data?.data2?.active == false) {
            active = !data.data1.active;
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if ((data?.data6?.active == true)) {
            System.out.println("Conditions met!");
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if ((data?.data6?.active == false)) {
            System.out.println("Conditions met!");
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if ((flag
                || data?.data1?.active == true)

                && (data?.data2?.active == true ||

                data?.data3?.active == true
                        && data.data3.data4?.active == true) ||

                (data?.data5?.active == true) &&
                        (flag && flag || flag &&

                                data?.data6?.active == true)) {
            System.out.println("Conditions met!");
        }
    }

    public void notFullRoll(Data data) {
        Data data2 = data;
        if (data.data1.data2.data3?.active == true) {

        }

        if (data2.data1.data2?.active == true) {

        }


        if (data2.data1?.active == true) {

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
