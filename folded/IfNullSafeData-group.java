package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        var threeChains = [0:"data != null\n                && data.getData1() != null"][12:"data != null\n                && data.getData1() != null"]!= null
                && [1:"data != null\n                && data.getData1() != null"][13:"data != null\n                && data.getData1() != null"]!= null
                && data != null
                && [2:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"][14:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])"].[18:"isActive()"];

        var notChain = data != null && !data.[4:"getData1()"][21:"getData1()"].[3:"isActive()"][20:"isActive()"];
        var chain = [5:"data != null && data.getData1() != null && data.getData1().getData4() != null"][22:"data != null && data.getData1() != null && data.getData1().getData4() != null"]"getData4()"] != null;

        if ([26:"data != null && data.getData1() != null &&\n                data.getData1().getData2() != null && data.getData1().\n                getData2()\n                .getData3() != null"]                .[30:"getData3()"] != null) {
            System.out.println("data?.data1?.data2?.data3 != null");
        }
        if ([33:"data != null && data.getData1() != null"]!= null) {
            System.out.println("data?.data1 != null");
        }
        if ([35:"data != null && data.isActive()"]ive()"]) {
            System.out.println("data?.active == true");
        }
        if ([37:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]ata1()"].[47:"getData2()"].[46:"getData3()"].[45:"getData4()"] != null
                && data != null
                && [38:"data != null\n                && data.getData1() != null\n                && !data.getData1().isActive()"])"].[50:"isActive()"]
        ) {
            System.out.println("2chainz");
        }
        boolean has = [6:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"][52:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]ata1()"].[61:"getData2()"].[60:"getData3()"].[59:"getData4()"] != null;
        var active = [7:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"][63:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])"].[65:"isActive()"];
        var inactive = [8:"data != null && !data.isActive()"][67:"data != null && !data.isActive()"]ive()"];
        while ([9:"data != null && data.getData2() != null && !data.getData2().isActive()"][69:"data != null && data.getData2() != null && !data.getData2().isActive()"])"].[71:"isActive()"]) {
            active = !data.[11:"getData1()"][74:"getData1()"].[10:"isActive()"][73:"isActive()"];
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if (([75:"data != null && data.getData6() != null &&\n                data.getData6().isActive()"])"].[77:"isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if (([79:"data != null && data.getData6() != null &&\n                !data.getData6().isActive()"])"].[81:"isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if ((flag
                || [83:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])"].[85:"isActive()"])

                && ([87:"data != null\n                && data.getData2() != null\n                && data.getData2().isActive()"])"].[89:"isActive()"] ||

                [91:"data != null\n                        && data.getData3() != null\n                        && data.getData3().isActive()"])"].[94:"isActive()"]
                        && [92:"data.getData3().getData4() != null\n                        && data.getData3().getData4().isActive()"].[99:"getData4()"].[98:"isActive()"]) ||

                ([101:"data != null\n                        && data.getData5() != null\n                        && data.getData5().isActive()"]5()"].[103:"isActive()"]) &&
                        (flag && flag || flag &&

                                [105:"data != null &&\n                                data.getData6() != null &&\n                                data.getData6().isActive()"]6()"].[107:"isActive()"])) {
            System.out.println("Conditions met!");
        }
    }

    public void notFullRoll(Data data) {
        Data data2 = data;
        if ([109:"data.getData1().getData2().getData3() != null &&\n                data.getData1().getData2().getData3().isActive()"][115:"getData2()"].[114:"getData3()"].[113:"isActive()"]) {

        }

        if ([117:"data2.getData1().getData2() != null &&\n                data2.getData1().getData2().isActive()"]"].[121:"getData2()"].[120:"isActive()"]) {

        }


        if ([123:"data2.getData1() != null &&\n                data2.getData1().isActive()"]1()"].[125:"isActive()"]) {

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
