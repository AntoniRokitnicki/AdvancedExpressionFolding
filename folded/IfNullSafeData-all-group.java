package data;

@SuppressWarnings("ALL")
public class IfNullSafeData {
    public void enter(Data data) {
        [0:"var"][20:"var"] threeChains = [1:"data != null\n                && data.getData1() != null"][21:"data != null\n                && data.getData1() != null"]!= null
                && [2:"data != null\n                && data.getData1() != null"][22:"data != null\n                && data.getData1() != null"]!= null
                && data != null
                && [3:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"][23:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])"].[27:"isActive()"];

        [4:"var"][29:"var"] notChain = data != null && !data.[6:"getData1()"][31:"getData1()"].[5:"isActive()"][30:"isActive()"];
        [7:"var"][32:"var"] chain = [8:"data != null && data.getData1() != null && data.getData1().getData4() != null"][33:"data != null && data.getData1() != null && data.getData1().getData4() != null"]"getData4()"] != null;

        if [9:"("][37:"("][38:"data != null && data.getData1() != null &&\n                data.getData1().getData2() != null && data.getData1().\n                getData2()\n                .getData3() != null"]                .[42:"getData3()"] != null[9:")"][37:")"] {
            [45:"System.out."]println([46:"\"data?.data1?.data2?.data3 != null\""]);
        }
        if [10:"("][47:"("][48:"data != null && data.getData1() != null"]!= null[10:")"][47:")"] {
            [50:"System.out."]println([51:"\"data?.data1 != null\""]);
        }
        if [11:"("][52:"("][53:"data != null && data.isActive()"]ive()"][11:")"][52:")"] {
            [55:"System.out."]println([56:"\"data?.active == true\""]);
        }
        if [12:"("][57:"("][58:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]ata1()"].[68:"getData2()"].[67:"getData3()"].[66:"getData4()"] != null
                && data != null
                && [59:"data != null\n                && data.getData1() != null\n                && !data.getData1().isActive()"])"].[71:"isActive()"]
        [12:")"][57:")"] {
            [73:"System.out."]println([74:"\"2chainz\""]);
        }
        [13:"boolean"][75:"boolean"] has = [14:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"][76:"data != null\n                && data.getData1() != null\n                && data.getData1().getData2() != null\n                && data.getData1().getData2().getData3() != null\n                && data.getData1().getData2().getData3().getData4() != null"]ata1()"].[85:"getData2()"].[84:"getData3()"].[83:"getData4()"] != null;
        [15:"var"][87:"var"] active = [16:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"][88:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"])"].[90:"isActive()"];
        [17:"var"][92:"var"] inactive = [18:"data != null && !data.isActive()"][93:"data != null && !data.isActive()"]ive()"];
        while [19:"("][95:"("][96:"data != null && data.getData2() != null && !data.getData2().isActive()"])"].[98:"isActive()"][19:")"][95:")"] {
            active = !data.[101:"getData1()"].[100:"isActive()"];
        }
    }

    public void equalsTrue(Data data, boolean flag) {
        if [102:"("][103:"("]([104:"data != null && data.getData6() != null &&\n                data.getData6().isActive()"])[102:")"][103:")"] {
            [105:"System.out."]println([106:"\"Conditions met!\""]);
        }
    }

    public void equalsFalse(Data data, boolean flag) {
        if [107:"("][108:"("]([109:"data != null && data.getData6() != null &&\n                !data.getData6().isActive()"])[107:")"][108:")"] {
            [110:"System.out."]println([111:"\"Conditions met!\""]);
        }
    }

    public void checkConditions(Data data, boolean flag) {
        if [112:"("][113:"("](flag
                || [114:"data != null\n                && data.getData1() != null\n                && data.getData1().isActive()"]1()"].[116:"isActive()"])

                && ([118:"data != null\n                && data.getData2() != null\n                && data.getData2().isActive()"]2()"].[120:"isActive()"] ||

                [122:"data != null\n                        && data.getData3() != null\n                        && data.getData3().isActive()"]3()"].[125:"isActive()"]
                        && [123:"data.getData3().getData4() != null\n                        && data.getData3().getData4().isActive()"]"].[130:"getData4()"].[129:"isActive()"]) ||

                ([132:"data != null\n                        && data.getData5() != null\n                        && data.getData5().isActive()"]) &&
                        (flag && flag || flag &&

                                [133:"data != null &&\n                                data.getData6() != null &&\n                                data.getData6().isActive()"]6()"].[135:"isActive()"])[112:")"][113:")"] {
            [137:"System.out."]println([138:"\"Conditions met!\""]);
        }
    }

    public void notFullRoll(Data data) {
        [139:"Data"][143:"Data"] data2 = data;
        if [140:"("][144:"("][145:"data.getData1().getData2().getData3() != null &&\n                data.getData1().getData2().getData3().isActive()"][151:"getData2()"].[150:"getData3()"].[149:"isActive()"][140:")"][144:")"] {

        }

        if [141:"("][153:"("][154:"data2.getData1().getData2() != null &&\n                data2.getData1().getData2().isActive()"]"].[158:"getData2()"].[157:"isActive()"][141:")"][153:")"] {

        }


        if [142:"("][160:"("][161:"data2.getData1() != null &&\n                data2.getData1().isActive()"]1()"].[163:"isActive()"][142:")"][160:")"] {

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
