package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        [0:"var"] threeChains = [1:"data != null\n                && data.getData1() != null"]
                && [2:"data != null\n                && data.getData1() != null"]
                && data != null
                && [3:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"];

        [4:"var"] notChain = data != null && !data.[6:"getData1()"].[5:"isActive()"];
        [7:"var"] chain = [8:"data != null && data.getData1() != null && data.getData1().getData4() != null"];

        if [9:"("][38:"data != null && data.getData1() != null &&\n                data.getData1().getData2() != null && data.getData1().\n                getData2()\n                .getData3() != null"][9:")"] {
            [45:"System.out."]println([46:"\"data?.data1?.data2?.data3 != null\""]);
        }
        if [10:"("][48:"data != null && data.getData1() != null"][10:")"] {
            [50:"System.out."]println([51:"\"data?.data1 != null\""]);
        }
        if [11:"("][53:"data != null && data.isActive()"][11:")"] {
            [55:"System.out."]println([56:"\"data?.active == true\""]);
        }
        if [12:"("][58:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]
                && data != null
                && [59:"data != null\n                && data.getData1() != null\n                && !data.getData1().isActive()"]
        [12:")"] {
            [73:"System.out."]println([74:"\"2chainz\""]);
        }
        [13:"boolean"] has = [14:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"];
        [15:"var"] active = [16:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"];
        [17:"var"] inactive = [18:"data != null && !data.isActive()"];
        while [19:"("][96:"data != null && data.getData2() != null && !data.getData2().isActive()"][19:")"] {
            active = !data.[101:"getData1()"].[100:"isActive()"];
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if [102:"("]([104:"data != null && data.getData6() != null &&\n                data.getData6().isActive()"])[102:")"] {
            [105:"System.out."]println([106:"\"Conditions met!\""]);
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if [107:"("]([109:"data != null && data.getData6() != null &&\n                !data.getData6().isActive()"])[107:")"] {
            [110:"System.out."]println([111:"\"Conditions met!\""]);
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if [112:"("](flag
                || [114:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])

                && ([118:"data != null\n                && data.getData2() != null\n                && data.getData2().isActive()"] ||

                [122:"data != null\n                        && data.getData3() != null\n                        && data.getData3().isActive()"]
                        && [123:"data.getData3().getData4() != null\n                        && data.getData3().getData4().isActive()"]) ||

                ([132:"data != null\n                        && data.getData5() != null\n                        && data.getData5().isActive()"]) &&
                        (flag && flag || flag &&

                                [133:"data != null &&\n                                data.getData6() != null &&\n                                data.getData6().isActive()"])[112:")"] {
            [137:"System.out."]println([138:"\"Conditions met!\""]);
        }
    }

    public void notFullRoll(Data data) {
        [139:"Data"] data2 = data;
        if [140:"("][145:"data.getData1().getData2().getData3() != null &&\n                data.getData1().getData2().getData3().isActive()"][140:")"] {

        }

        if [141:"("][154:"data2.getData1().getData2() != null &&\n                data2.getData1().getData2().isActive()"][141:")"] {

        }


        if [142:"("][161:"data2.getData1() != null &&\n                data2.getData1().isActive()"][142:")"] {

        }
    }

    static class Data {
        [165:"public"][165:" "][165:"Data"][165:" "]getData1[165:"()"] {[165:"\n            "][165:"return"][165:" "]null[165:";"][165:"\n        "]}

        [166:"public"][166:" "][166:"Data"][166:" "]getData2[166:"()"] {[166:"\n            "][166:"return"][166:" "]null[166:";"][166:"\n        "]}

        [167:"public"][167:" "][167:"Data"][167:" "]getData3[167:"()"] {[167:"\n            "][167:"return"][167:" "]null[167:";"][167:"\n        "]}

        [168:"public"][168:" "][168:"Data"][168:" "]getData4[168:"()"] {[168:"\n            "][168:"return"][168:" "]null[168:";"][168:"\n        "]}

        [169:"public"][169:" "][169:"Data"][169:" "]getData5[169:"()"] {[169:"\n            "][169:"return"][169:" "]null[169:";"][169:"\n        "]}

        [170:"public"][170:" "][170:"Data"][170:" "]getData6[170:"()"] {[170:"\n            "][170:"return"][170:" "]null[170:";"][170:"\n        "]}

        [171:"public"][171:" "][171:"boolean"][171:" "]isActive[171:"()"] {[171:"\n            "][171:"return"][171:" "]true[171:";"][171:"\n        "]}
    }
}
