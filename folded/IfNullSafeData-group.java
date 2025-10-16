package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        var threeChains = [0:"data != null\n                && data.getData1() != null"]
                && [1:"data != null\n                && data.getData1() != null"]
                && data != null
                && [2:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"];

        var notChain = data != null && !data.[4:"getData1()"].[3:"isActive()"];
        var chain = [5:"data != null && data.getData1() != null && data.getData1().getData4() != null"];

        if ([26:"data != null && data.getData1() != null &&\n                data.getData1().getData2() != null && data.getData1().\n                getData2()\n                .getData3() != null"]) {
            System.out.println("data?.data1?.data2?.data3 != null");
        }
        if ([33:"data != null && data.getData1() != null"]) {
            System.out.println("data?.data1 != null");
        }
        if ([35:"data != null && data.isActive()"]) {
            System.out.println("data?.active == true");
        }
        if ([37:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]
                && data != null
                && [38:"data != null\n                && data.getData1() != null\n                && !data.getData1().isActive()"]
        ) {
            System.out.println("2chainz");
        }
        boolean has = [6:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"];
        var active = [7:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"];
        var inactive = [8:"data != null && !data.isActive()"];
        while ([9:"data != null && data.getData2() != null && !data.getData2().isActive()"]) {
            active = !data.[11:"getData1()"].[10:"isActive()"];
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if (([75:"data != null && data.getData6() != null &&\n                data.getData6().isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if (([79:"data != null && data.getData6() != null &&\n                !data.getData6().isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if ((flag
                || [83:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])

                && ([87:"data != null\n                && data.getData2() != null\n                && data.getData2().isActive()"] ||

                [91:"data != null\n                        && data.getData3() != null\n                        && data.getData3().isActive()"]
                        && [92:"data.getData3().getData4() != null\n                        && data.getData3().getData4().isActive()"]) ||

                ([101:"data != null\n                        && data.getData5() != null\n                        && data.getData5().isActive()"]) &&
                        (flag && flag || flag &&

                                [105:"data != null &&\n                                data.getData6() != null &&\n                                data.getData6().isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void notFullRoll(Data data) {
        Data data2 = data;
        if ([109:"data.getData1().getData2().getData3() != null &&\n                data.getData1().getData2().getData3().isActive()"]) {

        }

        if ([117:"data2.getData1().getData2() != null &&\n                data2.getData1().getData2().isActive()"]) {

        }


        if ([123:"data2.getData1() != null &&\n                data2.getData1().isActive()"]) {

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
