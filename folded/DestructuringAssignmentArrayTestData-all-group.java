package data;

import java.util.List;

@SuppressWarnings("ALL")
public class DestructuringAssignmentArrayTestData {
    public void enter(Data data, Data[] array) {
        [0:"Data"] ignored1 = array[0];

        [23:"Data"] first[1:" = array[0];"][1:"\n\n        Data "]second[1:" = array[1];"][1:"\n        Data "]third[1:" = array[2];"][1:"\n        Data "]fourth[1:" "]= array[1:"[3]"];

        [5:"Data"] ignored21 = data.[6:"getArray()"][4];
        [7:"Data"] ignored22 = data.[8:"getArray()"][5];

        [32:"Data"] getter1[8:" = data.getArray()[0];"][8:"\n        Data "]getter2[8:" = data.getArray()[1];"][8:"\n        Data "]getter3[8:" "]= data.[12:"getArray()"][8:"[2]"];
        getter1 = data;

        [39:"Data"] deepGetter1[12:" = data.getData().getArray()[0];"][12:"\n        Data "]deepGetter2[12:" "]= data.[15:"getData()"].[14:"getArray()"][12:"[1]"];
        deepGetter2 = data;
        
        [16:"Data"] wrongParent1 = data.[17:"getArray()"][0];
        [18:"Data"] wrongParent2 = data.[20:"getData()"].[19:"getArray()"][1];

        blackhole(first, second, third, fourth, ignored21, ignored22, getter1, getter2, deepGetter1, deepGetter2, wrongParent1, wrongParent2);
    }

    static class Data {
        [50:"public"][50:" "][50:"Data"][50:" "]getData[50:"()"] {[50:"\n            "][50:"return"][50:" "]null[50:";"][50:"\n        "]}

        [51:"public"][51:" "][51:"Data[]"][51:" "]getArray[51:"()"] {[51:"\n            "][51:"return"][51:" "]null[51:";"][51:"\n        "]}

        [52:"public"][52:" "][52:"List<Data>"][52:" "]getList[52:"()"] {[52:"\n            "][52:"return"][52:" "]null[52:";"][52:"\n        "]}
    }

    void blackhole(Data... datas) {}

}
