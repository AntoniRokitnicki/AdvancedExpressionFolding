package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentListTestData {
    public void enter(Data data, List<Data> list) {
        [0:"Data"] ignored1 = list[1:".get"]([1:"0"]);

        [35:"Data"] first[2:" = list.get(0);"][2:"\n        Data "]second[2:" = list.get(1);"][2:"\n        Data "]third[2:" = list.get(2);"][2:"\n        Data "]fourth[2:" "]= list[8:".get("]3[8:")"];

        [9:"Data"] ignored21 = data.[11:"getList()"][10:".get("]4[10:")"];
        [12:"Data"] ignored22 = data.[14:"getList()"][13:".get("]5[13:")"];

        [50:"Data"] getter1[14:" = data.getList().get(0);"][14:"\n        Data "]getter2[14:" = data.getList().get(1);"][14:"\n        Data "]getter3[14:" "]= data.[20:"getList()"][19:".get("]2[19:")"];
        getter1 = data;

        [60:"Data"] deepGetter1[20:" = data.getData().getList().get(0);"][20:"\n        Data "]deepGetter2[20:" "]= data.[24:"getData()"].[23:"getList()"][22:".get("]1[22:")"];
        deepGetter2 = data;

        [25:"Data"] wrongParent1 = data.[27:"getList()"][26:".get"]([26:"0"]);
        [28:"Data"] wrongParent2 = data.[31:"getData()"].[30:"getList()"][29:".get("]1[29:")"];

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }

    static class Data {
        [75:"public"][75:" "][75:"Data"][75:" "]getData[75:"()"] {[75:"\n            "][75:"return"][75:" "]null[75:";"][75:"\n        "]}

        [76:"public"][76:" "][76:"Data[]"][76:" "]getArray[76:"()"] {[76:"\n            "][76:"return"][76:" "]null[76:";"][76:"\n        "]}

        [77:"public"][77:" "][77:"List<Data>"][77:" "]getList[77:"()"] {[77:"\n            "][77:"return"][77:" "]null[77:";"][77:"\n        "]}
    }

    void blackhole(Data... datas) {}

}
